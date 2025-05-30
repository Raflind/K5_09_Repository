package Action.Cooking;

import java.util.List;
import java.util.ArrayList;

import Items.*;

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
        if(fishFound.size() < 2 || potatoFound.size() < 1) {
            System.out.println("Tidak cukup bahan untuk membuat Fish and Chips.");
            return; // Tidak cukup bahan untuk membuat Fish and Chips
        }
        inventory.removeItem(fishFound.get(0)); // Remove one fish
        inventory.removeItem(fishFound.get(1)); // Remove another fish
        inventory.removeItem(potatoFound.get(0)); // Remove one potato
    }

    @Override
    public void addDishToInventory(Inventory inventory) {
        inventory.addItem((Items)FoodsList.FishNChips.create());
    }
}