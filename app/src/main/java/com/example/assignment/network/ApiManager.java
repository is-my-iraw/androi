package com.example.assignment.network;

import com.example.assignment.model.Wheather;

import java.util.List;
import java.util.WeakHashMap;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {
    String SERVER_URL = "https://dataservice.accuweather.com";

    @GET("/forecasts/v1/hourly/12hour/353412?apikey=XBGlVsfz5vaBfxkzXjLrGPbHVoF82TuN&language=vi-vn&metric=true")
    Call<List<Wheather>> getHour();

    @GET("/forecasts/v1/daily/5day/353412?apikey=XBGlVsfz5vaBfxkzXjLrGPbHVoF82TuN&language=vi-vn&metric=true")
    Call<List<Wheather>> getDay();
}
