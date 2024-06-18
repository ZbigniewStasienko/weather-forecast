package com.stasienko.weather_forecast.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class WeatherService {

    @Value("${weatherapi.url}")
    private String baseUrl;

    @Value("${weatherapi.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public String getWeatherForecast(String city) {
        int days = 4;

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/forecast.json")
                .queryParam("key", apiKey)
                .queryParam("q", city)
                .queryParam("days", days)
                .toUriString();

        ResponseEntity<String> response = restTemplate.getForEntity(urlTemplate, String.class);
        try {
            Object json = objectMapper.readValue(response.getBody(), Object.class);
            return objectMapper.writeValueAsString(json);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing JSON";
        }
    }
}
