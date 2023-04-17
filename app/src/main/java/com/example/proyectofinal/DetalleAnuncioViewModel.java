package com.example.proyectofinal;

import androidx.lifecycle.ViewModel;

public class DetalleAnuncioViewModel extends ViewModel {
    private String mNombre;
    private String mDireccion;
    private String mTipo;

    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String nombre) {
        mNombre = nombre;
    }

    public String getDireccion() {
        return mDireccion;
    }

    public void setDireccion(String direccion) {
        mDireccion = direccion;
    }

    public String getTipo() {
        return mTipo;
    }

    public void setTipo(String tipo) {
        mTipo = tipo;
    }
}

