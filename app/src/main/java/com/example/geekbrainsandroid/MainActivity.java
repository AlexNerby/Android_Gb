package com.example.geekbrainsandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final boolean LOG = true;
    private static final String TAG = "weatherActionPage";

    private ImageView rainMain, alarm;
    RecyclerView horizontalListHour, verticalListDay;
    HorizontalAdapter horizontalAdapter;
    ArrayList<MainModels> mainModels;
    ArrayList<MainModels> mainModelsVert;
    String[] hours;
    String[] temperature;
    MainModels model;
    MainModels modelVert;
    VerticalAdapter verticalAdapter;
    Integer[] days;
    TextView cityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initHourList();
        initDayList();
    }

    private void initHourList() {
        if (LOG) {
            Log.d(TAG, "init in method initHoursList");
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this
                , RecyclerView.HORIZONTAL, false);
        horizontalListHour.setLayoutManager(layoutManager);
        horizontalListHour.setItemAnimator(new DefaultItemAnimator());
//        horizontalListHour.setHasFixedSize(true);
        horizontalAdapter = new HorizontalAdapter(MainActivity.this, mainModels);
        horizontalListHour.setAdapter(horizontalAdapter);
    }

    private void initDayList() {
        if (LOG) {
            Log.d(TAG, "init in method initDayList");
        }
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(MainActivity.this
                , RecyclerView.VERTICAL, false);
        verticalListDay.setLayoutManager(layoutManager2);
        verticalListDay.setItemAnimator(new DefaultItemAnimator());

        verticalAdapter = new VerticalAdapter(MainActivity.this, mainModelsVert);
        verticalListDay.setAdapter(verticalAdapter);
    }

    private void init() {
        if (LOG) {
            Log.d(TAG, "init variable in method init");
        }
        cityMain = findViewById(R.id.city_main);
        cityMain.setText(getIntent().getStringExtra("city"));
        rainMain = findViewById(R.id.rain_main);
        alarm = findViewById(R.id.imageView2);
        rainMain.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_rain));
        alarm.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_alarm));
        horizontalListHour = findViewById(R.id.list_hour);
        verticalListDay = findViewById(R.id.vertical_list_day);

        if (LOG) {
            Log.d(TAG, "init arrays in method init");
        }
        days = new Integer[]{R.string.today, R.string.tomorrow, R.string.oct_27, R.string.oct_28
                , R.string.oct_29, R.string.oct_30, R.string.oct_31, R.string.nov_1, R.string.nov_2
                , R.string.nov_3};

        hours = new String[]{"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00"
                , "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00"
                , "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"
                , "21:00", "22:00", "23:00"};

        temperature = new String[]{"+4", "+4", "+3", "+3", "+2", "+2", "+2", "+3"
                , "+3", "+4", "+4", "+5", "+5", "+6", "+6", "+7", "+7", "+6", "+6"
                , "+5", "+5", "+5", "+4", "+4"};

        mainModels = new ArrayList<>();
        for (int i = 0; i < hours.length; i++) {
            model = new MainModels(hours[i], temperature[i]);
            mainModels.add(model);
        }
        mainModelsVert = new ArrayList<>();
        for (int i = 0; i < days.length; i++) {
            modelVert = new MainModels(days[i]);
            mainModelsVert.add(i, modelVert);
        }
    }
}