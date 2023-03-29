package com.example.proyectofinal;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.proyectofinal.databinding.ActivityMainClientBinding;

public class MainClientActivity extends AppCompatActivity {

    private ActivityMainClientBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainClientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView bottom=findViewById(R.id.nav_view);
        replaceFragment(new SearchFragment());

        bottom.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.Search:
                    replaceFragment(new SearchFragment());
                    break;
                case R.id.Dates:
                    replaceFragment(new DatesClientFragment());
                    break;
                case R.id.Profile:
                    replaceFragment(new ProfileClientFragment());
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

}