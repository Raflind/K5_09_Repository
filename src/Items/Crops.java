package Items;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Crops extends Items {
    private int daystoHarvest;
    private boolean isWatered;
    private int dayelapsed;

    public Crops(String name, int sellPrice, int buyPrice, boolean isEdible, int itemID, int daystoHarvest, BufferedImage image) {
        super(name, sellPrice, buyPrice, isEdible, itemID, image);
        this.daystoHarvest = daystoHarvest;
        this.isWatered = false;
        this.dayelapsed = 0;
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
