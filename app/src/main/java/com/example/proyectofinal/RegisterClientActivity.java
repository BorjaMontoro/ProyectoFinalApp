package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterClientActivity extends AppCompatActivity {
    EditText nombre = findViewById(R.id.registroNombre);
    EditText apellidos = findViewById(R.id.registroApellidos);
    EditText correo = findViewById(R.id.registroCorreos);
    EditText telefono = findViewById(R.id.registroTelefono);
    EditText password = findViewById(R.id.registroPassword);
    EditText compPassword = findViewById(R.id.registroRepetirPassword);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);

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
                    obj.put("surname",apellidos.getText());
                    obj.put("email", correo.getText());
                    obj.put("phone",telefono.getText());
                    obj.put("password",password.getText());
                    String value = checkPassword(password.getText().toString(),compPassword.getText().toString());
                    if (value.equals("true")) {
                        UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/signup_client", obj.toString(), (response) -> {
                            try {
                                JSONObject obj2 = new JSONObject(response);
                                if (obj2.getString("status").equals("OK")) {
                                    dialog(obj2.getString("status"),obj2.getString("message"));
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
                AlertDialog.Builder alerta = new AlertDialog.Builder(RegisterClientActivity.this);
                if (status.equals("OK")) {
                    alerta.setTitle("Registro");
                    alerta.setMessage(mesage);
                    alerta.setNegativeButton("Cerrar" ,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(RegisterClientActivity.this,MainActivity.class));
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