package Action.Cooking;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


import Items.*;

public abstract class Cooking {
    public final void cook(Inventory inventory) {
        if(checkIngredients(inventory)) {
            consumeIngredients(inventory);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    addDishToInventory(inventory);
                    System.out.println("Masakan selesai dimasak!");
                }
            }, 12000);
        } else {
            System.out.println("Bahan tidak cukup untuk memasak.");
        }
    }
    public abstract boolean checkIngredients(Inventory inventory);
    abstract void consumeIngredients(Inventory inventory);
    abstract void addDishToInventory(Inventory inventory);
}
