package jackmcnair.gcuweather;

public class WeatherItem {
    private String locationName;
    private int temperature;

    public WeatherItem(String locationName, int temperature) {
        this.locationName = locationName;
        this.temperature = temperature;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
