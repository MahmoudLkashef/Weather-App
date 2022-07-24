package com.example.weatherapp.Network;

import com.example.weatherapp.Model.WeatherData;

import retrofit2.Response;

public interface RetrofitResponseListener {
    void onSuccess();
    void onFailure();

}
