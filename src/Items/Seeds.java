package Items;

import java.awt.image.BufferedImage;
<<<<<<< HEAD
import javax.imageio.ImageIO;
=======
import java.util.ArrayList;

import TimeSeasonWeather.EnvironmentStatus.Season;
>>>>>>> 9564a1ea3ec46dffcc09f9cfcd183ec7cbcc292f

public class Seeds extends Items {
    private int daystoHarvest;
    private ArrayList<Season> harvestSeason;
    private boolean isWatered;
    private int dayelapsed;

<<<<<<< HEAD
    public Seeds(String name, int sellPrice, int buyPrice, boolean isEdible, int itemID, int daystoHarvest, BufferedImage image) {
        super(name, sellPrice, buyPrice, isEdible, itemID, image);
=======
    public Seeds(String name, int sellPrice, int buyPrice, boolean isEdible, int daystoHarvest, BufferedImage image, Season harvestSeason) {
        super(name, sellPrice, buyPrice, isEdible, image);
>>>>>>> 9564a1ea3ec46dffcc09f9cfcd183ec7cbcc292f
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
