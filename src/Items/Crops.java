package Items;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Crops extends Items {
    private int cropsTotal;

    public Crops(String name, int sellPrice, int buyPrice, boolean isEdible, BufferedImage image, int cropsTotal) {
        super(name, sellPrice, buyPrice, isEdible, image);
        this.cropsTotal = cropsTotal;
    }

    public int getCropsTotal() {
        return cropsTotal;
    }
}
