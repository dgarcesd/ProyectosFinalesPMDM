package com.example.proyectofinal1ev;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal1ev.juegoAPI.JuegoapiService;
import com.example.proyectofinal1ev.model.Juego;
import com.example.proyectofinal1ev.model.JuegoRespuesta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarrerasActivity extends AppCompatActivity {

// nuevo comentario
    //sexto comentario
    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private ListaJuegosAdapter listaJuegosAdapter;
    // C 1
    private int offset;

// HOLA SOY SERGIO EN LA b304
    // E 2
    private boolean aptoParaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carreras);

        recyclerView = findViewById(R.id.recyclerView);
        listaJuegosAdapter = new ListaJuegosAdapter(this);
        recyclerView.setAdapter(listaJuegosAdapter);

        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);

        recyclerView.setLayoutManager(layoutManager);

        // D - Nuestro RecyclerView detecta el movimiento con este Scroll
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // D 2 - Hacemos una serie de preguntas para preguntar si el scroll es hacia abajo y llegó al ultimo item
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                //E 1 me creo aptoparaCargar para controlar las llamadas a la API
                if (aptoParaCargar){


                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount){
                        Log.i("videojuegos","Llegamos al Final");
                        aptoParaCargar = false;
                        offset += 20;
                        obtenerDatos(offset);
                    }
                }

            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://63cd65a1d4d47898e39822fe.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // E 3
        aptoParaCargar=true;
        // C 2
        offset=0;
        obtenerDatos(offset);
    }

    private void obtenerDatos(int offset) {

        JuegoapiService service = retrofit.create(JuegoapiService.class);

        // B - Colocamos los parametros, limit sera 20, pero offset tiene que cambiar
        Call<JuegoRespuesta> juegoRespuestaCall = service.obtenerListaJuegos (20, offset);

        juegoRespuestaCall.enqueue(new Callback<JuegoRespuesta>() {

            @Override
            public void onResponse(Call<JuegoRespuesta> call, Response<JuegoRespuesta> response) {
                // E 4
                aptoParaCargar = true;
                if (response.isSuccessful()){
                    JuegoRespuesta juegoRespuesta = response.body();


                    ArrayList<Juego> listaJuegos = juegoRespuesta.getResults();

                    //14 - Cuando recibimos los datos ya no los mostramos por consola
                    //se los mandamos al adaptador por medio de un metodo
                    listaJuegosAdapter.adicionarJuego((ArrayList<Juego>) listaJuegos);
                }else{
                    Log.i("videojuegos", "onResponse: "+response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<JuegoRespuesta> call, Throwable t) {
                // E 4
                aptoParaCargar = true;
                Log.i("videojuegos", "onFailure: "+t.getMessage());
            }
        });
    }
}