package Action.Fishing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import Entity.Player;
import Items.Fish;
import Items.Fish.FishType;


public class Fishing {
    private Random generator;
    private Player player;
    private List<Fish> possibleFish;
    private int attempt;
    public static Map<FishType, Integer> countFish = new HashMap<>();


    public Fishing(Player player){
        generator = new Random();
        this.player = player;
        possibleFish = new ArrayList<>();
        attempt = 0;
    }

    
    public void start(){

    }

    private Fish getFish(){
        int fishType = generator.nextInt(); // jadi indeks dari fish nya
        // return create

        
    }
}
