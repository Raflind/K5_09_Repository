package Entity;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Entity.PlayerNeeds.*;
import Exception.EnergyLowException;
import Items.Inventory;
import NPC.NPC;
import NPC.Relationship;
import main.GamePanel;
import main.KeyHandler;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Entity{
    //atribut related to game
    GamePanel gp;
    KeyHandler keyH;
    public Inventory inventory = new Inventory(); // ini nggak diinstansiasi di konstruktor?
    public final int screenX;
    public final int screenY;
    public BufferedImage lelah;
    public boolean enMove = true; // kl bisa move

    //atribut related to player logic
    private String name;
    private String farmName;
    private int energy;
    public int MAX_ENERGY = 100;
    public enum Gender{
        F, M
    }
    private Gender gender;
    private Map<String, Relationship> partner = new HashMap<>();
    private GoldManager goldManager;
    private ShippingBin shippingBin;

    /**
     * mau bikin constructor yang bisa masukin energy, name, farmName, dan gender
     * tapi takut ngeganggu si GamePanel jadi konstruktornya dibikin nanti aja - @hananda23
     */


    public Player(GamePanel gp, KeyHandler keyH, int worldX, int worldY){
        this.gp = gp;
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
        energy = 20;
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
            } else {
                gp.ui.showCookingScreen = false;
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
        }
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
    public Relationship getPartnerStatus(String npcName){
        return partner.get(npcName);
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

    public void setPartnerStatus(NPC npc){
        partner.replace(npc.getName(), npc.getRelationship());

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
}
