package com.example.geekbrainsandroid;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder> {

    ArrayList<MainModels> mainModels;
    Context context;

    public HorizontalAdapter(Context context, ArrayList<MainModels> mainModels) {
        this.context = context;
        this.mainModels = mainModels;
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.horizontal_list, parent, false);
         return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {
        holder.hourTextView.setText(mainModels.get(position).getHour());
        holder.temperatureTextView.setText(mainModels.get(position).getTemperature());
    }

    @Override
    public int getItemCount() {
        return mainModels.size();
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder {

        TextView hourTextView;
        TextView temperatureTextView;

        public HorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            hourTextView = itemView.findViewById(R.id.time_temperature);
            temperatureTextView = itemView.findViewById(R.id.hour_temperature);
        }

    }
}
