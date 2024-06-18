package com.stasienko.weather_forecast.model;

import com.stasienko.weather_forecast.services.Day;

public class ForecastForADay {

    private double maxTempCelsius;
    private double minTempCelsius;
    private double avgTempCelsius;
    private double maxWind;
    private double totalPrecipitation;
    private double totalSnow;
    private double avgVisibility;
    private int avgHumidity;
    private double uv;
    private String date;

    public ForecastForADay(Day day, String date) {
        maxTempCelsius = day.getMaxtemp_c();
        minTempCelsius = day.getMintemp_c();
        avgTempCelsius = day.getAvgtemp_c();
        maxWind = day.getMaxwind_kph();
        totalPrecipitation = day.getTotalprecip_mm();
        totalSnow = day.getTotalsnow_cm();
        avgVisibility = day.getAvgvis_km();
        avgHumidity = day.getAvghumidity();
        uv = day.getUv();
        this.date = date;
    }

    public ForecastForADay(){

    }

    public double getMaxTempCelsius() {
        return maxTempCelsius;
    }

    public void setMaxTempCelsius(double maxTempCelsius) {
        this.maxTempCelsius = maxTempCelsius;
    }

    public double getMinTempCelsius() {
        return minTempCelsius;
    }

    public void setMinTempCelsius(double minTempCelsius) {
        this.minTempCelsius = minTempCelsius;
    }

    public double getAvgTempCelsius() {
        return avgTempCelsius;
    }

    public void setAvgTempCelsius(double avgTempCelsius) {
        this.avgTempCelsius = avgTempCelsius;
    }

    public double getMaxWind() {
        return maxWind;
    }

    public void setMaxWind(double maxWind) {
        this.maxWind = maxWind;
    }

    public double getTotalPrecipitation() {
        return totalPrecipitation;
    }

    public void setTotalPrecipitation(double totalPrecipitation) {
        this.totalPrecipitation = totalPrecipitation;
    }

    public double getTotalSnow() {
        return totalSnow;
    }

    public void setTotalSnow(double totalSnow) {
        this.totalSnow = totalSnow;
    }

    public double getAvgVisibility() {
        return avgVisibility;
    }

    public void setAvgVisibility(double avgVisibility) {
        this.avgVisibility = avgVisibility;
    }

    public int getAvgHumidity() {
        return avgHumidity;
    }

    public void setAvgHumidity(int avgHumidity) {
        this.avgHumidity = avgHumidity;
    }

    public double getUv() {
        return uv;
    }

    public void setUv(double uv) {
        this.uv = uv;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
