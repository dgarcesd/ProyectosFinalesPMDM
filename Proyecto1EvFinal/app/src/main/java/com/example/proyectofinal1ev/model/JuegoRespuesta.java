package com.example.proyectofinal1ev.model;

import java.util.ArrayList;
import java.util.List;

public class JuegoRespuesta {
    // 5 - Tenemos que poner los atributos de aquella informacion que nos interesa del JSON
    // en nuestro caso nos interesan los results
    private ArrayList<Juego> results;

    public ArrayList<Juego> getResults() {
        return results;
    }

    public void setResults(ArrayList<Juego> results) {
        this.results = results;
    }
//    private List<Juego> juegos;
//
//    public JuegoRespuesta() {
//        this.juegos = new ArrayList<>();
//    }
//
//    public JuegoRespuesta(List<Juego> juegos) {
//        this.juegos = juegos;
//    }
//
//    public List<Juego> getJuegos() {
//        return juegos;
//    }
//
//    public void setJuegos(List<Juego> juegos) {
//        this.juegos = juegos;
//    }
}
