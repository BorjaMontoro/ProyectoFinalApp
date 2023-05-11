package com.example.proyectofinal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ServicesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicesFragment newInstance(String param1, String param2) {
        ServicesFragment fragment = new ServicesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_services, container, false);

        Button addService = root.findViewById(R.id.buttonServicio);
        TextView nombre = root.findViewById(R.id.inputNombre);
        TextView duracion = root.findViewById(R.id.inputDuracion);
        TextView precio = root.findViewById(R.id.inputPrecio);

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject obj = new JSONObject("{}");
                    obj.put("id",RegisterCompanyActivity.id);
                    obj.put("name",nombre.getText());
                    obj.put("price",precio.getText());
                    obj.put("duration",duracion.getText());
                    UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/insert_service", obj.toString(), (response) -> {
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
                    throw new RuntimeException(e);
                }
            }
        });

        return root;
    }
    private void dialog(String status ,String mesage) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
                if (status.equals("OK")) {
                    alerta.setTitle("Servicio añadido");
                    alerta.setMessage(mesage);
                    alerta.setNegativeButton("Cerrar" ,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alerta.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {

                        }
                    });
                    alerta.show();
                } else if (status.equals("ERROR")) {
                    alerta.setTitle("Error al añadir el servicio");
                    alerta.setMessage(mesage);
                    alerta.setNegativeButton("Cerrar" ,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alerta.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {

                        }
                    });
                    alerta.show();
                }

            }
        });
    }
}