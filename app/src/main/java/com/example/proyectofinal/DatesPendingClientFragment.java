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
 * Use the {@link DatesPendingClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatesPendingClientFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private DateClientAdapter dateClientAdapter;

    public DatesPendingClientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatesPendingClientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatesPendingClientFragment newInstance(String param1, String param2) {
        DatesPendingClientFragment fragment = new DatesPendingClientFragment();
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
        View root = inflater.inflate(R.layout.fragment_dates_pending_client, container, false);

        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<DateClient> citas=new ArrayList<DateClient>();

        dateClientAdapter = new DateClientAdapter(getContext(), citas);
        recyclerView.setAdapter(dateClientAdapter);
        loadDates();
        return root;
    }

    private void loadDates(){
        try {
            JSONObject obj = new JSONObject("{}");
            obj.put("id", RegisterCompanyActivity.id);
            obj.put("status", "Pending");
            UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/get_client_dates", obj.toString(), (response) -> {
                try {
                    JSONObject obj2 = new JSONObject(response);
                    JSONArray jsonArray=obj2.getJSONArray("citas");
                    List<DateClient> citas=new ArrayList<DateClient>();

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject cita=jsonArray.getJSONObject(i);
                        citas.add(new DateClient(cita.getString("nombreServicio"),cita.getString("nombreEmpresa"),cita.getString("mes"),Integer.toString(cita.getInt("dia")),cita.getString("hora"),Integer.toString(cita.getInt("year"))));
                    }

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            dateClientAdapter = new DateClientAdapter(getContext(), citas);
                            recyclerView.setAdapter(dateClientAdapter);
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