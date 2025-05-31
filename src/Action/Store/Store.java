package Action.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Entity.Player;
import Items.*;
import main.GamePanel;


public class Store {
    public List<Items> itemsStore;

    public Store(){
        loadItemsStore();
    }


    private void loadItemsStore(){
        itemsStore = new ArrayList<>();
        for (ItemMiscList item : ItemMiscList.values()) {
            itemsStore.add(item.create());
        }
        for (FoodsList food : FoodsList.values()) {
            if(!food.getName().equals("Spakbor Salad") && !food.getName().equals("Fugu") && !food.getName().equals("The Legends of Spakbor")) {
                itemsStore.add(food.create());
            }
        }
        for (SeedsList seed : SeedsList.values()) {
            itemsStore.add(seed.create());
        }
        for (CropsList crop : CropsList.values()) {
            if(!crop.getName().equals("Potato") && !crop.getName().equals("Cranberry") && !crop.getName().equals("Hot Pepper") && !crop.getName().equals("Melon")) {
                // Exclude Potato and Cranberry from the store
                itemsStore.add(crop.create());
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

     public void buyItem(String itemName, Player player) {
        for(Items item : itemsStore) {
            if (item.getName().equals(itemName)) {
                if (player.goldManager.spendGold(item.getBuyPrice())) {
                    player.inventory.addItem(item);
                    System.out.println("You have bought: " + itemName);
                    return;
                } else {
                    System.out.println("Not enough gold to buy: " + itemName);
                    return;
                }
            }
        }   
    }
    public List<Items> getItemsStore() {
        return itemsStore;
    }
}
