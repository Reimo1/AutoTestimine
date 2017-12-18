package openWeather.weatherRepository;


import openWeather.weather.Coordinates;
import openWeather.weather.DayWeather;
import openWeather.weatherReport.CurrentWeatherReport;
import openWeather.weatherReport.ForecastWeatherReport;
import openWeather.weatherReport.WeatherRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class WeatherRepository implements Weather{

    @Override
    public CurrentWeatherReport getCurrentWeather(WeatherRequest request){

        JSONObject weatherReportInJson = request.makeCurrentWeatherRequest(request);
        JSONObject sys = (JSONObject) weatherReportInJson.get("sys");
        JSONObject main = (JSONObject) weatherReportInJson.get("main");
        JSONObject coord = (JSONObject) weatherReportInJson.get("coord");
        String cityName = (String) weatherReportInJson.get("name");
        String countryCode = (String) sys.get("country");
        long tempCurrent = (long) main.get("temp");
        double longitude = (double) coord.get("lon");
        double latitude = (double) coord.get("lat");
        Coordinates coordinates = new Coordinates(longitude, latitude);
        return new CurrentWeatherReport(cityName, coordinates, countryCode, tempCurrent);
    }


    @Override
    public ForecastWeatherReport getForecastThreeDays(WeatherRequest weatherRequest){
        JSONObject forecastReportInJson = weatherRequest.makeForecastRequest(weatherRequest);
        JSONObject cityObject = (JSONObject) forecastReportInJson.get("city");
        JSONObject sys = (JSONObject) forecastReportInJson.get("sys");
        JSONObject coord = (JSONObject) cityObject.get("coord");
        String cityName = (String) cityObject.get("name");
        String countryCode = (String) cityObject.get("country");
        double longitude = (double) coord.get("lon");
        double latitude = (double) coord.get("lat");
        Coordinates coordinates = new Coordinates(longitude, latitude);
        double previousMaxTemp = Integer.MIN_VALUE;
        double previousMinTemp = Integer.MAX_VALUE;
        int previousDay = 0;
        JSONArray forecastArray = (JSONArray) forecastReportInJson.get("list");
        List<DayWeather> threeDayForecast = new ArrayList<>();
        for (Object aForecastArray : forecastArray) {
            JSONObject singleForecast = (JSONObject) aForecastArray;
            JSONObject main = (JSONObject) singleForecast.get("main");
            Timestamp timestamp = new Timestamp((Long) singleForecast.get("dt") * 1000);
            Object minTempObj = main.get("temp_min");
            Object maxTempObj = main.get("temp_max");
            double minTemp = new Double(minTempObj.toString());
            double maxTemp = new Double(maxTempObj.toString());
            int dayOfMonthToday = (new Timestamp(System.currentTimeMillis())).toLocalDateTime().getDayOfMonth();
            int numberOfDaysFromToday = timestamp.toLocalDateTime().getDayOfMonth() - dayOfMonthToday;
            if (numberOfDaysFromToday < 4) {
                if (minTemp < previousMinTemp) {
                    previousMinTemp = minTemp;
                }
                if (maxTemp > previousMaxTemp) {
                    previousMaxTemp = maxTemp;
                }
                if (numberOfDaysFromToday > previousDay) {
                    DayWeather oneDay = new DayWeather(previousMaxTemp, previousMinTemp);
                    threeDayForecast.add(oneDay);
                    previousMaxTemp = Integer.MIN_VALUE;
                    previousMinTemp = Integer.MAX_VALUE;
                }
            }
            previousDay = numberOfDaysFromToday;
        }
        DayWeather[] threeDays = threeDayForecast.toArray(new DayWeather[threeDayForecast.size()]);
        return new ForecastWeatherReport(cityName, coordinates, countryCode, threeDays);
    }

}
