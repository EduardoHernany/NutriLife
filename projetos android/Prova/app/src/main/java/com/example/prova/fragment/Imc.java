package com.example.prova.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.prova.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Imc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Imc extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Imc() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmAltaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Imc newInstance(String param1, String param2) {
        Imc produto = new Imc();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        produto.setArguments(args);
        return produto;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_produto);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View view;
    private EditText nome, peso, altura, idade;
    private Button calcular, limpar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.activity_frag_imc, container, false);

        iniciarcomponentes(view);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nome.getText().toString().isEmpty() || altura.getText().toString().isEmpty() ||
                        peso.getText().toString().isEmpty()) {

                    AlertDialog.Builder janela = new AlertDialog.Builder(getContext(), R.style.dialog2);
                    janela.setTitle("NutriLife");
                    janela.setMessage("Todos os campos devem ser preenchidos!! ");
                    janela.setNeutralButton("ok", null);
                    janela.show();

                } else {

                    String n = nome.getText().toString();
                    double p = Double.parseDouble(peso.getText().toString());
                    double a = Double.parseDouble(altura.getText().toString());
                    int i = Integer.parseInt(idade.getText().toString());


                    String c = classificacao(imc(p, a), i);

                    //String im= String.format("%.2f",imc(p,a));

                    double teste = imc(p, a);
                    DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
                    decimalFormat.setMinimumFractionDigits(2);
                    String im = decimalFormat.format(teste);

                    StringBuilder x = new StringBuilder();

                    x.append("Olá, ").append(n).append("\n");
                    x.append("Seu IMC é: ").append(im).append("\n\n");
                    x.append("A sua classificação de risco é: ").append(c);


                    String resultado = x.toString();

                    AlertDialog.Builder janela = new AlertDialog.Builder(getContext(), R.style.dialog2);
                    janela.setTitle(R.string.app_name);
                    janela.setMessage("Todos dados estao corretos?");
                    janela.setNeutralButton("não", null);
                    janela.setPositiveButton("sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            AddDialogFragment dialogFragment = new AddDialogFragment(resultado,"IMC");
                            dialogFragment.setCancelable(false);

                            dialogFragment.show(getActivity().getSupportFragmentManager(), dialogFragment.getTag());

                        }
                    });
                    janela.show();
                }
            }
        });

        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpar();
                Toast.makeText(getContext(), "Os dados apagados com sucesso!!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void limpar() {
        nome.setText("");
        idade.setText("");
        altura.setText("");
        peso.setText("");

    }

    private void iniciarcomponentes(View view) {
        nome = view.findViewById(R.id.nome_imc);
        altura = view.findViewById(R.id.altura_imc);
        peso = view.findViewById(R.id.peso_imc);
        calcular = view.findViewById(R.id.calcular_imc);
        idade = view.findViewById(R.id.idade_imc);
        limpar = view.findViewById(R.id.limpar_imc);

    }


    String classificacao(double imc, int idade) {
        String x = " ";

        if (idade < 60) {
            if (imc < 18.5) {
                x = "Magreza";
            } else if (imc >= 18.5 && imc < 25) {
                x = "Normal";
            } else if (imc >= 25 && imc < 30) {
                x = "sobrepeso";
            } else if (imc >= 30 && imc < 40) {
                x = "Obesidade Moderada";
            } else {
                x = "Obesidade Mórbida";
            }
        } else {
            if (imc < 23) {
                x = "Magreza";
            } else if (imc >= 23 && imc < 28) {
                x = "Normal";
            } else if (imc >= 28 && imc < 30) {
                x = "sobrepeso";
            } else {
                x = "Obesidade";
            }
        }

        return x;
    }

    double imc(double peso1, double altura1) {
        double imc1;

        imc1 = peso1 / (altura1 * altura1);

        return imc1;
    }
}
