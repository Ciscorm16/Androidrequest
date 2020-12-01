package com.example.trabajofinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorPersona extends RecyclerView.Adapter<AdaptadorPersona.MiHolder>
{
    private List<Personas> ListaPersonas;

    public AdaptadorPersona(List<Personas> listaPersonas)
    {
        this.ListaPersonas = listaPersonas;
    }

    @NonNull
    @Override
    public MiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona, parent,false);
        return new MiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersona.MiHolder holder, int position) {
        Personas person = ListaPersonas.get(position);
        holder.setData(person);
    }

    @Override
    public int getItemCount() {
        return ListaPersonas.size();
    }

    public class MiHolder extends RecyclerView.ViewHolder {

        private TextView id;
        private TextView nombre;
        private TextView numero;
        private TextView creado;
        private TextView modificado;

        public MiHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.ID);
            nombre = itemView.findViewById(R.id.nombre);
            numero = itemView.findViewById(R.id.numero);
            creado = itemView.findViewById(R.id.creado);
            modificado = itemView.findViewById(R.id.editado);
        }

        public void setData(final Personas person) {

            id.setText(person.getId());
            nombre.setText(person.getNombre());
            numero.setText(person.getNumero());
            creado.setText(person.getCreated_at());
            modificado.setText(person.getUpdated_at());
        }


    }
}
