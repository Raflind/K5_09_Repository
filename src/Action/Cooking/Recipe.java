package Action.Cooking;

import java.util.List;

import Items.Inventory;

public class Recipe {
    private String name;
    private boolean unlocked;
    private Cooking cookingAction;

    public Recipe(String name, Boolean unlocked, Cooking cookingAction) {
        this.name = name;
        this.unlocked = unlocked;
    }

    public String getName() {
        return name;
    }
    public boolean isUnlocked() {
        return unlocked;
    }
    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }
    public void unlock() {
        this.unlocked = true;
    }
    public void cook(Inventory inventory) {
        if (cookingAction != null) {
            cookingAction.cook(inventory);
        } else {
            System.out.println("Tidak ada aksi memasak yang ditetapkan untuk resep ini.");
        }
    }
}
