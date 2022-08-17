package com.example.prova.fragment;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;


import com.example.prova.adapters.BancoControlador;
import com.example.prova.R;

public class AddDialogFragment extends DialogFragment {
    String result, tipo;
    Button voltar, mais;

    public AddDialogFragment(String resul, String t) {
        this.result = resul;
        this.tipo = t;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_create, container);
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        super.show(manager, tag);
    }

    private TextView resultado;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inciarcomponentes(view);
        resultado.setText(result);
        BancoControlador bd = new BancoControlador(getContext());

        ContentValues cv = new ContentValues();
        cv.put("resultado",result);

        String msg = "";
        long res = bd.inserir(cv);



        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
//                Intent intent = new Intent(getContext(), MainActivity.class);
//                startActivity(intent);
            }
        });
        String url;
        if(tipo.equals("IMC")){
             url = "https://www.drnutricao.com.br/antropometria/imc";
        }else if(tipo.equals("RCQ")){
             url = "https://blog.portaleducacao.com.br/rcq-relacao-cintura-quadril/";
        }else{
             url = "https://www.drnutricao.com.br/Antropometria/calcular-peso-ideal";
        }


        mais.setText("Saiba mais sobre "+tipo);
        mais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlString = url;
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(urlString));
                startActivity(webIntent);
            }
        });

    }

    public void inciarcomponentes(View view){
        resultado = view.findViewById(R.id.textresultado);
        voltar = view.findViewById(R.id.voltar);
        mais = view.findViewById(R.id.mais);

    }

}
