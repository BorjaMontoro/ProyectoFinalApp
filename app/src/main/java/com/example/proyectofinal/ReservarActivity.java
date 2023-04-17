package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReservarActivity extends AppCompatActivity {
    private Servicio servicio;
    private String empresa;
    private CalendarView calendario;
    private RecyclerView listaHorasDisponibles;
    private List<String> horasDisponibles;
    private String selectedHora;
    private Calendar fechaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);

        servicio = (Servicio) getIntent().getSerializableExtra("servicio");
        empresa = getIntent().getStringExtra("empresa");
        System.out.println(empresa);
        TextView nombreServicioTextView = findViewById(R.id.nombre_servicio_text_view);
        TextView precioServicioTextView = findViewById(R.id.precio_servicio_text_view);
        TextView duracionServicioTextView = findViewById(R.id.duracion_servicio_text_view);

        nombreServicioTextView.setText(servicio.getNombre());
        precioServicioTextView.setText(servicio.getPrecio());
        duracionServicioTextView.setText(servicio.getDuracion());

        calendario = findViewById(R.id.calendario_view);
        listaHorasDisponibles = findViewById(R.id.horas_disponibles_list_view);

        int espacio = getResources().getDimensionPixelSize(R.dimen.espacio_items);

        CustomItemDecoration itemDecoration = new CustomItemDecoration(espacio);

        listaHorasDisponibles.addItemDecoration(itemDecoration);

        fechaSeleccionada = Calendar.getInstance();
        calendario.setDate(fechaSeleccionada.getTimeInMillis());
        calendario.setMinDate(fechaSeleccionada.getTimeInMillis());
        int year = fechaSeleccionada.get(Calendar.YEAR);
        int month = fechaSeleccionada.get(Calendar.MONTH);
        int day = fechaSeleccionada.get(Calendar.DAY_OF_MONTH);
        consultarHorasDisponibles(day,month,year);

        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Actualizar la fecha seleccionada y hacer una nueva consulta de horas disponibles
                fechaSeleccionada.set(year, month, dayOfMonth);
                System.out.println(dayOfMonth+"---"+month+"---"+year);
                consultarHorasDisponibles(dayOfMonth,month,year);
            }
        });

        Button confirmarButton = findViewById(R.id.confirmar_button);
        confirmarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = fechaSeleccionada.get(Calendar.YEAR);
                int month = fechaSeleccionada.get(Calendar.MONTH);
                int day = fechaSeleccionada.get(Calendar.DAY_OF_MONTH);
                System.out.println(day+"---"+month+"---"+year);
                System.out.println(selectedHora);
                // aquí debes guardar la reserva en la base de datos o en algún otro lugar
                // y mostrar un mensaje de confirmación al usuario

                finish();
            }
        });

        ImageButton btnAtras = findViewById(R.id.btn_back);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void consultarHorasDisponibles(int day,int month,int year){
        horasDisponibles=new ArrayList<>();
        horasDisponibles.add("8:30");
        horasDisponibles.add("9:00");
        horasDisponibles.add("9:30");
        horasDisponibles.add("10:00");
        horasDisponibles.add("10:30");
        guardarHoraSeleccionada(horasDisponibles.get(0));
        HorasAdapter adapter = new HorasAdapter(horasDisponibles,this);
        listaHorasDisponibles.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        listaHorasDisponibles.setAdapter(adapter);

    }
    public void guardarHoraSeleccionada(String hora) {
        selectedHora = hora;
    }
}