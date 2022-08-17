package com.trabalho.geometria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Fragmento_trapezio extends Fragment {
    private View vista;
    private EditText edt_baseMenor, edt_baseMaior, edt_altura;
    private Button calcular;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragmento_trapezio, container, false);
        vista =  inflater.inflate(R.layout.fragmento_trapezio,container,false);
        iniciacomponentes(vista);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double baseMenor, baseMaior, altura;

                altura = Double.parseDouble(edt_altura.getText().toString());
                baseMaior = Double.parseDouble(edt_baseMaior.getText().toString());
                baseMenor = Double.parseDouble(edt_baseMenor.getText().toString());


               double resultado = ((baseMaior + baseMenor) * altura) / 2;

                if(edt_altura.getText().toString().isEmpty() || edt_baseMaior.getText().toString().isEmpty() || edt_baseMenor.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Preencha todos os campos!!",Toast.LENGTH_SHORT);
                }else{
                    AlertDialog.Builder janela = new AlertDialog.Builder(getContext(),R.style.dialog2);
                    janela.setTitle("Área do trapezio");
                    janela.setIcon(R.drawable.trapezio_preto_branco);

                    janela.setMessage("A área do trapezio é: " + resultado);
                    janela.setNeutralButton("ok", null);
                    janela.show();
                }
            }
        });


        return vista;
    }

    public void iniciacomponentes(View v){
        edt_altura = v.findViewById(R.id.alturaTrapezio);
        edt_baseMaior = v.findViewById(R.id.baseMaior);
        edt_baseMenor = v.findViewById(R.id.baseMenor);
        calcular = v.findViewById(R.id.calcular);


    }

}