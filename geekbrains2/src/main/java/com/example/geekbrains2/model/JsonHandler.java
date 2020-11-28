package com.example.geekbrains2.model;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.geekbrains2.BuildConfig;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class JsonHandler {


    private static final String TAG = "modelWeather";

    private static String[] arr;

    public static String[] weatherParcel(String city) {
        arr = new String[5];
        try {
            final String url = String.format(
                    "https://api.openweathermap.org/data/2.5/weather?q=%s,RU&appid=%s",
                    city, BuildConfig.WEATHER_API_KEY);
            final URL uri = new URL(url);
            final Handler handler = new Handler();

            new Thread(() -> {
            HttpsURLConnection urlConnection = null;
            try {
                urlConnection = (HttpsURLConnection) uri.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000);

                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String result = getLines(in);

                Gson gson = new Gson();
                final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);

                displayWeather(weatherRequest);

            } catch (Exception e) {
                Log.e(TAG, "Fail connection", e);
                e.printStackTrace();
                arr[0] = "Город не найден";
                for (int i = 1; i < arr.length; i++) {
                    arr[i] = "";
                }
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            }).start();
        } catch (MalformedURLException e) {
            Log.e(TAG, "Fail URI", e);
            e.printStackTrace();
            arr[0] = "Ошибка соединения";
            for (int i = 1; i < arr.length; i++) {
                arr[i] = "";
            }
        }
        return arr;
    }


    private static String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }

    private static void displayWeather(WeatherRequest weatherRequest) {
        arr[0] = weatherRequest.getName();
        float a = weatherRequest.getMain().getTemp() - 273;
        int result = Math.round(a);
        if (result >= 1) {
            arr[1] = "+" + result;
        } else {
            arr[1] = String.valueOf(result);
        }
        arr[2] = String.valueOf(weatherRequest.getMain().getPressure());
        arr[3] = String.valueOf(weatherRequest.getMain().getHumidity());
        arr[4] = String.valueOf(weatherRequest.getWind().getSpeed());
    }
}
