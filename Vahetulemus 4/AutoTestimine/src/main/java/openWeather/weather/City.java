package openWeather.weather;

public class City {
    private String name;

    public City() {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" + "name='" + name + '\'' + '}';
    }
}
