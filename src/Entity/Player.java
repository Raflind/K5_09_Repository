package Entity;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import Entity.NPC.*;
import Entity.PlayerNeeds.*;
import Exception.EnergyLowException;
import Items.Crops;
import Items.CropsList;
import Items.Inventory;
import Items.Items;
import Items.Seeds;
import main.GamePanel;
import main.KeyHandler;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Entity{
    //atribut related to game
    KeyHandler keyH;
    public Inventory inventory = new Inventory(); // ini nggak diinstansiasi di konstruktor?
    public final int screenX;
    public final int screenY;
    public BufferedImage lelah, hoe, axe, rod, can;
    public boolean enMove = true; // kl bisa move
    public int currTileNum;
    public int interactNPC = 0;

    //atribut related to player logic
    private String name;
    private String farmName;
    private int energy;
    public int MAX_ENERGY = 100;
    public enum Gender{
        F, M
    }
    private Gender gender;
    private NPC partner;
    public GoldManager goldManager = new GoldManager();
    public ShippingBin shippingBin = new ShippingBin();
    public enum Relationship {
        SINGLE,
        FIANCE,
        SPOUSE
    }
    public Relationship relationshipStatus = Relationship.SINGLE;

    /**
     * mau bikin constructor yang bisa masukin energy, name, farmName, dan gender
     * tapi takut ngeganggu si GamePanel jadi konstruktornya dibikin nanti aja - @hananda23
     */


    public Player(GamePanel gp, KeyHandler keyH, int worldX, int worldY){
        super(gp);
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenWidth/2 - gp.tileSize/2;
        solidArea = new Rectangle();
        solidArea.x = 15;
        solidArea.y = 18;
        solidArea.width = 18;
        solidArea.height = 30;
        speed = 5;
        direction = "diam";
        this.worldX = worldX;
        this.worldY = worldY;
        energy = MAX_ENERGY;
        getPlayerImage();
    }

    public void getPlayerImage(){
        try{
            diam = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Player/diam.png"));
            depan1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Player/depan1.png"));
            depan2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Player/depan2.png"));
            blkg1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Player/blkg1.png"));
            blkg2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Player/blkg2.png"));
            kanan1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Player/kanan1.png"));
            kanan2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Player/kanan2.png"));
            kiri1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Player/kiri1.png"));
            kiri2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Player/kiri2.png"));
            lelah = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Player/lelah.png"));
            hoe = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Player/Hoe.png"));
            axe = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Player/Pickaxe.png"));
            rod = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Player/Fishing.png"));
            can = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Player/WateringCan.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(gp.tileM.currMap.equals("Farm")){
            if(isVisitHouseTile()){
                gp.ui.showVisitHousePrompt = true;
            } else {
                gp.ui.showVisitHousePrompt = false;
            }
        }
        if(gp.tileM.currMap.equals("HousePlayer")){
            if(isSleepTile()){
                gp.ui.showSleepPrompt = true;
            } else {
                gp.ui.showSleepPrompt = false;
            }
        }
        if(gp.tileM.currMap.equals("HousePlayer")){
            if(isCookingTile()){
                gp.ui.showCookingScreen = true;
            }            
            else if(isTVTile()){
                gp.ui.showTVprompt = true;
            } 
            else {
                gp.ui.showCookingScreen = false;
                gp.ui.showTVprompt = false;
            }
        }
        if(gp.tileM.currMap.equals("Farm")){
            if(isShippingBinTile()){
                gp.ui.showShippingBinScreen = true;
            }
            else if (isFishingTile()){
                gp.ui.showFishPrompt = true;
            } 
            else {
                gp.ui.showShippingBinScreen = false;
                gp.ui.showFishPrompt = false;
            }
        }
        if(gp.tileM.currMap.equals("Mountain") || gp.tileM.currMap.equals("Forest") || gp.tileM.currMap.equals("Ocean")){
            if(isFishingTile()){
                gp.ui.showFishPrompt = true;
            } else {
                gp.ui.showFishPrompt = false;
            }
        }
        if(keyH.up == true && enMove){
            direction = "blkg";
        }
        else if(keyH.down == true && enMove){
            direction = "depan";
        }
        else if(keyH.right == true && enMove){
            direction = "kanan";
        }
        else if(keyH.left == true && enMove){
            direction = "kiri";
        }
        else if(enMove){
            direction = "diam";
        }
        //collision
        collisionOn = false;
        gp.cChecker.checkTile(this); 
        if(gp.npcManager.getActiveNPC()!=null){
            interactNPC  =  gp.cChecker.checkEntity(this, gp.npcManager.getActiveNPC());
        }
        if(collisionOn == false){
            switch(direction){
                case "depan":
                    worldY = Math.min(worldY + speed, gp.tileM.size * gp.tileSize - 4*gp.tileSize/3);
                    break;
                case "blkg":
                    worldY = Math.max(worldY - speed, 0);
                    break;
                case "kiri":
                    worldX = Math.max(worldX - speed, 0);
                    break;
                case "kanan":
                    worldX = Math.min(worldX + speed, gp.tileM.size * gp.tileSize - gp.tileSize);
                    break;
            }
        }
        if(keyH.up==true || keyH.down==true || keyH.left==true || keyH.right==true){
            spriteCounter++;
            if(spriteCounter>15){
                if(spriteNum==1){
                    spriteNum = 2;
                }
                else if(spriteNum==2){
                    spriteNum=1;
                }
                spriteCounter=0;
            }
        } else {
            spriteNum = 1; // kalau diam, spriteNum tetap 1
        }
        int tileX = (worldX + gp.tileSize / 2) / gp.tileSize;
        int tileY = (worldY + gp.tileSize / 2) / gp.tileSize;
        currTileNum = gp.tileM.mapTileNum[tileX][tileY];
    }

    public void draw(Graphics2D comp){
        BufferedImage image = null;
        switch(direction){
            case "depan":
            if(spriteNum==1){image = depan1;}
            if(spriteNum==2){image = depan2;}
            break;
            case "blkg":
            if(spriteNum==1){image = blkg1;}
            if(spriteNum==2){image = blkg2;}
            break;
            case "kiri":
            if(spriteNum==1){image = kiri1;}
            if(spriteNum==2){image = kiri2;}
            break;
            case "kanan":
            if(spriteNum==1){image = kanan1;}
            if(spriteNum==2){image = kanan2;}
            break;
            case "diam":
            image = diam;
            break;
            case "lelah":
            image = lelah;
            break;
            case "hoe":
            image = hoe;
            break;
            case "axe":
            image = axe;
            break;
            case "rod":
            image = rod;
            break;
            case "can":
            image = can;
            break;
        }
        comp.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    //getter
    public int getEnergy(){
        return energy;
    }
    public String getName(){
        return name;
    }
    public String getFarmName() {
        return farmName;
    }
    public Gender getGender(){
        return gender;
    }
    public GoldManager getPlayerWallet(){
        return goldManager;
    }
    public ShippingBin getShippingBin(){
        return shippingBin;
    }
    public NPC getPartner(){
        return partner;
    }
    public NPC.Relationship getPartnerStatus(){
        return getPartner().getRelationship();
    }
    public Inventory getInventory(){
        return inventory;
    }
    
    //setter

    public void setName(String name){
        this.name = name;
    }

    public void setEnergy(int energy) {
        if (energy <= MAX_ENERGY && energy >= -20) {
            this.energy = energy;
        } else if (energy > MAX_ENERGY) {
            this.energy = MAX_ENERGY;
        } else {
            this.energy = -20; // batas bawah energi
        }
    }

    public void consumeEnergy(int energy) {
        setEnergy(this.energy - energy);
        if(this.energy<=-20){
            gp.ui.isAction = true;
            direction = "lelah";
            enMove = false;
            gp.ui.isTired = true;
            new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                gp.ui.isTired = false;
                direction = "diam";
                enMove = true;
                gp.environmentStatus.bangun();
                setEnergy(10);
                gp.ui.isAction = false;
            }}, 3000);
        }
    }

    public void setFarmName(String farmName){
        this.farmName = farmName;
    }
    public void setGender(Gender gender){
        this.gender = gender;
    }

    public void setPartner(NPC partner){
        this.partner = partner;
    }

    public boolean isVisitHouseTile() {
        int[] visitTilesOld = {29, 31, 32, 37, 38, 39};
        for(int t : visitTilesOld){
            if(gp.cChecker.colTile1 == t || gp.cChecker.colTile2 == t){
                return true;
            }
        }
        return false;
    }

    public boolean isSleepTile() {
        int[] visitTiles = {28, 29, 40, 41, 51, 52, 66, 67};
        for(int t : visitTiles){
            if(gp.cChecker.colTile1 == t || gp.cChecker.colTile2 == t){
                return true;
            }
        }
        return false;
    }
    public boolean isCookingTile(){
        int[] cookingTiles = {26,27};
        for(int t : cookingTiles){
            if(gp.cChecker.colTile1 == t || gp.cChecker.colTile2 == t){
                return true;
            }
        }
        return false;
    }
    public boolean isShippingBinTile(){
        int[] shippingBinTiles = {44, 46, 47};
        for(int t : shippingBinTiles){
            if(gp.cChecker.colTile1 == t || gp.cChecker.colTile2 == t){
                return true;
            }
        }
        return false;
    }
    public boolean isTVTile(){
        int[] tvTiles = {19, 20};
        for(int t : tvTiles){
            if(gp.cChecker.colTile1 == t || gp.cChecker.colTile2 == t){
                return true;
            }
        }
        return false;
    }

    public void tilling(){
        if(currTileNum==1){
            gp.ui.isAction = true;
            consumeEnergy(5);
            direction = "hoe";
            enMove = false;
            int tileX = (worldX + gp.tileSize / 2) / gp.tileSize;
            int tileY = (worldY + gp.tileSize / 2) / gp.tileSize;   
            new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                direction = "diam";
                enMove = true;
                gp.tileM.mapTileNum[tileX][tileY] = 0;
                gp.ui.isAction = false;
            }}, 1000);
        }
    }

    public void untilling(){
        if(currTileNum==0){
            gp.ui.isAction = true;
            consumeEnergy(5);
            direction = "axe";
            enMove = false;
            int tileX = (worldX + gp.tileSize / 2) / gp.tileSize;
            int tileY = (worldY + gp.tileSize / 2) / gp.tileSize;
            new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                direction = "diam";
                enMove = true;
                gp.tileM.mapTileNum[tileX][tileY] = 1;
                gp.ui.isAction = false;
            }}, 1000);
        }
    }

    public boolean isFishingTile(){
        int[] fishingTiles = null;
        if(gp.tileM.currMap == "Farm"){
            fishingTiles = new int[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60};
        }
        if(gp.tileM.currMap == "Mountain"){
            fishingTiles = new int[]{320, 330, 345, 340, 322, 336, 327, 325, 323};
        }
        else if(gp.tileM.currMap == "Forest"){
            fishingTiles = new int[]{18};
        }
        else if(gp.tileM.currMap == "Ocean"){
            fishingTiles = new int[]{14};
        }
        for(int t : fishingTiles){
            if(gp.cChecker.colTile1 == t || gp.cChecker.colTile2 == t){
                return true;
            }
        }
        return false;
    }

    public void updateRelation(){
        switch (relationshipStatus) {
            case SINGLE:
                relationshipStatus = Relationship.FIANCE;
            case FIANCE:
                relationshipStatus = Relationship.SPOUSE;
            default:
                break;
        }
    }
    
    public boolean isSingle(){
        return relationshipStatus == Relationship.SINGLE;
    }

    public boolean isEngaged(){
        return relationshipStatus == Relationship.FIANCE;
    }

    public void planting(){
        int index = gp.ui.slotRow * 6 + gp.ui.slotCol;
        Items item = gp.player.inventory.getItem(index);
        if(currTileNum == 0 && item != null && item instanceof Seeds && ((Seeds) item).isPlantable(gp.environmentStatus.season)){
            gp.ui.isAction = true;
            consumeEnergy(5);
            direction = "diam";
            enMove = false;
            Seeds seed = (Seeds) item;
            gp.player.inventory.plantedSeeds.add(seed);
            gp.player.inventory.removeItem(seed);
            int tileX = (worldX + gp.tileSize / 2) / gp.tileSize;
            int tileY = (worldY + gp.tileSize / 2) / gp.tileSize;
            seed.posX = tileX;
            seed.posY = tileY;
            seed.dayelapsed = 0; // reset hari berlalu
            new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                enMove = true;
                gp.tileM.mapTileNum[tileX][tileY] = 66;
                gp.tileM.wetDaysLeft[tileX][tileY] = 0;
                gp.ui.isAction = false;
            }}, 1000);
        }
        else if(!(item instanceof Seeds)){
            gp.ui.isAction = false;
            System.out.println("item bukan benih");
        }
        else if(((Seeds) gp.player.inventory.getItem(index)).isPlantable(gp.environmentStatus.season)){
            System.out.println("weather tidak cocok");
        }
    }

    public void watering(){
        if(currTileNum == 67 || currTileNum == 66){
            gp.ui.isAction = true;
            consumeEnergy(5);
            direction = "can";
            enMove = false;
            int tileX = (worldX + gp.tileSize / 2) / gp.tileSize;
            int tileY = (worldY + gp.tileSize / 2) / gp.tileSize;
            Seeds seed = null;
            for(Seeds s : inventory.plantedSeeds){
                if(s.posX == tileX && s.posY == tileY){
                    seed = s;
                    break;
                }
            }
            if(seed != null){
                new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    enMove = true;
                    gp.ui.isAction = false;
                    direction = "diam";
                    gp.tileM.mapTileNum[tileX][tileY] = 67;
                    gp.tileM.wetDaysLeft[tileX][tileY] = 1; // basah 1 hari
                }}, 1000);
            }
        }
    }

    public void harvesting(){
    if (currTileNum == 68) {
        Seeds harvestedSeed = null;
        int tileX = (worldX + gp.tileSize / 2) / gp.tileSize;
        int tileY = (worldY + gp.tileSize / 2) / gp.tileSize;
        for (Seeds s : inventory.plantedSeeds) {
            if (s.posX == tileX && s.posY == tileY) {
                harvestedSeed = s;
                break;
            }
        }
        if (harvestedSeed != null) {
            if(harvestedSeed.getName().equals("Wheat Seeds")){
                Crops crop = CropsList.Wheat.create();
                for(int i = 0; i < crop.getCropsTotal(); i++){
                    gp.player.inventory.addItem(crop);
                }
                System.out.println("Berhasil panen: " + crop.getName());
            }
            else if(harvestedSeed.getName().equals("Parsnip Seeds")){
                Crops crop = CropsList.Parsnip.create();
                for(int i = 0; i < crop.getCropsTotal(); i++){
                    gp.player.inventory.addItem(crop);
                }
                System.out.println("Berhasil panen: " + crop.getName());
            }
            else if(harvestedSeed.getName().equals("Cauliflower Seeds")){
                Crops crop = CropsList.Cauliflower.create();
                for(int i = 0; i < crop.getCropsTotal(); i++){
                    gp.player.inventory.addItem(crop);
                }
                System.out.println("Berhasil panen: " + crop.getName());
            }
            else if(harvestedSeed.getName().equals("Potato Seeds")){
                Crops crop = CropsList.Potato.create();
                for(int i = 0; i < crop.getCropsTotal(); i++){
                    gp.player.inventory.addItem(crop);
                }
                System.out.println("Berhasil panen: " + crop.getName());
            }
            else if(harvestedSeed.getName().equals("Blueberry Seeds")){
                Crops crop = CropsList.Blueberry.create();
                for(int i = 0; i < crop.getCropsTotal(); i++){
                    gp.player.inventory.addItem(crop);
                }
                System.out.println("Berhasil panen: " + crop.getName());
            }
            else if(harvestedSeed.getName().equals("Tomato Seeds")){
                Crops crop = CropsList.Tomato.create();
                for(int i = 0; i < crop.getCropsTotal(); i++){
                    gp.player.inventory.addItem(crop);
                }
                System.out.println("Berhasil panen: " + crop.getName());
            }
            else if(harvestedSeed.getName().equals("Hot Pepper Seeds")){
                Crops crop = CropsList.HotPepper.create();
                for(int i = 0; i < crop.getCropsTotal(); i++){
                    gp.player.inventory.addItem(crop);
                }
                System.out.println("Berhasil panen: " + crop.getName());
            }
            else if(harvestedSeed.getName().equals("Melon Seeds")){
                Crops crop = CropsList.Melon.create();
                for(int i = 0; i < crop.getCropsTotal(); i++){
                    gp.player.inventory.addItem(crop);
                }
                System.out.println("Berhasil panen: " + crop.getName());
            }
            else if(harvestedSeed.getName().equals("Cranberry Seeds")){
                Crops crop = CropsList.Cranberry.create();
                for(int i = 0; i < crop.getCropsTotal(); i++){
                    gp.player.inventory.addItem(crop);
                }
                System.out.println("Berhasil panen: " + crop.getName());
            }
            else if(harvestedSeed.getName().equals("Pumpkin Seeds")){
                Crops crop = CropsList.Pumpkin.create();
                for(int i = 0; i < crop.getCropsTotal(); i++){
                    gp.player.inventory.addItem(crop);
                }
                System.out.println("Berhasil panen: " + crop.getName());
            }
            else if(harvestedSeed.getName().equals("Grape Seeds")){
                Crops crop = CropsList.Grape.create();
                for(int i = 0; i < crop.getCropsTotal(); i++){
                    gp.player.inventory.addItem(crop);
                }
                System.out.println("Berhasil panen: " + crop.getName());
            }

            
            //tambah totalharvested
            gp.cropHarvested++;

            // Hapus seed dari daftar tanam
            inventory.plantedSeeds.remove(harvestedSeed);

            // Ubah tile jadi tanah kosong
            gp.tileM.mapTileNum[tileX][tileY] = 0;
            gp.tileM.wetDaysLeft[tileX][tileY] = 0;
        }
    }
    }

}
