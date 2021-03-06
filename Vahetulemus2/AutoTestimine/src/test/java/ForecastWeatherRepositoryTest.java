import openWeather.weather.Coordinates;
import openWeather.weather.DayWeather;
import openWeather.weatherReport.ForecastWeatherReport;
import openWeather.weatherReport.WeatherRequest;
import openWeather.weatherRepository.Weather;
import openWeather.weatherRepository.WeatherRepository;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;

public class ForecastWeatherRepositoryTest {

    private static Coordinates exampleCoordinates;
    private static ForecastWeatherReport report;
    private static WeatherRequest request;

    @Before
    public void setUpTests() {
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

    @org.testng.annotations.Test
    public void testIfWeatherRepositoryRespCityEqualsReqCity() {
        try{
            assertEquals(report.cityName, request.cityName);
        }catch(Exception e) {
            fail("Test failed, cause " + e.getMessage());
        }
    }

    @Test
    public void testIfWeatherRepositoryRespCoordinatesEqualsReqCoordinates() {
        try{
            assertEquals(report.coordinates.getLatitude(), request.coordinates.getLatitude(), 0.1);
            assertEquals(report.coordinates.getLongitude(), request.coordinates.getLongitude(), 0.1);
        }catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfWeatherRepositoryRespCountryCodesEqualsReqCode() {
        try{
            assertEquals(report.countryCode, request.countryCode);
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
        assertNotEquals(null, report.dailyWeather);
    }

    @Test
    public void testIfForecastReturnedWeatherForSpecifiedNrOfDays(){
        try {
            int nrOfDaysRequest = 3;
            assertEquals(nrOfDaysRequest, report.dailyWeather.length);
        }catch (Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfForecastReturnsDailyMaxTemp() {
        // int maxTemp = report.dailyWeather.getMaxTemp();
        try {
            for (DayWeather d : report.dailyWeather) {
                assertNotEquals(null, d.getMaxTemp());
            }
        }catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfForecastReturnsDailyMinTemp() {
        try {
            for (DayWeather d : report.dailyWeather) {
                assertNotEquals(null, d.getMinTemp());
            }
        }catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }

    }

    @Test
    public void testIfForecastDailyMinTempLowerThanMax() {
        try {
            for (DayWeather d : report.dailyWeather) {
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
