package Items;

import java.awt.image.BufferedImage;

public class Equipment extends Items{
    public Equipment(String name, int sellPrice, int buyPrice, boolean isEdible, BufferedImage image) {
        super(name, sellPrice, buyPrice, isEdible, image); // Assuming image is not needed for Equipment
    }
}
