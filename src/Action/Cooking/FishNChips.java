package Action.Cooking;

import Player.Inventory;

import java.util.List;
import java.util.ArrayList;

import Items.Fish;
import Items.Items;
import Player.Inventory;
import Items.Foods;
import Items.ItemFactory;

public class FishNChips extends Cooking {
    List<Fish> fishFound = new ArrayList<>();
    List<Items> potatoFound = new ArrayList<>();
    @Override
    public boolean checkIngredients(Inventory inventory) {
        // Assuming we have methods to check the inventory for fish and potatoes
        for (Items item : inventory.getItems()) {
            if (item instanceof Fish) {
                fishFound.add((Fish) item);
            } else if (item.getName().equalsIgnoreCase("kentang")) {
                potatoFound.add(item);
            }
        }

        if (fishFound.size() >= 2 && potatoFound.size() >= 1) {
            return true;
        } else {
            System.out.println("Bahan tidak cukup untuk memasak Fish and Chips.");
            return false;
        }
        
    }

    @Override
    public void consumeIngredients(Inventory inventory) {
        inventory.removeItem(fishFound.get(0)); // Remove one fish
        inventory.removeItem(fishFound.get(1)); // Remove another fish
        inventory.removeItem(potatoFound.get(0)); // Remove one potato
    }

    @Override
    public void addDishToInventory(Inventory inventory) {
        Foods fishNChips = ItemFactory.createFoods("Fish and Chips", 15, 30, true, 1001, 0, 0);
        try {
            inventory.addItem(fishNChips);
        } catch (Exception e) {
            System.err.println("Gagal menambahkan Fish and Chips ke inventory: " + e.getMessage());
        }
    }
}