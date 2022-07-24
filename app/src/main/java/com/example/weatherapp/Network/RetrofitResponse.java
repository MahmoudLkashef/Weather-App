package com.example.weatherapp.Network;

import com.example.weatherapp.Model.WeatherData;

import retrofit2.Response;

public interface RetrofitResponse {
    void WeatherData(Response<WeatherData> response);
}
