package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.Network.RetrofitResponseListener;
import com.example.weatherapp.Network.WeatherClient;


public class MainActivity extends AppCompatActivity implements RetrofitResponseListener {
private SharedPreferences sharedPreferences;
    private EditText city;
    private String c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView searchIcon =findViewById(R.id.search_icon);
        city=findViewById(R.id.city_edit_text);
        TextView cityTV=findViewById(R.id.city_tv_cache);
        TextView tempTV=findViewById(R.id.temp_tv_cache);
        TextView cloudTV=findViewById(R.id.cloud_tv_cache);

        MainActivity2 mainActivity2=new MainActivity2();
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        cityTV.setText(sharedPreferences.getString(mainActivity2.CITY_KEY, "null"));
        tempTV.setText(sharedPreferences.getString(mainActivity2.TEMP_KEY, "null"));
        cloudTV.setText(sharedPreferences.getString(mainActivity2.CLOUD_KEY, "null"));

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=city.getText().toString();
                WeatherClient.getINSTANCE().validData(c,MainActivity.this);

            }
        });
    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("city", c);
        startActivity(intent);
    }

    @Override
    public void onFailure() {
        city.setError("Invalid city");
    }

}