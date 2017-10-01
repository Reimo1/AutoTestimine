package currentWeatherData.WeatherRepository;

import currentWeatherData.weatherReport.CurrentWeatherReport;
import currentWeatherData.exceptions.WeatherReportNotFoundException;
import currentWeatherData.weatherReport.ForecastWeatherReport;
import currentWeatherData.weatherReport.WeatherRequest;

public interface Weather {
    CurrentWeatherReport getCurrentWeather(WeatherRequest request) throws WeatherReportNotFoundException;
    ForecastWeatherReport getForecastThreeDays(WeatherRequest request) throws WeatherReportNotFoundException;
}