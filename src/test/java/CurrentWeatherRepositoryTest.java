import currentWeatherData.WeatherRepository.Weather;
import currentWeatherData.WeatherRepository.WeatherRepository;
import currentWeatherData.weatherReport.CurrentWeatherReport;
import currentWeatherData.weather.*;
import currentWeatherData.weatherReport.WeatherRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CurrentWeatherRepositoryTest {
    private static CurrentWeatherReport report;
    private static WeatherRequest request;
    private static Coordinates exampleCoordinates;
    
    @Before
    public void setUpTests() {
        exampleCoordinates = new Coordinates(24.75, 59.44);
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
    public void IfWeatherRepositoryRespCoordEqualsReqCoord() {
        try {
            // given
            Weather weatherRepo = new WeatherRepository();
            // when
            CurrentWeatherReport report = weatherRepo.getCurrentWeather(request);
            //then
            assertEquals(request.coordinates, report.coordinates);
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
            assertTrue(report.tempCurrent < 57);
            assertTrue( report.tempCurrent > -90);
        }catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
    }


}
