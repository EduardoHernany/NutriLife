package com.example.prova.layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.prova.R;
import com.example.prova.adapters.Adapter;
import com.example.prova.adapters.BancoControlador;

import java.util.ArrayList;
import java.util.List;

public class Historico extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button voltar;
    private ImageView his;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        recyclerView = findViewById(R.id.recycle);
        voltar = findViewById(R.id.voltar_his);
        his = findViewById(R.id.his);
        //his.setAlpha(0);
        //his.setText("Voltar");


        //his.setImageDrawable(R.drawable.deixou);
        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        List<ContentValues> lista = new ArrayList<>();
        BancoControlador bd = new BancoControlador(getApplicationContext());
        lista = bd.pesquiarPorTodos();

        Adapter adapter = new Adapter(lista);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(Historico.this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

}