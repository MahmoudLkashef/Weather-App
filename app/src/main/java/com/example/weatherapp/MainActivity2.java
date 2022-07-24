package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StringLoader;
import com.example.weatherapp.Model.WeatherData;
import com.example.weatherapp.Network.RetrofitResponse;
import com.example.weatherapp.Network.WeatherClient;

import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity implements RetrofitResponse {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public final String CITY_KEY = "city";
    public final String TEMP_KEY = "temp";
    public final String CLOUD_KEY = "CLOUD";

    TextView cityTV;
    TextView tempTV;
    TextView weatherTV;
    TextView humidityTV;
    TextView windSpeedTV;


    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        city = intent.getStringExtra("city");

        ImageView temp = findViewById(R.id.temp);
        ImageView weather = findViewById(R.id.weather);
        ImageView humidity = findViewById(R.id.humidity);
        ImageView windSpeed = findViewById(R.id.wind);

        cityTV = findViewById(R.id.city_tv);
        tempTV = findViewById(R.id.temp_tv);
        weatherTV = findViewById(R.id.weather_tv);
        humidityTV = findViewById(R.id.humidity_tv);
        windSpeedTV = findViewById(R.id.wind_speed_tv);


        Glide.with(this).load(R.drawable.temp).into(temp);
        Glide.with(this).load(R.drawable.cloud_gif).into(weather);
        Glide.with(this).load(R.drawable.humidity).into(humidity);
        Glide.with(this).load(R.drawable.wind).into(windSpeed);

        WeatherClient.getINSTANCE().getData(city, MainActivity2.this);
    }

    @Override
    public void WeatherData(Response<WeatherData> response) {
        cityTV.setText(response.body().getName());
        tempTV.setText(String.valueOf(response.body().getMain().getTemp()));
        weatherTV.setText(response.body().getWeather().get(0).getDescription());
        humidityTV.setText(String.valueOf(response.body().getMain().getHumidity()));
        windSpeedTV.setText(String.valueOf(response.body().getWind().getSpeed()));

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity2.this);
        editor = sharedPreferences.edit();

        editor.putString(CITY_KEY, response.body().getName());
        editor.putString(TEMP_KEY, String.valueOf(response.body().getMain().getTemp()));
        editor.putString(CLOUD_KEY, response.body().getWeather().get(0).getMain());
        editor.apply();
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(MainActivity2.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}