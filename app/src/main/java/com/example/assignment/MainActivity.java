package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.adapter.HourAdapter;
import com.example.assignment.model.Wheather;
import com.example.assignment.network.ApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvHour;
    private TextView tvTem;
    private TextView tvStatus;
    private Retrofit retrofit;
    private ApiManager apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiManager = retrofit.create(ApiManager.class);

        tvTem = (TextView) findViewById(R.id.tvTem);
        tvStatus = (TextView) findViewById(R.id.tvStatus);

        getHours();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rvHour = (RecyclerView) findViewById(R.id.rvHour);
        rvHour.setLayoutManager(layoutManager);
    }

    private void getHours() {

        apiManager.getHour().enqueue(new Callback<List<Wheather>>() {
            @Override
            public void onResponse(@NonNull Call<List<Wheather>> call, @NonNull Response<List<Wheather>> response) {
                if (response.body() == null) return;
                List<Wheather> weatherList = response.body();
                HourAdapter hourAdapter = new HourAdapter(MainActivity.this, weatherList);
                rvHour.setHasFixedSize(true);
                rvHour.setAdapter(hourAdapter);

                Wheather weather = weatherList.get(0);
                tvTem.setText(weather.getTemperature().getValue().intValue() + "°");
                tvStatus.setText(weather.getIconPhrase());
            }

            @Override
            public void onFailure(Call<List<Wheather>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}