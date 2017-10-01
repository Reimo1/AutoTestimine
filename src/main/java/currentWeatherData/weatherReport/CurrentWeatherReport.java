package currentWeatherData.weatherReport;

import currentWeatherData.weather.Coordinates;

public class CurrentWeatherReport extends WeatherReport {
    public final double tempCurrent;

    public CurrentWeatherReport(String cityName, Coordinates coordinates, String countryCode, double tempCurrent) {
        super(cityName, coordinates, countryCode);
        this.tempCurrent = tempCurrent;
    }
}