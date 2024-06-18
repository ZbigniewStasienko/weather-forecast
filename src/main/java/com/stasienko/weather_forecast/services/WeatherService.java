package com.stasienko.weather_forecast.services;

import com.stasienko.weather_forecast.model.ForecastForADay;
import com.stasienko.weather_forecast.model.ForecastForNext3DaysForCity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    @Value("${weatherapi.url}")
    private String baseUrl;

    @Value("${weatherapi.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    private static final List<String> biggestPolishCities = List.of(new String[]{"Warsaw", "Cracow", "Wroclaw", "Lodz", "Poznan"});

    private static final int days = 4;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ForecastForNext3DaysForCity> getWeatherForecast(){
        List<ForecastForNext3DaysForCity> result = new ArrayList<>();
        biggestPolishCities.forEach(city -> {
            WeatherAPIResponse weatherAPIResponse = getWeatherForecastForCity(city);
            result.add(convertFromWeatherApiDataToOutputModel(weatherAPIResponse));
        });
        return result;
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

        ForecastForNext3DaysForCity forecastForNext3DaysForCity = new ForecastForNext3DaysForCity();

        forecastForNext3DaysForCity.setCityName(weatherAPIResponse.getLocation().getName());
        
        Day day = weatherAPIResponse.getForecast().getForecastday().get(1).getDay();
        String date = weatherAPIResponse.getForecast().getForecastday().get(1).getDate();
        forecastForNext3DaysForCity.setDay1(new ForecastForADay(day.getMaxtemp_c(), day.getMaxtemp_c(), day.getAvgtemp_c(), day.getMaxwind_kph(), day.getTotalprecip_mm(), day.getTotalsnow_cm(), day.getAvgvis_km(), day.getAvghumidity(), day.getUv(), date));

        day = weatherAPIResponse.getForecast().getForecastday().get(2).getDay();
        date = weatherAPIResponse.getForecast().getForecastday().get(2).getDate();
        forecastForNext3DaysForCity.setDay2(new ForecastForADay(day.getMaxtemp_c(), day.getMaxtemp_c(), day.getAvgtemp_c(), day.getMaxwind_kph(), day.getTotalprecip_mm(), day.getTotalsnow_cm(), day.getAvgvis_km(), day.getAvghumidity(), day.getUv(), date));
        
        day = weatherAPIResponse.getForecast().getForecastday().get(3).getDay();
        date = weatherAPIResponse.getForecast().getForecastday().get(3).getDate();
        forecastForNext3DaysForCity.setDay3(new ForecastForADay(day.getMaxtemp_c(), day.getMaxtemp_c(), day.getAvgtemp_c(), day.getMaxwind_kph(), day.getTotalprecip_mm(), day.getTotalsnow_cm(), day.getAvgvis_km(), day.getAvghumidity(), day.getUv(), date));

        return forecastForNext3DaysForCity;
    }

}
