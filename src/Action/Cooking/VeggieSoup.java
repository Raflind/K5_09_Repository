package Action.Cooking;

import java.util.List;
import java.util.ArrayList;
import Items.*;

public class VeggieSoup extends Cooking {
    Items cauliflower;
    Items parsnip;
    Items potato;
    Items tomato;
    
    @Override
    public boolean checkIngredients(Inventory inventory) {
        // Memeriksa apakah ada cukup sayuran dalam inventaris
        for (Items item : inventory.getItems()) {
            if (item.getName().equalsIgnoreCase("Cauliflower")) {
                cauliflower = item;
            } else if (item.getName().equalsIgnoreCase("Parsnip")) {
                parsnip = item;
            } else if (item.getName().equalsIgnoreCase("Potato")) {
                potato = item;
            } else if (item.getName().equalsIgnoreCase("Tomato")) {
                tomato = item;
            }
        }

        if (cauliflower != null && parsnip != null && potato != null && tomato != null) {
            return true;
        } else {
            System.out.println("Bahan tidak cukup untuk memasak Veggie Soup.");
            return false;
        }
    }

    @Override
    public void consumeIngredients(Inventory inventory) {
        if(cauliflower == null || parsnip == null || potato == null || tomato == null) {
            System.out.println("Tidak cukup bahan untuk membuat Veggie Soup.");
            return; // Tidak cukup bahan untuk membuat Veggie Soup
        }
        inventory.removeItem(cauliflower); // Menghapus Cauliflower dari inventaris
        inventory.removeItem(parsnip); // Menghapus Parsnip dari inventaris
        inventory.removeItem(potato); // Menghapus Potato dari inventaris
        inventory.removeItem(tomato); // Menghapus Tomato dari inventaris
    }

    @Override
    public void addDishToInventory(Inventory inventory) {
        Foods veggieSoup = ItemFactory.createFoods("Veggie Soup", 15, 30, true, 1005, 0, 0);
        try {
            inventory.addItem(veggieSoup);
        } catch (Exception e) {
            System.err.println("Gagal menambahkan Veggie Soup ke inventory: " + e.getMessage());
        }
    }
}
