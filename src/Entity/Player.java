package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    String direction;
    
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
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
        y -= speed;
        direction = "blkg";
    }
    else if(keyH.down == true){
        y += speed;
        direction = "depan";
    }
    else if(keyH.right == true){
        x += speed;
        direction = "kanan";
    }
    else if(keyH.left == true){
        x -= speed;
        direction = "kiri";
    }
    else{
        direction = "diam";
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
        comp.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
