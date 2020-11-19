package com.example.geekbrains2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CityFragment extends Fragment {

    private static final boolean LOG = true;
    public static final String TAG = "weatherFragmentCity";

    private final Pattern checkAddCity = Pattern.compile("^[A-Z][a-z]{2,}$");
    private ReplaceCityWithDays replaceCityWithDays;
    private Context context;
    private TextInputEditText addCity;
    private Button buttonOk;
    private String value;
    private List<String> citys;
    private String[] getResourceArray;
    private RecyclerView recyclerView;
    private String key = "key";

    public interface ReplaceCityWithDays {
        void replaceFragment(String city);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_city, container, false);
        buttonOk = view.findViewById(R.id.button_city_apply);
        addCity = view.findViewById(R.id.inputCityName);
        context = getActivity();
        recyclerView = view.findViewById(R.id.city_recycler);
        replaceCityWithDays = (ReplaceCityWithDays) context;
        getResourceArray = getResources().getStringArray(R.array.citys);
        citys = new ArrayList<>(Arrays.asList(getResourceArray));

        Bundle bundle = getArguments();
        if (bundle != null) {
            citys.add(bundle.getString(MainActivity.TAG));
        }
        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(context, LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(context.getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);

        CityAdapter adapter = new CityAdapter(citys);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new CityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Snackbar.make(view, getResources().getString(R.string.qvest), Snackbar.LENGTH_LONG)
                        .setAction(R.string.yes, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(context, getResources().getString(R.string.snackbar_on), Toast.LENGTH_LONG).show();
                                replaceCityWithDays.replaceFragment(((TextView) view).getText().toString());
                            }
                        }).show();
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick button");
                TextView tv = (TextView) addCity;
                validate(tv, checkAddCity, citys);
                Snackbar.make(view, getResources().getString(R.string.qvest), Snackbar.LENGTH_LONG)
                        .setAction(R.string.yes, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(context, getResources().getString(R.string.snackbar_on), Toast.LENGTH_LONG).show();
                                replaceCityWithDays.replaceFragment(value);
                            }
                        }).show();
            }
        });
        return view;
    }

    private void validate(TextView tv, Pattern check, List<String> cites) {
        Log.d(TAG, "validate");
        value = tv.getText().toString();
        if (check.matcher(value).matches()) {
            tv.setError(null);
            cites.add(value);
        } else {
            tv.setError(getResources().getString(R.string.incorrect_city_name));
        }
    }
}