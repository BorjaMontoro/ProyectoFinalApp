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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterCompanyActivity extends AppCompatActivity {

    EditText nombreEmpresa,nombre,apellidos,correo,telefono,password,compPassword;
    TextView loginText;
    Button registerButton;
    public static int id;
    public static String name,surname,mail,phone,companyName,companyImage;
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
        registerButton = findViewById(R.id.registrar);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerButton.setEnabled(false);
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
                                    RegisterCompanyActivity.id=obj2.getInt("id");
                                    dialog(obj2.getString("status"),obj2.getString("message"));

                                } else if (obj2.getString("status").equals("ERROR")) {
                                    dialog(obj2.getString("status"),obj2.getString("message"));

                                }
                            } catch (JSONException e) {
                                System.out.println();
                            }
                        });
                    } else {
                        dialog("ERROR","Las contraseñas no son iguales.");
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
                            JSONObject obj = null;
                            try {
                                obj = new JSONObject("{}");
                                obj.put("id",id);
                                UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/get_user", obj.toString(), (response) -> {
                                    try {
                                        JSONObject obj2 = new JSONObject(response);
                                        if (obj2.getString("status").equals("OK")) {
                                            JSONArray userList  = obj2.getJSONArray("user");
                                            JSONObject user = (JSONObject) userList.get(0);
                                            RegisterCompanyActivity.name=user.getString("nombre");
                                            RegisterCompanyActivity.surname=user.getString("apellidos");
                                            RegisterCompanyActivity.mail=user.getString("correo");
                                            RegisterCompanyActivity.phone=user.getString("telefono");
                                            RegisterCompanyActivity.companyName=user.getString("nombreEmpresa");
                                        }
                                    } catch (JSONException e) {
                                        System.out.println();
                                    }
                                });
                                Intent intent = new Intent(RegisterCompanyActivity.this,ActivityAnuncio.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    alerta.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            JSONObject obj = null;
                            try {
                                obj = new JSONObject("{}");
                                obj.put("id",id);
                                UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/get_user", obj.toString(), (response) -> {
                                    try {
                                        JSONObject obj2 = new JSONObject(response);
                                        if (obj2.getString("status").equals("OK")) {
                                            JSONArray userList  = obj2.getJSONArray("user");
                                            JSONObject user = (JSONObject) userList.get(0);
                                            RegisterCompanyActivity.name=user.getString("nombre");
                                            RegisterCompanyActivity.surname=user.getString("apellidos");
                                            RegisterCompanyActivity.mail=user.getString("correo");
                                            RegisterCompanyActivity.phone=user.getString("telefono");
                                            RegisterCompanyActivity.companyName=user.getString("nombreEmpresa");
                                        }
                                    } catch (JSONException e) {
                                        System.out.println();
                                    }
                                });
                                Intent intent = new Intent(RegisterCompanyActivity.this,ActivityAnuncio.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    alerta.show();
                } else if (status.equals("ERROR")) {
                    alerta.setTitle("Error de registro");
                    alerta.setMessage(mesage);
                    alerta.setNegativeButton("Cerrar" ,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            registerButton.setEnabled(true);

                        }
                    });
                    alerta.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {
                            registerButton.setEnabled(true);
                        }
                    });
                    alerta.show();
                }

            }
        });
    }
}