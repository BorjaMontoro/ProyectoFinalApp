package com.example.proyectofinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DateCompanyAdapter extends RecyclerView.Adapter<DateCompanyAdapter.ViewHolder> {

    private List<DateCompany> citas;

    public DateCompanyAdapter(List<DateCompany> citas) {
        this.citas = citas;
    }
    public void setData(List<DateCompany> nuevasCitas) {
        this.citas = nuevasCitas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dates_company, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DateCompany cita = citas.get(position);
        holder.bind(cita);
    }

    @Override
    public int getItemCount() {
        return citas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvService;
        private TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name_usu_text_view);
            tvService = itemView.findViewById(R.id.service_name_text_view);
            tvTime = itemView.findViewById(R.id.time_text_view);
        }

        public void bind(DateCompany cita) {
            tvName.setText(cita.getName());
            tvService.setText(cita.getService());
            tvTime.setText(cita.getTime());
        }
    }
}

