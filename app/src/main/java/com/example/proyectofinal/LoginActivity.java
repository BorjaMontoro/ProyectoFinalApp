package com.example.proyectofinal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = findViewById(R.id.button);
        TextView mail = findViewById(R.id.loginCorreos);
        TextView password = findViewById(R.id.loginPassword);
        TextView registerTextClient = findViewById(R.id.textViewSignUpClient);
        TextView registerTextCompany = findViewById(R.id.textViewSignUpCompany);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                /*if (mail.getText()!=null && password.getText()!=null) {
                    try {
                        JSONObject obj = new JSONObject("{}");
                        obj.put("email", mail.getText());
                        obj.put("password",password.getText());
                        UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/login", obj.toString(), (response) -> {
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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    dialog("ERROR","Por favor, llena los campos mail y contraseña");
                }*/
            }
        });

        registerTextClient.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterClientActivity.class));
            }
        });
        registerTextCompany.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterCompanyActivity.class));
            }
        });
    }
    private void dialog(String status ,String mesage) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder alerta = new AlertDialog.Builder(LoginActivity.this);
                if (status.equals("OK")) {
                    alerta.setTitle("Login");
                    alerta.setMessage(mesage);
                    alerta.setNegativeButton("OK" ,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }
                    });
                    alerta.show();
                } else if (status.equals("ERROR")) {
                    alerta.setTitle("ERROR");
                    alerta.setMessage(mesage);
                    alerta.setNegativeButton("OK" ,new DialogInterface.OnClickListener() {
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
