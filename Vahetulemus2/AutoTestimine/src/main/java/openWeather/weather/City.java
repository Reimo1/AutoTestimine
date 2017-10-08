package openWeather.weather;

public class City {
    private int id;
    private String name;
    private Coordinates coordinates;
    private String countryCode;

    public City(int id, String name, Coordinates coordinates, String countryCode) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.countryCode = countryCode;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
