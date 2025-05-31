package Items;

import java.util.List;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import TimeSeasonWeather.EnvironmentStatus;
import Items.ItemCreator;

public enum FishList implements ItemCreator<Fish>{
    BullHead("Bullhead", 0, 0, false, Fish.FishType.Common, 0, 24, List.of("Mountain"), List.of(EnvironmentStatus.Season.Spring, EnvironmentStatus.Season.Fall,EnvironmentStatus.Season.Summer, EnvironmentStatus.Season.Winter), List.of(EnvironmentStatus.Weather.Sunny, EnvironmentStatus.Weather.Rainy), null),
    Carp("Carp", 0, 0, false, Fish.FishType.Common, 0, 24, List.of("Mountain", "Farm"), List.of(EnvironmentStatus.Season.Spring, EnvironmentStatus.Season.Fall,EnvironmentStatus.Season.Summer, EnvironmentStatus.Season.Winter), List.of(EnvironmentStatus.Weather.Sunny, EnvironmentStatus.Weather.Rainy), null),
    Chub("Chub", 0, 0, false, Fish.FishType.Common, 0, 24, List.of("Mountain", "Forest"), List.of(EnvironmentStatus.Season.Spring, EnvironmentStatus.Season.Fall,EnvironmentStatus.Season.Summer, EnvironmentStatus.Season.Winter), List.of(EnvironmentStatus.Weather.Sunny, EnvironmentStatus.Weather.Rainy), null),
    LargeMouthBass("Large Mouth Bass", 0, 0, false, Fish.FishType.Regular, 6, 18, List.of("Mountain"), List.of(EnvironmentStatus.Season.Spring, EnvironmentStatus.Season.Fall,EnvironmentStatus.Season.Summer, EnvironmentStatus.Season.Winter), List.of(EnvironmentStatus.Weather.Sunny, EnvironmentStatus.Weather.Rainy), null),
    RainbowTrout("Rainbow Trout", 0, 0, false, Fish.FishType.Regular, 6, 18, List.of("Mountain", "Forest"), List.of(EnvironmentStatus.Season.Summer), List.of(EnvironmentStatus.Weather.Sunny), null),
    Sturgeon("Sturgeon", 0, 0, false, Fish.FishType.Regular, 6, 18, List.of("Mountain"), List.of(EnvironmentStatus.Season.Winter, EnvironmentStatus.Season.Summer), List.of(EnvironmentStatus.Weather.Sunny, EnvironmentStatus.Weather.Rainy), null),
    MidnightCarp("Midnight Carp", 0, 0, false, Fish.FishType.Regular, 20, 2, List.of("Mountain", "Farm"), List.of(EnvironmentStatus.Season.Fall, EnvironmentStatus.Season.Winter), List.of(EnvironmentStatus.Weather.Sunny, EnvironmentStatus.Weather.Rainy), null),
    Flounder("Flounder", 0, 0, false, Fish.FishType.Regular, 6, 22, List.of("Ocean"), List.of(EnvironmentStatus.Season.Spring, EnvironmentStatus.Season.Summer), List.of(EnvironmentStatus.Weather.Sunny, EnvironmentStatus.Weather.Rainy), null),
    Halibut("Halibut", 0, 0, false, Fish.FishType.Regular, 6, 11, List.of("Ocean"), List.of(EnvironmentStatus.Season.Spring, EnvironmentStatus.Season.Summer, EnvironmentStatus.Season.Fall, EnvironmentStatus.Season.Winter), List.of(EnvironmentStatus.Weather.Sunny, EnvironmentStatus.Weather.Rainy), null),
    Octopus("Octopus", 0, 0, false, Fish.FishType.Regular, 6, 22, List.of("Ocean"), List.of(EnvironmentStatus.Season.Summer), List.of(EnvironmentStatus.Weather.Sunny, EnvironmentStatus.Weather.Rainy), null),
    Pufferfish("Pufferfish", 0, 0, false, Fish.FishType.Regular, 00, 16, List.of("Ocean"), List.of(EnvironmentStatus.Season.Summer), List.of(EnvironmentStatus.Weather.Sunny), null),
    Sardine("Sardine", 0, 0, false, Fish.FishType.Regular, 6, 18, List.of("Ocean"), List.of(EnvironmentStatus.Season.Spring, EnvironmentStatus.Season.Summer, EnvironmentStatus.Season.Fall, EnvironmentStatus.Season.Winter), List.of(EnvironmentStatus.Weather.Sunny, EnvironmentStatus.Weather.Rainy), null),
    SuperCucumber("Super Cucumber", 0, 0, false, Fish.FishType.Regular, 18, 2, List.of("Ocean"), List.of(EnvironmentStatus.Season.Summer, EnvironmentStatus.Season.Fall, EnvironmentStatus.Season.Winter), List.of(EnvironmentStatus.Weather.Sunny, EnvironmentStatus.Weather.Rainy), null),
    Catfish("Catfish", 0, 0, false, Fish.FishType.Regular, 6, 22, List.of("Forest", "Farm"), List.of(EnvironmentStatus.Season.Spring, EnvironmentStatus.Season.Summer, EnvironmentStatus.Season.Fall), List.of(EnvironmentStatus.Weather.Rainy), null),
    Salmon("Salmon", 0, 0, false, Fish.FishType.Regular, 6, 18, List.of("Forest"), List.of(EnvironmentStatus.Season.Fall), List.of(EnvironmentStatus.Weather.Sunny, EnvironmentStatus.Weather.Rainy), null),
    Angler("Angler", 0, 0, false, Fish.FishType.Legendary, 8, 20, List.of("Farm"), List.of(EnvironmentStatus.Season.Fall), List.of(EnvironmentStatus.Weather.Sunny, EnvironmentStatus.Weather.Rainy), null),
    CrimsonFish("Crimson Fish", 0, 0, false, Fish.FishType.Legendary, 8, 20, List.of("Ocean"), List.of(EnvironmentStatus.Season.Summer), List.of(EnvironmentStatus.Weather.Sunny, EnvironmentStatus.Weather.Rainy), null),
    Glacierfish("Glacier Fish", 0, 0, false, Fish.FishType.Legendary, 8, 20, List.of("Forest"), List.of(EnvironmentStatus.Season.Winter), List.of(EnvironmentStatus.Weather.Sunny, EnvironmentStatus.Weather.Rainy), null),
    Legend("Legend", 0, 0, false, Fish.FishType.Legendary, 8, 20, List.of("Mountain"), List.of(EnvironmentStatus.Season.Spring), List.of(EnvironmentStatus.Weather.Rainy), null);
    
    private String name;
    private int sellPrice;
    private int buyPrice;
    private boolean isEdible;
    private Fish.FishType fishType;
    private int startTime;
    private int endTime;
    private List<String> location;
    private List<EnvironmentStatus.Season> season;
    private List<EnvironmentStatus.Weather> weather;
    private BufferedImage image;

    private FishList(String name, int sellPrice, int buyPrice, boolean isEdible, Fish.FishType fishType, int startTime, int endTime, List<String> location, List<EnvironmentStatus.Season> season, List<EnvironmentStatus.Weather> weather, BufferedImage image) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.isEdible = isEdible;
        this.fishType = fishType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.season = season;
        this.weather = weather;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    @Override
    public Fish create() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(FishList.class.getClassLoader().getResourceAsStream("res/Items/" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Fish fish = new Fish(name, sellPrice, buyPrice, isEdible, fishType, season, startTime, endTime, location, weather, image);
        fish.calculateSellPrice();
        return fish;
    }
}
