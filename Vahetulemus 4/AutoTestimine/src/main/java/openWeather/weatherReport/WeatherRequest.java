package openWeather.weatherReport;

import openWeather.weather.Coordinates;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;

import static openWeather.weatherRepository.WeatherURLBuilder.buildCurrentWeatherURL;
import static openWeather.weatherRepository.WeatherURLBuilder.buildForecastURL;

public class WeatherRequest{
    private String cityName;
    private Coordinates coordinates;
    private String countryCode;
    private String units;

    public WeatherRequest(String cityName, Coordinates coordinates, String countryCode) {
        this.cityName = cityName;
        this.coordinates = coordinates;
        this.countryCode = countryCode;
    }

    public WeatherRequest(String cityName, String countryCode, String units) {
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.units = units;
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

    public String getCityName() {
        return cityName;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getUnits() {
        return units;
    }
}
