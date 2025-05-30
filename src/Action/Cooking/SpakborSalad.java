package Action.Cooking;

import Items.*;

public class SpakborSalad extends Cooking {
    Items melon;
    Items cranberry;
    Items blueberry;
    Items tomato;
    @Override
    public boolean checkIngredients(Inventory inventory) {
        for(Items item : inventory.getItems()) {
            if (item.getName().equalsIgnoreCase("Melon")) {
                melon = item;
            }
            if(item.getName().equalsIgnoreCase("Cranberry")) {
                cranberry = item;
            }
            if(item.getName().equalsIgnoreCase("Blueberry")) {
                blueberry = item;
            }
            if(item.getName().equalsIgnoreCase("Tomato")) {
                tomato = item;
            }
        }
        if(melon != null && cranberry != null && blueberry != null && tomato != null) {
            return true; // Semua bahan tersedia
        }
        System.out.println("Bahan tidak cukup untuk memasak Spakbor Salad.");
        return false; // Tidak cukup bahan
    }

    @Override
    public void consumeIngredients(Inventory inventory) {
        if(melon == null || cranberry == null || blueberry == null || tomato == null) {
            System.out.println("Tidak cukup bahan untuk membuat Spakbor Salad.");
            return; // Tidak cukup bahan untuk membuat Spakbor Salad
        }
        inventory.removeItem(melon); // Menghapus Melon dari inventaris
        inventory.removeItem(cranberry); // Menghapus Cranberry dari inventaris
        inventory.removeItem(blueberry); // Menghapus Blueberry dari inventaris
        inventory.removeItem(tomato); // Menghapus Tomato dari inventaris
    }

    @Override
    public void addDishToInventory(Inventory inventory) {
        inventory.addItem((Items)FoodsList.SpakborSalad.create()); // Menambahkan Spakbor Salad ke inventaris
    }
    
}
