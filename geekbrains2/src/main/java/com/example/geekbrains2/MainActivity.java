package com.example.geekbrains2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements CityFragment.ReplaceCityWithDays
        , DaysFragment.ReplaceDaysWithHours {

    private static final int SETTING_CODE = 88;

    private static final boolean LOG = true;
    public static final String TAG = "weatherActivity";

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
        settings = findViewById(R.id.settings);

        settings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivityForResult(intent, SETTING_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_CODE) {
            recreate();
        }
    }

    private void initCityFragment() {
        Log.v(TAG, "Инициализируем City фрагмент");
        cityFragment = new CityFragment();
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

        Bundle bundle = new Bundle();
        bundle.putString(TAG, city);
        cityFragment.setArguments(bundle);
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