
package com.foo.umbrella.data.model.ResponseModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("current_observation")
    @Expose
    private CurrentObservation currentObservation;
    @SerializedName("hourly_forecast")
    @Expose
    private List<HourlyForecast> hourlyForecast = null;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(CurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }

    public List<HourlyForecast> getHourlyForecast() {
        return hourlyForecast;
    }

    public void setHourlyForecast(List<HourlyForecast> hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

}
