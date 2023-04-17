package com.example.proyectofinal;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServiciosAdapter extends RecyclerView.Adapter<ServiciosAdapter.ServiciosViewHolder> {

    private List<Servicio> listaServicios;
    private String empresa;

    public ServiciosAdapter(List<Servicio> listaServicios,String empresa) {
        this.listaServicios = listaServicios;
        this.empresa=empresa;
    }

    @Override
    public ServiciosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicio, parent, false);
        return new ServiciosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServiciosViewHolder holder, int position) {
        Servicio servicio = listaServicios.get(position);
        holder.nombreServicio.setText(servicio.getNombre());
        holder.precioServicio.setText(servicio.getPrecio());
        holder.duracionServicio.setText(servicio.getDuracion());
        holder.botonReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReservarActivity.class);
                intent.putExtra("servicio", servicio);
                intent.putExtra("empresa",empresa);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaServicios.size();
    }

    public static class ServiciosViewHolder extends RecyclerView.ViewHolder {
        TextView nombreServicio;
        TextView precioServicio;
        TextView duracionServicio;
        Button botonReservar;

        public ServiciosViewHolder(View itemView) {
            super(itemView);
            nombreServicio = itemView.findViewById(R.id.nombre_servicio);
            precioServicio = itemView.findViewById(R.id.precio_servicio);
            duracionServicio = itemView.findViewById(R.id.duracion_servicio);
            botonReservar = itemView.findViewById(R.id.boton_reservar);
        }
    }
}

