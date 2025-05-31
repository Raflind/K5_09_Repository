package Action.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Entity.Player;
import Items.*;
import main.GamePanel;


public class Store {
    private List<String> itemsStore;
    private HashMap<String, Boolean> RecipeStore;
    private GamePanel gp;
    private Inventory inventory;
    private Player player;
    private List<ItemCreator<? extends Items>> itemCreators;

    public Store(GamePanel gp){
        this.gp = gp;
        this.player = gp.player;
        this.inventory = player.inventory;
        this.RecipeStore = gp.recipe;
        loadItemsStore();
    }


    private void loadItemsStore(){
        itemsStore = new ArrayList<>();
        for (ItemMiscList item : ItemMiscList.values()) {
            itemsStore.add(item.getName());
            itemCreators.add(item);
        }
        for (FoodsList food : FoodsList.values()) {
            itemsStore.add(food.getName());
            itemCreators.add(food);
        }
        for (SeedsList seed : SeedsList.values()) {
            itemsStore.add(seed.getName());
            itemCreators.add(seed);
        }
        for (CropsList crop : CropsList.values()) {
            itemsStore.add(crop.getName());
            itemCreators.add(crop);
        }

    }

    public void pay(int amount){
        if (player.goldManager.spendGold(amount)){
            System.out.println("Payment successful. You paid: " + amount + " gold.");
        } else {
            System.out.println("Not enough gold to pay: " + amount + " gold.");
        } 
    }

    public void buyRecipe(String recipeName){
        if (recipeName.equals(" Fish Sandwich") || recipeName.equals("Fish n' Chips")){
            if (!RecipeStore.get(recipeName)) {
                if(recipeName.equals(" Fish Sandwich")){
                    pay(150);
                } else if(recipeName.equals("Fish n' Chips")){
                    pay(100);
                }
                RecipeStore.put(recipeName, true);
                System.out.println("You have bought the recipe: " + recipeName);
            } else {
                System.out.println("You already own this recipe");
            }
        } else {
            System.out.println("Recipe is not available in the store");
        }
    }

     public void buyItem(String itemName) {
        if (!itemsStore.contains(itemName)) {
            System.out.println("Item not found in the store");
            return;
        }

        // Cari ItemCreator dari itemCreators
        for (ItemCreator<? extends Items> creator : itemCreators) {
            if (creator.getName().equalsIgnoreCase(itemName)) {
                Items item = creator.create();

                if (player.goldManager.spendGold(item.getBuyPrice())) {
                    inventory.addItem(item);
                    System.out.println("You have bought: " + item.getName());
                } else {
                    System.out.println("Not enough gold to buy: " + item.getName());
                }
                return; // stop loop
            }
        }

        // fallback, kalau ternyata tidak ketemu (tidak mungkin terjadi jika itemsStore benar)
        System.out.println("Unexpected error: item template not found for " + itemName);
    }

    public List<String> getItemsStore() {
        return itemsStore;
    }
    public HashMap<String, Boolean> getRecipeStore() {
        return RecipeStore;
    }

}
