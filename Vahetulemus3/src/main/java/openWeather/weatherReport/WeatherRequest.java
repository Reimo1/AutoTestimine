package openWeather.weatherReport;

import openWeather.weather.Coordinates;

public class WeatherRequest{
    public final String cityName;
    public final Coordinates coordinates;
    public final String countryCode;

    public WeatherRequest(String cityName, Coordinates coordinates, String countryCode) {
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
