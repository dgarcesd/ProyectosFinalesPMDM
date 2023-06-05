package com.example.proyectofinal1ev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.messaging.FirebaseMessaging;

enum ProviderType {
    BASIC,
    GOOGLE
}
public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnRegistrar, btnLogin, btnLoginGoogle;
    LinearLayout authLayout;

    private FirebaseAnalytics mFirebaseAnalytics;
    // 1
    private FirebaseAuth mAuth;

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // Si el usuario está logueado (!= nulo) iremos a HomeActivity
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login Firebase");
        etEmail = findViewById(R.id.emailEditText);
        etPassword = findViewById(R.id.passwordEditText);
        btnRegistrar = findViewById(R.id.registerButton);
        btnLogin = findViewById(R.id.loginButton);
        btnLoginGoogle = findViewById(R.id.loginGoogleButton);
        // 2
        iniciarAnalytics();
        // Initialize Firebase Auth
        iniciarAuthentication();
        //Comprobamos si tenemosla sesion abierta
        comprobarSiEstaLogueado();
        // Push Notifications
        notificaciones();
        suscripcionaCoches();

    }

    private void suscripcionaCoches() {
        FirebaseMessaging.getInstance().subscribeToTopic("coches")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                        Log.d("PUSH", msg);
                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void notificaciones() {

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()){
                            Log.w("PUSH", "El registro FCM del token ha fallado");
                            return;
                        }

                        // Obtenemos el nuevo FCM registration token
                        String token = task.getResult();
                        Log.i("PUSH","El token de mi movil es: "+token);

                    }
                });
    }

    private void comprobarSiEstaLogueado() {
        SharedPreferences sesion = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        String _email = sesion.getString("email", null);
        String _metodo = sesion.getString("metodo", null);
        if(_email != null && _metodo != null){
            irAMainActivity(_email, ProviderType.valueOf(_metodo));
        }
    }

    private void iniciarAuthentication() {
        mAuth = FirebaseAuth.getInstance();
        btnRegistrar.setOnClickListener(v -> {
            registrarConEmailPassword();
        });
        btnLogin.setOnClickListener(v -> {
            loguearConEmailyPassword();
        });
        btnLoginGoogle.setOnClickListener(v -> {
            loguearConGoogle();
        });
    }

    private void loguearConGoogle() {
        // Al hacer click en el boton de login con Google:
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Nos creamos el GoogleSignIn Client
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100){
            // Esto signifoca que venimos de loguearnos con Google
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                // La autenticacion con Google ha sido exitosa
                Log.w("FIREBASE", "firebasAuthConGoogle: "+ account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e){
                // La autenticacion con Google ha fallado
                Log.w("FIREBASE","Google SignIn ha fallado",e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            // Voy a HomeActivity
                            irAMainActivity(user.getEmail(),ProviderType.GOOGLE);
                        } else {
                            Log.w("FIREBASE", "Logueo con Google: Fallo");
                        }
                    }
                });
    }


    private void loguearConEmailyPassword() {
        String _email = etEmail.getText().toString();
        String _password = etPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(_email, _password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FIREBASE", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // En caso de usuario y password correctos pasaremos a HomeActivity
                            irAMainActivity(_email, ProviderType.BASIC);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FIREBASE", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Error al Loguaerse el usuario",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void irAMainActivity(String email, ProviderType tipoLogueo) {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        i.putExtra("email",email);
        i.putExtra("metodo",tipoLogueo.toString());
        startActivity(i);
    }

    private void iniciarAnalytics() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString("DAM2","Aplicacion Iniciada");
        mFirebaseAnalytics.logEvent("DAM2", bundle);
    }

    // REGISTRARÁ USUARIOS EN FIREBASE CON EMAIL/PASSWORD
    private void registrarConEmailPassword() {
        String _email = etEmail.getText().toString();
        String _password = etPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(_email, _password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FIREBASE", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "El Usuario se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FIREBASE", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Error al Registrar el Usuario",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}