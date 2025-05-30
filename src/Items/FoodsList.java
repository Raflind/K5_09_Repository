package Items;

import java.util.ArrayList;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum FoodsList implements ItemCreator<Foods>{
    Baguette("Baguette", 80, 100, true, 25, null),
    FishNChips("Fish", 135, 150, true, 50, null),
    Sashimi("Sashimi", 275, 300, true, 70, null),
    Fugu("Fugu", 135, 0, true, 50, null),
    Wine("Wine", 90, 100, true, 20, null),
    PumpkinPie("Pumpkin Pie", 100, 120, true, 35, null),
    VeggieSoup("Veggie Soup", 120, 140, true, 40, null),
    FishStew("Fish Stew", 260, 280, true, 70, null),
    SpakborSalad("Spakbor Salad", 250, 0, true, 70, null),
    FishSandwich("Fish Sandwich", 180, 200, true, 50, null),
    TheLegendsofSpakbor("The Legends of Spakbor", 2000, 0, true, 100, null);

    private String name;
    private int sellPrice;
    private int buyPrice;
    private boolean isEdible;
    private int energyPoints;
    private BufferedImage image;

    private FoodsList(String name, int sellPrice, int buyPrice, boolean isEdible, int energyPoints, BufferedImage image) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.isEdible = isEdible;
        this.energyPoints = energyPoints;
        this.image = image;
    }
    @Override
    public Foods create() {
        BufferedImage image = null;
        try{
            image = ImageIO.read(FoodsList.class.getClassLoader().getResourceAsStream("res/Items/" + name + ".png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new Foods(name, sellPrice, buyPrice, isEdible, energyPoints, image);
    }
}
