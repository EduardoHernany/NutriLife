package com.example.prova.adapters;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prova.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    List<ContentValues> lista = new ArrayList<>();

    public Adapter(List<ContentValues> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ContentValues cv = new ContentValues();
        cv = lista.get(position);

        holder.ids.setText(cv.getAsString("id"));
        //holder.ids.setAlpha(0);
        holder.resul.setText(cv.getAsString("resultado"));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ids, resul;
        private RecyclerView recyclerView;
        private Adapter adapter;

        public MyViewHolder(View itemLista) {
            super(itemLista);
            iniciarcomponentes(itemView);

            itemLista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder janela=new AlertDialog.Builder(itemLista.getContext(),R.style.dialog2);
                    janela.setTitle("NutriLife");
                    janela.setMessage("Deseja excluir este item? " );
                    janela.setNeutralButton("Não", null);
                    janela.setPositiveButton("sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            BancoControlador bd = new BancoControlador(itemLista.getContext());
                            int rem = Integer.parseInt(ids.getText().toString());
                            bd.deletarRegistro(rem);
                            Toast.makeText(itemLista.getContext(), "Item removido com sucesso!!", Toast.LENGTH_SHORT).show();

                            lista.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());


                        }
                    });
                    janela.show();
                }
            });

        }

        private void iniciarcomponentes(View itemView) {
            resul = itemView.findViewById(R.id.resultado_adap);
            ids = itemView.findViewById(R.id.id);
            recyclerView= itemView.findViewById(R.id.recycle);
        }

    }

}
