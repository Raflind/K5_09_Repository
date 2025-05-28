package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import Items.Inventory;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public Inventory inventory = new Inventory();
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenWidth/2 - gp.tileSize/2;
        solidArea = new Rectangle();
        solidArea.x = 15;
        solidArea.y = 18;
        solidArea.width = 18;
        solidArea.height = 30;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize*16; // 13 ini tile ke-n yg mau karakternya digambarin
        worldY = gp.tileSize*11; // sama kyk di atas
        speed = 5;
        direction = "diam";
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
            worldY += speed;
            break;
            case "blkg":
            worldY -= speed;
            break;
            case "kiri":
            worldX -= speed;
            break;
            case "kanan":
            worldX += speed;
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
}
