import openWeather.weather.Coordinates;
import openWeather.weather.DayWeather;
import openWeather.weatherReport.*;
import openWeather.weatherRepository.WeatherRepository;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static openWeather.weatherReport.UpdateWeatherTask.INPUT_FILE;
import static openWeather.weatherReport.UpdateWeatherTask.OUTPUT_PATH;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateWeatherTaskTest {
    private static WeatherRequestFactory factory;
    private static WeatherRepository repositoryMock;
    private static UpdateWeatherTask weatherTask;

    @BeforeClass
    public static void setUpTests() throws IOException, ParseException {
        repositoryMock = mock(WeatherRepository.class);
        weatherTask = new UpdateWeatherTask();

        Coordinates randomCoordinates = new Coordinates(24.3, 43.5);
        DayWeather dayOneWeather = new DayWeather(14.2, 13.3);
        DayWeather dayTwoWeather = new DayWeather(-30, -33.1);
        DayWeather dayThreeWeather = new DayWeather(43.4, -11);
        DayWeather[] randomDayWeathers = {dayOneWeather, dayTwoWeather, dayThreeWeather};
        CurrentWeatherReport randomTallinnCurrentWeatherReport  = new CurrentWeatherReport("Tallinn", randomCoordinates, "EE", 11);
        ForecastWeatherReport randomTallinnForecastWeatherReport = new ForecastWeatherReport("Tallinn", randomCoordinates, "EE", randomDayWeathers);
        CurrentWeatherReport randomTartuCurrentWeatherReport = new CurrentWeatherReport("Tartu", randomCoordinates, "EE", 40);
        ForecastWeatherReport randomTartuForecastWeatherReport = new ForecastWeatherReport("Tartu", randomCoordinates, "EE", randomDayWeathers);
        CurrentWeatherReport randomSakuCurrentWeatherReport = new CurrentWeatherReport("Saku", randomCoordinates, "EE", -22);
        ForecastWeatherReport randomSakuForecastWeatherReport = new ForecastWeatherReport("Saku", randomCoordinates, "EE", randomDayWeathers);
        when(repositoryMock.getCurrentWeather(any(WeatherRequest.class)))
                .thenReturn(randomTallinnCurrentWeatherReport)
                .thenReturn(randomTartuCurrentWeatherReport)
                .thenReturn(randomSakuCurrentWeatherReport);
        when(repositoryMock.getForecastThreeDays(any(WeatherRequest.class)))
                .thenReturn(randomTallinnForecastWeatherReport)
                .thenReturn(randomTartuForecastWeatherReport)
                .thenReturn(randomSakuForecastWeatherReport);

        weatherTask.updateWeather(repositoryMock);
    }

    @Test
    public void testForEveryCityInInputAReportIsWrittenToFile() throws IOException, ParseException {
        try {
            factory = new WeatherRequestFactory();
            WeatherRequest[] requests = factory.makeRequestsFromFile(INPUT_FILE);
            int nrOfRequest = requests.length;
            int nrOfInputFilesCreated = new File(OUTPUT_PATH).listFiles().length;
            assertEquals(nrOfRequest, nrOfInputFilesCreated);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
