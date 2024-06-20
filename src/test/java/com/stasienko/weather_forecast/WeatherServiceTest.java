package com.stasienko.weather_forecast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.stasienko.weather_forecast.services.WeatherService;
import com.stasienko.weather_forecast.model.ForecastForNext3DaysForCity;
import com.stasienko.weather_forecast.services.WeatherAPIResponse;
import com.stasienko.weather_forecast.services.Day;
import com.stasienko.weather_forecast.services.Location;
import com.stasienko.weather_forecast.services.Forecast;
import com.stasienko.weather_forecast.services.ForecastDay;

import java.util.Arrays;

public class WeatherServiceTest {

    @Test
    public void testConvertFromWeatherApiDataToOutputModel() {
        WeatherAPIResponse apiResponse = new WeatherAPIResponse();
        Location location = new Location();
        location.setName("Test City");
        Forecast forecast = new Forecast();
        ForecastDay day1 = new ForecastDay("2023-04-01", new Day(10, 5, 7.5, 15, 1.2, 0.5, 0, 80, 5));
        ForecastDay day2 = new ForecastDay("2023-04-02", new Day(12, 6, 9, 20, 1.5, 1.0, 0, 82, 5.5));
        ForecastDay day3 = new ForecastDay("2023-04-03", new Day(15, 10, 12.5, 25, 2, 1.2, 0, 85, 6));
        forecast.setForecastday(Arrays.asList(null, day1, day2, day3));
        apiResponse.setLocation(location);
        apiResponse.setForecast(forecast);

        WeatherService service = new WeatherService(null);
        ForecastForNext3DaysForCity result = service.convertFromWeatherApiDataToOutputModel(apiResponse);

        assertEquals("Test City", result.getCityName());
        assertEquals("2023-04-01", result.getDay1().getDate());
        assertEquals(10, result.getDay1().getMaxTempCelsius());
        assertEquals("2023-04-02", result.getDay2().getDate());
        assertEquals(12, result.getDay2().getMinTempCelsius());
        assertEquals("2023-04-03", result.getDay3().getDate());
        assertEquals(12.5, result.getDay3().getAvgTempCelsius());
    }
}
