package com.example.geekbrains2;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.geekbrains2.model.JsonHandler;

public class MainActivity extends BaseActivity implements CityFragment.ReplaceCityWithDays
        , DaysFragment.ReplaceDaysWithHours {

    private static final int SETTING_CODE = 88;
    public static final String KEY_BUNDLE = "keyBundle";

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
    private ProgressBar progressBar;

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
        progressBar = findViewById(R.id.progressBar);

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
        fragmentTransaction.add(R.id.container, cityFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void replaceFragment(String[] arr) {

        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                progressBar.setVisibility(View.INVISIBLE);
                cityMainText.setText(arr[0]);
                temperatureMainText.setText(arr[1]);

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, daysFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }, 2000);

        Bundle bundle = new Bundle();
        bundle.putString(KEY_BUNDLE, arr[0]);
        cityFragment.setArguments(bundle);
    }

    @Override
    public void replaceFragment2(String city) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, hoursFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        temperatureMainText.setText(city);
    }
}