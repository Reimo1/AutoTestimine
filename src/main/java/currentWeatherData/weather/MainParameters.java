package currentWeatherData.weather;

public class MainParameters {
    private double temperature;
    private int pressure;
    private int humidity;
    private double temp_min;
    private double temp_max;

    public MainParameters(double temperature, int pressure, int humidity, double temp_min, double temp_max) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }
}
