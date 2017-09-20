package com.foo.umbrella.data.remote.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.foo.umbrella.data.api.WeatherService;
import com.foo.umbrella.data.model.ResponseModel.WeatherResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class for server communication.
 */
@Singleton
public class WeatherRepository {
    private WeatherService weatherService;

    @Inject
    public WeatherRepository(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Method which return weather conditions from the server
     *
     * @return list of hourly data
     * @param zipCode
     */
    public LiveData<Response<WeatherResponse>> getWeatherResponse(String zipCode) {
        final MutableLiveData<Response<WeatherResponse>> data = new MutableLiveData<>();

        weatherService.forecastForZipCallable(zipCode).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> weatherResponse) {
                data.setValue(weatherResponse);
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
            }
        });

        return data;
    }

}
