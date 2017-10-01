package currentWeatherData.weather;

public class WeatherReport
{
    public final String cityName;
    public final Coordinates coordinates;
    public final String countryCode;

        public WeatherReport(String cityName, Coordinates coordinates, String countryCode) {
            this.cityName = cityName;
            this.coordinates = coordinates;
            this.countryCode = countryCode;
        }
}
