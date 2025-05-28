package Action.Cooking;

import java.util.List;
import java.util.ArrayList;
import Items.*;

public class FishSandwich extends Cooking {
    Fish fishFound;
    List<Items> wheatFound = new ArrayList<>();
    Items tomato;
    Items hotpepper;

    @Override
    public boolean checkIngredients(Inventory inventory) {
        for(Items item : inventory.getItems()) {
            if (item instanceof Fish) {
                fishFound = (Fish) item; // Assuming we only need one fish
            } else if (item.getName().equalsIgnoreCase("Wheat")) {
                wheatFound.add(item);
            } else if (item.getName().equalsIgnoreCase("Tomato")) {
                tomato = item;
            } else if (item.getName().equalsIgnoreCase("Hot Pepper")) {
                hotpepper = item;
            }
        }
        if(fishFound != null && wheatFound.size() >= 2 && tomato != null && hotpepper != null) {
            return true; // Enough ingredients found
        } 
        System.out.println("Bahan tidak cukup untuk memasak Fish Sandwich.");
        return false; // Not enough ingredients
    }

    @Override
    public void consumeIngredients(Inventory inventory) {
        if(fishFound == null || wheatFound.size() < 2 || tomato == null || hotpepper == null) {
            System.out.println("Tidak cukup bahan untuk membuat Fish Sandwich.");
            return; // Not enough ingredients to make Fish Sandwich
        }
        inventory.removeItem(fishFound); // Remove the fish
        inventory.removeItem(wheatFound.get(0)); // Remove one wheat
        inventory.removeItem(wheatFound.get(1)); // Remove another wheat
        inventory.removeItem(tomato); // Remove tomato
        inventory.removeItem(hotpepper); // Remove hot pepper
    }

    @Override
    public void addDishToInventory(Inventory inventory) {
        Foods fishSandwich = ItemFactory.createFoods("Fish Sandwich", 15, 30, true, 1007, 0, 0);
        try {
            inventory.addItem(fishSandwich); // Add Fish Sandwich to inventory
        } catch (Exception e) {
            System.err.println("Gagal menambahkan Fish Sandwich ke inventory: " + e.getMessage());
        }
    }
    
}
