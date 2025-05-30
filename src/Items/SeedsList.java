package Items;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

import TimeSeasonWeather.EnvironmentStatus.Season;

public enum SeedsList implements ItemCreator<Seeds>{
    Parsnip("Parsnip Seeds", 10, 20, false, 1, null, Season.SPRING),
    Cauliflowe("Cauliflower Seeds", 40, 80, false, 5, null,  Season.SPRING),
    Potato("Potato Seeds", 25, 50, false, 3, null, Season.SPRING),
    Wheat("Wheat Seeds",30, 60, false, 1, null, Season.SPRING),
    Blueberry("Blueberry Seeds", 40, 80, false, 7, null, Season.SUMMER),
    Tomato("Tomato Seeds", 25, 50, false, 3, null, Season.SUMMER),
    HotPepper("Hot Pepper Seeds", 20, 40, false, 1, null, Season.SUMMER),
    Melon("Melon Seeds", 40, 80, false, 4, null, Season.SUMMER),
    Cranberry("Cranberry Seeds", 50, 100, false, 2, null, Season.AUTUMN),
    Pumpkin("Pumpkin Seeds", 75, 150, false, 7, null, Season.AUTUMN),
    Grape("Grape Seeds", 30, 60, false, 3, null, Season.AUTUMN);

    private String name;
    private int sellPrice;
    private int buyPrice;
    private boolean isEdible;
    public BufferedImage image;
    private int daystoHarvest;
    private Season harvestSeason;

    private SeedsList(String name, int sellPrice, int buyPrice, boolean isEdible, int daystoHarvest, BufferedImage image, Season harvestSeason) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.isEdible = isEdible;
        this.image = image;
        this.daystoHarvest = daystoHarvest;
        this.harvestSeason = harvestSeason;
    }

    @Override
    public Seeds create() {
        return new Seeds(name, sellPrice, buyPrice, isEdible, daystoHarvest, image, harvestSeason);
    }
    
}
