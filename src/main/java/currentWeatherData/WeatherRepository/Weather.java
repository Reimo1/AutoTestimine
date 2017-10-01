package currentWeatherData.WeatherRepository;

import currentWeatherData.currentWeather.CurrentWeatherReport;
import currentWeatherData.exceptions.WeatherReportNotFoundException;
import currentWeatherData.weather.WeatherRequest;

public interface Weather {
    CurrentWeatherReport getCurrentWeather(WeatherRequest request) throws WeatherReportNotFoundException;
}