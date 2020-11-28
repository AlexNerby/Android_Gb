package com.example.geekbrains2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DaysFragment extends Fragment {

    private static final boolean LOG = true;
    private static final String TAG = "weatherFragmentDays";

    private String[] days;
    private RecyclerView recyclerView;
    private ReplaceDaysWithHours replaceDaysWithHours;

    public interface ReplaceDaysWithHours {
        void replaceFragment2(String text);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        replaceDaysWithHours = (ReplaceDaysWithHours) getActivity();
        View view = inflater.inflate(R.layout.fragment_days, container, false);
        days = getResources().getStringArray(R.array.days);
        recyclerView = view.findViewById(R.id.day_recycler);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getActivity().getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);

        DaysAdapter adapter = new DaysAdapter(days);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new DaysAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                replaceDaysWithHours.replaceFragment2(((TextView) view).getText().toString());
            }
        });
        return view;
    }
}