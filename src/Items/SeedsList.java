package Items;

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import TimeSeasonWeather.EnvironmentStatus.Season;

public enum SeedsList implements ItemCreator<Seeds>{
    Parsnip("Parsnip Seeds", 10, 20, false, 1, null, Season.Spring),
    Cauliflowe("Cauliflower Seeds", 40, 80, false, 5, null,  Season.Spring),
    Potato("Potato Seeds", 25, 50, false, 3, null, Season.Spring),
    Wheat("Wheat Seeds",30, 60, false, 1, null, Season.Spring),
    Blueberry("Blueberry Seeds", 40, 80, false, 7, null, Season.Summer),
    Tomato("Tomato Seeds", 25, 50, false, 3, null, Season.Summer),
    HotPepper("Hot Pepper Seeds", 20, 40, false, 1, null, Season.Summer),
    Melon("Melon Seeds", 40, 80, false, 4, null, Season.Summer),
    Cranberry("Cranberry Seeds", 50, 100, false, 2, null, Season.Fall),
    Pumpkin("Pumpkin Seeds", 75, 150, false, 7, null, Season.Fall),
    Grape("Grape Seeds", 30, 60, false, 3, null, Season.Fall);

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
        BufferedImage image = null;
        try{
            image = ImageIO.read(SeedsList.class.getClassLoader().getResourceAsStream("res/Items/" + name + ".png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return new Seeds(name, sellPrice, buyPrice, isEdible, daystoHarvest, image, harvestSeason);
    }
    
}
