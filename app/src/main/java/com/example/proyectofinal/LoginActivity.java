package com.example.proyectofinal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.KeyEvent;
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
                if (mail.getText()!=null && password.getText()!=null) {
                    try {
                        JSONObject obj = new JSONObject("{}");
                        obj.put("email", mail.getText());
                        obj.put("password",password.getText());
                        UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/login", obj.toString(), (response) -> {
                            try {
                                JSONObject obj2 = new JSONObject(response);
                                if (obj2.getString("status").equals("OK")) {
                                    dialog(obj2.getString("status"),obj2.getString("message"));
                                    if(obj2.getInt("esEmpresa")==0) {
                                        RegisterClientActivity.id=obj2.getInt("id");
                                        startActivity(new Intent(LoginActivity.this,MainClientActivity.class));
                                    }
                                    else if(obj2.getInt("esEmpresa")==1) {
                                        RegisterClientActivity.id=obj2.getInt("id");
                                        try {
                                            JSONObject obj3 = new JSONObject("{}");
                                            obj3.put("id", RegisterClientActivity.id);
                                            UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/have_advertisment", obj3.toString(), (response2) -> {
                                                try {
                                                    JSONObject obj4 = new JSONObject(response2);
                                                    if (obj4.getString("status").equals("OK")) {
                                                        if(obj4.getBoolean("anuncio")) {
                                                            startActivity(new Intent(LoginActivity.this,MainCompanyActivity.class));
                                                        }
                                                        else if(!obj4.getBoolean("anuncio")) {
                                                            startActivity(new Intent(LoginActivity.this, ActivityAnuncio.class));
                                                        }
                                                    } else if (obj4.getString("status").equals("ERROR")) {
                                                        dialog(obj4.getString("status"),obj4.getString("message"));

                                                    }
                                                } catch (JSONException e) {
                                                    System.out.println();
                                                }
                                            });
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
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
                }
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
                if (status.equals("ERROR")) {
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // Ignorar el evento del botón de retroceso
            return true;
        }
        // Dejar que otros eventos de tecla se procesen
        return super.onKeyDown(keyCode, event);
    }

}
