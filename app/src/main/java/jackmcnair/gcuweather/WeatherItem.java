package jackmcnair.gcuweather;

import java.util.ArrayList;
import java.util.Date;

public class WeatherItem {
    private String locationName;
    private ArrayList<String> day = new ArrayList<>(), mintemp = new ArrayList<>(),  maxtemp = new ArrayList<>(), windspeed = new ArrayList<>(), pressure = new ArrayList<>(), humidity = new ArrayList<>(), uvrisk = new ArrayList<>(), rain = new ArrayList<>(), winddir = new ArrayList<>(), visibility = new ArrayList<>(), pollution = new ArrayList<>(), sunrise = new ArrayList<>(), sunset = new ArrayList<>();

    public WeatherItem() {
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public ArrayList<String> getDay() {
        return day;
    }

    public void setDay(ArrayList<String> day) {
        this.day = day;
    }

    public ArrayList<String> getMintemp() {
        return mintemp;
    }

    public void setMintemp(ArrayList<String> mintemp) {
        this.mintemp = mintemp;
    }

    public ArrayList<String> getMaxtemp() {
        return maxtemp;
    }

    public void setMaxtemp(ArrayList<String> maxtemp) {
        this.maxtemp = maxtemp;
    }

    public ArrayList<String> getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(ArrayList<String> windspeed) {
        this.windspeed = windspeed;
    }

    public ArrayList<String> getPressure() {
        return pressure;
    }

    public void setPressure(ArrayList<String> pressure) {
        this.pressure = pressure;
    }

    public ArrayList<String> getHumidity() {
        return humidity;
    }

    public void setHumidity(ArrayList<String> humidity) {
        this.humidity = humidity;
    }

    public ArrayList<String> getUvrisk() {
        return uvrisk;
    }

    public void setUvrisk(ArrayList<String> uvrisk) {
        this.uvrisk = uvrisk;
    }

    public ArrayList<String> getRain() {
        return rain;
    }

    public void setRain(ArrayList<String> rain) {
        this.rain = rain;
    }

    public ArrayList<String> getWinddir() {
        return winddir;
    }

    public void setWinddir(ArrayList<String> winddir) {
        this.winddir = winddir;
    }

    public ArrayList<String> getVisibility() {
        return visibility;
    }

    public void setVisibility(ArrayList<String> visibility) {
        this.visibility = visibility;
    }

    public ArrayList<String> getPollution() {
        return pollution;
    }

    public void setPollution(ArrayList<String> pollution) {
        this.pollution = pollution;
    }

    public ArrayList<String> getSunrise() {
        return sunrise;
    }

    public void setSunrise(ArrayList<String> sunrise) {
        this.sunrise = sunrise;
    }

    public ArrayList<String> getSunset() {
        return sunset;
    }

    public void setSunset(ArrayList<String> sunset) {
        this.sunset = sunset;
    }
}
