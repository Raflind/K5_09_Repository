package Items;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Items {
    private String name;
    private int sellPrice;
    private int buyPrice;
    private boolean isEdible;
    public BufferedImage image;


    public Items(String name, int sellPrice, int buyPrice, boolean isEdible, BufferedImage image) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.isEdible = isEdible;
        this.image = image;
    }
    public String getName() {
        return name;
    }  
    public int getSellPrice() {
        return sellPrice;
    }
    public int getBuyPrice() {
        return buyPrice;
    }
    public boolean isEdible() {
        return isEdible;
    }
    
    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }
}

