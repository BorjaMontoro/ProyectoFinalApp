package com.example.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class DetalleAnuncio extends AppCompatActivity {

    // ...

    private ImageView mAnuncioImageView;
    private TextView mNombreTextView;
    private TextView mDireccionTextView;
    private TextView mTipoTextView;
    private TextView mHorarioTextView;
    private TextView mServiciosTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_anuncio);
        mAnuncioImageView = findViewById(R.id.anuncio_image_view);
        mNombreTextView = findViewById(R.id.nombre_text_view);
        mDireccionTextView = findViewById(R.id.direccion_text_view);
        mTipoTextView = findViewById(R.id.tipo_text_view);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ListadoServiciosFragment());
        fragmentList.add(new HorariosFragment());

        List<String> titleList = new ArrayList<>();
        titleList.add("Servicios");
        titleList.add("Detalles");

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragmentList);

        viewPager2.setAdapter(adapter);

        ImageButton btnAtras = findViewById(R.id.btn_back);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleAnuncio.this, MainClientActivity.class);
                startActivity(intent);
            }
        });

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(titleList.get(position))
        ).attach();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("ANUNCIO")) {
            Anuncio anuncio = (Anuncio) intent.getSerializableExtra("ANUNCIO");

            //mAnuncioImageView.setImageResource(anuncio.getImageResource());
            mNombreTextView.setText(anuncio.getNombre());
            mDireccionTextView.setText(anuncio.getDireccion());
            mTipoTextView.setText(anuncio.getTipo());
        }
    }

    // ...

}

