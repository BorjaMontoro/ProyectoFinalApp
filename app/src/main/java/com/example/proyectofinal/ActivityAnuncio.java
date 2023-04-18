package com.example.proyectofinal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Base64;

public class ActivityAnuncio extends AppCompatActivity {
    ActivityResultLauncher<Intent> someActivityResultLauncher;
    String[] horasLunes = new String[4];
    String[] horasMartes = new String[4];
    String[] horasMiercoles = new String[4];
    String[] horasJueves = new String[4];
    String[] horasViernes = new String[4];
    String[] horasSabado = new String[4];
    String[] horasDomingo = new String[4];
    TextView direccion;
    TextView tipo;
    String base64=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_anuncio);
        Button foto = findViewById(R.id.button2);
        direccion = findViewById(R.id.direccion);
        tipo = findViewById(R.id.tipo);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSomeActivityForResult(v);
            }
        });
        Button horario = findViewById(R.id.button4);
        String hora1="nada";
        String hora2="nada";
        String hora3="nada";
        String hora4="nada";
        horasLunes[0]=hora1;
        horasLunes[1]=hora2;
        horasLunes[2]=hora3;
        horasLunes[3]=hora4;
        horasMartes[0]=hora1;
        horasMartes[1]=hora2;
        horasMartes[2]=hora3;
        horasMartes[3]=hora4;
        horasMiercoles[0]=hora1;
        horasMiercoles[1]=hora2;
        horasMiercoles[2]=hora3;
        horasMiercoles[3]=hora4;
        horasJueves[0]=hora1;
        horasJueves[1]=hora2;
        horasJueves[2]=hora3;
        horasJueves[3]=hora4;
        horasViernes[0]=hora1;
        horasViernes[1]=hora2;
        horasViernes[2]=hora3;
        horasViernes[3]=hora4;
        horasSabado[0]=hora1;
        horasSabado[1]=hora2;
        horasSabado[2]=hora3;
        horasSabado[3]=hora4;
        horasDomingo[0]=hora1;
        horasDomingo[1]=hora2;
        horasDomingo[2]=hora3;
        horasDomingo[3]=hora4;
        horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText hores1 = findViewById(R.id.hora1);
                String hora1 = String.valueOf(hores1.getText());
                EditText hores2 = findViewById(R.id.hora2);
                String hora2 = String.valueOf(hores2.getText());
                EditText hores3 = findViewById(R.id.hora3);
                String hora3 = String.valueOf(hores3.getText());
                EditText hores4 = findViewById(R.id.hora4);
                String hora4 = String.valueOf(hores4.getText());
                Spinner diaSpinner = findViewById(R.id.spinner2);
                String dia = (String) diaSpinner.getSelectedItem();
                if(dia.equalsIgnoreCase("Lunes")){
                    horasLunes[0]=hora1;
                    horasLunes[1]=hora2;
                    horasLunes[2]=hora3;
                    horasLunes[3]=hora4;
                }
                if(dia.equalsIgnoreCase("Martes")){
                    horasMartes[0]=hora1;
                    horasMartes[1]=hora2;
                    horasMartes[2]=hora3;
                    horasMartes[3]=hora4;
                }
                if(dia.equalsIgnoreCase("Miercoles")){
                    horasMiercoles[0]=hora1;
                    horasMiercoles[1]=hora2;
                    horasMiercoles[2]=hora3;
                    horasMiercoles[3]=hora4;
                }
                if(dia.equalsIgnoreCase("Jueves")){
                    horasJueves[0]=hora1;
                    horasJueves[1]=hora2;
                    horasJueves[2]=hora3;
                    horasJueves[3]=hora4;
                }
                if(dia.equalsIgnoreCase("Viernes")){
                    horasViernes[0]=hora1;
                    horasViernes[1]=hora2;
                    horasViernes[2]=hora3;
                    horasViernes[3]=hora4;
                }
                if(dia.equalsIgnoreCase("Sabado")){
                    horasSabado[0]=hora1;
                    horasSabado[1]=hora2;
                    horasSabado[2]=hora3;
                    horasSabado[3]=hora4;
                }
                if(dia.equalsIgnoreCase("Domingo")){
                    horasDomingo[0]=hora1;
                    horasDomingo[1]=hora2;
                    horasDomingo[2]=hora3;
                    horasDomingo[3]=hora4;
                }
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
                            byte[] fileContent1 = data.getByteArrayExtra(Intent.EXTRA_LOCAL_ONLY);
                            File file1 = new File(String.valueOf(uri));
                            File file = null;
                            try {
                                file = FileUtil.from(ActivityAnuncio.this,uri);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            //System.out.println("Bytes: "+fileContent.toString());
                            byte[] fileContent = new byte[0];
                            try {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    System.out.println(uri.getPath());
                                    fileContent = Files.readAllBytes(file.toPath());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            String lletres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                            // La cadena en donde iremos agregando un carácter aleatorio
                            String name = "";
                            for (int x = 0; x < 30; x++) {
                                int indiceAleatorio = (int) (Math.random()*lletres.length()-1);
                                char caracterAleatorio = lletres.charAt(indiceAleatorio);
                                name += caracterAleatorio;
                            }


                            // Transformar el byte[] en una cadena de text
                            base64 = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                base64 = Base64.getEncoder().encodeToString(fileContent);
                            }

                        }
                    }
                });
        Button enviarAnunci = findViewById(R.id.button3);
        enviarAnunci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (base64!=null&&direccion.getText()!=null&&tipo.getText()!=null) {
                    try {
                        JSONObject obj = null;
                        obj = new JSONObject("{}");
                        obj.put("id", RegisterClientActivity.id);
                        obj.put("tipo", tipo.getText());
                        obj.put("imagen", base64);
                        obj.put("direccion", direccion.getText());
                        obj.put("diaInicioLunes", horasLunes[0]);
                        obj.put("diaFinalLunes", horasLunes[1]);
                        obj.put("tardeInicioLunes", horasLunes[2]);
                        obj.put("tardeFinalLunes", horasLunes[3]);
                        obj.put("diaInicioMartes", horasMartes[0]);
                        obj.put("diaFinalMartes", horasMartes[1]);
                        obj.put("tardeInicioMartes", horasMartes[2]);
                        obj.put("tardeFinalMartes", horasMartes[3]);
                        obj.put("diaInicioMiercoles", horasMiercoles[0]);
                        obj.put("diaFinalMiercoles", horasMiercoles[1]);
                        obj.put("tardeInicioMiercoles", horasMiercoles[2]);
                        obj.put("tardeFinalMiercoles", horasMiercoles[3]);
                        obj.put("diaInicioJueves", horasJueves[0]);
                        obj.put("diaFinalJueves", horasJueves[1]);
                        obj.put("tardeInicioJueves", horasJueves[2]);
                        obj.put("tardeFinalJueves", horasJueves[3]);
                        obj.put("diaInicioViernes", horasViernes[0]);
                        obj.put("diaFinalViernes", horasViernes[1]);
                        obj.put("tardeInicioViernes", horasViernes[2]);
                        obj.put("tardeFinalViernes", horasViernes[3]);
                        obj.put("diaInicioSabado", horasSabado[0]);
                        obj.put("diaFinalSabado", horasSabado[1]);
                        obj.put("tardeInicioSabado", horasSabado[2]);
                        obj.put("tardeFinalSabado", horasSabado[3]);
                        obj.put("diaInicioDomingo", horasDomingo[0]);
                        obj.put("diaFinalDomingo", horasDomingo[1]);
                        obj.put("tardeInicioDomingo", horasDomingo[2]);
                        obj.put("tardeFinalDomingo", horasDomingo[3]);
                        UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/create_advertisment", obj.toString(), (response) -> {
                            try {
                                JSONObject obj2 = new JSONObject(response);
                                if (obj2.getString("status").equals("OK")) {
                                    dialog(obj2.getString("status"), obj2.getString("message"));
                                } else if (obj2.getString("status").equals("ERROR")) {
                                    dialog(obj2.getString("status"), obj2.getString("message"));
                                }
                            } catch (JSONException e) {
                                System.out.println();
                            }
                        });
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ActivityAnuncio.this);
                    alerta.setTitle("Error");
                    alerta.setMessage("Rellena los campos");
                    alerta.setNegativeButton("Cerrar" ,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(ActivityAnuncio.this, MainCompanyActivity.class));
                            }
                    });
                    alerta.show();
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
    private void dialog(String status ,String mesage) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder alerta = new AlertDialog.Builder(ActivityAnuncio.this);
                if (status.equals("OK")) {
                    alerta.setTitle("Anuncio creado");
                    alerta.setMessage(mesage);
                    alerta.setNegativeButton("Cerrar" ,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(ActivityAnuncio.this, MainCompanyActivity.class));
                        }
                    });
                    alerta.show();
                } else if (status.equals("ERROR")) {
                    alerta.setTitle("Error de anuncio");
                    alerta.setMessage(mesage);
                    alerta.setNegativeButton("Cerrar" ,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alerta.show();
                }

            }
        });
    }
    public void comprobarHoras(){

    }
}
