package currentWeatherData.weather;

public class WeatherInfo {
    private int id;
    private String mainInfo;
    private String description;
    private String icon;

    public WeatherInfo(int id, String mainInfo, String description, String icon) {
        this.id = id;
        this.mainInfo = mainInfo;
        this.description = description;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getMainInfo() {
        return mainInfo;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
