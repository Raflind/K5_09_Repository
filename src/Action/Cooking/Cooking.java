package Action.Cooking;

import java.util.HashMap;

import Items.*;

public abstract class Cooking {
    public final void cook(Inventory inventory) {
        if(checkIngredients(inventory)) {
            consumeIngredients(inventory);
            try {
                Thread.sleep(12000);
            } catch (InterruptedException e) {
                System.err.println("Cooking interrupted: " + e.getMessage());
            }
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
