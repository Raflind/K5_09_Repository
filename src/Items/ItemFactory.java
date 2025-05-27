package Items;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import Items.Fish;

import java.io.IOException;

public class ItemFactory {
    public static Items createItem(String name, int sellPrice, int buyPrice, boolean isEdible, int itemID) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(ItemFactory.class.getClassLoader().getResourceAsStream("res/Items/" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Items(name, sellPrice, buyPrice, isEdible, itemID, image);
    }

    public static Fish createFish(String name, int sellPrice, int buyPrice, boolean isEdible, int itemID, Fish.FishType type) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(ItemFactory.class.getClassLoader().getResourceAsStream("res/Items/" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Fish(name, sellPrice, buyPrice, isEdible, itemID, type, image);
    }

    public static Crops createCrops(String name, int sellPrice, int buyPrice, boolean isEdible, int itemID, int daystoHarvest) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(ItemFactory.class.getClassLoader().getResourceAsStream("res/Items/" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Crops(name, sellPrice, buyPrice, isEdible, itemID, daystoHarvest, image);
    }
    public static Seeds createSeeds(String name, int sellPrice, int buyPrice, boolean isEdible, int itemID, int daystoHarvest) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(ItemFactory.class.getClassLoader().getResourceAsStream("res/Items/" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Seeds(name, sellPrice, buyPrice, isEdible, itemID, daystoHarvest, image);
    }

    public static Foods createFoods(String name, int sellPrice, int buyPrice, boolean isEdible, int itemID, int healthPoints, int energyPoints) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(ItemFactory.class.getClassLoader().getResourceAsStream("res/Items/" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Foods(name, sellPrice, buyPrice, isEdible, itemID, healthPoints, energyPoints, image);
    }

}

