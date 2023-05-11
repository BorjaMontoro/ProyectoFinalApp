package com.example.proyectofinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements AnunciosAdapter.OnAnuncioClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String busqueda="";
    private String tipo="Todos";
    private RecyclerView recyclerView;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment
        recyclerView = root.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        List<Anuncio> b=new ArrayList<Anuncio>();

        AnunciosAdapter adapter=new AnunciosAdapter(b);
        adapter.setOnAnuncioClickListener(SearchFragment.this);
        recyclerView.setAdapter(adapter);


        SearchView searchView = root.findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Aquí puedes llamar al adaptador y actualizar la lista de anuncios según los términos de búsqueda
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Aquí puedes llamar al adaptador y actualizar la lista de anuncios según los términos de búsqueda
                busqueda=newText;
                generarDatos();
                return true;
            }
        });

        Spinner spinner = root.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Aquí puedes llamar al adaptador y actualizar la lista de anuncios según el tipo seleccionado
                tipo=(String) spinner.getSelectedItem();
                generarDatos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se hace nada si no se selecciona nada en el Spinner
            }
        });

        return root;
    }

    @Override
    public void onAnuncioClick(Anuncio anuncio) {
        Intent intent = new Intent(getActivity(), DetalleAnuncio.class);
        intent.putExtra("ANUNCIO", anuncio);
        startActivity(intent);
    }

    public void generarDatos(){
        try {
            JSONObject obj = new JSONObject("{}");
            obj.put("search", busqueda);
            obj.put("tipo",tipo);
            UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/get_advertisments", obj.toString(), (response) -> {
                try {
                    JSONObject obj2 = new JSONObject(response);
                    JSONArray jsonArray=obj2.getJSONArray("advertisments");
                    List<Anuncio> anuncios=new ArrayList<Anuncio>();

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject anuncioIndividual=jsonArray.getJSONObject(i);
                        anuncios.add(new Anuncio(anuncioIndividual.getString("nombreEmpresa"),anuncioIndividual.getString("direccion"),anuncioIndividual.getString("tipo"),anuncioIndividual.getString("imagen")));
                    }
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            AnunciosAdapter adapter=new AnunciosAdapter(anuncios);
                            adapter.setOnAnuncioClickListener(SearchFragment.this);
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