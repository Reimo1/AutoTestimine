package currentWeatherData.weather;

public class WeatherRequest{
    public final String cityName;
    public final Coordinates coordinates;
    public final String countryCode;

    public WeatherRequest(String cityName, Coordinates coordinates, String countryCode) {
        this.cityName = cityName;
        this.coordinates = coordinates;
        this.countryCode = countryCode;
    }
}
