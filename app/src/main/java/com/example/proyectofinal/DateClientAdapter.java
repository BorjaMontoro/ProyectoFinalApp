package com.example.proyectofinal;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DateClientAdapter extends RecyclerView.Adapter<DateClientAdapter.ViewHolder> {
    private List<DateClient> dates;
    private Context context;

    public DateClientAdapter(Context context,List<DateClient> dates) {
        this.dates = dates;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dates, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DateClient date = dates.get(position);
        holder.serviceTextView.setText(date.getService());
        holder.companyTextView.setText(date.getCompany());
        holder.monthTextView.setText(date.getMonth());
        holder.dayTextView.setText(date.getDay());
        holder.timeTextView.setText(date.getTime());
        holder.yearTextView.setText(date.getYear());
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView serviceTextView;
        private TextView companyTextView;
        private TextView monthTextView;
        private TextView dayTextView;
        private TextView timeTextView;
        private TextView yearTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceTextView = itemView.findViewById(R.id.service_name_text_view);
            companyTextView = itemView.findViewById(R.id.company_name_text_view);
            monthTextView = itemView.findViewById(R.id.month_text_view);
            dayTextView = itemView.findViewById(R.id.day_text_view);
            timeTextView = itemView.findViewById(R.id.time_text_view);
            yearTextView = itemView.findViewById(R.id.year_text_view);
        }
    }
}

