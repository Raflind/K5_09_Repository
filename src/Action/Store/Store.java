package Action.Store;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import Entity.Player;
import Items.*;
import main.GamePanel;


public class Store {
    public List<Items> itemsStore;
    private BufferedImage image;

    public Store(){
        itemsStore = new ArrayList<>();
        try {
            image = ImageIO.read(Store.class.getClassLoader().getResourceAsStream("res/Items/Recipe.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        itemsStore.add(new Items("Recipe FishNChips", 0, 400, false, image));
        itemsStore.add(new Items("Recipe Fish Sandwich", 0, 750, false, image));
        loadItemsStore();

    }


    private void loadItemsStore(){
        for (ItemMiscList item : ItemMiscList.values()) {
            itemsStore.add(item.create());
        }
        for (FoodsList food : FoodsList.values()) {
            if(!food.getName().equals("Spakbor Salad") && !food.getName().equals("Fugu") && !food.getName().equals("The Legends of Spakbor")) {
                itemsStore.add((Items)food.create());
            }
        }
        for (SeedsList seed : SeedsList.values()) {
            itemsStore.add(seed.create());
        }
        for (CropsList crop : CropsList.values()) {
            if(!crop.getName().equals("Potato") && !crop.getName().equals("Cranberry") && !crop.getName().equals("Hot Pepper") && !crop.getName().equals("Melon")) {
                // Exclude Potato and Cranberry from the store
                itemsStore.add((Items)crop.create());
            }
        }

    }

    public void pay(int amount, Player player){
        if (player.goldManager.spendGold(amount)){
            System.out.println("Payment successful. You paid: " + amount + " gold.");
        } else {
            System.out.println("Not enough gold to pay: " + amount + " gold.");
        } 
    }

     public void buyItem(Items boughItems, Player player) {
        for(Items item : itemsStore) {
            if(item.getName().equals("Recipe FishNChips") || item.getName().equals("Recipe Fish Sandwich")) {
                // Exclude Recipe FishNChips and Recipe Fish Sandwich from the store
                continue;
            }
            if (item.equals(boughItems)) {
                if (player.goldManager.spendGold(item.getBuyPrice())) {
                    player.inventory.addItem(item);
                    System.out.println("You have bought: " + boughItems.getName());
                    return;
                } else {
                    System.out.println("Not enough gold to buy: " + boughItems.getName());
                    return;
                }
            }
        }   
    }
    public List<Items> getItemsStore() {
        return itemsStore;
    }
    public Items getItem(int index) {
        if (index < 0 || index >= itemsStore.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return itemsStore.get(index);
    }
}
