package com.example.geekbrains2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CityFragment.ReplaceCityWithDays
        , DaysFragment.ReplaceDaysWithHours {

    private static final boolean LOG = true;
    private static final String TAG = "weatherActivity";

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    CityFragment cityFragment;
    DaysFragment daysFragment;
    HoursFragment hoursFragment;
    TextView cityMain;
    TextView temperatureMain;

    Button settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Инициализируем City фрагмент");
        cityFragment = new CityFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, cityFragment, CityFragment.TAG);
        fragmentTransaction.addToBackStack(CityFragment.TAG);
        fragmentTransaction.commit();

        daysFragment = new DaysFragment();
        hoursFragment = new HoursFragment();

        cityMain = findViewById(R.id.city_main_activity_text);
        temperatureMain = findViewById(R.id.temperature_main_activity_text);
    }

    @Override
    public void replaceFragment(String city) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, daysFragment, DaysFragment.TAG);
        fragmentTransaction.addToBackStack(DaysFragment.TAG);
        fragmentTransaction.commit();

        cityMain.setText(city);
        temperatureMain.setText(R.string.main_temperature);
    }

    @Override
    public void replaceFragment2(String city) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, hoursFragment, HoursFragment.TAG);
        fragmentTransaction.addToBackStack(HoursFragment.TAG);
        fragmentTransaction.commit();

        temperatureMain.setText(city);

    }
}