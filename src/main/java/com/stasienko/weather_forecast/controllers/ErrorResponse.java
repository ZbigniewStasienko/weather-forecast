package com.stasienko.weather_forecast.controllers;

import java.time.Instant;

public class ErrorResponse {

    public static final String API_KEY_ERROR_EXAMPLE = "{\n" +
            "  \"timestamp\": \"2024-06-19T07:46:11.592+00:00\",\n" +
            "  \"status\": 401,\n" +
            "  \"error\": \"Unauthorized\",\n" +
            "  \"path\": \"/api/weather/biggest_polish_cities\"\n" +
            "}";

    public static final String SERVER_ERROR_EXAMPLE = "{\n" +
            "  \"timestamp\": \"2024-06-19T07:48:55.980+00:00\",\n" +
            "  \"status\": 500,\n" +
            "  \"error\": \"Internal Server Error\",\n" +
            "  \"path\": \"/api/weather/biggest_polish_cities\"\n" +
            "}";

    private Instant timestamp;
    private int status;
    private String error;
    private String path;

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
