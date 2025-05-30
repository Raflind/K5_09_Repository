package Action.Cooking;

import java.util.HashMap;

import Items.*;

abstract class Cooking {
    public HashMap<String, Boolean> recipe = new HashMap<String, Boolean>();

    public Cooking() {
        recipe.put("FishNChips", false);
        recipe.put("Baguette", true);
        recipe.put("Sashimi", false);
        recipe.put("Fugu", false);
        recipe.put("Wine", true);
        recipe.put("PumpkinPie", true);
        recipe.put("FishStew", false);
        recipe.put("SpakborSalad", true);
        recipe.put("Sandwich", false);
        recipe.put("Legends", false);
    }
    public final void cook(Inventory inventory) {
        if(checkIngredients(inventory)) {
            consumeIngredients(inventory);
            addDishToInventory(inventory);
            System.out.println("Masakan berhasil dibuat!");
        } else {
            System.out.println("Bahan tidak cukup untuk memasak.");
        }
    }
    abstract boolean checkIngredients(Inventory inventory);
    abstract void consumeIngredients(Inventory inventory);
    abstract void addDishToInventory(Inventory inventory);
}
