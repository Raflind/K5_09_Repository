package Action.Fishing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import Entity.Player;
import Items.Fish;
import Items.Fish.FishType;
import Items.FishList;
import Items.Inventory;
import TimeSeasonWeather.EnvironmentStatus;
import java.util.Scanner;


public class Fishing {
    private Random generator;
    private List<Fish> possibleFish;
    public int attempt;
    public Boolean guess;
    public Fish caughtFish;
    private static List<Fish> fishList = new ArrayList<>();
    static{
        for (FishList fish : FishList.values()) {
            fishList.add(fish.create());
        }
    };
    public static Map<FishType, Integer> countFish = new HashMap<>(
        Map.of(
            FishType.Regular, 10,
            FishType.Common, 10,
            FishType.Legendary, 7
        )
    );


    public Fishing(){
        generator = new Random();
        possibleFish = new ArrayList<>();
        attempt = 0;
        guess = false;
        caughtFish = null;
    }

    
    public void start(EnvironmentStatus.Season season, EnvironmentStatus.Weather weather, String location, int hour, Inventory inventory, List<Integer> guessList){
        possibleFish.clear();
        caughtFish = null;
        for(Fish fish : possibleFish){
            if(fish.isInLocation(location) && fish.isInSeason(season) && fish.isInWeather(weather) && fish.isInTime(hour)){
                possibleFish.add(fish);
            }
        }
        caughtFish = getFish();
        int attempt = countFish.get(caughtFish.getFishType());
        int chance;
        switch(caughtFish.getFishType()){
            case Regular:
                chance = 10;
                break;
            case Common:
                chance = 100;
                break;
            case Legendary:
                chance = 500;
                break;
            default:
                chance = 0;
        }
        int answer = generator.nextInt(chance);
        Scanner scanner = new Scanner(System.in);
        guess = false;
        for(int i = 0 ; i < attempt; i++){
            if(guessList.get(i) == answer){
                guess = true;
                System.out.println("You caught a " + caughtFish.getName() + "!");
                caughtFish.calculateSellPrice();
                inventory.addItem(caughtFish);
                break;
            } else {
                System.out.println("Wrong guess! Try again.");
            }
        }
    }

    private Fish getFish(){
        int fishType = generator.nextInt() % possibleFish.size(); // jadi indeks dari fish nya
        return possibleFish.get(fishType);
        // return create
    }
}
