package com.demo.Weather.repos;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.demo.Weather.ApiInterface;
import com.demo.Weather.models.Root;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepo {

    private static final String WEATHER_SERVICE_BASE_URL = "https://api.weatherapi.com/";

    private ApiInterface apiInterface;
    private MutableLiveData<Root> forecastModelLiveDataLiveData;
    Root root;
    Context context;

    public WeatherRepo(Context context) {
        forecastModelLiveDataLiveData = new MutableLiveData<>();
        this.context = context;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        apiInterface = new retrofit2.Retrofit.Builder()
                .baseUrl(WEATHER_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface.class);
    }

    public void setForecastData() {
        apiInterface.getCurrentForecast("ac1763cd50fd42cd9fe131850210912", "30.030495,31.0116918", "ar",2)
                .enqueue(new Callback<Root>() {
                    @Override
                    public void onResponse(Call<Root> call, Response<Root> response) {
                        forecastModelLiveDataLiveData.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<Root> call, Throwable t) {
                        Log.i("errorApi",t.getMessage());
                    }
                });
    }

    public LiveData<Root> getForecastModelLiveDataLiveData() {
        return forecastModelLiveDataLiveData;
    }


}
