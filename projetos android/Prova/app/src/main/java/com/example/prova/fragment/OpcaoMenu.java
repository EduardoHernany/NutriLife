package com.example.prova.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.prova.R;
import com.example.prova.adapters.BancoControlador;
import com.example.prova.layouts.Historico;

public class OpcaoMenu extends DialogFragment {


    public OpcaoMenu() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.opcaopmenu, container);
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
    private Button his, dicas ;
    private ImageView voltar;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inciarcomponentes(view);

        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Historico.class);
                startActivity(intent);
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        dicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuilder x = new StringBuilder();
                x.append("CIRCUNFERÊNCIA DA CINTURA – por intermédio de uma fita métrica aferir a circunferência do abdômen na" +
                        " altura do umbigo, coletar a medida em centímetros (cuidado para que a fita fique bem posicionada, sem " +
                        "dobras e alinhada horizontalmente), a fita não deve ser apertada e sim somente colocada na barriga.");

                x.append("\n\n").append("CIRCUNFERÊNCIA DO QUADRIL – por intermédio de uma fita métrica aferir a circunferência do " +
                        "quadril na altura da maior circunferência das nádegas, coletar a medida em centímetros (cuidado para que a fita " +
                        "fique bem posicionada, sem dobras e alinhada horizontalmente), a fita não deve ser apertada e sim somente " +
                        "colocada no quadril.");

                x.append("\n\n").append("Para o peso, use uma balança.");
                x.append("\n\n").append("Para a altura, use uma fita");





                androidx.appcompat.app.AlertDialog.Builder janela = new AlertDialog.Builder(getContext(), R.style.dialog2);
                janela.setTitle("NutriLife");
                janela.setMessage(x.toString());
                janela.setNeutralButton("ok", null);
                janela.show();
            }
        });


    }

    public void inciarcomponentes(View view){
        his = view.findViewById(R.id.his_menu);
        dicas = view.findViewById(R.id.dicas_menu);
        voltar = view.findViewById(R.id.voltar_dialog);
    }

//    @Override
//    public void onResume() {
//        dismiss();
//        super.onResume();
//    }
}
