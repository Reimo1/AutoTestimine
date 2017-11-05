package openWeather.weatherReport;

import openWeather.weather.Coordinates;

public class CurrentWeatherReport extends WeatherReport {
    public final long tempCurrent;

    public CurrentWeatherReport(String cityName, Coordinates coordinates, String countryCode, long tempCurrent) {
        super(cityName, coordinates, countryCode);
        this.tempCurrent = tempCurrent;
    }
}