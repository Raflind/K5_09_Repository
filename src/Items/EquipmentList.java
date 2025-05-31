package Items;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public enum EquipmentList implements ItemCreator<Equipment> {
    Hoe("Hoe", 0, 0, false, null),
    Pickaxe("Pickaxe", 0, 0, false, null),
    WateringCan("Watering Can", 0, 0, false, null),
    FishingRod("Fishing Rod", 0, 0, false, null);


    private String name;
    private int sellPrice;
    private int buyPrice;
    private boolean isEdible;
    private BufferedImage image;

    private EquipmentList(String name, int sellPrice, int buyPrice, boolean isEdible, BufferedImage image) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.isEdible = isEdible;
        this.image = image;
    }

    @Override
    public Equipment create() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(EquipmentList.class.getClassLoader().getResourceAsStream("res/Items/" + name + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Equipment(name, sellPrice, buyPrice, isEdible, image);
    }

    public String getName() {
        return name;
    }
    
}
