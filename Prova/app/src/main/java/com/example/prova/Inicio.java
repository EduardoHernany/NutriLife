package com.example.prova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.prova.layouts.MainActivity;

public class Inicio extends AppCompatActivity {
    Button iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
         iniciar = findViewById(R.id.inicar);

         iniciar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent =new Intent(getApplicationContext(), MainActivity.class);
                 startActivity(intent);
             }
         });

    }
}