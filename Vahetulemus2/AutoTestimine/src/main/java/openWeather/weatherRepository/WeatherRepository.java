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

public class WeatherRepository implements Weather{
    public static final String APIKey = "842dbd846e7b625dba8507036c21b971";

    public String buildCurrentWeatherURL(WeatherRequest request) {
        URIBuilder builder = new URIBuilder()
                .setScheme("http")
                .setHost("api.openweathermap.org")
                .setPath("data/2.5/weather")
                .addParameter("q", request.getCityName() + "," + request.getCountryCode())
                .addParameter("APPID", APIKey);
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
        double tempCurrent = (double) main.get("temp");
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
                .addParameter("APPID", APIKey);
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
        JSONObject sys = (JSONObject) forecastReportInJson.get("sys");
        JSONObject coord = (JSONObject) forecastReportInJson.get("coord");
        String cityName = (String) forecastReportInJson.get("name");
        String countryCode = (String) sys.get("country");
        double longitude = (double) coord.get("lon");
        double latitude = (double) coord.get("lat");
        Coordinates coordinates = new Coordinates(longitude, latitude);
        JSONArray forecastArray = (JSONArray) forecastReportInJson.get("list");

        DayWeather[] threeDayForecast = new DayWeather[3];

        for (int i = 0; i < forecastArray.size(); i++) {
            JSONObject singleForecast = (JSONObject) forecastArray.get(i);
            JSONObject main = (JSONObject) singleForecast.get("main");
            Object minTempObj = main.get("temp_min");
            Object maxTempObj = main.get("temp_max");
            double minTemp = new Double(minTempObj.toString());
            double maxTemp = new Double(maxTempObj.toString());
            DayWeather oneDay = new DayWeather(maxTemp, minTemp);
            threeDayForecast[i] = oneDay;
        }
        return new ForecastWeatherReport(cityName, coordinates, countryCode, threeDayForecast);
    }

}
