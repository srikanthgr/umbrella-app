package com.foo.umbrella.data.model.ResponseModel;

import java.util.List;

/**
 * Created by JMI Guest on 9/19/2017.
 */

public class WeatherListResponse {

    private List<WeatherItemModel> weatherItemModelList;

    private HourlyForecast highestWeatherItemModel;

    public List<WeatherItemModel> getWeatherItemModelList() {
        return weatherItemModelList;
    }

    public void setWeatherItemModelList(List<WeatherItemModel> weatherItemModelList) {
        this.weatherItemModelList = weatherItemModelList;
    }

    public HourlyForecast getHighestWeatherItemModel() {
        return highestWeatherItemModel;
    }

    public void setHighestWeatherItemModel(HourlyForecast highestWeatherItemModel) {
        this.highestWeatherItemModel = highestWeatherItemModel;
    }

    public HourlyForecast getLowestWeatherItemModel() {
        return lowestWeatherItemModel;
    }

    public void setLowestWeatherItemModel(HourlyForecast lowestWeatherItemModel) {
        this.lowestWeatherItemModel = lowestWeatherItemModel;
    }

    private HourlyForecast lowestWeatherItemModel;

}
