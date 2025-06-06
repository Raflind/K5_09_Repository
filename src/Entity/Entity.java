package Entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public BufferedImage diam, depan1, depan2, blkg1, blkg2, kanan1, kanan2, kiri1, kiri2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }
}
