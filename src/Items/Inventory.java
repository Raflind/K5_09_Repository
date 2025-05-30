package Items;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Inventory {
    public List<Items> items;
    private int capacity;

    public Inventory() {
        this.items = new ArrayList<>();
        this.capacity = 20; 
        try {
            items.add(new Items("Wheat", 10, 5, true, 1, ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Items/Wheat.png"))));
            items.add(new Items("Wheat", 10, 5, true, 1, ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Items/Wheat.png"))));
            items.add(new Items("Wheat", 10, 5, true, 1, ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Items/Wheat.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        } 
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