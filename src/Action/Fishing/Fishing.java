package Action.Fishing;
import java.util.Random;

import Entity.Player;
import Items.Inventory;
import TimeSeasonWeather.Time;


public class Fishing {
    private Random generator;
    private Player player;
    private Inventory inventory;
    private Time time;

    
    public void start(){

    }

    private Fish getFish(){
        int fishType = generator.nextInt(); // jadi indeks dari fish nya
        // return create

        
    }
}
