package Action.Cooking;

import Items.*;

abstract class Cooking {
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
