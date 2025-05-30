package Action.Cooking;

import java.util.List;
import java.util.ArrayList;
import Items.*;

public class Wine extends Cooking {
    List<Items> grapeFound = new ArrayList<>();
    @Override
    public boolean checkIngredients(Inventory inventory) {
        for (Items item : inventory.getItems()) {
            if (item.getName().equalsIgnoreCase("Grape")) {
                grapeFound.add(item); // Menambahkan Anggur yang ditemukan ke daftar
                return true; // Ditemukan Anggur dalam inventaris
            }
        }
        if(grapeFound.size() >= 2) {
            return true; // Cukup Anggur untuk membuat Wine
        }
        System.out.println("Bahan tidak cukup untuk membuat Wine.");
        return false; // Anggur tidak ditemukan dalam inventaris
    }

    @Override
    public void consumeIngredients(Inventory inventory) {
        if(grapeFound.size() < 2) {
            System.out.println("Tidak cukup Anggur untuk membuat Wine.");
            return; // Tidak cukup Anggur untuk membuat Wine
        }
        inventory.removeItem(grapeFound.get(0)); // Menghapus Anggur dari inventaris
        inventory.removeItem(grapeFound.get(1)); // Menghapus Anggur kedua dari inventaris
    }

    @Override
    public void addDishToInventory(Inventory inventory) {
        inventory.addItem((Items)FoodsList.Wine.create());
    }
}
