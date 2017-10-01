import currentWeatherData.WeatherRepository.Weather;
import currentWeatherData.WeatherRepository.WeatherRepository;
import currentWeatherData.weather.Coordinates;
import currentWeatherData.weatherReport.ForecastWeatherReport;
import currentWeatherData.weatherReport.WeatherRequest;
import org.junit.Before;
import org.junit.Test;


import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ForecastWeatherRepositoryTest {

    private static Coordinates exampleCoordinates;
    private static ForecastWeatherReport report;
    private static WeatherRequest request;

    @Before
    public void setUpTests() {
        //given
        exampleCoordinates = new Coordinates(24.75, 59.44);
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
            assertEquals(report.cityName, request.cityName);
        }catch(Exception e) {
            fail("Test failed, cause " + e.getMessage());
        }
    }

    @Test
    public void testIfWeatherRepositoryRespCoordinatesEqualsReqCoordinates() {
        try{
            assertEquals(report.coordinates, request.coordinates);
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
           assertNotEquals(null, report.dailyWeather);
        }catch(Exception e) {
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfForecastReturnsDailyMinTemp() {

    }

    @Test
    public void testIfForecastMinTempLowerThanMax() {

    }


}
