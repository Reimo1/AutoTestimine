package openWeather.weatherReport;

import openWeather.weather.Coordinates;
import openWeather.weather.DayWeather;

public class ForecastWeatherReport extends WeatherReport{
    private DayWeather[] dailyWeather;

    public ForecastWeatherReport(String cityName, Coordinates coordinates, String countryCode, DayWeather[] dailyWeather){
        super(cityName, coordinates, countryCode);
        this.dailyWeather = dailyWeather;
    }

    public DayWeather[] getDailyWeather() {
        return dailyWeather;
    }


}
