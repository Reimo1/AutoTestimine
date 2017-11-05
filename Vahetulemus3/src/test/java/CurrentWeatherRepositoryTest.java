import openWeather.weather.Coordinates;
import openWeather.weatherReport.CurrentWeatherReport;
import openWeather.weatherReport.WeatherRequest;
import openWeather.weatherRepository.Weather;
import openWeather.weatherRepository.WeatherRepository;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class CurrentWeatherRepositoryTest {
    private static CurrentWeatherReport report;
    private static WeatherRequest request;
    private static Coordinates exampleCoordinates;

    @BeforeClass
    public static void setUpTests() {
        exampleCoordinates = new Coordinates(24.7, 59.4);
        request = new WeatherRequest("Tallinn", exampleCoordinates,"EE");
        Weather weatherRepo = new WeatherRepository();
        try{
            report = weatherRepo.getCurrentWeather(request);
        }catch(Exception e){
            fail("All test will be ignored, cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfWeatherRepositoryRespCityEqualsReqCity() {
        try {
            // given
            Weather weatherRepo = new WeatherRepository();
            // when
            CurrentWeatherReport report = weatherRepo.getCurrentWeather(request);
            // then
            assertEquals(request.cityName, report.cityName);
        }catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void TestIfWeatherRepositoryRespCoordEqualsReqCoord() {
        try {
            // given
            Weather weatherRepo = new WeatherRepository();
            // when
            CurrentWeatherReport report = weatherRepo.getCurrentWeather(request);
            //then
            //assertEquals(request.coordinates, report.coordinates);
            assertEquals(request.coordinates.getLongitude(), report.coordinates.getLongitude(), 0.1);
            assertEquals(request.coordinates.getLatitude(), report.coordinates.getLatitude(), 0.1);
        }catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void TestIfWeatherRepositoryRespCountryCodeEqualsReqCountryCode() {
        try {
            // given
            Weather weatherRepo = new WeatherRepository();
            // when
            CurrentWeatherReport report = weatherRepo.getCurrentWeather(request);
            //then
            assertEquals(request.countryCode, report.countryCode);
        }catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void TestIfWeatherRepositoryRespHasTemp() {
        try {
            //given
            Weather weatherRepo = new WeatherRepository();
            //when
            CurrentWeatherReport report = weatherRepo.getCurrentWeather(request);
            //then
            assertNotEquals(null, report.tempCurrent);
        }catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void TestIfCurrentTemperatureValidInCelsius(){
        try {
            Weather weatherRepo = new WeatherRepository();
            CurrentWeatherReport report = weatherRepo.getCurrentWeather(request);
            assertTrue(report.tempCurrent < 599);
            assertTrue( report.tempCurrent > -900);
        }catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
    }


}
