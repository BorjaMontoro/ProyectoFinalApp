package com.example.proyectofinal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
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
    Spinner tipo;
    Button enviarAnunci;
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
        String hora1="";
        String hora2="";
        String hora3="";
        String hora4="";
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
        /*someActivityResultLauncher = registerForActivityResult(
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
                });*/
        final byte[][] fileContent = new byte[1][1];
        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            Bitmap originalBitmap = null;
                            if (uri != null) {
                                try {
                                    originalBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                    InputStream inputStream = getContentResolver().openInputStream(uri);
                                    ExifInterface exif = new ExifInterface(inputStream);

                                    int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                                    Matrix matrix = new Matrix();
                                    switch (orientation) {
                                        case ExifInterface.ORIENTATION_ROTATE_90:
                                            matrix.postRotate(90);
                                            break;
                                        case ExifInterface.ORIENTATION_ROTATE_180:
                                            matrix.postRotate(180);
                                            break;
                                        case ExifInterface.ORIENTATION_ROTATE_270:
                                            matrix.postRotate(270);
                                            break;
                                        default:
                                            break;
                                    }
                                    originalBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.getWidth(), originalBitmap.getHeight(), matrix, true);


                                    int originalWidth = originalBitmap.getWidth();
                                    int originalHeight = originalBitmap.getHeight();
                                    int newWidth = 1000;
                                    int newHeight = (int) ((float) originalHeight / originalWidth * newWidth);

                                    Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, false);

                                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                    resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 30, outputStream);
                                    byte[] byteArray = outputStream.toByteArray();
                                    base64 = Base64.getEncoder().encodeToString(byteArray);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }


                        }
                    }
                }
        );
        enviarAnunci = findViewById(R.id.button3);
        enviarAnunci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarAnunci.setEnabled(false);
                if (base64!=null&&direccion.getText()!=null&&tipo.getSelectedItem()!=null) {
                    try {
                        JSONObject obj = null;
                        obj = new JSONObject("{}");
                        obj.put("id", RegisterCompanyActivity.id);
                        obj.put("tipo", tipo.getSelectedItem());
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
                                enviarAnunci.setEnabled(true);

                            }
                    });
                    alerta.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {
                            enviarAnunci.setEnabled(true);                        }
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
                            JSONObject obj5 = null;
                            try {
                                obj5 = new JSONObject("{}");
                                obj5.put("id",RegisterCompanyActivity.id);
                                UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/get_user", obj5.toString(), (response3) -> {
                                    try {
                                        JSONObject obj6 = new JSONObject(response3);
                                        if (obj6.getString("status").equals("OK")) {
                                            JSONArray userList  = obj6.getJSONArray("user");
                                            JSONObject user = (JSONObject) userList.get(0);
                                            RegisterCompanyActivity.name=user.getString("nombre");
                                            RegisterCompanyActivity.surname=user.getString("apellidos");
                                            RegisterCompanyActivity.mail=user.getString("correo");
                                            RegisterCompanyActivity.phone=user.getString("telefono");
                                            if(user.getInt("esEmpresa")==1){
                                                RegisterCompanyActivity.companyName=user.getString("nombreEmpresa");
                                                JSONObject obj7 = new JSONObject("{}");
                                                obj7.put("id",RegisterCompanyActivity.id);
                                                UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/get_image", obj7.toString(), (response4) -> {
                                                    try {
                                                        JSONObject obj8 = new JSONObject(response4);
                                                        if (obj8.getString("status").equals("OK")) {
                                                            RegisterCompanyActivity.companyImage=obj8.getString("anuncio");
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                });
                                            }
                                        } else if (obj6.getString("status").equals("ERROR")) {
                                            dialog(obj6.getString("status"),obj6.getString("message"));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(ActivityAnuncio.this,MainCompanyActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }
                    });
                    alerta.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            JSONObject obj5 = null;
                            try {
                                obj5 = new JSONObject("{}");
                                obj5.put("id",RegisterCompanyActivity.id);
                                UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/get_user", obj5.toString(), (response3) -> {
                                    try {
                                        JSONObject obj6 = new JSONObject(response3);
                                        if (obj6.getString("status").equals("OK")) {
                                            JSONArray userList  = obj6.getJSONArray("user");
                                            JSONObject user = (JSONObject) userList.get(0);
                                            RegisterCompanyActivity.name=user.getString("nombre");
                                            RegisterCompanyActivity.surname=user.getString("apellidos");
                                            RegisterCompanyActivity.mail=user.getString("correo");
                                            RegisterCompanyActivity.phone=user.getString("telefono");
                                            if(user.getInt("esEmpresa")==1){
                                                RegisterCompanyActivity.companyName=user.getString("nombreEmpresa");
                                                JSONObject obj7 = new JSONObject("{}");
                                                obj7.put("id",RegisterCompanyActivity.id);
                                                UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/get_image", obj7.toString(), (response4) -> {
                                                    try {
                                                        JSONObject obj8 = new JSONObject(response4);
                                                        if (obj8.getString("status").equals("OK")) {
                                                            RegisterCompanyActivity.companyImage=obj8.getString("anuncio");
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                });
                                            }
                                        } else if (obj6.getString("status").equals("ERROR")) {
                                            dialog(obj6.getString("status"),obj6.getString("message"));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(ActivityAnuncio.this,MainCompanyActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }
                    });
                    alerta.show();
                } else if (status.equals("ERROR")) {
                    alerta.setTitle("Error de anuncio");
                    alerta.setMessage(mesage);
                    alerta.setNegativeButton("Cerrar" ,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            enviarAnunci.setEnabled(true);

                        }
                    });
                    alerta.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {
                            enviarAnunci.setEnabled(true);                        }
                    });
                    alerta.show();
                }

            }
        });
    }
}
