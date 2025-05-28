package Action.Cooking;

import Items.*;

public class Fugu extends Cooking {
    Fish fuguFish;
    @Override
    public boolean checkIngredients(Inventory inventory) {
        for(Items item : inventory.getItems()) {
            if(item instanceof Fish) {
                if(item.getName().equalsIgnoreCase("Pufferfish")) {
                    fuguFish = (Fish) item; // Menyimpan referensi ke Fugu
                    return true; // Ditemukan Fugu dalam inventaris
                }
            }
        }
        System.out.println("Bahan tidak cukup untuk memasak Fugu.");
        return false; // Fugu tidak ditemukan dalam inventaris
    }

    @Override
    public void consumeIngredients(Inventory inventory) {
        if(fuguFish == null) {
            System.out.println("Tidak ada Fugu yang ditemukan untuk memasak.");
            return; // Tidak ada Fugu untuk dimasak
        }
        inventory.removeItem(fuguFish); // Menghapus Fugu dari inventaris
    }

    @Override
    public void addDishToInventory(Inventory inventory) {
        // Implementasi logika untuk menambahkan Fugu ke inventaris
        Foods fugu = ItemFactory.createFoods("Fugu", 20, 50, true, 1004, 0, 0);
        try {
            inventory.addItem(fugu);
        } catch (Exception e) {
            System.err.println("Gagal menambahkan Fugu ke inventory: " + e.getMessage());
        }
    }
}
