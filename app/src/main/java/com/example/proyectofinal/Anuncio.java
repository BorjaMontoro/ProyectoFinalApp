package com.example.proyectofinal;

public class Anuncio {
    private String nombre,direccion,tipo;
    Anuncio(String nombre, String direccion, String tipo){
        this.nombre=nombre;
        this.direccion=direccion;
        this.tipo=tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
