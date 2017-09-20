package com.foo.umbrella.data.api;

import com.foo.umbrella.BuildConfig;
import com.foo.umbrella.constants.ApiConstants;
import com.foo.umbrella.data.model.ResponseModel.WeatherResponse;
import com.foo.umbrella.data.model.WeatherData;
import retrofit2.Call;
import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Retrofit interface for fetching weather data
 */
public interface WeatherService {

    /**
     * Get the forecast for a given zip code using {@link Call}
     */
    @GET("/api/" + ApiConstants.TOKEN + "/conditions/hourly/q/{zip}.json")
    Call<WeatherResponse> forecastForZipCallable(@Path("zip") String zipCode);

    /**
     * Get the forecast for a given zip code using {@link Observable}
     */
    @GET("/api/" + BuildConfig.API_KEY + "/conditions/hourly/q/{zip}.json")
    Observable<Result<WeatherResponse>> forecastForZipObservable(@Path("zip") String zipCode);
}
