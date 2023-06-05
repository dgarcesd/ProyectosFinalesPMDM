package com.example.proyectofinal1ev.model;

public class Juego {

//    private int id;
//    private String nombre;
//    private String url;
//    private float Valoracion;
//    private String Categoria;
//    private String Desarrollador;
//    private int anyo;
//
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public float getValoracion() {
//        return Valoracion;
//    }
//
//    public void setValoracion(float valoracion) {
//        Valoracion = valoracion;
//    }
//
//    public String getCategoria() {
//        return Categoria;
//    }
//
//    public void setCategoria(String categoria) {
//        Categoria = categoria;
//    }
//
//    public String getDesarrollador() {
//        return Desarrollador;
//    }
//
//    public void setDesarrollador(String desarrollador) {
//        Desarrollador = desarrollador;
//    }
//
//    public int getAnyo() {
//        return anyo;
//    }
//
//    public void setAnyo(int anyo) {
//        this.anyo = anyo;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    private String id;
    private String Nombre;
    private String Imagen;
    private float Valoracion;
    private String Categoria;
    private String Desarrollador;
    private int Anyo;

    public Juego() {}

    public Juego(String id, String nombre, String imagen, float valoracion, String categoria, String desarrollador, int anyo) {
        this.id = id;
        this.Nombre = nombre;
        this.Imagen = imagen;
        this.Valoracion = valoracion;
        this.Categoria = categoria;
        this.Desarrollador = desarrollador;
        this.Anyo = anyo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        this.Imagen = imagen;
    }

    public float getValoracion() {
        return Valoracion;
    }

    public void setValoracion(float valoracion) {
        this.Valoracion = valoracion;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        this.Categoria = categoria;
    }

    public String getDesarrollador() {
        return Desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.Desarrollador = desarrollador;
    }

    public int getAnyo() {
        return Anyo;
    }

    public void setAnyo(int anyo) {
        this.Anyo = anyo;
    }

//    package com.example.proyectofinal1ev.model;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//    public class Juego implements Parcelable {
//        private String id;
//        private String Nombre;
//        private String Imagen;
//        private float Valoracion;
//        private String Categoria;
//        private String Desarrollador;
//        private int Anyo;
//
//        public Juego() {
//        }
//
//        public Juego(String id, String nombre, String imagen, float valoracion, String categoria, String desarrollador, int anyo) {
//            this.id = id;
//            this.Nombre = nombre;
//            this.Imagen = imagen;
//            this.Valoracion = valoracion;
//            this.Categoria = categoria;
//            this.Desarrollador = desarrollador;
//            this.Anyo = anyo;
//        }
//
//        protected Juego(Parcel in) {
//            id = in.readString();
//            Nombre = in.readString();
//            Imagen = in.readString();
//            Valoracion = in.readFloat();
//            Categoria = in.readString();
//            Desarrollador = in.readString();
//            Anyo = in.readInt();
//        }
//
//        public static final Creator<Juego> CREATOR = new Creator<Juego>() {
//            @Override
//            public Juego createFromParcel(Parcel in) {
//                return new Juego(in);
//            }
//
//            @Override
//            public Juego[] newArray(int size) {
//                return new Juego[size];
//            }
//        };
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getNombre() {
//            return Nombre;
//        }
//
//        public void setNombre(String nombre) {
//            this.Nombre = nombre;
//        }
//
//        public String getImagen() {
//            return Imagen;
//        }
//
//        public void setImagen(String imagen) {
//            this.Imagen = imagen;
//        }
//
//        public float getValoracion() {
//            return Valoracion;
//        }
//
//        public void setValoracion(float valoracion) {
//            this.Valoracion = valoracion;
//        }
//
//        public String getCategoria() {
//            return Categoria;
//        }
//
//        public void setCategoria(String categoria) {
//            this.Categoria = categoria;
//        }
//
//        public String getDesarrollador() {
//            return Desarrollador;
//        }
//
//        public void setDesarrollador(String desarrollador) {
//            this.Desarrollador = desarrollador;
//        }
//
//        public int getAnyo() {
//            return Anyo;
//        }
//
//        public void setAnyo(int anyo) {
//            this.Anyo = anyo;
//        }
//
//        @Override
//        public int describeContents() {
//            return 0;
//        }
//
//        @Override
//        public void writeToParcel(Parcel dest, int flags) {
//            dest.writeString(id);
//            dest.writeString(Nombre);
//            dest.writeString(Imagen);
//            dest.writeFloat(Valoracion);
//            dest.writeString(Categoria);
//            dest.writeString(Desarrollador);
//            dest.writeInt(Anyo);
//        }
//    }

}
