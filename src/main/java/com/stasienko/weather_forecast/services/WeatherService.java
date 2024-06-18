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
        
        Day day1WeatherInfo = weatherAPIResponse.getForecast().getForecastday().get(1).getDay();
        String day1Date = weatherAPIResponse.getForecast().getForecastday().get(1).getDate();
        forecastForNext3Days.setDay1(new ForecastForADay(day1WeatherInfo.getMaxtemp_c(), day1WeatherInfo.getMaxtemp_c(), day1WeatherInfo.getAvgtemp_c(), day1WeatherInfo.getMaxwind_kph(), day1WeatherInfo.getTotalprecip_mm(), day1WeatherInfo.getTotalsnow_cm(), day1WeatherInfo.getAvgvis_km(), day1WeatherInfo.getAvghumidity(), day1WeatherInfo.getUv(), day1Date));

        Day day2WeatherInfo = weatherAPIResponse.getForecast().getForecastday().get(2).getDay();
        String day2Date = weatherAPIResponse.getForecast().getForecastday().get(2).getDate();
        forecastForNext3Days.setDay2(new ForecastForADay(day2WeatherInfo.getMaxtemp_c(), day2WeatherInfo.getMaxtemp_c(), day2WeatherInfo.getAvgtemp_c(), day2WeatherInfo.getMaxwind_kph(), day2WeatherInfo.getTotalprecip_mm(), day2WeatherInfo.getTotalsnow_cm(), day2WeatherInfo.getAvgvis_km(), day2WeatherInfo.getAvghumidity(), day2WeatherInfo.getUv(), day2Date));
        
        Day day3WeatherInfo = weatherAPIResponse.getForecast().getForecastday().get(3).getDay();
        String day3Date = weatherAPIResponse.getForecast().getForecastday().get(3).getDate();
        forecastForNext3Days.setDay3(new ForecastForADay(day3WeatherInfo.getMaxtemp_c(), day3WeatherInfo.getMaxtemp_c(), day3WeatherInfo.getAvgtemp_c(), day3WeatherInfo.getMaxwind_kph(), day3WeatherInfo.getTotalprecip_mm(), day3WeatherInfo.getTotalsnow_cm(), day3WeatherInfo.getAvgvis_km(), day3WeatherInfo.getAvghumidity(), day3WeatherInfo.getUv(), day3Date));

        return new ForecastForNext3DaysForCity(forecastForNext3Days, weatherAPIResponse.getLocation().getName());
    }

}
