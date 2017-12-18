package openWeather.weatherRepository;

import openWeather.weatherReport.CurrentWeatherReport;
import openWeather.weatherReport.ForecastWeatherReport;
import openWeather.weatherReport.WeatherRequest;

public interface Weather {
    CurrentWeatherReport getCurrentWeather(WeatherRequest request);
    ForecastWeatherReport getForecastThreeDays(WeatherRequest request);
}