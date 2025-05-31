package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import Action.Store.Store;
import Entity.Player;
import Items.CropsList;
import Items.FishList;
import Map.TileManager;
import TimeSeasonWeather.*;
import Action.Cooking.*;
import Entity.NPCManager;
import Action.Fishing.Fishing;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
   final int initTileSize = 16;
   final int scale = 3;
   final public int tileSize = initTileSize*scale;
   final public int w = 16;
   final public int h = 12;
   final public int screenWidth = w*tileSize; //16*16*3=768
   final public int screenHeight =h*tileSize; // 16*12*3 = 576
   final int FPS = 60;
   private int frameCounter = 0;
   public TileManager farmMap, oceanMap, forestMap, mountainMap, storeMap, housePlayerMap, abigailMap, carolineMap, dascoMap, mayorMap, perryMap; /*blm lengkap*/
   public TileManager tileM;
   public NPCManager npcManager;
   KeyHandler keyH = new KeyHandler(this);
   

   public Fishing fishing = new Fishing();
   public Cooking curCooking;
   public UI ui = new UI(this);
   public Time time = new Time(6, 0);
   public Store store = new Store();
   public List<Integer> guessList = new ArrayList<>();
   public HashMap<String, Boolean> recipe = new HashMap<String, Boolean>() {{
    put("Fish And Chips", false);
    put("Baguette", true);
    put("Sashimi", false);
    put("Fugu", false);
    put("Wine", true);
    put("Pumpkin Pie", true);
    put("Fish Stew", false);
    put("Veggie Soup", false);
    put("Spakbor Salad", true);
    put("Fish Sandwich", false);
    put("Legends of Spakbor", false);
}};
   Timer timer = new Timer();
   public EnvironmentStatus environmentStatus = new EnvironmentStatus(time, this);
   Thread gameThread;
   public CollisionChecker cChecker = new CollisionChecker(this);
   public Player player;
   //default pos
   int x = 100;
   int y = 100;
   int speed = 5;
   public int stoveFuel = 2;
   //world setting
   final public int worldwh = 32;
   public final int worldWidth = worldwh*tileSize;
   public final int worldHeight = worldwh*tileSize;
   //state
   public int gameState;
   public final int titleState = 0;
   public final int inventoryState = 1;
   public final int playState = 2;
   public final int dialogueState = 3; 
   public final int pauseState = 4;
   public final int mapSelectState = 5;
   public final int optionState = 6;
   public final int cookingState = 7;
   public final int shippingBinState = 8;
   public final int insufficientResourcesCookingState = 9;
   public final int fishingState = 10;
   public final int insufficientEnergyState = 11;
   public final int stoveState = 12; 
   public final int addFuelState = 13; 
   public final int storeState = 14;
   public final int giftState = 15;
   public final int watchState = 16;
   public final int endgameState = 17;

    public int subState = 0;
    public final int subState_none = 0;
    public final int subState_help = 1;
    public final int subState_listObject = 2;
    public final int subState_statistics = 3;
    public final int subState_actions = 4;
    public int lastDay = -1;

    //endgame
    public List<Integer> fishcaught= new ArrayList<>(){{
        add(0);
        add(0);
        add(0);
    }};
    int totalIncome = 0;
    int totalExpenditure = 0;
    int averageIncome = 0;
    int averageExpenditure = 0;
    int totaldays = 1;
    public int cropHarvested = 0;



   public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
    farmMap = new TileManager(this, "Farm");
    oceanMap = new TileManager(this, "Ocean");
    forestMap = new TileManager(this, "Forest");
    mountainMap = new TileManager(this, "Mountain");
    storeMap = new TileManager(this, "Store");
    housePlayerMap = new TileManager(this, "HousePlayer");
    abigailMap = new TileManager(this, "Abigail");
    carolineMap = new TileManager(this, "Caroline");
    dascoMap = new TileManager(this, "Dasco");
    mayorMap = new TileManager(this, "Mayor");
    perryMap = new TileManager(this, "Perry");
    tileM = farmMap;
    player = new Player(this, keyH, tileM.worldX*tileSize, tileM.worldY*tileSize);
    npcManager = new Entity.NPCManager(this);
    setupGame();
   }

   public void setupGame(){
    gameState = titleState;
   }

   public void startGameThread(){
    gameThread = new Thread(this);
    gameThread.start();
   }

   @Override
   public void run(){
    double intervalDraw = 1000000000/FPS;
    double nextDrawTime = System.nanoTime() + intervalDraw;
    while(gameThread != null){
        update(); // update position
        repaint(); //draw layar dgn updated position
        try{
            double remainingTime = (nextDrawTime - System.nanoTime())/1000000; // in milis
            if(remainingTime<0){
                remainingTime = 0;
            }
            Thread.sleep((long) remainingTime);
            nextDrawTime+=intervalDraw;
        }
        catch (InterruptedException e){
            e.printStackTrace();;
        }
    }
   }

   public void update(){
    if(gameState==playState){
        player.update();
        npcManager.activeNPC();      // Pilih NPC aktif sesuai map
        npcManager.updateActiveNPC(); // Update NPC aktif
        frameCounter++;
        if(frameCounter >= FPS && !ui.isAction) {
            time.addFiveMinutes();
            environmentStatus.setTime(time);
            environmentStatus.nextDay();
            if (environmentStatus.getDay() != lastDay) {
                totaldays++;
                lastDay = environmentStatus.getDay();
            }
            checkEndgameMarried();
            checkEndgamegold();
            checkPufferfish();
            checkFishingCaught();
            checkHotPepper();
            checkLegend();
            calculateAverageExpenditure();
            calculateAverageIncome();
            frameCounter = 0;
            // Update other environmental aspects if needed
        }
    }

    else if(gameState == optionState) {
        if (subState == subState_none) {
            if (keyH.up) {
                ui.commandNum--;
                if (ui.commandNum < 0) {
                    ui.commandNum = 5; 
                }
                keyH.up = false;
            }
            if (keyH.down) {
                ui.commandNum++;
                if (ui.commandNum > 5) {
                    ui.commandNum = 0;
                }
                keyH.down = false;
            }

            if (keyH.enterPressed) {
                switch (ui.commandNum) {
                    case 0: subState = subState_help; break;
                    case 1: subState = subState_listObject; break;
                    case 2: subState = subState_statistics; break;
                    case 3: subState = subState_actions; break;
                    case 4: gameState = titleState; break; 
                    case 5: gameState = playState; break;  
                }
                keyH.enterPressed = false;
            }

            if (keyH.escPressed) {
                gameState = playState;
                keyH.escPressed = false;
            }
        } else {
            if (keyH.enterPressed || keyH.escPressed) {
                subState = subState_none; 
                keyH.enterPressed = false;
                keyH.escPressed = false;
            }
        }
    }
   }
   
   @Override
    public void paintComponent(Graphics g){
        Graphics2D comp = (Graphics2D) g;
        if(gameState == titleState){
            ui.draw(comp);
        }
        else if(gameState == playState){
            super.paintComponent(g);
            tileM.draw(comp, tileM.getActiveTileArray());
            npcManager.drawActiveNPC(comp); // <-- Tambahkan ini
            player.draw(comp);
            ui.draw(comp);
        }
        else if(gameState == inventoryState){
            ui.draw(comp);
        }
        else if(gameState == dialogueState){
           // Draw dialogue box or other UI elements for dialogue state
            // This can be implemented later
            ui.draw(comp); // Ini akan memanggil ui.draw(comp)
        }
        else if(gameState == pauseState){ 
            ui.draw(comp);
        }
        else if(gameState == mapSelectState){
            ui.draw(comp);
        }
        else if(gameState == optionState) {
            ui.draw(comp);;
        }
        else if(gameState == cookingState){
            ui.draw(comp);
        }
        else if(gameState == shippingBinState){
            ui.draw(comp);
        }
        else if(gameState == insufficientResourcesCookingState){
            ui.draw(comp);
        }
        else if(gameState == fishingState){
            ui.draw(comp);
        }
        else if(gameState == insufficientEnergyState){
            ui.draw(comp);
        }   
        else if(gameState == stoveState){
            ui.draw(comp);
        }
        else if(gameState == addFuelState){
            ui.draw(comp);
        }
        else if(gameState == storeState){
            ui.draw(comp);
        }
        else if(gameState == giftState){
            ui.draw(comp);
        }
        else if(gameState == watchState){
            ui.draw(comp);
        }
        else if(gameState == endgameState){
            ui.draw(comp);
        }
        comp.dispose();
    }
    public void cookSelectedRecipe(String recipeName){
        Cooking cooking = null;
        switch(recipeName) {
            case "Fish And Chips":
                cooking = new FishNChips();
                break;
            case "Baguette":
                cooking = new Baguette();
                break;
            case "Sashimi":
                cooking = new Sashimi();
                break;
            case "Fugu":
                cooking = new Fugu();
                break;
            case "Wine":
                cooking = new Wine();
                break;
            case "Pumpkin Pie":
                cooking = new PumpkinPie();
                break;
            case "Fish Stew":
                cooking = new FishStew();
                break;
            case "Spakbor Salad":
                cooking = new SpakborSalad();
                break;
            case "Fish Sandwich":
                cooking = new FishSandwich();
                break;
            case "Legends of Spakbor":
                cooking = new TheLegendsofSpakbor();
                break;
        }
        if(cooking != null){
            if(!cooking.checkIngredients(player.getInventory()) || stoveFuel < 1){
                gameState = insufficientResourcesCookingState;
                
            }
            else{
                cooking.cook(player.getInventory());
                gameState = playState;
            }
        }
    }
    public void checkPufferfish(){
        if(player.inventory.containsItem(FishList.Pufferfish.create())){
            recipe.put("Fugu", true);
        }
    }
    public void checkFishingCaught(){
        int total = 0;
        for(Integer i : fishcaught){
            total += i;
        }
        if(total >= 10){
            recipe.put("Sashimi", true);
        }
    }
    public void checkHotPepper(){
        if(player.inventory.containsItem(CropsList.HotPepper.create())){
            recipe.put("Fish Stew", true);
        }
    }
    public void checkLegend(){
        if(player.inventory.containsItem(FishList.Legend.create()) || player.inventory.containsItem(FishList.Glacierfish.create()) || player.inventory.containsItem(FishList.CrimsonFish.create())){
            recipe.put("Legends of Spakbor", true);
        }
    }
    public void checkVeggie(){
        if(cropHarvested >= 1){
            recipe.put("Veggie Soup", true);
        }
    }
    public void calculateAverageIncome() {
        if (totaldays > 0) {
            averageIncome = totalIncome / Math.max(Math.floorDiv(totaldays, 30), 1);
        } else {
            averageIncome = 0; // Avoid division by zero
        }
    }
    public void calculateAverageExpenditure() {
        if (totaldays > 0) {
            averageExpenditure = totalExpenditure / Math.max(Math.floorDiv(totaldays, 30), 1);
        } else {
            averageExpenditure = 0; // Avoid division by zero
        }
    }
    public void checkEndgamegold(){
        if(player.goldManager.getGold() >= 17209){
            gameState = endgameState;
        }
    }
    public void checkEndgameMarried(){
        if(player.relationshipStatus == player.relationshipStatus.SPOUSE){
            gameState = endgameState;
        }
    }
}
