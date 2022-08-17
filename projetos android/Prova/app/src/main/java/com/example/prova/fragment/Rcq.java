package com.example.prova.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.prova.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Rcq#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Rcq extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Rcq() {
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
    public static Rcq newInstance(String param1, String param2) {
        Rcq carrinho = new Rcq();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        carrinho.setArguments(args);
        return carrinho;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private RadioGroup radioGroup;
    private Button calcular,limpar;
    private EditText editt_nome,editt_idade,editt_cintura,editt_quadril;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_frag_rcq, container, false);
        inciarcomponentes(view);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder principal=new AlertDialog.Builder(getContext(),R.style.dialog2);
                principal.setTitle("NutriLife");


                if((editt_nome.getText().toString().isEmpty())|| (radioGroup.getCheckedRadioButtonId()==-1)
                        ||editt_idade.getText().toString().isEmpty()||(editt_cintura.getText().toString().isEmpty())
                        ||(editt_quadril.getText().toString().isEmpty())){


                    principal.setMessage("Preencha todos os campos");
                    principal.setNeutralButton("ok",null);
                    principal.show();
                }else {
                    String nome = String.format(editt_nome.getText().toString());
                    int idade=Integer.parseInt(editt_idade.getText().toString());
                    double cintura=Double.parseDouble(editt_cintura.getText().toString());
                    double quadril=Double.parseDouble(editt_quadril.getText().toString());

                    double teste= rcq(cintura,quadril);
                    DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
                    decimalFormat.setMinimumFractionDigits(2);

                    String rc = decimalFormat.format(teste);

                    StringBuilder x = new StringBuilder();

                    x.append("Olá, ").append(nome).append("\n");
                    x.append("Seu RCQ é: ").append(rc).append("\n\n");
                    x.append("A sua classificação de risco é: ").append(risco(rcq(cintura,quadril),idade));


                    String resultado = x.toString();



                    principal.setMessage("Todos dados estao corretos?");
                    principal.setNegativeButton("Não",null);
                    principal.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            AddDialogFragment dialogFragment = new AddDialogFragment(resultado,"RCQ");
                            dialogFragment.setCancelable(false);

                            dialogFragment.show(getActivity().getSupportFragmentManager(),dialogFragment.getTag());

//
                        }
                    });
                    principal.show();
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

    private double rcq(double cintura,double quadril){
        return (cintura/quadril);
    }



    private String risco(double rcq, int idade) {
        String x="";
        int op = radioGroup.getCheckedRadioButtonId();
//__________________Masculino__________________________________
        if (op == R.id.radio_masculino_rcq) {
            if(idade <= 29){
                if(rcq<0.83){
                    x="Normal";
                    //
                }else if(rcq >= 0.83 && rcq< 0.89){
                    x="Moderado";
                    //
                }else if(rcq >= 0.89 && rcq< 0.95){
                    x="Alto";
                    //
                }else{
                    x="Muito Alto";
                    //
                }
                //-----------------------------------

            }else if(idade >=30 && idade <40){
                if(rcq<0.84){
                    x="Normal";
                    //
                }else if(rcq >= 0.84 && rcq< 0.92){
                    x="Moderado";
                    //
                }else if(rcq >= 0.92 && rcq< 0.97){
                    x="Alto";
                    //
                }else{
                    x="Muito Alto";
                    //
                }
                //-------------------------------------

            }else if(idade >= 40 && idade <50){
                if(rcq<0.88){
                    x="Normal";
                    //
                }else if(rcq >= 0.88 && rcq< 0.96){
                    x="Moderado";
                    //
                }else if(rcq >= 0.96 && rcq< 1.01){
                    x="Alto";
                    //
                }else{
                    x="Muito Alto";
                    //
                }
                //--------------------------------------

            }else if(idade >= 50 && idade <60){
                if(rcq<0.90){
                    x="Normal";
                    //
                }else if(rcq >= 0.90 && rcq< 0.97){
                    x="Moderado";
                    //
                }else if(rcq >= 0.97 && rcq< 1.03){
                    x="Alto";
                    //
                }else{
                    x="Muito Alto";
                    //
                }
                //---------------------------------------

            }else{
                if(rcq<0.91){
                    x="Normal";
                    //
                }else if(rcq >= 0.91 && rcq< 0.99){
                    x="Moderado";
                    //
                }else if(rcq >= 0.99 && rcq< 1.04){
                    x="Alto";
                    //
                }else{
                    x="Muito Alto";
                    //
                }
                //---------------------------------------

            }

//__________________Feminino__________________________________
        }else if (op == R.id.radio_feminino_rcq) {

            if(idade <= 29){
                if(rcq<0.71){
                    x="Normal";
                    //
                }else if(rcq >= 0.71 && rcq< 0.78){
                    x="Moderado";
                    //
                }else if(rcq >= 0.78 && rcq< 0.83){
                    x="Alto";
                    //
                }else{
                    x="Muito Alto";
                    //
                }
                //-----------------------------------

            }else if(idade >=30 && idade <40){
                if(rcq<0.72){
                    x="Normal";
                    //
                }else if(rcq >= 0.72 && rcq< 0.79){
                    x="Moderado";
                    //
                }else if(rcq >= 0.79 && rcq< 0.86){
                    x="Alto";
                    //
                }else{
                    x="Muito Alto";
                    //
                }
                //-------------------------------------

            }else if(idade >= 40 && idade <50){
                if(rcq<0.73){
                    x="Normal";
                    //
                }else if(rcq >= 0.73 && rcq< 0.80){
                    x="Moderado";
                    //
                }else if(rcq >= 0.80 && rcq< 0.88){
                    x="Alto";
                    //
                }else{
                    x="Muito Alto";
                    //
                }
                //--------------------------------------

            }else if(idade >= 50 && idade <60){
                if(rcq<0.74){
                    x="Normal";
                    //
                }else if(rcq >= 0.74 && rcq< 0.82){
                    x="Moderado";
                    //
                }else if(rcq >= 0.82 && rcq< 0.89){
                    x="Alto";
                    //
                }else{
                    x="Muito Alto";
                    //
                }
                //---------------------------------------

            }else{
                if(rcq<0.76){
                    x="Normal";
                    //
                }else if(rcq >= 0.76 && rcq< 0.84){
                    x="Moderado";
                    //
                }else if(rcq >= 0.84 && rcq< 0.91){
                    x="Alto";
                    //
                }else{
                    x="Muito Alto";
                    //
                }
                //---------------------------------------

            }

        }



        return x;
    }

    private void limpar() {
        editt_nome.setText("");
        editt_idade.setText("");
        editt_cintura.setText("");
        editt_quadril.setText("");
        radioGroup.clearCheck();
    }

    private void inciarcomponentes(View view) {
        editt_nome=view.findViewById(R.id.nome_rcq);
        editt_idade=view.findViewById(R.id.idade_rcq);
        editt_cintura=view.findViewById(R.id.cintura_rcq);
        editt_quadril=view.findViewById(R.id.quadril_rcq);
        radioGroup=view.findViewById(R.id.radioGroup);
        calcular=view.findViewById(R.id.calcular_rcq);
        limpar=view.findViewById(R.id.limpar_rcq);
    }
}