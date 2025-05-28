package Items;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Items> items;
    private int capacity;

    public Inventory() {
        this.items = new ArrayList<>();
        this.capacity = 20; 
    }

    public Inventory(int capacity) {
        this.items = new ArrayList<>();
        this.capacity = capacity; 
    }

    public boolean addItem(Items item) {
        if (items.size() >= capacity) {
            System.out.println("Inventory penuh!");
            return false;
        }
        items.add(item);
        return true;
    }

    public boolean removeItem(Items item) {
        return items.remove(item);
    }

    public boolean containsItem(Items item) {
        return items.contains(item);
    }

    public List<Items> getItems() {
        return items;
    }

    public Items getItem(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return items.get(index);
    }

    public void displayInventory() {
        System.out.println("== Inventory ==");
        if (items.isEmpty()) {
            System.out.println("Inventory kosong.");
        } else {
            for (Items item : items) {
                System.out.println("- " + item.getName() + " (Sell: " + item.getSellPrice() + ")");
            }
        }
    }
    public int getCapacity() {
        return capacity;
    }
}