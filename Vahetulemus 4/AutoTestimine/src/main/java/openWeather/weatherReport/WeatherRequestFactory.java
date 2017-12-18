package openWeather.weatherReport;

import openWeather.file.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WeatherRequestFactory {
    private FileReader reader = new FileReader();

    public WeatherRequest[] makeRequestsFromFile(String inputFile) throws IOException, ParseException {
        JSONArray input = reader.readFile(inputFile);
        List<WeatherRequest> weatherRequestList = new ArrayList<>();
        for (Object anInput : input) {
            JSONObject inputJSON = (JSONObject) anInput;
            String city = (String) inputJSON.get("city");
            String countryCode = (String) inputJSON.get("countryCode");
            String units = (String) inputJSON.get("units");
            WeatherRequest weatherReq = new WeatherRequest(city, countryCode, units);
            weatherRequestList.add(weatherReq);
        }

        return weatherRequestList.toArray(new WeatherRequest[weatherRequestList.size()]);
    }
}
