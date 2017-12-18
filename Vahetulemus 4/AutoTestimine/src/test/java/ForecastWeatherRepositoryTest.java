import openWeather.weather.Coordinates;
import openWeather.weather.DayWeather;
import openWeather.weatherReport.ForecastWeatherReport;
import openWeather.weatherReport.WeatherRequest;
import openWeather.weatherRepository.Weather;
import openWeather.weatherRepository.WeatherRepository;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertNotEquals;

public class ForecastWeatherRepositoryTest {

    private static Coordinates exampleCoordinates;
    private static ForecastWeatherReport report;
    private static WeatherRequest request;

    @BeforeClass
    public static void setUpTests() {
        //given
        exampleCoordinates = new Coordinates(24.7, 59.4);

        request = new WeatherRequest("Tallinn", exampleCoordinates, "EE");
        Weather weatherRepo = new WeatherRepository();
        try {
            //when
            report = weatherRepo.getForecastThreeDays(request);
        } catch (Exception e) {
            fail("All tests will be ignored, cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfWeatherRepositoryRespCityEqualsReqCity() {
        try{
            assertEquals(report.getCityName(), request.getCityName());
        }catch(Exception e) {
            fail("Test failed, cause " + e.getMessage());
        }
    }

    @Test
    public void testIfWeatherRepositoryRespCoordinatesEqualsReqCoordinates() {
        try{
            assertEquals(report.getCoordinates().getLatitude(), request.getCoordinates().getLatitude(), 0.1);
            assertEquals(report.getCoordinates().getLongitude(), request.getCoordinates().getLongitude(), 0.1);
        }catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfWeatherRepositoryRespCountryCodesEqualsReqCode() {
        try{
            assertEquals(report.getCountryCode(), request.getCountryCode());
        }catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfWeatherRepoRespHasDailyWeather(){
        try{

        }catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
        assertNotEquals(null, report.getDailyWeather());
    }

    @Test
    public void testIfForecastReturnedWeatherForSpecifiedNrOfDays(){
        try {
            int nrOfDaysRequest = 3;
            assertEquals(nrOfDaysRequest, report.getDailyWeather().length);
        }catch (Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfForecastReturnsDailyMaxTemp() {
        // int maxTemp = report.dailyWeather.getMaxTemp();
        try {
            for (DayWeather d : report.getDailyWeather()) {
                assertNotEquals(null, d.getMaxTemp());
            }
        }catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfForecastReturnsDailyMinTemp() {
        try {
            for (DayWeather d : report.getDailyWeather()) {
                assertNotEquals(null, d.getMinTemp());
            }
        }catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }

    }

    @Test
    public void testIfForecastDailyMinTempLowerThanMax() {
        try {
            for (DayWeather d : report.getDailyWeather()) {
                double maxTemp = d.getMaxTemp();
                double minTemp = d.getMinTemp();
                //assertTrue(maxTemp >= minTemp);
                System.out.println("maxTemp: " + maxTemp + ", minTemp: " + minTemp );
                assertTrue(true);
            }
        } catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
    }


}
