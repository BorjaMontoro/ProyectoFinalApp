package com.example.proyectofinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileCompanyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileCompanyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileCompanyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileCompanyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileCompanyFragment newInstance(String param1, String param2) {
        ProfileCompanyFragment fragment = new ProfileCompanyFragment();
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
        View root = inflater.inflate(R.layout.fragment_profile_company, container, false);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView nombreComp = root.findViewById(R.id.nomComp);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView nomAp = root.findViewById(R.id.nomAp);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView telefono = root.findViewById(R.id.telef);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView correo = root.findViewById(R.id.correo);
        ImageView imagen = root.findViewById(R.id.imageView2);
        Button logout = root.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterCompanyActivity.id=0;
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });
        TextView nom=root.findViewById(R.id.nomAp);
        TextView telefon = root.findViewById(R.id.telef);
        TextView correo1 = root.findViewById(R.id.correo);
        TextView empresa = root.findViewById(R.id.nomComp);
        ImageView imgVw = root.findViewById(R.id.imageView2);
        nom.setText(RegisterCompanyActivity.name+" "+RegisterCompanyActivity.surname);
        telefon.setText(RegisterCompanyActivity.phone);
        correo1.setText(RegisterCompanyActivity.mail);
        empresa.setText(RegisterCompanyActivity.companyName);
        byte[] imageBytes = Base64.decode(RegisterCompanyActivity.companyImage, Base64.DEFAULT);
        System.out.println("Hola");
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        System.out.println("Hola2");
        imgVw.setImageBitmap(bitmap);
        // Inflate the layout for this fragment
        return root;
    }
}