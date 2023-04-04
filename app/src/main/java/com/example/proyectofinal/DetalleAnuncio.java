package com.example.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleAnuncio extends AppCompatActivity {

    // ...

    private ImageView mAnuncioImageView;
    private TextView mNombreTextView;
    private TextView mDireccionTextView;
    private TextView mTipoTextView;
    private TextView mHorarioTextView;
    private TextView mServiciosTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_anuncio);

        /*mAnuncioImageView = findViewById(R.id.anuncio_image_view);
        mNombreTextView = findViewById(R.id.nombre_text_view);
        mDireccionTextView = findViewById(R.id.direccion_text_view);
        mTipoTextView = findViewById(R.id.tipo_text_view);
        mHorarioTextView = findViewById(R.id.horario_text_view);
        mServiciosTextView = findViewById(R.id.servicios_text_view);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("ANUNCIO")) {
            Anuncio anuncio = intent.getParcelableExtra("ANUNCIO");

            mAnuncioImageView.setImageResource(anuncio.getImageResource());
            mNombreTextView.setText(anuncio.getNombre());
            mDireccionTextView.setText(anuncio.getDireccion());
            mTipoTextView.setText(anuncio.getTipo());
            mHorarioTextView.setText(anuncio.getHorario());
            mServiciosTextView.setText(anuncio.getServicios());
        }*/
    }

    // ...

}

