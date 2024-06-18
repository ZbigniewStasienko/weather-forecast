package com.stasienko.weather_forecast.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stasienko.weather_forecast.model.ForecastForADay;
import com.stasienko.weather_forecast.model.ForecastForNext3Days;
import com.stasienko.weather_forecast.model.ForecastForNext3DaysForCity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class WeatherService {

    @Value("${weatherapi.url}")
    private String baseUrl;

    @Value("${weatherapi.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private static final List<String> biggestPolishCities = List.of(new String[]{"Warsaw", "Cracow", "Wroclaw", "Lodz", "Poznan"});

    private static final int days = 4;

    public WeatherService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String getWeatherForecastForSingleCity(String city) {
        try {
            WeatherAPIResponse weatherAPIResponse = getWeatherForecastForCity(city);
            ForecastForNext3DaysForCity forecastForNext3DaysForCity = convertFromWeatherApiDataToOutputModel(weatherAPIResponse);
            return objectMapper.writeValueAsString(forecastForNext3DaysForCity);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    public String getWeatherForecast(){
        StringBuilder result = new StringBuilder();
        for(String city : biggestPolishCities){
            result.append(getWeatherForecastForSingleCity(city));
        }
        return result.toString();
    }

    private WeatherAPIResponse getWeatherForecastForCity(String city) {

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/forecast.json")
                .queryParam("key", apiKey)
                .queryParam("q", city)
                .queryParam("days", WeatherService.days)
                .toUriString();

        return restTemplate.getForEntity(urlTemplate, WeatherAPIResponse.class).getBody();
    }

    private ForecastForNext3DaysForCity convertFromWeatherApiDataToOutputModel(WeatherAPIResponse weatherAPIResponse) {
        ForecastForNext3Days forecastForNext3Days = new ForecastForNext3Days();
        forecastForNext3Days.setDay1(new ForecastForADay(weatherAPIResponse.getForecast().getForecastday().get(1).getDay(), weatherAPIResponse.getForecast().getForecastday().get(1).getDate()));
        forecastForNext3Days.setDay2(new ForecastForADay(weatherAPIResponse.getForecast().getForecastday().get(2).getDay(), weatherAPIResponse.getForecast().getForecastday().get(2).getDate()));
        forecastForNext3Days.setDay3(new ForecastForADay(weatherAPIResponse.getForecast().getForecastday().get(3).getDay(), weatherAPIResponse.getForecast().getForecastday().get(3).getDate()));
        return new ForecastForNext3DaysForCity(forecastForNext3Days, weatherAPIResponse.getLocation().getName());
    }

}
