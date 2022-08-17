package com.trabalho.geometria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragmento_losango extends Fragment {
    private View vista;
    EditText edt_diagonalMaior, edt_diaginalMenor;
    private Button calcular;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragmento_losango,container,false);
        vista =  inflater.inflate(R.layout.fragmento_losango,container,false);
        iniciacomponentes(vista);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





               // double diagonalMenor, diagonalMaior;
                //double resultado =  (diagonalMaior * diagonalMenor) / 2;
            }
        });

        return vista;
    }

    public void iniciacomponentes(View v){

        edt_diagonalMaior = v.findViewById(R.id.diagonalMaior);
        edt_diaginalMenor = v.findViewById(R.id.diagonalMenor);
        calcular = v.findViewById(R.id.calcular);


    }

}