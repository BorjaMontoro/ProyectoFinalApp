package com.example.proyectofinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HorariosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HorariosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HorariosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static HorariosFragment newInstance(String param1, String param2) {
        HorariosFragment fragment = new HorariosFragment();
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
        View root = inflater.inflate(R.layout.fragment_horarios, container, false);

        // Inflate the layout for this fragment
        TextView phoneTextView = root.findViewById(R.id.company_phone_text_view);
        TableLayout hoursTableLayout = root.findViewById(R.id.hours_table);

        // Crear una instancia de la clase CompanyInfo con los datos que queremos mostrar
        CompanyInfo companyInfo = new CompanyInfo(
                "673 70 60 50",
                Arrays.asList("9:00"+" - "+"12:00", "14:00"+" - "+"20:00"),  // Horario del lunes
                Arrays.asList("9:00"+" - "+"12:00", "14:00"+" - "+"20:00"),  // Horario del martes
                Arrays.asList("9:00"+" - "+"12:00", "14:00"+" - "+"20:00"),  // Horario del miércoles
                Arrays.asList("9:00"+" - "+"12:00", "14:00"+" - "+"20:00"),  // Horario del jueves
                Arrays.asList("9:00"+" - "+"12:00", "14:00"+" - "+"20:00"),  // Horario del viernes
                Arrays.asList("10:00"+" - "+"12:00"), // Horario del sábado
                Arrays.asList("Cerrado")                                  // Horario del domingo
        );

        // Mostrar el número de teléfono de la empresa en el TextView correspondiente
        phoneTextView.setText(companyInfo.getPhone());

        // Mostrar los horarios de la empresa en la tabla correspondiente
        setHoursTable(hoursTableLayout, companyInfo);

        return root;
    }

    private void setHoursTable(TableLayout tableLayout, CompanyInfo companyInfo) {
        // Obtener los días de la semana
        String[] daysOfWeek = getResources().getStringArray(R.array.dias_semana);

        // Agregar una fila para cada día de la semana
        for (int i = 0; i < daysOfWeek.length; i++) {
            TableRow row = new TableRow(getContext());

            // Agregar el nombre del día de la semana en la primera columna
            TextView dayTextView = new TextView(getContext());
            dayTextView.setText(daysOfWeek[i]);
            row.addView(dayTextView);

            // Agregar los horarios de trabajo para ese día en la segunda columna
            List<String> hours = null;
            switch (i) {
                case 0:
                    hours = companyInfo.getMondayHours();
                    break;
                case 1:
                    hours = companyInfo.getTuesdayHours();
                    break;
                case 2:
                    hours = companyInfo.getWednesdayHours();
                    break;
                case 3:
                    hours = companyInfo.getThursdayHours();
                    break;
                case 4:
                    hours = companyInfo.getFridayHours();
                    break;
                case 5:
                    hours = companyInfo.getSaturdayHours();
                    break;
                case 6:
                    hours = companyInfo.getSundayHours();
                    break;
                default:
                    break;

            }
            // Agregar los horarios a la segunda columna
            TextView hoursTextView = new TextView(getContext());
            if (hours != null) {
                StringBuilder hoursBuilder = new StringBuilder();
                for (String hour : hours) {
                    hoursBuilder.append(hour).append("\n");
                }
                hoursTextView.setText(hoursBuilder.toString());
            }
            row.addView(hoursTextView);

            // Agregar la fila a la tabla
            tableLayout.addView(row);

        }
    }
}