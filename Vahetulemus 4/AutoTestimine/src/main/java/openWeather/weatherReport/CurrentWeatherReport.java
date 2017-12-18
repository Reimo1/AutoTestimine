package openWeather.weatherReport;

import openWeather.weather.Coordinates;

public class CurrentWeatherReport extends WeatherReport {
    private long tempCurrent;

    public CurrentWeatherReport(String cityName, Coordinates coordinates, String countryCode, long tempCurrent) {
        super(cityName, coordinates, countryCode);
        this.tempCurrent = tempCurrent;
    }

    public long getTempCurrent() {
        return tempCurrent;
    }
}