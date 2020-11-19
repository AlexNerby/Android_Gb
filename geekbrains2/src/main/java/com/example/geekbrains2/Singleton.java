package com.example.geekbrains2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Singleton implements Parcelable {

    private String city;

    protected Singleton(Parcel in) {
        city = in.readString();
    }

    public static final Creator<Singleton> CREATOR = new Creator<Singleton>() {
        @Override
        public Singleton createFromParcel(Parcel in) {
            String city = in.readString();
            return new Singleton(city);
        }

        @Override
        public Singleton[] newArray(int size) {
            return new Singleton[size];
        }
    };

    public Singleton(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(city);
    }
}
