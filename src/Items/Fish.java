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
        switch(Integer.compare(startTime, endTime)) {
            case -1: // startTime < endTime
                return (hour >= startTime && hour <= endTime);
            case 1: // startTime > endTime
                return (hour >= startTime || hour <= endTime);
            default:
                return false;
        }
    }
    
}
