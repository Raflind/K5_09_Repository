package Items;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Foods extends Items {
    private int energyPoints;

    public Foods(String name, int sellPrice, int buyPrice, boolean isEdible,int energyPoints, BufferedImage image) {
        super(name, sellPrice, buyPrice, isEdible, image);
        this.energyPoints = energyPoints;
    }

    public int getEnergyPoints() {
        return energyPoints;
    }
}
