package Items;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import TimeSeasonWeather.EnvironmentStatus.Season;

public class Seeds extends Items {
    private int daystoHarvest;
    private ArrayList<Season> harvestSeason;
    private boolean isWatered;
    public int dayelapsed;
    public int posX, posY;

    public Seeds(String name, int sellPrice, int buyPrice, boolean isEdible, int daystoHarvest, BufferedImage image, Season harvestSeason) {
        super(name, sellPrice, buyPrice, isEdible, image);
        this.daystoHarvest = daystoHarvest;
        this.isWatered = false;
        this.dayelapsed = 0;
        this.harvestSeason = new ArrayList<Season>();
        this.harvestSeason.add(harvestSeason);
        if (name.equals("Wheat")) {
            this.harvestSeason.add(Season.Fall);
        }
        posX = 33;
        posY = 33;
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
        return dayelapsed >= daystoHarvest;
    }
    public boolean isPlantable(Season curr){
        for (Season season : harvestSeason) {
            if (curr == season) {
                return true;
            }
        }
        return false;
    }
}
