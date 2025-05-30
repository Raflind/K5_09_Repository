package main;

import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font; 
import java.awt.Graphics2D;
import java.awt.FontFormatException;
import javax.imageio.ImageIO;
import Entity.Player;
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
    public boolean showVisitHousePrompt = false; // Tambahkan variabel ini
    public boolean inHouse = false; // Untuk menandakan apakah pemain berada di dalam rumah NPC
    public boolean showFishPrompt = false;
    public boolean showSleepPrompt = false;
    public boolean showSleepScreen = false;
    public boolean showCookingScreen = false;
    private long sleepScreenStartTime = 0;
    public boolean isAction = false;
    public boolean isTired = false;
    public String inputName = "";
    public boolean showNameInputScreen = false;
    public String errorMessage = "";
    public long errorMessageTime = 0;
    int subState = 0;

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
            if(showSleepScreen) {
                drawSleepScreen();
                if(System.currentTimeMillis() - sleepScreenStartTime >= 3000) {
                    showSleepScreen = false;
                    if (gp.player.getEnergy()<0.1*gp.player.MAX_ENERGY) {
                        gp.player.setEnergy(gp.player.MAX_ENERGY/2);
                    }   
                    else {
                        gp.player.setEnergy(gp.player.MAX_ENERGY); // Reset energy to 100 after sleep
                    }
                    gp.environmentStatus.bangun();
                }
                return;
            }
            if(showCookingScreen) {
                drawCookingPrompt();
            }
            if(!isAction){
                drawGameStatus();
                drawEnergyBar(g2, gp.player);
            }
            if(showVisitHousePrompt){
                drawEnterHouse();
            }
            if(inHouse) {
                publicExitHouse();
            }
            if(showSleepPrompt) {
                drawSleepPrompt();
            }
            if(isTired){
                drawTiredPrompt();
            }
            
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
                drawNameInputScreen(); 
            }
            else if (titleScreenState == 2) {
                drawFarmNameInputScreen();
            }
            else if (titleScreenState == 3) {
                drawStoryScreen();
            }
        }
        if(gp.gameState == gp.mapSelectState){
            drawMapSelectionScreen();
        }
        if(gp.gameState == gp.dialogueState){
            // drawDialogueScreen();
        }
        if(gp.gameState == gp.optionState){
            drawOptionScreen(g2);
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

        //DRAW ITEMS
        for(int i = 0; i < gp.player.inventory.items.size(); i++) {
            if(gp.player.inventory.getItem(i) != null) {
                g2.drawImage(gp.player.inventory.getItem(i).image, slotX, slotY, null);
            }
            slotX += gp.tileSize; // Move to the next slot
            if((i + 1) % 4 == 0) { // Move to the next row after 4 items
                slotX = slotXstart;
                slotY += gp.tileSize;
            }
        }
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

        // BufferedImage bg;
        // try{
        //     bg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Menu/Background-dimmed.png"));
        //     g2.drawImage(bg, 0, 0, null); 
        // }
        // catch(IOException e){
        //     e.printStackTrace();
        // }

    public void drawOptionScreen(Graphics2D g2) {
            g2.setColor(Color.black);
            g2.setFont(stardew.deriveFont(Font.PLAIN, 32F));

            int frameX = gp.tileSize * 6;
            int frameY = gp.tileSize;
            int frameWidth = gp.tileSize * 8;
            int frameHeight = gp.tileSize * 10;
            drawSubWindow(frameX, frameY, frameWidth, frameHeight);

            switch (subState) {
                case 0: options_main(frameX, frameY); break;
                case 1: options_help(frameX, frameY); break;
                case 2: options_listObject(frameX, frameY); break;
                case 3: options_statistics(frameX, frameY); break;
                case 4: options_actions(frameX, frameY); break;
                case 5: options_exitConfirmation(frameX, frameY); break;
            }

            gp.keyH.enterPressed = false;
        }
    
    // MAIN OPTIONS SCREEN
    public void options_main(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize;

        g2.drawString("Options", getXforCenteredText("Options"), textY);

        textY += gp.tileSize * 2;

        String[] options = {
            "Help", "List Object", "Statistics", "Actions", "Exit", "Back"
        };

        for (int i = 0; i < options.length; i++) {
            g2.drawString(options[i], textX, textY);
            if (commandNum == i) {
                g2.drawString(">", textX - 25, textY);

                if (gp.keyH.enterPressed) {
                    switch (i) {
                        case 0: subState = 1; break;
                        case 1: subState = 2; break;
                        case 2: subState = 3; break;
                        case 3: subState = 4; break;
                        case 4: subState = 5; break;
                        case 5: gp.gameState = gp.playState; commandNum = 0; break;
                    }
                }
            }
            textY += gp.tileSize;
        }
    }

    // HELP SCREEN
    public void options_help(int frameX, int frameY) {
        int textX = frameX + gp.tileSize / 2;
        int textY = frameY + gp.tileSize;

        g2.setFont(g2.getFont().deriveFont(18F));

        String[] helpText = {
            "=== HELP MENU ===",
            "",
            "Welcome to Spakbor Hills — a cozy farming simulation game",
            "where you create your own story.",
            "",
            "In this game, you play as a new resident who inherits a plot",
            "of land. Your goal is simple: live your life however you want.",
            "You can farm, explore, interact with NPCs, build relationships,",
            "and even get married.",
            "",
            "There is no true ending — the game continues as long as you wish.",
            "You’re free to play at your own pace, set your own goals, and",
            "create your own routine.",
            "",
            "— HOW TO PLAY —",
            "",
            "• Move with W (up), A (left), S (down), D (right).",
            "• Use game menu or command input to do actions.",
            "• Actions consume energy and time.",
            "• Check inventory to manage your items.",
            "• Use the shipping bin to sell and earn gold.",
            "• Build relationships: talk, give gifts, fall in love.",
            "• Sleep to recover and start a new day.",
            "",
            "Enjoy the peaceful rhythm of life in Spakbor Hills.",
            "There's no rush — just farm, live, and explore your way!"
        };

        for (int i = 0; i < helpText.length; i++) {
            g2.drawString(helpText[i], textX, textY);
            textY += 24;
            if (textY > frameY + gp.tileSize * 9) break; // biar ga overflow
        }

        // BACK option
        g2.setFont(g2.getFont().deriveFont(22F));
        textY = frameY + gp.tileSize * 9 + 10;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 20, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
            }
        }
    }


    // LIST OBJECTS SCREEN
    public void options_listObject(int frameX, int frameY) {
        int textX = frameX + gp.tileSize / 2;
        int textY = frameY + gp.tileSize;

        g2.setFont(g2.getFont().deriveFont(18F));

        String[] objectList = {
            "=== OBJECT LIST ===",
            "",
            "1. Single Bed      → Tempat untuk tidur dan mengakhiri hari.",
            "2. TV              → Digunakan untuk hiburan.",
            "3. Kompor          → Digunakan untuk memasak makanan.",
            "4. Lemari          → Furniture dekoratif.",
            "5. Kursi           → Furniture dekoratif.",
            "6. Meja            → Furniture dekoratif.",
            "7. Gantungan Baju  → Furniture dekoratif.",
            "8. Jam             → Furniture dekoratif.",
            "9. Jendela         → Furniture dekoratif.",
            "10. Karpet         → Furniture dekoratif.",
            "",
            "Kamu dapat berinteraksi dengan beberapa objek tertentu,",
            "tergantung fungsi dan lokasi di dalam rumah atau luar."
        };

        for (int i = 0; i < objectList.length; i++) {
            g2.drawString(objectList[i], textX, textY);
            textY += 24;
            if (textY > frameY + gp.tileSize * 9) break;
        }

        // BACK option
        g2.setFont(g2.getFont().deriveFont(22F));
        textY = frameY + gp.tileSize * 9 + 10;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 20, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
            }
        }
    }

    // STATISTICS SCREEN
    public void options_statistics(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize;

        g2.drawString("Statistics", getXforCenteredText("Statistics"), textY);

        // Masih kosong

        // BACK
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
            }
        }
    }

    // ACTIONS SCREEN
    public void options_actions(int frameX, int frameY) {
        int textX = frameX + gp.tileSize / 2;
        int textY = frameY + gp.tileSize;

        g2.setFont(g2.getFont().deriveFont(18F));

        String[] actionsText = {
            "=== ACTIONS MENU ===",
            "",
            "In Spakbor Hills, you can live the life of a farmer while also",
            "being an active member of the village.",
            "Here are the various actions you can perform. Each action affects",
            "your energy, time, or social relationships.",
            "",
            "1. Planting        → Plant seeds on tilled soil.",
            "2. Watering        → Water your crops to help them grow.",
            "3. Harvesting      → Harvest crops that are ready.",
            "4. Eating          → Eat food to restore your energy.",
            "5. Sleeping        → Sleep to recover energy and start a new day.",
            "6. Fishing         → Fish in specific locations.",
            "7. Cooking         → Cook food using ingredients from inventory.",
            "8. Gifting         → Give gifts to NPCs to increase heartPoints.",
            "9. Chatting        → Talk with NPCs to build relationships.",
            "10. Proposing      → Propose to an NPC (max heartPoints needed).",
            "11. Marry          → Marry your fiancé(e) and start a new chapter.",
            "12. Visiting       → Visit areas outside your farm.",
            "13. Moving         → Move between tiles on the map.",
            "14. ShowTime       → Show current time, season, weather, and day.",
            "15. ShowLocation   → Show your current location.",
            "16. OpenInventory  → Display all inventory items.",
            "17. SellToBin      → Sell items using the Shipping Bin.",
            "18. Tilling        → Turn land into tillable soil.",
            "19. RecoverLand    → Revert soil back to normal.",
            "20. Watching       → Watch TV for fun.",
            "21. Save           → Save your game progress.",
            "",
            "Note:",
            "- Some actions need items or conditions.",
            "- Use energy and time wisely.",
            "- Smart planning = rich or married 😏"
        };

        for (int i = 0; i < actionsText.length; i++) {
            g2.drawString(actionsText[i], textX, textY);
            textY += 24;
            if (textY > frameY + gp.tileSize * 9) break;
        }

        // BACK option
        g2.setFont(g2.getFont().deriveFont(22F));
        textY = frameY + gp.tileSize * 9 + 10;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 20, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
            }
        }
    }

    // EXIT CONFIRMATION SCREEN
    public void options_exitConfirmation(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize;

        g2.drawString("Are you sure you want to exit?", getXforCenteredText("Are you sure you want to exit?"), textY);

        textY += gp.tileSize * 2;

        String[] options = {"Yes", "No"};
        for (int i = 0; i < options.length; i++) {
            g2.drawString(options[i], textX, textY);
            if (commandNum == i) {
                g2.drawString(">", textX - 25, textY);
                if (gp.keyH.enterPressed) {
                    if (i == 0) {
                        gp.gameState = gp.titleState; 
                    } else {
                        subState = 0; 
                    }
                }
            }
            textY += gp.tileSize;
        }
    }

    public void drawEnterHouse(){
        g2.setFont(stardew.deriveFont(Font.BOLD, 24F));
        g2.setColor(Color.white);
        String msg = "Want to Visit Player House? Click Y";
        int y = gp.screenHeight - gp.tileSize; // posisi Y (bawah layar)
        g2.drawString(msg, getXforCenteredText(msg), y);
    }

    public void publicExitHouse(){
        g2.setFont(stardew.deriveFont(Font.BOLD, 24F));
        g2.setColor(Color.white);
        String msg = "Want to Exit House? Click X";
        int y = gp.screenHeight - gp.tileSize; // posisi Y (bawah layar)
        g2.drawString(msg, getXforCenteredText(msg), y);
    }

    public void drawEnergyBar(Graphics2D g2, Player player) {
        int barX = gp.tileSize;
        int barY = gp.tileSize / 2;
        int barWidth = gp.tileSize * 4;
        int barHeight = gp.tileSize / 2;

        // Background bar (abu-abu)
        // g2.setColor(Color.GRAY);
        // g2.fillRoundRect(barX, barY, barWidth, barHeight, 10, 10);

        // Isi bar (merah/oranye)
        float energyPercent = Math.max(0, Math.min(1, player.getEnergy() / 100f));
        int fillWidth = (int)(barWidth * energyPercent);
        g2.setColor(new Color(255, 140, 0)); // oranye
        g2.fillRoundRect(barX, barY, fillWidth, barHeight, 10, 10);

        // Border
        g2.setColor(new Color(139, 69, 19, 180));
        g2.drawRoundRect(barX, barY, barWidth, barHeight, 10, 10);

        // Text
        g2.setColor(kuninggelap);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18f));
        String text = "Energy: " + player.getEnergy();
        int textX = barX + barWidth/2 - g2.getFontMetrics().stringWidth(text)/2;
        int textY = barY + barHeight - 5;
        g2.drawString(text, textX, textY);
    }

    public void drawFishPrompt() {
        g2.setFont(stardew.deriveFont(Font.BOLD, 24F));
        g2.setColor(Color.white);
        String msg = "Click F to Fish";
        int y = gp.screenHeight - gp.tileSize * 2; 
        g2.drawString(msg, getXforCenteredText(msg), y);
    }

    public void drawSleepPrompt() {
        g2.setFont(stardew.deriveFont(Font.BOLD, 24F));
        g2.setColor(Color.white);
        String msg = "Click Z to Sleep";
        int y = gp.screenHeight - gp.tileSize * 2; 
        g2.drawString(msg, getXforCenteredText(msg), y);
    }

    public void drawCookingPrompt() {
        g2.setFont(stardew.deriveFont(Font.BOLD, 24F));
        g2.setColor(Color.white);
        String msg = "Click C to Cook";
        int y = gp.screenHeight - gp.tileSize * 2; 
        g2.drawString(msg, getXforCenteredText(msg), y);
    }

    public void drawTiredPrompt() {
        g2.setFont(stardew.deriveFont(Font.BOLD, 24F));
        g2.setColor(Color.white);
        String msg = "You are overly tired, you are sleeping now";
        int y = gp.screenHeight - gp.tileSize * 2; 
        g2.drawString(msg, getXforCenteredText(msg), y);
    }

    public void startSleepScreen() {
        showSleepScreen = true;
        sleepScreenStartTime = System.currentTimeMillis();
    }
    
    public void drawSleepScreen() {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(stardew.deriveFont(Font.BOLD, 48F));
        g2.setColor(Color.white);
        String msg = "You are sleeping";
        int x = getXforCenteredText(msg);
        int y = gp.screenHeight / 2;
        g2.drawString(msg, x, y);
    }

    public void drawNameInputScreen() {
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
        g2.setFont(stardew.deriveFont(Font.BOLD, 36F));
        g2.setColor(kuninggelap);
        String prompt = "Enter Your Name:";
        int promptX = getXforCenteredText(prompt);
        int promptY = gp.screenHeight / 2 - gp.tileSize;
        g2.drawString(prompt, promptX+3, promptY+3);
        g2.setColor(kuning);
        g2.drawString(prompt, promptX, promptY);

        // Kotak input
        int boxWidth = gp.tileSize * 6;
        int boxHeight = gp.tileSize;
        int boxX = (gp.screenWidth - boxWidth) / 2;
        int boxY = gp.screenHeight / 2 - 30;
        g2.setColor(new Color(139, 69, 19, 180));
        g2.drawRect(boxX, boxY, boxWidth, boxHeight);

        // Nama yang sedang diketik
        g2.setFont(stardew.deriveFont(Font.PLAIN, 32F));
        g2.setColor(Color.white);
        g2.drawString(inputName, boxX + 10, boxY + boxHeight - 15);

        // Petunjuk
        g2.setFont(stardew.deriveFont(Font.PLAIN, 24F));
        g2.setColor(kuninggelap);
        String info = "Press ENTER to confirm";
        g2.drawString(info, getXforCenteredText(info) + 3, boxY + boxHeight + 30 + 3);
        g2.setColor(kuning);
        g2.drawString(info, getXforCenteredText(info), boxY + boxHeight + 30);

        // Pesan error
        if(!errorMessage.isEmpty() && System.currentTimeMillis() - errorMessageTime < 2000){
            g2.setColor(Color.RED);
            g2.setFont(stardew.deriveFont(Font.BOLD, 18F));
            g2.drawString(errorMessage, getXforCenteredText(errorMessage), boxY + boxHeight + 60);
        }
    }

    public void drawFarmNameInputScreen() {
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
        g2.setFont(stardew.deriveFont(Font.BOLD, 36F));
        g2.setColor(kuninggelap);
        String prompt = "Enter Your Farm Name:";
        int promptX = getXforCenteredText(prompt);
        int promptY = gp.screenHeight / 2 - gp.tileSize;
        g2.drawString(prompt, promptX+3, promptY+3);
        g2.setColor(kuning);
        g2.drawString(prompt, promptX, promptY);

        // Kotak input
        int boxWidth = gp.tileSize * 6;
        int boxHeight = gp.tileSize;
        int boxX = (gp.screenWidth - boxWidth) / 2;
        int boxY = gp.screenHeight / 2 - 30;
        g2.setColor(new Color(139, 69, 19, 180));
        g2.drawRect(boxX, boxY, boxWidth, boxHeight);

        // Nama yang sedang diketik
        g2.setFont(stardew.deriveFont(Font.PLAIN, 32F));
        g2.setColor(Color.white);
        g2.drawString(inputName, boxX + 10, boxY + boxHeight - 15);

        // Petunjuk
        g2.setFont(stardew.deriveFont(Font.PLAIN, 24F));
        g2.setColor(kuninggelap);
        String info = "Press ENTER to confirm";
        g2.drawString(info, getXforCenteredText(info) + 3, boxY + boxHeight + 30 + 3);
        g2.setColor(kuning);
        g2.drawString(info, getXforCenteredText(info), boxY + boxHeight + 30);

        // Pesan error
        if(!errorMessage.isEmpty() && System.currentTimeMillis() - errorMessageTime < 2000){
            g2.setColor(Color.RED);
            g2.setFont(stardew.deriveFont(Font.BOLD, 18F));
            g2.drawString(errorMessage, getXforCenteredText(errorMessage), boxY + boxHeight + 60);
        }
    }

    public void drawCookingMenu(){
        int width = gp.tileSize*5;
        int height = gp.tileSize*2;
        int x = (gp.screenWidth - width)/2;
        int y = (gp.screenHeight - height)/2;

        drawSubWindow(x, y, width, height);

        g2.setFont(stardew.deriveFont(Font.BOLD, 36F));
        String title = "Memasak";
        int titleX = getXforCenteredText(title);
        int titleY = y + gp.tileSize;
        g2.setColor(kuninggelap);
        g2.drawString(title, titleX + 3, titleY + 3);
        g2.setColor(kuning);
        g2.drawString(title, titleX, titleY); 


        g2.setFont(stardew.deriveFont(Font.PLAIN, 24F));
        String [] option = {"Memasak", "Back"};
        int optionY = titleY + gp.tileSize;
        for(int i = 0; i < option.length; i++) {
            int optionX = getXforCenteredText(option[i]);
            if(commandNum == i){
                g2.setColor(kuninggelap);
                g2.drawString(option[i], optionX + 3, optionY + 3);
                g2.setColor(kuning);
                g2.drawString(option[i], optionX, optionY);
            } else {
                g2.setColor(Color.white);
                g2.drawString(option[i], optionX, optionY);
            }
        }
        if(commandNum == 0){
            List<String> availableRecipes = new ArrayList<>();
            for(String recipe : gp.recipe.keySet()){
                if(Boolean.TRUE.equals(gp.recipe.get(recipe))) {
                    availableRecipes.add(recipe);
                }
            }
            int recipeY = optionY + gp.tileSize / 2;
            g2.setFont(stardew.deriveFont(Font.PLAIN, 24F));
            for (int i = 0; i < availableRecipes.size(); i++) {
                int recipeX = getXforCenteredText(availableRecipes.get(i));
                // Suppose you have a variable commandNum2 for recipe selection
                if (0 == i) {
                    g2.setColor(kuninggelap);
                    g2.drawString(">", recipeX - 32 + 3, recipeY + 3);
                    g2.setColor(kuning);
                    g2.drawString(">", recipeX - 32, recipeY);
                }
                g2.setColor(kuninggelap);
                g2.drawString(availableRecipes.get(i), recipeX + 3, recipeY + 3);
                g2.setColor(kuning);
                g2.drawString(availableRecipes.get(i), recipeX, recipeY);
                recipeY += gp.tileSize;
            }
        }
    }
}

