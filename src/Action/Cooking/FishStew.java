package Action.Cooking;

import java.util.List;
import java.util.ArrayList;
import Items.*;

public class FishStew extends Cooking {
    List<Fish> fishFound = new ArrayList<>();
    List<Items> cauliFlower = new ArrayList<>();
    Items hotpepper;

    @Override
    public boolean checkIngredients(Inventory inventory) {
        for(Items item : inventory.getItems()) {
            if (item instanceof Fish) {
                fishFound.add((Fish) item);
            } else if (item.getName().equalsIgnoreCase("Cauliflower")) {
                cauliFlower.add(item);
            } else if (item.getName().equalsIgnoreCase("Hot Pepper")) {
                hotpepper = item;
            }
        }
        if(fishFound.size() >= 2 && cauliFlower.size() >= 2 && hotpepper != null) {
            return true; // Enough ingredients found
        } else {
            System.out.println("Bahan tidak cukup untuk memasak Fish Stew.");
            return false; // Not enough ingredients
        }
    }

    @Override
    void consumeIngredients(Inventory inventory) {
        if(fishFound.size() < 2 || cauliFlower.size() < 2 || hotpepper == null) {
            System.out.println("Tidak cukup bahan untuk membuat Fish Stew.");
            return; // Not enough ingredients to make Fish Stew
        }
        inventory.removeItem(fishFound.get(0)); // Remove one fish
        inventory.removeItem(fishFound.get(1)); // Remove another fish
        inventory.removeItem(cauliFlower.get(0)); // Remove one cauliflower
        inventory.removeItem(cauliFlower.get(1)); // Remove another cauliflower
        inventory.removeItem(hotpepper); // Remove hot pepper
    }

    @Override
    void addDishToInventory(Inventory inventory) {
        inventory.addItem((Items)FoodsList.FishStew.create());
    }
    
}
