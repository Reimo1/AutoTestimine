package openWeather.weatherReport;

import openWeather.file.FileWriter;
import openWeather.weather.Coordinates;
import openWeather.weather.DayWeather;
import openWeather.weatherRepository.WeatherRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class UpdateWeatherTask {
    public static final String INPUT_FILE = "C:/Users/User/IdeaProjects/AutoTestimine/input.txt";
    public static final String OUTPUT_PATH = "C:/Users/User/IdeaProjects/AutoTestimine/outputs/";


    public void updateWeather(WeatherRepository repository) throws IOException, ParseException {
        WeatherRequestFactory factory = new WeatherRequestFactory();
        WeatherRequest[] requests = factory.makeRequestsFromFile(INPUT_FILE);
        FileWriter fileWriter = new FileWriter();
        for (WeatherRequest request : requests) {
            ForecastWeatherReport forecastWeatherReport = repository.getForecastThreeDays(request);
            CurrentWeatherReport currentWeatherReport = repository.getCurrentWeather(request);
            JSONObject fullReportJson = makeReportsToJson(forecastWeatherReport, currentWeatherReport);
            String OUTPUT_FILE = OUTPUT_PATH + forecastWeatherReport.getCityName() + ".txt";
            fileWriter.writeJsonToFile(fullReportJson, OUTPUT_FILE);
        }
    }

    public JSONObject makeReportsToJson(ForecastWeatherReport forecastWeatherReport, CurrentWeatherReport currentWeatherReport) {
        //Gson gson = new Gson();
        JSONObject fullWeatherReport = new JSONObject();
        String city = currentWeatherReport.getCityName();
        Coordinates coordinates = forecastWeatherReport.getCoordinates();
        JSONObject coordJson = new JSONObject();
        double lat = coordinates.getLatitude();
        double lon = coordinates.getLongitude();
        coordJson.put("longitude", lon);
        coordJson.put("latitude", lat);
        DayWeather[] dayWeathers = forecastWeatherReport.getDailyWeather();
        JSONArray dayWeathersJson = new JSONArray();
        for (DayWeather dayWeather : dayWeathers) {
            JSONObject dayWeatherJson = new JSONObject();
            double maxTemp = dayWeather.getMaxTemp();
            double minTemp = dayWeather.getMinTemp();
            dayWeatherJson.put("maxTemp", maxTemp);
            dayWeatherJson.put("minTemp", minTemp);
            dayWeathersJson.add(dayWeatherJson);
        }
        long currentTemp = currentWeatherReport.getTempCurrent();
        fullWeatherReport.put("city", city);
        fullWeatherReport.put("coordinates", coordJson);
        fullWeatherReport.put("dailyWeather", dayWeathersJson);
        fullWeatherReport.put("CurrentTemp", currentTemp);

        return fullWeatherReport;
    }

}
