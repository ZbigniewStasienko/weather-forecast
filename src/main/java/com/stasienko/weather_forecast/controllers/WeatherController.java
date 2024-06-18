package com.stasienko.weather_forecast.controllers;

import com.stasienko.weather_forecast.services.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather/biggest_polish_cities")
    public String getWeather() {
        return weatherService.getWeatherForecast();
    }
}