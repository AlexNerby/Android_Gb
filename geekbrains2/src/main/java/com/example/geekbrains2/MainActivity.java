package com.example.geekbrains2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CityFragment.ReplaceCityWithDays
        , DaysFragment.ReplaceDaysWithHours {

    private static final boolean LOG = true;
    private static final String TAG = "weatherActivity";

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private CityFragment cityFragment;
    private DaysFragment daysFragment;
    private HoursFragment hoursFragment;
    private TextView cityMainText;
    private TextView temperatureMainText;

    private Button settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        initCityFragment();

        daysFragment = new DaysFragment();
        hoursFragment = new HoursFragment();

        cityMainText = findViewById(R.id.city_main_activity_text);
        temperatureMainText = findViewById(R.id.temperature_main_activity_text);
    }

    private void initCityFragment() {
        Log.v(TAG, "Инициализируем City фрагмент");
        cityFragment = new CityFragment();
        SettingsFragment settingsFragment = new SettingsFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, cityFragment, CityFragment.TAG);
        fragmentTransaction.addToBackStack(CityFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void replaceFragment(String city) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, daysFragment, DaysFragment.TAG);
        fragmentTransaction.addToBackStack(DaysFragment.TAG);
        fragmentTransaction.commit();

        cityMainText.setText(city);
        temperatureMainText.setText(R.string.main_temperature);
    }

    @Override
    public void replaceFragment2(String city) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, hoursFragment, HoursFragment.TAG);
        fragmentTransaction.addToBackStack(HoursFragment.TAG);
        fragmentTransaction.commit();

        temperatureMainText.setText(city);

    }
}