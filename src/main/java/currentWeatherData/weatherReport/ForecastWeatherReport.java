package currentWeatherData.weatherReport;

import currentWeatherData.weather.Coordinates;
import currentWeatherData.weather.DayWeather;

public class ForecastWeatherReport extends WeatherReport{
    public final DayWeather[] dailyWeather;

    public ForecastWeatherReport(String cityName, Coordinates coordinates, String countryCode, DayWeather[] dailyWeather){
        super(cityName, coordinates, countryCode);
        this.dailyWeather = dailyWeather;
    }
}
