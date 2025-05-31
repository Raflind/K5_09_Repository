package Items;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.imageio.ImageIO;

import TimeSeasonWeather.EnvironmentStatus.Season;
import TimeSeasonWeather.EnvironmentStatus.Weather;

public class Fish extends Items {
    public enum FishType{
        Regular, Common, Legendary
    }
    private FishType fishType;
    private List<Season> season;
    private int startTime;
    private int endTime;
    private List<String> location;
    private List<Weather> weather;

    public Fish(String name, int sellPrice, int buyPrice, boolean isEdible, FishType type, List<Season> season, int startTime, int endTime, List<String> location,List<Weather> weather, BufferedImage image) {
        super(name, sellPrice, buyPrice, isEdible, image);
        this.fishType = type;
        this.season = season;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.weather = weather;
    }

    public int getSeasonCount() {
        return season.size();
    }
    public int endTime() {
        return endTime;
    }
    public int startTime() {
        return startTime;
    }
    public FishType getFishType() {
        return fishType;
    }
    public int getInterval(){
        int interval = 0;
        if(this.getName().equals("Halibut")){
            interval = 7;
        }
        return (interval + ((endTime - startTime + 24) % 24));
    }
    public int getWeatherCount() {
        return weather.size();
    }
    public int getLocationCount() {
        return location.size();
    }
    public void calculateSellPrice() {
        int seasonCount = Math.max(getSeasonCount(), 1);
        int timeCount = Math.max(getInterval(), 1);
        int weatherCount = Math.max(getWeatherCount(), 1);
        int locationCount = Math.max(getLocationCount(), 1);
        int basePrice = 0;
        switch(fishType) {
            case Regular:
                basePrice = 5;
                break;
            case Common:
                basePrice = 10;
                break;
            case Legendary:
                basePrice = 25;
                break;
            default:
                System.out.println("Invalid fish type.");
        }
        int newSellPrice = (4/seasonCount + 24/timeCount + 2/weatherCount + 4/locationCount) * basePrice;
        setSellPrice(newSellPrice);
    }

    public boolean isLegendary() {
        return fishType == FishType.Legendary;
    }

    public boolean isInSeason(Season season) {
        return this.season.contains(season);
    }

    public boolean isInWeather(Weather weather) {
        return this.weather.contains(weather);
    }
    public boolean isInLocation(String location) {
        return this.location.contains(location);
    }
    public boolean isInTime(int hour) {
        if(startTime <= endTime) {
            return hour >= startTime && hour < endTime;
        } else {
            return hour >= startTime || hour < endTime; // Handles overnight fishing
        }
    }

    public String getWeather(){
        StringBuilder weatherString = new StringBuilder();
        for (int i = 0; i < weather.size(); i++) {
            weatherString.append(weather.get(i).toString());
            if (i < weather.size() - 1) {
                weatherString.append(", ");
            }
        }
        return weatherString.toString();
    }

    public String getSeason(){
        StringBuilder seasonString = new StringBuilder();
        for (int i = 0; i < season.size(); i++) {
            seasonString.append(season.get(i).toString());
            if (i < season.size() - 1) {
                seasonString.append(", ");
            }
        }
        return seasonString.toString();
    }

    public String getLocation(){
        StringBuilder locationString = new StringBuilder();
        for (int i = 0; i < location.size(); i++) {
            locationString.append(location.get(i));
            if (i < location.size() - 1) {
                locationString.append(", ");
            }
        }
        return locationString.toString();
    }
    
}
