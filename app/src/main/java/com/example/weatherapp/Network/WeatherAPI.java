package com.example.weatherapp.Network;

import com.example.weatherapp.Model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    @GET("data/2.5/weather")
    public Call<WeatherData> getData(
            @Query("q")String city,
            @Query("appid")String API_KEY,
            @Query ("units")String unit
    );
}
