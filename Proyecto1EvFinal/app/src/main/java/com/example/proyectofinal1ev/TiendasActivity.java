package com.example.proyectofinal1ev;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.proyectofinal1ev.databinding.ActivityTiendasBinding;
import com.example.proyectofinal1ev.model.Tienda;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

// Para poder mostrar un mapa necesitamos implementar a esta interfaz
public class TiendasActivity extends FragmentActivity implements OnMapReadyCallback {

    // Esto se declara aqui por si el mapa se quiere usar en algun metodo que no sea onMapReady
    private GoogleMap mMap;
    private ActivityTiendasBinding binding;

    // 1 - Creamos una lista de Restaurantes
    private List<Tienda> listaTiendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTiendasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // 3 - Llenamos la lista de restaurantes
        addFakeTiendas();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // 2 - Añadimos un metodo para crear restaurantes aleatorios
    private void addFakeTiendas(){
        listaTiendas = new ArrayList<>();
        listaTiendas.add(new Tienda("GAME", 40.343720,-1.106471));
        listaTiendas.add(new Tienda("GAME", 41.651014, -0.884098));
        listaTiendas.add(new Tienda("GAME", 41.669490, -0.889843));
        listaTiendas.add(new Tienda("GAME", 42.138446, -0.405654));
        listaTiendas.add(new Tienda("GAME", 41.608485, -0.887726));
    }



    // Este método ya trae el mapa y ya podemos trabajar con el
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Creamos un objeto de tipo LatLng
        LatLng zaragoza = new LatLng(41.648878, -0.888921);

        BitmapDescriptor tienda = BitmapDescriptorFactory.fromResource(R.drawable.ic_tienda);
        // 4 - Vamos a crear un marcador para cada restaurante
        for (Tienda r:listaTiendas){
            LatLng posicion = new LatLng(r.getLatitud(),r.getLongitud());
            String nombre = r.getNombre().toString();
            // 5 - Para añadir un icono personalizado a cada marker

            mMap.addMarker(new MarkerOptions()
                    .position(posicion)
                    .icon(bitmapDescriptorFromVector(this, R.drawable.ic_tienda))
                    .title(nombre));
        }

        // Para añadir el marcador necesitamos MarkerOptions
        // Le asigna un título, pero podemos asignarle más cosas
        mMap.addMarker(new MarkerOptions().position(zaragoza).title("ZARAGOZA"));
        // Le dice al mapa que mueva la camara
        // Si queremos que aparaezca mas cerca
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zaragoza,8.0f));
    }


    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}