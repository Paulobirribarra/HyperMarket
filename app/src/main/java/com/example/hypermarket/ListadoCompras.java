package com.example.hypermarket;

public class ListadoCompras {
int id;
String Nombre;
String Categoria;
String Precio;
String Cantidad;
String Ubicacion;
String CodBarra;

    public ListadoCompras() {
    }

    public ListadoCompras(String nombre, String categoria, String precio, String cantidad, String ubicacion, String codBarra) {
        Nombre = nombre;
        Categoria = categoria;
        Precio = precio;
        Cantidad = cantidad;
        Ubicacion = ubicacion;
        CodBarra = codBarra;
    }

    public ListadoCompras(int id, String nombre, String categoria, String precio, String cantidad, String ubicacion, String codBarra) {
        this.id = id;
        Nombre = nombre;
        Categoria = categoria;
        Precio = precio;
        Cantidad = cantidad;
        Ubicacion = ubicacion;
        CodBarra = codBarra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public String getCodBarra() {
        return CodBarra;
    }

    public void setCodBarra(String codBarra) {
        CodBarra = codBarra;
    }
}
