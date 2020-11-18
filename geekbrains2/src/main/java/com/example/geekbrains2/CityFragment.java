package com.example.geekbrains2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CityFragment extends Fragment {

    private static final boolean LOG = true;
    public static final String TAG = "weatherFragmentCity";

    private ReplaceCityWithDays replaceCityWithDays;
    private Context context;

    public interface ReplaceCityWithDays{
        void replaceFragment(String city);
    }

    private String[] citys;
    private RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city, container, false);
        context = getActivity();
        replaceCityWithDays = (ReplaceCityWithDays) context;
        citys = getResources().getStringArray(R.array.citys);
        recyclerView = view.findViewById(R.id.city_recycler);

        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);

        // Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(context,  LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(context.getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);
        // Установим адаптер
        CityAdapter adapter = new CityAdapter(citys);
        recyclerView.setAdapter(adapter);



        // Установим слушателя
        adapter.SetOnItemClickListener(new CityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                replaceCityWithDays.replaceFragment(((TextView) view).getText().toString());
            }
        });
        return view;
    }
}