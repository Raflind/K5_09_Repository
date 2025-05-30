package Action.Cooking;

import java.util.List;
import java.util.ArrayList;
import Items.*;

public class TheLegendsofSpakbor extends Cooking {
    Fish fishFound;
    List<Items> potatoFound = new ArrayList<>();
    Items parsnip;
    Items tomato;
    Items eggplant;

    @Override
    public boolean checkIngredients(Inventory inventory) {
        for(Items item : inventory.getItems()) {
            if (item instanceof Fish) {
                Fish fish = (Fish) item;
                if(fish.isLegendary()){
                    fishFound = fish;
                }
            } else if (item.getName().equalsIgnoreCase("kentang")) {
                potatoFound.add(item);
            } else if (item.getName().equalsIgnoreCase("parsnip")) {
                parsnip = item;
            } else if (item.getName().equalsIgnoreCase("tomato")) {
                tomato = item;
            } else if (item.getName().equalsIgnoreCase("terong")) {
                eggplant = item;
            }
        }
        if(fishFound != null && potatoFound.size() >= 2 && parsnip != null && tomato != null && eggplant != null) {
            return true; // Enough ingredients found
        }
        System.out.println("Bahan tidak cukup untuk memasak The Legend of Spakbor.");
        return false; // Not enough ingredients
    }

    @Override
    public void consumeIngredients(Inventory inventory) {
        if(fishFound == null || potatoFound.size() < 2 || parsnip == null || tomato == null || eggplant == null) {
            System.out.println("Tidak cukup bahan untuk membuat The Legend of Spakbor.");
            return; // Not enough ingredients to make The Legend of Spakbor
        }
        inventory.removeItem(fishFound); // Remove the legendary fish
        inventory.removeItem(potatoFound.get(0)); // Remove one potato
        inventory.removeItem(potatoFound.get(1)); // Remove another potato
        inventory.removeItem(parsnip); // Remove parsnip
        inventory.removeItem(tomato); // Remove tomato
        inventory.removeItem(eggplant); // Remove eggplant
    }

    @Override
    public void addDishToInventory(Inventory inventory) {
        inventory.addItem((Items)FoodsList.TheLegendsofSpakbor.create());
    }
    
}
