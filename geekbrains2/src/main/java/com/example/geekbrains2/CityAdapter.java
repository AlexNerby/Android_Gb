package com.example.geekbrains2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private static final boolean LOG = true;
    private static final String TAG = "weatherCityAdapter";

    private String[] city;
    private OnItemClickListener itemClickListener;  // Слушатель будет устанавливаться извне

    public CityAdapter(String[] city) {
        Log.d(TAG, "Создаем конструктор CityAdapter и вносим массив в адаптер");
        this.city = city;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_element_recycler, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        holder.textView.setText(city[position]);

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount");
        return city.length;
    }

    // Интерфейс для обработки нажатий как в ListView
    public interface OnItemClickListener {
        void onItemClick(View view);
    }

    // Сеттер слушателя нажатий
    public void SetOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "создаем конструктор ViewHolder и инициализируем textView");
            textView = itemView.findViewById(R.id.city_element);

            // Обработчик нажатий на этом ViewHolder
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v);
                    }
                }
            });

        }
    }
}
