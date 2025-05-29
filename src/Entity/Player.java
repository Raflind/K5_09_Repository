package Entity;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
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

public class Player extends Entity{
    //atribut related to game
    GamePanel gp;
    KeyHandler keyH;
    public Inventory inventory = new Inventory(); // ini nggak diinstansiasi di konstruktor?
    public final int screenX;
    public final int screenY;

    //atribut related to player logic
    private String name;
    private String farmName;
    private int energy;
    private int MAX_ENERGY = 100;
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
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(gp.tileM.currMap.equals("Farm")){
            int playerTileX = worldX / gp.tileSize;
            int playerTileY = worldY / gp.tileSize;
            int tileNum = gp.tileM.mapTileNum[playerTileX][playerTileY];
            if(tileNum==38 || tileNum==39 || tileNum==29 || tileNum==37 || tileNum==31 || tileNum==32){
                gp.ui.showVisitHousePrompt = true;
            } else {
                gp.ui.showVisitHousePrompt = false;
            }
        }
        if(keyH.up == true){
            direction = "blkg";
        }
        else if(keyH.down == true){
            direction = "depan";
        }
        else if(keyH.right == true){
            direction = "kanan";
        }
        else if(keyH.left == true){
            direction = "kiri";
        }
        else{
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

    public void consumeEnergy(int energy) throws EnergyLowException{
        if (this.energy - energy >= 0) {
            this.energy -= energy;
        } else if (this.energy - energy < 0) {
            throw new EnergyLowException();
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


}
