package com.example.proyectofinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListadoServiciosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListadoServiciosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String empresa;
    private RecyclerView recyclerView;

    public ListadoServiciosFragment(String empresa) {
        // Required empty public constructor
        this.empresa=empresa;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListadoServiciosFragment newInstance(String param1, String param2, String empresa) {
        ListadoServiciosFragment fragment = new ListadoServiciosFragment(empresa);
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
        View root = inflater.inflate(R.layout.fragment_listado_servicios, container, false);
        recyclerView = root.findViewById(R.id.recycler_view_servicios);

        List<Servicio> servicios=new ArrayList<Servicio>();
        ServiciosAdapter adapter = new ServiciosAdapter(servicios,empresa);

        recyclerView.setAdapter(adapter);

        obtenerListaServicios();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Inflate the layout for this fragment
        return root;
    }

    public void obtenerListaServicios(){
        try {
            JSONObject obj = new JSONObject("{}");
            obj.put("name", empresa);
            UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/get_services", obj.toString(), (response) -> {
                try {
                    JSONObject obj2 = new JSONObject(response);
                    JSONArray jsonArray=obj2.getJSONArray("services");
                    List<Servicio> servicios=new ArrayList<Servicio>();

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject servicioIndividual=jsonArray.getJSONObject(i);
                        servicios.add(new Servicio(servicioIndividual.getString("nombre"),servicioIndividual.getString("precio"),servicioIndividual.getString("duracion")));
                    }

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ServiciosAdapter adapter = new ServiciosAdapter(servicios,empresa);
                            recyclerView.setAdapter(adapter);

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
}