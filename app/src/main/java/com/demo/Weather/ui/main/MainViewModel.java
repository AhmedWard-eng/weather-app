package com.demo.Weather.ui.main;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.demo.Weather.models.Root;
import com.demo.Weather.repos.WeatherRepo;

public class MainViewModel extends ViewModel {
    WeatherRepo weatherRepo;

    public void init(Context context){
        weatherRepo = new WeatherRepo(context);
    }
    public  void setForecastData(){
        weatherRepo.setForecastData();
    }
    public LiveData<Root> getForecastLiveData(){
        return weatherRepo.getForecastModelLiveDataLiveData();
    }
}