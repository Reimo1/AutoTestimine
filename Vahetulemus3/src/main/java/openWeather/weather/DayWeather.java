package openWeather.weather;

public class DayWeather {
    private double maxTemp;
    private double minTemp;


    public DayWeather(double maxTemp, double minTemp) {
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }
}
