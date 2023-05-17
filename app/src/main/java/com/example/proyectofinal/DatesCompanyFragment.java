package com.example.proyectofinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatesCompanyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatesCompanyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CalendarView calendarView;
    private RecyclerView citasRecyclerView;
    private DateCompanyAdapter citasAdapter;

    public DatesCompanyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatesCompanyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatesCompanyFragment newInstance(String param1, String param2) {
        DatesCompanyFragment fragment = new DatesCompanyFragment();
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
        View root = inflater.inflate(R.layout.fragment_dates_company, container, false);

        calendarView = root.findViewById(R.id.calendarView);
        citasRecyclerView = root.findViewById(R.id.recyclerView);
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

        citasRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<DateCompany> citas = new ArrayList<>();
        citasAdapter = new DateCompanyAdapter(citas);
        citasRecyclerView.setAdapter(citasAdapter);

        Calendar fechaSeleccionada = Calendar.getInstance();
        calendarView.setDate(fechaSeleccionada.getTimeInMillis());
        int year = fechaSeleccionada.get(Calendar.YEAR);
        int month = fechaSeleccionada.get(Calendar.MONTH);
        int day = fechaSeleccionada.get(Calendar.DAY_OF_MONTH);
        loadDates(year,month,day);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                loadDates(year,month,day);
            }
        });
        return root;
    }

    private void loadDates(int year, int month, int day){
        try {
            JSONObject obj = new JSONObject("{}");
            obj.put("id", RegisterCompanyActivity.id);
            obj.put("year", year);
            obj.put("month", month);
            obj.put("day", day);

            UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/get_company_dates", obj.toString(), (response) -> {
                try {
                    JSONObject obj2 = new JSONObject(response);
                    JSONArray jsonArray=obj2.getJSONArray("citas");
                    List<DateCompany> citas=new ArrayList<DateCompany>();
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject cita=jsonArray.getJSONObject(i);
                        citas.add(new DateCompany(cita.getString("nombreUsuario"),cita.getString("nombreServicio"),cita.getString("horaInicio"),cita.getString("horaFin")));
                    }
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            citasAdapter = new DateCompanyAdapter(citas);
                            citasRecyclerView.setAdapter(citasAdapter);
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