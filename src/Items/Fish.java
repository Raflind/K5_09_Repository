package Items;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Fish extends Items {
    public enum FishType{
        Regular, Common, Legendary
    }
    private FishType fishType;
    private int seasonCount;
    private int timeCount;
    private int weatherCount;
    private int locationCount;

    public Fish(String name, int sellPrice, int buyPrice, boolean isEdible, int itemID, FishType type, BufferedImage image) {
        super(name, sellPrice, buyPrice, isEdible, itemID, image);
        this.fishType = type;
        this.seasonCount = 0;
        this.timeCount = 0;
        this.weatherCount = 0;
        this.locationCount = 0;
    }

    public int getSeasonCount() {
        return seasonCount;
    }
    public int getTimeCount() {
        return timeCount;
    }
    public int getWeatherCount() {
        return weatherCount;
    }
    public int getLocationCount() {
        return locationCount;
    }
    public void calculateSellPrice(int seasonCount, int timeCount, int weatherCount, int locationCount) {
        int basePrice = 0;
        switch(fishType) {
            case Regular:
                basePrice = 5;
            case Common:
                basePrice = 10;
            case Legendary:
                basePrice = 25;
            default:
                System.out.println("Invalid fish type.");
        }
        int newSellPrice = (4/seasonCount + 24/timeCount + 2/weatherCount + 4/locationCount) * basePrice;
        setSellPrice(newSellPrice);
    }
    
}
