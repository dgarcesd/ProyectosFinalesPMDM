package com.example.proyectofinal1ev.juegoAPI;

import com.example.proyectofinal1ev.model.JuegoRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JuegoapiService {
   // A - Modificamos el método para que sea parametrizable
    @GET("videojuegos/{Categoria}")
    Call<JuegoRespuesta> obtenerListaJuegos(@Path("Categoria") String categoria, @Query("limit") int limit, @Query("offset") int offset);
}
