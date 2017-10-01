package currentWeatherData.weather;

public class Wind {
    private double speed;
    private int directionInDegrees;

    public Wind(double speed, int directionInDegrees) {
        this.speed = speed;
        this.directionInDegrees = directionInDegrees;
    }

    public double getSpeed() {
        return speed;
    }

    public int getDirectionInDegrees() {
        return directionInDegrees;
    }
}
