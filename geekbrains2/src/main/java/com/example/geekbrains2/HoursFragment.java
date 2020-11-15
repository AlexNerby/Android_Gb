package com.example.geekbrains2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HoursFragment extends Fragment {

    private static final boolean LOG = true;
    public static final String TAG = "weatherFragmentHours";

    String[] hours;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hours, container, false);
        hours = getResources().getStringArray(R.array.hours);
        recyclerView = view.findViewById(R.id.hour_recycler);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        HoursAdapter adapter = new HoursAdapter(hours);
        recyclerView.setAdapter(adapter);
        return view;
    }
}