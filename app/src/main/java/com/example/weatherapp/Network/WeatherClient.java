package com.example.weatherapp.Network;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.Model.WeatherData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherClient {
    private static final String BASE_URL="https://api.openweathermap.org/";
    private WeatherAPI weatherAPI;
    private static WeatherClient INSTANCE;

    private WeatherClient() {
        Retrofit retrofit=new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        weatherAPI=retrofit.create(WeatherAPI.class);
    }

    public static WeatherClient getINSTANCE() {
        if(INSTANCE==null)INSTANCE=new WeatherClient();
        return INSTANCE;
    }
    public void validData(String city,RetrofitResponseListener responseListener)
    {
        weatherAPI.getData(city,"API_KEY","metric").enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if(response.isSuccessful()){responseListener.onSuccess();}

                else responseListener.onFailure();
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                responseListener.onFailure();
            }
        });
    }
    public void getData(String city, RetrofitResponse retrofitResponse)
    {
        weatherAPI.getData(city,"API_KEY","metric").enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if(response.isSuccessful())retrofitResponse.WeatherData(response);

            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {

            }
        });
    }


}
