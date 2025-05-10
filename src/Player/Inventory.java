import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;
    private int capacity;

    public Inventory() {
        this.items = new ArrayList<>();
        this.capacity = 20; 
    }

    public Inventory(int capacity) {
        this.items = new ArrayList<>();
        this.capacity = capacity; 
    }

    public boolean addItem(Item item) {
        if (items.size() >= capacity) {
            System.out.println("Inventory penuh!");
            return false;
        }
        items.add(item);
        return true;
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public boolean containsItem(Item item) {
        return items.contains(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void displayInventory() {
        System.out.println("== Inventory ==");
        if (items.isEmpty()) {
            System.out.println("Inventory kosong.");
        } else {
            for (Item item : items) {
                System.out.println("- " + item.getName() + " (Sell: " + item.getSellPrice() + ")");
            }
        }
    }
}