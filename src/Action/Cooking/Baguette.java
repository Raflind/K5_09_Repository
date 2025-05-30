package Action.Cooking;

import java.util.List;
import java.util.ArrayList;
import Items.*;

public class Baguette extends Cooking {
    List<Items> wheatFound = new ArrayList<>();

    @Override
    public boolean checkIngredients(Inventory inventory) {
        for (Items item : inventory.getItems()) {
            if (item.getName().equalsIgnoreCase("Wheat")) {
                wheatFound.add(item);
            }
        }

        if (wheatFound.size() >= 3) {
            return true;
        } else {
            System.out.println("Bahan tidak cukup untuk memasak Baguette.");
            return false;
        }
    }

    @Override
    public void consumeIngredients(Inventory inventory) {
        if( wheatFound.size() < 3) {
            System.out.println("Tidak cukup Wheat untuk membuat Baguette.");
            return; // Tidak cukup Wheat untuk membuat Baguette
        }
        inventory.removeItem(wheatFound.get(0)); // Remove one wheat
        inventory.removeItem(wheatFound.get(1)); // Remove another wheat
        inventory.removeItem(wheatFound.get(2)); // Remove third wheat
    }

    @Override
    public void addDishToInventory(Inventory inventory) {
        inventory.addItem((Items)FoodsList.Baguette.create());
    }
    
}
