package currentWeatherData.currentWeather;

import currentWeatherData.weather.City;
import currentWeatherData.weather.Coordinates;
import currentWeatherData.weather.MainParameters;
import currentWeatherData.weather.WeatherReport;

public class CurrentWeatherReport extends WeatherReport {
    public final double tempCurrent;

    public CurrentWeatherReport(String cityName, Coordinates coordinates, String countryCode, double tempCurrent) {
        super(cityName, coordinates, countryCode);
        this.tempCurrent = tempCurrent;
    }
}