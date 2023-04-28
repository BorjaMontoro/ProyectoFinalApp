package com.example.proyectofinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.util.ArrayList;
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
    private List<DateCompany> citas;


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

        // Configurar el adapter del RecyclerView
        citas = new ArrayList<>();
        citas.add(new DateCompany("Borja Montoro Plaza","Baño suizo", "9:30"));
        citas.add(new DateCompany("Pablo Munuera Garcia","Corte de pelo", "12:30"));
        citasAdapter = new DateCompanyAdapter(citas);
        citasRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        citasRecyclerView.setAdapter(citasAdapter);

        // Establecer el listener del CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                // Actualizar el RecyclerView con las citas correspondientes al día seleccionado
                citas.clear();
                citas.addAll(getAppointmentsForDay(year, month, day));
                citasAdapter.setData(citas);

            }
        });
        return root;
    }
    private List<DateCompany> getAppointmentsForDay(int year, int month, int day) {
        // Implementar la lógica para obtener las citas de ese día
        List<DateCompany> citas=new ArrayList<>();
        citas.add(new DateCompany("Alex Martinez Gonzalez","Cortar barba", "8:00"));
        citas.add(new DateCompany("Ignasi Mendez Fabra","Sesion maquillaje", "15:30"));
        return citas;
    }
}