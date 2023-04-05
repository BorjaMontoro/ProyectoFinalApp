package com.example.proyectofinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public ListadoServiciosFragment() {
        // Required empty public constructor
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
    public static ListadoServiciosFragment newInstance(String param1, String param2) {
        ListadoServiciosFragment fragment = new ListadoServiciosFragment();
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
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_servicios);
        List<Servicio> listaServicios = obtenerListaServicios(); // Aquí debes obtener la lista de servicios desde algún lugar
        ServiciosAdapter adapter = new ServiciosAdapter(listaServicios);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Inflate the layout for this fragment
        return root;
    }
    public List<Servicio> obtenerListaServicios(){
        List<Servicio> servicios=new ArrayList<Servicio>();
        servicios.add(new Servicio("Cortar pelo","20.03"+"€","30m"));
        servicios.add(new Servicio("Barba","5.03"+"€","15m"));
        servicios.add(new Servicio("Maquillar","30.25"+"€","1h"));
        servicios.add(new Servicio("Masaje","50.25"+"€","1:30h"));
        return servicios;
    }
}