package openWeather.weatherReport;

import openWeather.weather.Coordinates;

public class WeatherReport
{
    private String cityName;
    private Coordinates coordinates;
    private String countryCode;

        public WeatherReport(String cityName, Coordinates coordinates, String countryCode) {
            this.cityName = cityName;
            this.coordinates = coordinates;
            this.countryCode = countryCode;
        }

    public String getCityName() {
        return cityName;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
