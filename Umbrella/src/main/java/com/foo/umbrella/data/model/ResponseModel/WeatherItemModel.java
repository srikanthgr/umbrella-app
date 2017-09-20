package com.foo.umbrella.data.model.ResponseModel;

/**
 * Created by JMI Guest on 9/19/2017.
 */

public class WeatherItemModel implements DisplayableItem {

    private String time;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    private String condition;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    private boolean isHighestTemp;

    private boolean isLowestTemp;

    public boolean isHighestTemp() {
        return isHighestTemp;
    }

    public void setHighestTemp(boolean highestTemp) {
        isHighestTemp = highestTemp;
    }

    public boolean isLowestTemp() {
        return isLowestTemp;
    }

    public void setLowestTemp(boolean lowestTemp) {
        isLowestTemp = lowestTemp;
    }

    public String getHighestTemp() {
        return highestTemp;
    }

    public void setHighestTemp(String highestTemp) {
        this.highestTemp = highestTemp;
    }

    public String getLowestTemp() {
        return lowestTemp;
    }

    public void setLowestTemp(String lowestTemp) {
        this.lowestTemp = lowestTemp;
    }

    public boolean isHotTint() {
        return hotTint;
    }

    public void setHotTint(boolean hotTint) {
        this.hotTint = hotTint;
    }

    public boolean isCoolTint() {
        return coolTint;
    }

    public void setCoolTint(boolean coolTint) {
        this.coolTint = coolTint;
    }

    public boolean isDefaultTint() {
        return defaultTint;
    }

    public void setDefaultTint(boolean defaultTint) {
        this.defaultTint = defaultTint;
    }

    private String metric;

    private String english;

    private String highestTemp;

    public String getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(String fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    private String lowestTemp;

    private boolean hotTint;

    private boolean coolTint;

    private boolean defaultTint;

    private String fahrenheit;

}
