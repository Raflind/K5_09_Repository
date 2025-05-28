package main;

import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font; 
import java.awt.Graphics2D;
import java.awt.FontFormatException;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font stardew;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean finish = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    public List<MapInfo> mapList;
    public int mapSelectionNum = 0; // Untuk navigasi pilihan map
    Color kuning = new Color(0xE0A100);
    Color kuninggelap = new Color(0x413014);
    public int slotCol = 0;
    public int slotRow = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        mapList = new ArrayList<>();
        mapList.add(new MapInfo("Farm", "res/MapSelect/Farm.png"));
        mapList.add(new MapInfo("Forest River", "res/MapSelect/Forest.png"));
        mapList.add(new MapInfo("Mountain Lake", "res/MapSelect/Mountain.png"));
        mapList.add(new MapInfo("Ocean", "res/MapSelect/Ocean.png"));
        mapList.add(new MapInfo("Store", "res/MapSelect/Store.png"));
        mapList.add(new MapInfo("Abigail's House", "res/MapSelect/Abigail.png"));
        mapList.add(new MapInfo("Caroline's House", "res/MapSelect/Caroline.png"));
        mapList.add(new MapInfo("Dasco's House", "res/MapSelect/Dasco.png"));
        mapList.add(new MapInfo("Mayor Tadi's House", "res/MapSelect/MayorTadi.png"));
        mapList.add(new MapInfo("Perry's House", "res/MapSelect/Perry.png"));
        loadCustomFont();
    }

    private void loadCustomFont() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("res/font/StardewValley.ttf");
            if (is == null) {
                System.err.println("Font file not found: res/font/StardewValley.ttf");
                return;
            }
            stardew = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
        } catch (FontFormatException e) {
            System.err.println("Error loading font: Invalid font format.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error loading font: IO exception occurred.");
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        if(gp.gameState == gp.playState){
            drawGameStatus();
        }
        if (gp.gameState == gp.inventoryState){
            drawInventory();
        }
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        if(gp.gameState == gp.titleState){
            if(titleScreenState==0){
                drawTitleScreen();
            }
            else if (titleScreenState == 1) {
                drawStoryScreen(); 
            }
        }
        if(gp.gameState == gp.mapSelectState){
            drawMapSelectionScreen();
        }
    }
    
    public void drawTitleScreen(){
        final BufferedImage judul, bg;
        int x = 150;
        int y = 16;
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        try{
            bg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Menu/Background.png"));
            g2.drawImage(bg, 0, 0, null); 
            judul = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Menu/SPAKBOR.png"));
            g2.drawImage(judul, x, y, judul.getWidth()*2, judul.getHeight()*2, null);
            g2.setFont(stardew.deriveFont(Font.PLAIN, 48F));
            String text = "NEW GAME";
            x = getXforCenteredText(text);
            y+=judul.getHeight()*2+64+32;
            g2.setColor(kuninggelap);
            g2.drawString(text, x+3, y+3);
            g2.setColor(kuning);
            g2.drawString(text, x, y);
            if (commandNum==0){
                g2.setColor(kuninggelap);
                g2.drawString(">", x-32+3, y+3);
                g2.setColor(kuning);
                g2.drawString(">", x-32, y);
            }
            text = "CREDITS";
            x = getXforCenteredText(text);
            y+=64;
            g2.setColor(kuninggelap);
            g2.drawString(text, x+3, y+3);
            g2.setColor(kuning);
            g2.drawString(text, x, y);
            if (commandNum==1){
                g2.setColor(kuninggelap);
                g2.drawString(">", x-32+3, y+3);
                g2.setColor(kuning);
                g2.drawString(">", x-32, y);
            }
            text = "EXIT";
            x = getXforCenteredText(text);
            y+=64;
            g2.setColor(kuninggelap);
            g2.drawString(text, x+3, y+3);
            g2.setColor(kuning);
            g2.drawString(text, x, y);
            if (commandNum==2){
                g2.setColor(kuninggelap);
                g2.drawString(">", x-32+3, y+3);
                g2.setColor(kuning);
                g2.drawString(">", x-32, y);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public class MapInfo {
        public String name;
        public BufferedImage image; 
        String imagePath;

        public MapInfo(String name , String imagePath) {
            this.name = name;
            this.imagePath = imagePath;
             try{
                this.image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imagePath));
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    public void drawMapSelectionScreen() {
        g2.setColor(Color.black); 
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        BufferedImage bg;
        try{
            bg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Menu/Background-dimmed.png"));
            g2.drawImage(bg, 0, 0, null); 
        }
        catch(IOException e){
            e.printStackTrace();
        }

        g2.setFont(stardew.deriveFont(Font.BOLD, 60F));
        String title = "Choose The Map";
        int titleX = getXforCenteredText(title);
        int titleY = gp.tileSize * 2;

        g2.setColor(kuninggelap);
        g2.drawString(title, titleX + 4, titleY + 4);
        g2.setColor(kuning);
        g2.drawString(title, titleX, titleY);

        // Map
        int mapsPerRow = 5;
        int mapSize = gp.tileSize * 2;
        int xPadding = gp.tileSize / 2 + 12;
        int yPadding = gp.tileSize / 2 + 12; 
        int totalGridWidth = (mapSize * mapsPerRow) + (xPadding * (mapsPerRow - 1));
        int mapStartX = (gp.screenWidth - totalGridWidth) / 2; 
        int mapStartY = titleY + gp.tileSize;

        // Map drawing
        for (int i = 0; i < mapList.size(); i++) {
            int row = i / mapsPerRow;
            int col = i % mapsPerRow;

            int currentX = mapStartX + (mapSize + xPadding) * col;
            int currentY = mapStartY + (mapSize + yPadding + gp.tileSize - 12) * row;

            // Kotak Map
            g2.setColor(Color.BLACK);
            g2.drawImage(mapList.get(i).image, currentX, currentY, null);
            
            if (i == mapSelectionNum) {
                g2.setColor(Color.white);
                g2.drawRect(currentX, currentY, mapSize, mapSize); 
                g2.drawRect(currentX + 1, currentY + 1, mapSize - 2, mapSize - 2); 
            }

            g2.setFont(stardew.deriveFont(Font.PLAIN, 20F));
            String mapName = mapList.get(i).name;
            int nameX = currentX + (mapSize - (int)g2.getFontMetrics().getStringBounds(mapName, g2).getWidth()) / 2;
            int nameY = currentY + mapSize + 20;

            g2.setColor(kuninggelap);
            g2.drawString(mapName, nameX + 2, nameY + 2);
            g2.setColor(kuning);
            g2.drawString(mapName, nameX, nameY);
        }
    
        g2.setFont(stardew.deriveFont(Font.PLAIN, 32F));
        String backText = "BACK";
        int backY = gp.screenHeight - gp.tileSize;
        int backX = getXforCenteredText(backText);

        g2.setColor(kuninggelap);
        g2.drawString(backText, backX + 3, backY + 3);
        g2.setColor(kuning);
        g2.drawString(backText, backX, backY);

        if (mapSelectionNum == mapList.size()) {
            g2.setColor(kuninggelap);
            g2.drawString(">", backX - 32 + 3, backY + 3);
            g2.setColor(kuning);
            g2.drawString(">", backX - 32, backY);
        }
    }

     public void drawStoryScreen() {
        g2.setColor(Color.black); 
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        BufferedImage bg;
        try{
            bg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Menu/Background-dimmed.png"));
            g2.drawImage(bg, 0, 0, null); 
        }
        catch(IOException e){
            e.printStackTrace();
        }

        g2.setFont(stardew.deriveFont(Font.PLAIN, 36F));
        g2.setColor(Color.white);

        String storyLine1 = "Now that Dr. Asep has decided to reset his life,";
        String storyLine2 = "he tried to do something he has never done before...";
        String storyLine3 = "FARMING";

        int startY = gp.screenHeight / 2 - gp.tileSize;
        int lineHeight = (int)g2.getFontMetrics(stardew.deriveFont(Font.PLAIN, 36F)).getHeight();

        g2.setColor(kuning);
        int x1 = getXforCenteredText(storyLine1);
        g2.drawString(storyLine1, x1, startY);
        int x2 = getXforCenteredText(storyLine2);
        g2.drawString(storyLine2, x2, startY + lineHeight);
        int x3 = getXforCenteredText(storyLine3);
        g2.drawString(storyLine3, x3, startY + (lineHeight * 2));

        g2.setFont(stardew.deriveFont(Font.PLAIN, 24F));
        g2.setColor(kuning);
        String continueText = "CLICK ENTER to continue";
        
        int continueTextX = gp.screenWidth - (int)g2.getFontMetrics().getStringBounds(continueText, g2).getWidth() - gp.tileSize / 2;
        int continueTextY = gp.screenHeight - gp.tileSize / 2;
        g2.drawString(continueText, continueTextX, continueTextY);
    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public void drawPauseScreen() {
        BufferedImage bg;
        try{
            bg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Menu/Background-dimmed.png"));
            g2.drawImage(bg, 0, 0, null); 
        }
        catch(IOException e){
            e.printStackTrace();
        }
        g2.setFont(stardew.deriveFont(Font.BOLD, 48F));
        g2.setColor(kuninggelap);
        g2.drawString("Game Paused", getXforCenteredText("Game Paused") + 3, gp.screenHeight / 2 - 20 + 3);
        g2.setColor(kuning);
        g2.drawString("Game Paused", getXforCenteredText("Game Paused"), gp.screenHeight / 2 - 20);
        g2.setColor(kuninggelap);
        g2.drawString("Press ESC to Resume", getXforCenteredText("Press ESC to Resume") + 3, gp.screenHeight / 2 + 20 + 3);
        g2.setColor(kuning);
        g2.drawString("Press ESC to Resume", getXforCenteredText("Press ESC to Resume"), gp.screenHeight / 2 + 20);
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
        g2.setColor(new Color(139, 69, 19, 180)); // Semi-transparent brown
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25,25); // Draw the border
    }

    public void drawGameStatus(){
        int width = gp.tileSize*5;
        int height = gp.tileSize*2;
        int x = gp.screenWidth - width - gp.tileSize / 2;
        int y = gp.tileSize / 2;

        drawSubWindow(x, y, width, height);
            
        int hour = gp.time.getHour();
        int minute = gp.time.getMinute();
        int day = gp.environmentStatus.getDay();
        String season = gp.environmentStatus.season.name();

        g2.setColor(kuning);
        g2.setFont(stardew.deriveFont(Font.BOLD, 36F));
        int textX = x + 20;
        int textY = y + 15;
        g2.drawString("Time: " + hour + ":" + minute, textX, textY + 30);
        g2.drawString("Day " + day, textX, textY + 60);   
    }
}
