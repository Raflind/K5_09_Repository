package Action.Cooking;

import Items.*;

public class PumpkinPie extends Cooking {
    Items pumpkin;
    Items egg;
    Items wheat;
    @Override
    public boolean checkIngredients(Inventory inventory) {
        for (Items item : inventory.getItems()) {
            if (item.getName().equalsIgnoreCase("Pumpkin")) {
                pumpkin = item;
            }
            if(item.getName().equalsIgnoreCase("Egg")) {
                egg = item;
            }
            if(item.getName().equalsIgnoreCase("Wheat")) {
                wheat = item;
            }
        }

        if (pumpkin != null && egg != null && wheat != null) {
            return true;
        } else {
            System.out.println("Bahan tidak cukup untuk memasak Pumpkin Pie.");
            return false;
        }
    }

    @Override
    public void consumeIngredients(Inventory inventory) {
        if(pumpkin == null || egg == null || wheat == null) {
            System.out.println("Tidak cukup bahan untuk membuat Pumpkin Pie.");
            return; // Tidak cukup bahan untuk membuat Pumpkin Pie
        }
        inventory.removeItem(pumpkin); // Menghapus Pumpkin dari inventaris
        inventory.removeItem(egg); // Menghapus Egg dari inventaris
        inventory.removeItem(wheat); // Menghapus Wheat dari inventaris
    }

    @Override
    public void addDishToInventory(Inventory inventory) {
        inventory.addItem((Items)FoodsList.PumpkinPie.create()); // Menambahkan Pumpkin Pie ke inventaris
    }
}
