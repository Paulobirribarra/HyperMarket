package com.example.hypermarket;
import com.google.android.gms.maps.model.LatLng;

public class Direcciones {

    private LatLng directorio;
    private String titulo;

    public Direcciones(LatLng directorio, String titulo) {
        this.directorio = directorio;
        this.titulo = titulo;
    }

    public LatLng getDirectorio() {
        return directorio;
    }

    public String getTitulo() {
        return titulo;
    }
}
