package com.example.proyectofinal;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HorasAdapter extends RecyclerView.Adapter<HorasAdapter.ViewHolder> {

    private List<String> mHoras;
    private ReservarActivity activity;
    private int mPreviousPosition=0;
    private int mSelectedPosition=0;


    public HorasAdapter(List<String> horas, ReservarActivity activity) {
        mHoras = horas;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String hora = mHoras.get(position);
        holder.horaTextView.setText(hora);

        if (position == mSelectedPosition) {
            holder.horaTextView.setBackgroundResource(R.drawable.item_background_hora_selected);
            holder.horaTextView.setTextColor(Color.BLACK);
            holder.horaTextView.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            holder.horaTextView.setBackgroundResource(R.drawable.item_background_hora_no_selected);
            holder.horaTextView.setTextColor(Color.GRAY);
            holder.horaTextView.setTypeface(Typeface.DEFAULT);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreviousPosition = mSelectedPosition;
                mSelectedPosition = holder.getAdapterPosition();
                activity.guardarHoraSeleccionada(hora);
                notifyItemChanged(mPreviousPosition);
                notifyItemChanged(mSelectedPosition);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mHoras.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView horaTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            horaTextView = (TextView) itemView.findViewById(R.id.hora_disponible);
        }
    }
}

