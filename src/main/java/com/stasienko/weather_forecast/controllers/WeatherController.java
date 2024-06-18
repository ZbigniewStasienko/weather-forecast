package com.stasienko.weather_forecast.controllers;

import com.stasienko.weather_forecast.model.ForecastForNext3DaysForCity;
import com.stasienko.weather_forecast.services.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather/biggest_polish_cities")
    public List<ForecastForNext3DaysForCity> getWeather() {
        return weatherService.getWeatherForecast();
    }
}