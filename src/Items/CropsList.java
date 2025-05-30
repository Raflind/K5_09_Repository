package Items;

import java.util.ArrayList;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum CropsList implements ItemCreator<Crops> {
    Parsnip("Parsnip", 35, 50, true, 1, null),
    Cauliflower("Cauliflower", 150, 200, true, 1, null),
    Potato("Potato", 80, 0, true, 1, null),
    Wheat("Wheat", 30, 50, true, 3, null),
    Blueberry("Blueberry", 40, 150, true, 3, null),
    Tomato("Tomato", 60, 90, true, 1, null),
    HotPepper("Hot Pepper", 40, 0, true, 1, null),
    Melon("Melon", 250, 0, true, 1, null),
    Cranberry("Cranberry", 25, 0, true, 10, null),
    Pumpkin("Pumpkin", 250, 300, true, 1, null),
    Grape("Grape", 10, 100, true, 20, null);


    private String name;
    private int sellPrice;
    private int buyPrice;
    private boolean isEdible;
    private int cropsTotal;
    private BufferedImage image;


    CropsList(String name, int sellPrice, int buyPrice, boolean isEdible, int cropsTotal, BufferedImage image) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.isEdible = isEdible;
        this.cropsTotal = cropsTotal;
        this.image = image;
    }


    @Override
    public Crops create() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(CropsList.class.getClassLoader().getResourceAsStream("res/Items/" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Crops(name, sellPrice, buyPrice, isEdible, image, cropsTotal);
    }

}
