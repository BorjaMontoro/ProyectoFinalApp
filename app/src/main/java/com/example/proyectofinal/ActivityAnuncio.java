package com.example.proyectofinal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;

public class ActivityAnuncio extends AppCompatActivity {
    ActivityResultLauncher<Intent> someActivityResultLauncher;
    String[] horasLunes = new String[4];
    String[] horasMartes = new String[4];
    String[] horasMiercoles = new String[4];
    String[] horasJueves = new String[4];
    String[] horasViernes = new String[4];
    String[] horasSabado = new String[4];
    String[] horasDomingo = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_anuncio);
        Button horario = findViewById(R.id.button4);
        horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            Uri uri = data.getData();
                        }
                    }
                });
    }
    public void openSomeActivityForResult(View view) {
        //Create Intent
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        //Launch activity to get result
        //ImageView img = findViewById(R.id.img);
        someActivityResultLauncher.launch(intent);
    }
    public void comprobarHoras(){

    }
}
