package com.example.geekbrainsandroid;

public class MainModels {

    private String hour;
    private String temperature;
    private Integer day;

    public int getDay() {
        return day;
    }

    public MainModels(String hour, String temperature) {
        this.hour = hour;
        this.temperature = temperature;
//        this.day = day;
    }

    public MainModels(Integer day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public String getTemperature() {
        return temperature;
    }
}
