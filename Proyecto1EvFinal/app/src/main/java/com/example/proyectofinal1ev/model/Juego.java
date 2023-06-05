package com.example.proyectofinal1ev.model;

public class Juego {

    private int id;
    private String nombre;
    private String url;
    private float Valoracion;
    private String Categoria;
    private String Desarrollador;
    private int anyo;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getValoracion() {
        return Valoracion;
    }

    public void setValoracion(float valoracion) {
        Valoracion = valoracion;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getDesarrollador() {
        return Desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        Desarrollador = desarrollador;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
