package com.stasienko.weather_forecast.controllers;

import com.stasienko.weather_forecast.model.ForecastForNext3DaysForCity;
import com.stasienko.weather_forecast.services.WeatherService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@OpenAPIDefinition(
        servers = { @Server(url = "http://localhost:8080") },
        info = @Info(
                title = "Weather Forecast",
                description = "Service provides weather forecast",
                version = "v1",
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")))
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(path = "/api/weather/biggest_polish_cities", produces = "application/json")
    @Tag(name = "Weather forecast")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Weather forecast for the next 3 days for the 5 largest cities in Poland."),
            @ApiResponse(responseCode = "401", description = "Invalid API key", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = {@ExampleObject(value = ErrorResponse.API_KEY_ERROR_EXAMPLE)}
            )),
            @ApiResponse(responseCode = "500", description = "Internal server exception", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = {@ExampleObject(value = ErrorResponse.SERVER_ERROR_EXAMPLE)}
            ))
    })
    public List<ForecastForNext3DaysForCity> getWeather() {
        return weatherService.getWeatherForecast();
    }
}