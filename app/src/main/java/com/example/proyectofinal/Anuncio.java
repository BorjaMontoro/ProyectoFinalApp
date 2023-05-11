package com.example.proyectofinal;

import java.io.Serializable;

public class Anuncio implements Serializable {
    private String nombre,direccion,tipo,base64;
    Anuncio(String nombre, String direccion, String tipo,String base64){
        this.nombre=nombre;
        this.direccion=direccion;
        this.tipo=tipo;
        this.base64=base64;
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

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
