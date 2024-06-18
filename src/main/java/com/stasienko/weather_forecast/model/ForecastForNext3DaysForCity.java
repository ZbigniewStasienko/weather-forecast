package com.stasienko.weather_forecast.model;

public class ForecastForNext3DaysForCity {

    private ForecastForNext3Days forecastForNext3Days;

    private String cityName;

    public ForecastForNext3DaysForCity(ForecastForNext3Days forecastForNext3Days, String cityName) {
        this.forecastForNext3Days = forecastForNext3Days;
        this.cityName = cityName;
    }

    public ForecastForNext3DaysForCity() {
    }

    public ForecastForNext3Days getForecastForNext3Days() {
        return forecastForNext3Days;
    }

    public void setForecastForNext3Days(ForecastForNext3Days forecastForNext3Days) {
        this.forecastForNext3Days = forecastForNext3Days;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
