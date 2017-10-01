package currentWeatherData.WeatherRepository;

import currentWeatherData.weatherReport.CurrentWeatherReport;
import currentWeatherData.exceptions.WeatherReportNotFoundException;
import currentWeatherData.weatherReport.ForecastWeatherReport;
import currentWeatherData.weatherReport.WeatherRequest;

public class WeatherRepository implements Weather{

    @Override
    public CurrentWeatherReport getCurrentWeather(WeatherRequest request) throws WeatherReportNotFoundException {
        throw new WeatherReportNotFoundException("Missing interface implementation");
    }


    @Override
    public ForecastWeatherReport getForecastThreeDays(WeatherRequest request) throws WeatherReportNotFoundException {
        throw new WeatherReportNotFoundException("Missing interface implementation");
    }

}
