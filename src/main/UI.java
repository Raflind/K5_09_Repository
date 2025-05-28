package main;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    public int slotCol = 0;
    public int slotRow = 0;
    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        if(gp.gameState == gp.playState){
            drawGameStatus();
        }
        /* if (gp.gameState == gp.pauseState) {
            drawPauseScreen(); }*/
        if (gp.gameState == gp.inventoryState){
            drawInventory();
            drawGameStatus();
        }
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        
    }
    
    public void drawPauseScreen() {
        g2.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight); // Fill the screen
        
        g2.setColor(Color.WHITE);
        g2.drawString("Game Paused", gp.screenWidth / 2 - 50, gp.screenHeight / 2 - 20);
        g2.drawString("Press P to Resume", gp.screenWidth / 2 - 70, gp.screenHeight / 2 + 20);
    }
    public void drawInventory() {
        //FRAME
        int frameX = gp.tileSize*9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;

        //CURSOR
        int cursorX = slotX + (gp.tileSize * slotCol);
        int cursorY = slotY + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorheight = gp.tileSize;

        //DRAW CURSOR
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorheight, 10, 10); 

        /*DRAW ITEMS
        for(int i = 0; i < gp.player.inventory.getCapacity(); i++) {
            if(gp.player.inventory.getItem(i) != null) {
                g2.drawImage(gp.player.inventory.getItem(i).image, slotX, slotY, null);
            }
            slotX += gp.tileSize; // Move to the next slot
            if((i + 1) % 4 == 0) { // Move to the next row after 4 items
                slotX = slotXstart;
                slotY += gp.tileSize;
            }
        }*/
    }
    public void drawSubWindow(int x, int y, int width, int height) {
        g2.setColor(new Color(0, 0, 0, 200)); // Semi-transparent black
        g2.fillRoundRect(x, y, width, height, 35, 35); // Fill the window
        
        g2.setColor(new Color(255, 255, 255)); // Semi-transparent white for the border
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25,25); // Draw the border
    }

    public void drawGameStatus(){
        int width = gp.tileSize*5;
        int height = gp.tileSize*2;
        int x = gp.screenWidth - width - gp.tileSize / 2;
        int y = gp.tileSize / 2;

        drawSubWindow(x, y, width, height);
            
        String time = "6.00";
        String weather = "Sunny";
        String season = "Spring";

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(24f));
        int textX = x + 20;
        int textY = y + 15;
        g2.drawString("Time: " + time, textX, textY + 30);
        g2.drawString("Weather: " + weather, textX, textY + 60);
        
    }
}
