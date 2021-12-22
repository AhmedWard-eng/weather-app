package com.demo.Weather;

import com.demo.Weather.models.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/v1/current.json")
    Call<Root> getCurrentForecast(
            @Query("key") String key,
            @Query("q") String q,
            @Query("lang") String language,
            @Query("days") int days
    );
}
