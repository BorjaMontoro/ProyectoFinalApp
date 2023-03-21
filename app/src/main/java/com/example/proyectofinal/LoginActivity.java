package com.example.proyectofinal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
                    JSONObject obj = null;
                    try {
                        obj = new JSONObject("{}");
                        obj.put("email", mail.getText());
                        obj.put("foto", password.getText());

                        // Enviar l’objecte
                        UtilsHTTP.sendPOST("https" + "://" + "corns-production.up.railway.app:" + 443 + "/login",
                                obj.toString(), (response) -> {
                                    try {
                                        JSONObject objResponse = new JSONObject(response);
                                        if(objResponse.getInt("esEmpresa")==0) {
                                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        }
                                        else{
                                            startActivity(new Intent(LoginActivity.this, MainCompanyActivity.class));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(getBaseContext()).create();
                    alertDialog.setTitle("ERROR");
                    alertDialog.setMessage("Por favor, llena los campos mail y contraseña");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            });
                    alertDialog.show();
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

}
