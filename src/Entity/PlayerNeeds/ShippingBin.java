package Entity.PlayerNeeds;
import java.util.ArrayList;
import java.util.List;

import Items.Items;

public class ShippingBin {
    private List<Items> itemsToShip;

    public ShippingBin() {
        this.itemsToShip = new ArrayList<>();
    }

    public void addItem(Items item) {
        itemsToShip.add(item);
        System.out.println(item.getName() + " ditambahkan ke Shipping Bin.");
    }

    public int calculateTotalValue() {
        int total = 0;
        for (Items item : itemsToShip) {
            total += item.getSellPrice();
        }
        return total;
    }

    public void sellAll(GoldManager gm) {
        int totalGold = calculateTotalValue();
        gm.addGold(totalGold);
        itemsToShip.clear();
        System.out.println("Semua barang telah dijual. Total Gold: " + totalGold);
    }

    public void displayShippingItems() {
        System.out.println("== Shipping Bin ==");
        if (itemsToShip.isEmpty()) {
            System.out.println("Shipping Bin kosong.");
        } else {
            for (Items item : itemsToShip) {
                System.out.println("- " + item.getName() + " (Sell: " + item.getSellPrice() + ")");
            }
        }
    }
}