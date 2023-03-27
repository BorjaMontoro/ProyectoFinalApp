package com.example.proyectofinal;

import android.os.Bundle;
import android.view.View;

import com.example.proyectofinal.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.proyectofinal.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_blank,R.id.navigation_blank2)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //Navigation.findNavController(binding.navView,navController);
        //navView.removeView(navView.getChildAt(0));
        NavigationUI.setupWithNavController(binding.navView, navController);
        navView.removeView(findViewById(R.id.navigation_blank));
        View view = findViewById(R.id.navigation_blank);
        //view.setVisibility(View.INVISIBLE);
        View view2 = findViewById(R.id.navigation_home);
        view2.setVisibility(View.INVISIBLE);
        View view3 = findViewById(R.id.navigation_notifications);
        view3.setVisibility(View.INVISIBLE);
        View view4 = findViewById(R.id.navigation_dashboard);
        view4.setVisibility(View.INVISIBLE);
        view.setVisibility(View.VISIBLE);
        view2.setVisibility(View.VISIBLE);
        view4.setVisibility(View.VISIBLE);

    }

}