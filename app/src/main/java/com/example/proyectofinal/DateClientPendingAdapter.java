package com.example.proyectofinal;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DateClientPendingAdapter extends RecyclerView.Adapter<DateClientPendingAdapter.ViewHolder> {
    private List<DateClient> dates;
    private Context context;

    public DateClientPendingAdapter(Context context, List<DateClient> dates) {
        this.dates = dates;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dates_pending, parent, false);
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

        holder.deleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DateClient date = dates.get(holder.getAdapterPosition());
                dialog(date.getId());
            }
        });
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
        private TextView deleteTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceTextView = itemView.findViewById(R.id.service_name_text_view);
            companyTextView = itemView.findViewById(R.id.company_name_text_view);
            monthTextView = itemView.findViewById(R.id.month_text_view);
            dayTextView = itemView.findViewById(R.id.day_text_view);
            timeTextView = itemView.findViewById(R.id.time_text_view);
            yearTextView = itemView.findViewById(R.id.year_text_view);
            deleteTextView = itemView.findViewById(R.id.delete_text_view);
        }
    }
    private void dialog(String id) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setTitle("Cancelar cita");
                alerta.setMessage("¿Estás seguro de que quieres eliminar esta cita?");
                alerta.setPositiveButton("Si" ,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            JSONObject obj = new JSONObject("{}");
                            obj.put("id",id);
                            UtilsHTTP.sendPOST("https://proyectofinal-production-e1d3.up.railway.app:443/delete_date", obj.toString(), (response) -> {
                                /*try {
                                    JSONObject obj2 = new JSONObject(response);
                                    if (obj2.getString("status").equals("OK")) {
                                        dialog(obj2.getString("status"),obj2.getString("message"));

                                    } else if (obj2.getString("status").equals("ERROR")) {
                                        dialog(obj2.getString("status"),obj2.getString("message"));

                                    }
                                } catch (JSONException e) {
                                    System.out.println();
                                }*/
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                alerta.setNegativeButton("No" ,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }
        });
    }
}

