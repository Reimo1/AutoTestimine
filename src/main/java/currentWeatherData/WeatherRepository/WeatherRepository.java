package currentWeatherData.WeatherRepository;

import currentWeatherData.currentWeather.CurrentWeatherReport;
import currentWeatherData.exceptions.WeatherReportNotFoundException;
import currentWeatherData.weather.WeatherRequest;

import java.io.IOException;

public class WeatherRepository implements Weather{

    @Override
    public CurrentWeatherReport getCurrentWeather(WeatherRequest request) throws WeatherReportNotFoundException {
        throw new WeatherReportNotFoundException("Missing interface implementation");
    }

    /*
    @Override
    public ForecastWeatherReport getForecastThreeDays(WeatherRequest request) throws WeatherReportNotFoundException {
        throw new WeatherReportNotFoundException("Missing interface implementation");
    }
    */
}
