package com.example.proyectofinal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterCompanyActivity extends AppCompatActivity {

    EditText nombreEmpresa,nombre,apellidos,correo,telefono,password,compPassword;
    TextView loginText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company);
        nombreEmpresa = findViewById(R.id.registroNombeEmpresa);
        nombre = findViewById(R.id.registroNombre);
        apellidos = findViewById(R.id.registroApellidos);
        correo = findViewById(R.id.registroCorreos);
        telefono = findViewById(R.id.registroTelefono);
        password = findViewById(R.id.registroPassword);
        compPassword = findViewById(R.id.registroRepetirPassword);
        loginText = findViewById(R.id.textViewLogin);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                startActivity(new Intent(RegisterCompanyActivity.this,LoginActivity.class));
            }
        });
        try {
            registerButton();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }


    private void registerButton() throws JSONException {
        Button registerButton = findViewById(R.id.registrar);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject obj = new JSONObject("{}");
                    obj.put("name",nombre.getText());
                    obj.put("nameCompany",nombreEmpresa.getText());
                    obj.put("surname",apellidos.getText());
                    obj.put("email", correo.getText());
                    obj.put("phone",telefono.getText());
                    obj.put("password",password.getText());
                    String value = checkPassword(password.getText().toString(),compPassword.getText().toString());
                    if (value.equals("true")) {
                        UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/signup_company", obj.toString(), (response) -> {
                            try {
                                JSONObject obj2 = new JSONObject(response);
                                if (obj2.getString("status").equals("OK")) {
                                    dialog(obj2.getString("status"),obj2.getString("message"));
                                    RegisterClientActivity.id=obj2.getInt("id");

                                } else if (obj2.getString("status").equals("ERROR")) {
                                    dialog(obj2.getString("status"),obj2.getString("message"));

                                }
                            } catch (JSONException e) {
                                System.out.println();
                            }
                        });
                    } else {
                        dialog("ERROR","Les contrasenyes no són iguals.");
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private String checkPassword(String password, String password2) {
        if (password.equals(password2)) {
            return "true";
        }
        return "false";
    }

    private void dialog(String status ,String mesage) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder alerta = new AlertDialog.Builder(RegisterCompanyActivity.this);
                if (status.equals("OK")) {
                    alerta.setTitle("Registro");
                    alerta.setMessage(mesage);
                    alerta.setNegativeButton("Cerrar" ,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(RegisterCompanyActivity.this, MainCompanyActivity.class));
                        }
                    });
                    alerta.show();
                } else if (status.equals("ERROR")) {
                    alerta.setTitle("Error de registre");
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
}