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

public class Fragmento_triangulo extends Fragment {
    private View vista;
    private EditText edt_base, edt_altura;
    private Button calcular;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragmento_triangulo,container,false);
        vista =  inflater.inflate(R.layout.fragmento_triangulo,container,false);
        iniciacomponentes(vista);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double altura, base;

                altura = Double.parseDouble(edt_altura.getText().toString());
                base = Double.parseDouble(edt_base.getText().toString());

                double resultado = (base * altura) / 2;



                if(edt_altura.getText().toString().isEmpty() || edt_base.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Preencha todos os campos!!",Toast.LENGTH_SHORT);
                }else{
                    AlertDialog.Builder janela = new AlertDialog.Builder(getContext(),R.style.dialog1);
                    janela.setTitle("Área do triângulo");
                    janela.setIcon(R.drawable.triangulo_preto_branco);

                    janela.setMessage("A área do triangulo é: " + resultado);
                    janela.setNeutralButton("ok", null);
                    janela.show();
                }

            }
        });

        return vista;
    }

    public void iniciacomponentes(View v){
        edt_altura = v.findViewById(R.id.altura);
        edt_base = v.findViewById(R.id.base);
        calcular = v.findViewById(R.id.calcular);


    }


}