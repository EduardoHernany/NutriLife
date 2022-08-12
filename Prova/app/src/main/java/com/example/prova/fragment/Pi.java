package com.example.prova.fragment;

import android.content.DialogInterface;
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
 * Use the {@link Pi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pi extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Pi() {
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
    public static Pi newInstance(String param1, String param2) {
        Pi perfil = new Pi();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        perfil.setArguments(args);
        return perfil;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View view;
    private EditText nome_edt,altura_edt,idade_edt;
    private RadioGroup radioGroup;

    private Button calcular,limpar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.activity_frag_pi, container, false);
        iniciarcomponentes(view);

        AlertDialog.Builder principal=new AlertDialog.Builder(getContext(),R.style.dialog2);

        principal.setTitle("NutriLife");

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((nome_edt.getText().toString().isEmpty())|| (radioGroup.getCheckedRadioButtonId()==-1)
                        ||idade_edt.getText().toString().isEmpty()||(altura_edt.getText().toString().isEmpty())){

                    principal.setMessage("Preencha todos os campos");
                    principal.setNeutralButton("ok",null);
                    principal.show();

                }else{
                    String nome = String.format(nome_edt.getText().toString());
                    int idade=Integer.parseInt(idade_edt.getText().toString());
                    double altura=Double.parseDouble(altura_edt.getText().toString());

                    double p = pesoideal(altura);

                    DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
                    decimalFormat.setMinimumFractionDigits(2);
                    String pi = decimalFormat.format(p);

                    StringBuilder x = new StringBuilder();

                    x.append("Olá, ").append(nome).append("\n");
                    x.append("Seu peso ideal é: ").append(pi);
                    String resultado = x.toString();

                    principal.setMessage("Todos dados estao corretos?");
                    principal.setNegativeButton("Não",null);
                    principal.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            AddDialogFragment dialogFragment = new AddDialogFragment(resultado,"PI");
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
    private void limpar() {
        nome_edt.setText("");
        idade_edt.setText("");
        altura_edt.setText("");
        radioGroup.clearCheck();

    }

    private double pesoideal(double altura) {
        double imc;
        int op = radioGroup.getCheckedRadioButtonId();

        if (op == R.id.radio_masculino_rcq) {
            imc= 22;
            return ((altura*altura)*imc);

        }else{
            imc= 21;
            return ((altura*altura)*imc);

        }
    }

    private void iniciarcomponentes(View view) {
        nome_edt = view.findViewById(R.id.nome_pi);
        altura_edt= view.findViewById(R.id.altura_pi);
        idade_edt= view.findViewById(R.id.idade_pi);
        calcular= view.findViewById(R.id.calcular_pi);
        limpar= view.findViewById(R.id.limpar_pi);
        radioGroup= view.findViewById(R.id.radioGroup);

    }


}