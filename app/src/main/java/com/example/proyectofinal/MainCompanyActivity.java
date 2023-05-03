package com.example.proyectofinal;

import android.os.Bundle;
import android.view.KeyEvent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.proyectofinal.databinding.ActivityMainCompanyBinding;

public class MainCompanyActivity extends AppCompatActivity {

    private ActivityMainCompanyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainCompanyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView bottom=findViewById(R.id.nav_view);
        replaceFragment(new DatesCompanyFragment());
        bottom.setSelectedItemId(R.id.Dates);


        bottom.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.Services:
                    replaceFragment(new ServicesFragment());
                    break;
                case R.id.Dates:
                    replaceFragment(new DatesCompanyFragment());
                    break;
                case R.id.Profile:
                    replaceFragment(new ProfileCompanyFragment());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // Ignorar el evento del botón de retroceso
            return true;
        }
        // Dejar que otros eventos de tecla se procesen
        return super.onKeyDown(keyCode, event);
    }

}