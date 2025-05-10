import java.util.ArrayList;
import java.util.List;

public class ShippingBin {
    private List<Item> itemsToShip;

    public ShippingBin() {
        this.itemsToShip = new ArrayList<>();
    }

    public void addItem(Item item) {
        itemsToShip.add(item);
        System.out.println(item.getName() + " ditambahkan ke Shipping Bin.");
    }

    public int calculateTotalValue() {
        int total = 0;
        for (Item item : itemsToShip) {
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
            for (Item item : itemsToShip) {
                System.out.println("- " + item.getName() + " (Sell: " + item.getSellPrice() + ")");
            }
        }
    }
}