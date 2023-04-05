package com.example.proyectofinal;

public class Servicio {
    private String nombre,precio,duracion;

    public Servicio(String nombre, String precio, String duracion) {
        this.nombre = nombre;
        this.precio = precio;
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}
