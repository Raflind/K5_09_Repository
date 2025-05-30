package Items;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import TimeSeasonWeather.EnvironmentStatus.Season;

public class Seeds extends Items {
    private int daystoHarvest;
    private ArrayList<Season> harvestSeason;
    private boolean isWatered;
    private int dayelapsed;

    public Seeds(String name, int sellPrice, int buyPrice, boolean isEdible, int daystoHarvest, BufferedImage image, Season harvestSeason) {
        super(name, sellPrice, buyPrice, isEdible, image);
        this.daystoHarvest = daystoHarvest;
        this.isWatered = false;
        this.dayelapsed = 0;
        this.harvestSeason = new ArrayList<Season>();
        this.harvestSeason.add(harvestSeason);
        if (name.equals("Wheat")) {
            this.harvestSeason.add(Season.AUTUMN);
        }
    }

    public void water() {
        this.isWatered = true;
    }

    public void unwater() {
        this.isWatered = false;
    }
    public boolean isWatered() {
        return isWatered;
    }
    public int getDaystoHarvest() {
        return daystoHarvest;
    }
    public int getDayelapsed() {
        return dayelapsed;
    }
    public void incrementDayElapsed() {
        this.dayelapsed++;
    }
    public boolean isHarvestable() {
        return dayelapsed >= daystoHarvest && isWatered;
    }
}
