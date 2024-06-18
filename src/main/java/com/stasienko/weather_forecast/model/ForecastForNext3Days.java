package com.stasienko.weather_forecast.model;

public class ForecastForNext3Days {

    private ForecastForADay day1;
    private ForecastForADay day2;
    private ForecastForADay day3;

    public ForecastForNext3Days(ForecastForADay day1, ForecastForADay day3, ForecastForADay day2) {
        this.day1 = day1;
        this.day3 = day3;
        this.day2 = day2;
    }

    public ForecastForNext3Days() {
    }

    public ForecastForADay getDay1() {
        return day1;
    }

    public void setDay1(ForecastForADay day1) {
        this.day1 = day1;
    }

    public ForecastForADay getDay2() {
        return day2;
    }

    public void setDay2(ForecastForADay day2) {
        this.day2 = day2;
    }

    public ForecastForADay getDay3() {
        return day3;
    }

    public void setDay3(ForecastForADay day3) {
        this.day3 = day3;
    }
}

