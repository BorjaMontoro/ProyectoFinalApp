package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReservarActivity extends AppCompatActivity {
    private Servicio servicio;
    private String empresa;
    private CalendarView calendario;
    private RecyclerView listaHorasDisponibles;
    private String selectedHora;
    private Calendar fechaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);

        servicio = (Servicio) getIntent().getSerializableExtra("servicio");
        empresa = getIntent().getStringExtra("empresa");
        TextView nombreServicioTextView = findViewById(R.id.nombre_servicio_text_view);
        TextView precioServicioTextView = findViewById(R.id.precio_servicio_text_view);
        TextView duracionServicioTextView = findViewById(R.id.duracion_servicio_text_view);

        nombreServicioTextView.setText(servicio.getNombre());
        precioServicioTextView.setText(servicio.getPrecio());
        duracionServicioTextView.setText(servicio.getDuracion());

        calendario = findViewById(R.id.calendario_view);
        calendario.setFirstDayOfWeek(Calendar.MONDAY);

        listaHorasDisponibles = findViewById(R.id.horas_disponibles_list_view);

        int espacio = getResources().getDimensionPixelSize(R.dimen.espacio_items);

        CustomItemDecoration itemDecoration = new CustomItemDecoration(espacio);

        listaHorasDisponibles.addItemDecoration(itemDecoration);

        List<String> horasDisponibles=new ArrayList<>();

        HorasAdapter adapter = new HorasAdapter(horasDisponibles,ReservarActivity.this);
        listaHorasDisponibles.setLayoutManager(new LinearLayoutManager(ReservarActivity.this, LinearLayoutManager.HORIZONTAL, false));
        listaHorasDisponibles.setAdapter(adapter);

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
                if(selectedHora.equals("Ninguna")){
                    dialog("ERROR","No hay horas disponibles para este dia");
                }else{
                    try {
                        JSONObject obj = new JSONObject("{}");
                        obj.put("name", empresa);
                        obj.put("nameService", servicio.getNombre());
                        obj.put("day", day);
                        obj.put("month", month);
                        obj.put("year", year);
                        obj.put("hour", selectedHora);
                        obj.put("idUsu",RegisterCompanyActivity.id);
                        UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/save_date", obj.toString(), (response) -> {
                            try {
                                JSONObject obj2 = new JSONObject(response);
                                if(obj2.getString("status").equals("OK")){
                                    dialog("RESERVADO","Se ha reservado la cita correctamente");
                                }

                            } catch (JSONException e) {
                                System.out.println();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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
        try {
            JSONObject obj = new JSONObject("{}");
            obj.put("name", empresa);
            obj.put("day", day);
            obj.put("month", month);
            obj.put("year", year);
            obj.put("duration", servicio.getDuracion());
            UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/get_dates", obj.toString(), (response) -> {
                try {
                    JSONObject obj2 = new JSONObject(response);
                    JSONArray jsonArray=obj2.getJSONArray("hours");
                    List<String> horasDisponibles=new ArrayList<>();

                    for (int i=0;i<jsonArray.length();i++){
                        horasDisponibles.add(jsonArray.getString(i));
                    }
                    if(jsonArray.getString(0).equals("No hay horas disponibles")) {
                        horasDisponibles.clear();
                        guardarHoraSeleccionada("Ninguna");
                    }else{
                        guardarHoraSeleccionada(horasDisponibles.get(0));
                    }

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            HorasAdapter adapter = new HorasAdapter(horasDisponibles,ReservarActivity.this);
                            listaHorasDisponibles.setAdapter(adapter);
                        }
                    });

                } catch (JSONException e) {
                    System.out.println();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void guardarHoraSeleccionada(String hora) {
        selectedHora = hora;
    }

    private void dialog(String status ,String mesage) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(status.equals("ERROR")) {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ReservarActivity.this);
                    alerta.setTitle("ERROR");
                    alerta.setMessage(mesage);
                    alerta.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alerta.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {
                            dialog.cancel();
                        }
                    });

                    alerta.show();
                }else if(status.equals("RESERVADO")){
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ReservarActivity.this);
                    alerta.setTitle("RESERVADA");
                    alerta.setMessage(mesage);
                    alerta.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            startActivity(new Intent(ReservarActivity.this,MainClientActivity.class));
                        }
                    });

                    alerta.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {
                            startActivity(new Intent(ReservarActivity.this,MainClientActivity.class));
                        }
                    });

                    alerta.show();
                }
            }
        });
    }

}