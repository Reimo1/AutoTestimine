package openWeather.weatherRepository;


import openWeather.weather.Coordinates;
import openWeather.weather.DayWeather;
import openWeather.weatherReport.CurrentWeatherReport;
import openWeather.weatherReport.ForecastWeatherReport;
import openWeather.weatherReport.WeatherRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class WeatherRepository implements Weather{
    public static final String APIKey = "842dbd846e7b625dba8507036c21b971";

    public String buildCurrentWeatherURL(WeatherRequest request) {
        URIBuilder builder = new URIBuilder()
                .setScheme("http")
                .setHost("api.openweathermap.org")
                .setPath("data/2.5/weather")
                .addParameter("q", request.getCityName() + "," + request.getCountryCode())
                .addParameter("APPID", APIKey)
                .addParameter("units", "metric");
        URL url = null;
        try {
            url = builder.build().toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return url.toString();
    }

    public JSONObject makeCurrentWeatherRequest(WeatherRequest weatherRequest) {
        String url = buildCurrentWeatherURL(weatherRequest);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpRequest = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = client.execute(httpRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }



    @Override
    public CurrentWeatherReport getCurrentWeather(WeatherRequest request){
        JSONObject weatherReportInJson = makeCurrentWeatherRequest(request);
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

    public String buildForecastURL(WeatherRequest weatherRequest) {
        URIBuilder builder = new URIBuilder()
                .setScheme("http")
                .setHost("api.openweathermap.org")
                .setPath("/data/2.5/forecast")
                .addParameter("q", weatherRequest.getCityName() + "," + weatherRequest.getCountryCode())
                .addParameter("APPID", APIKey)
                .addParameter("units", "metric");
        URL url = null;
        try {
            url = builder.build().toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return url.toString();
    }

    public JSONObject makeForecastRequest(WeatherRequest weatherRequest) {
        String url = buildForecastURL(weatherRequest);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpRequest = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = client.execute(httpRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public ForecastWeatherReport getForecastThreeDays(WeatherRequest weatherRequest){
        JSONObject forecastReportInJson = makeForecastRequest(weatherRequest);

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
        List<DayWeather> threeDayForecast = new ArrayList<DayWeather>();
        //DayWeather[] threeDayForecast = new DayWeather[3];
        for (int i = 0; i < forecastArray.size(); i++) {
            JSONObject singleForecast = (JSONObject) forecastArray.get(i);
            JSONObject main = (JSONObject) singleForecast.get("main");
            Timestamp timestamp = new Timestamp((Long) singleForecast.get("dt") * 1000);

            //long minTemp = (long) main.get("temp_min");
            //long maxTemp = (long) main.get("temp_max");
            Object minTempObj = main.get("temp_min");
            Object maxTempObj = main.get("temp_max");
            double minTemp = new Double(minTempObj.toString());
            double maxTemp = new Double(maxTempObj.toString());
            int dayOfMonthToday = (new Timestamp(System.currentTimeMillis())).toLocalDateTime().getDayOfMonth();
            int numberOfDaysFromToday = timestamp.toLocalDateTime().getDayOfMonth() - dayOfMonthToday;
            if (numberOfDaysFromToday < 4) {
                if(minTemp < previousMinTemp) {
                    previousMinTemp = minTemp;
                }
                if(maxTemp > previousMaxTemp) {
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
