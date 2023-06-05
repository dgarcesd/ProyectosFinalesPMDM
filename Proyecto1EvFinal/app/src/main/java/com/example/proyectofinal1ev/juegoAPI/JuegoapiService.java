package com.example.proyectofinal1ev.juegoAPI;

import com.example.proyectofinal1ev.model.JuegoRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JuegoapiService {
   // A - Modificamos el método para que sea parametrizable
    @GET("videojuegos")
    Call<JuegoRespuesta> obtenerListaJuegos(@Query("limit") int limit, @Query("offset") int offset);
}
