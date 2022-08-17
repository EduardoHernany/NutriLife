package com.trabalho.geometria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Segunda_activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        iniciarcomponentes();
        //getSupportActionBar().hide();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmeto_principal,new Fragmento_triangulo()).commit();
        bottomNavigationView.setBackground(getDrawable(R.drawable.gradient_um));

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment select= null;

                switch (item.getItemId()){
                    case R.id.ic_triangulo:
                        select = new Fragmento_triangulo();
                        bottomNavigationView.setBackground(getDrawable(R.drawable.gradient_um));
                        break;

                    case R.id.ic_trapezio:
                        select = new Fragmento_trapezio();
                        bottomNavigationView.setBackground(getDrawable(R.drawable.gradient_dois));
                        break;

                    case R.id.ic_losango:
                        select = new Fragmento_losango();
                        bottomNavigationView.setBackground(getDrawable(R.drawable.gradient_tres));
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmeto_principal,select).commit();



                return false;
            }
        });


    }

    void iniciarcomponentes(){
        bottomNavigationView = findViewById(R.id.btv);
    }
}