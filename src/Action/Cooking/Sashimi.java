package Action.Cooking;

import java.util.List;
import java.util.ArrayList;
import Items.*;

public class Sashimi extends Cooking {
    List<Fish> fishFound = new ArrayList<>();
    @Override
    public boolean checkIngredients(Inventory inventory) {
        // Assuming we have a method to check for fish in the inventory
        for (Items item : inventory.getItems()) {
            if(item instanceof Fish){
                if (item.getName().equalsIgnoreCase("Salmon")) {
                    fishFound.add((Fish) item);
                }
            }
            
        }
        if(fishFound.size() >= 1) {
            return true;
        }
        System.out.println("Bahan tidak cukup untuk memasak Sashimi.");
        return false;
    }

    @Override
    public void consumeIngredients(Inventory inventory) {
        if(fishFound.size() < 1) {
            System.out.println("Tidak cukup Salmon untuk membuat Sashimi.");
            return; // Tidak cukup Salmon untuk membuat Sashimi
        }
        inventory.removeItem(fishFound.get(0)); // Remove one fish
    }

    @Override
    public void addDishToInventory(Inventory inventory) {
        inventory.addItem((Items)FoodsList.Sashimi.create());
    }
}
