package main;

import java.awt.image.BufferedImage;
// import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font; 
import java.awt.Graphics2D;
import java.awt.FontFormatException;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Menu {
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

    public Menu(GamePanel gp){
        this.gp = gp;
        loadCustomFont();
        initializeMaps();
    }

    private void loadCustomFont() {
        try {
            // Memuat font dari resource
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

    // Kelas bantu untuk menyimpan informasi map
    public class MapInfo {
        public String name;
        public BufferedImage image; // Akan jadi komentar untuk sementara
        // String imagePath; // Path ke gambar map jika nanti mau dimuat
        // Kamu bisa tambahkan properti lain seperti deskripsi map, dll.

        public MapInfo(String name /*, String imagePath */) {
            this.name = name;
            // this.imagePath = imagePath;
            this.image = null; // Inisialisasi null, nanti bisa dimuat dari imagePath
        }

        // Method untuk memuat gambar, jika diperlukan
        /*
        public void loadImage() {
            try {
                // this.image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imagePath));
            } catch (IOException e) {
                System.err.println("Error loading map image for " + name + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
        */
    }

    private void initializeMaps() {
        mapList = new ArrayList<>();
        mapList.add(new MapInfo("Forest Farm"));
        mapList.add(new MapInfo("Riverland Farm"));
        mapList.add(new MapInfo("Hill-top Farm"));
        mapList.add(new MapInfo("Standard Farm"));
        mapList.add(new MapInfo("Wilderness Farm"));
        mapList.add(new MapInfo("Four Corners Farm"));
        mapList.add(new MapInfo("Beach Farm"));
        mapList.add(new MapInfo("Meadowlands Farm"));
        mapList.add(new MapInfo("Custom Map A"));
        mapList.add(new MapInfo("Custom Map B"));
        // Jika nanti ingin memuat gambar, panggil loadImage() di sini
        // for (MapInfo map : mapList) {
        //     map.loadImage();
        // }
    }

    public void showMessage (String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        if(gp.gameState == gp.titleState){
            if(titleScreenState==0){
                drawTitleScreen();
            }
            else if (titleScreenState == 1) {
                drawMapSelectionScreen(); 
            }
        }
        if(gp.gameState == gp.playState){

        }
        if(gp.gameState == gp.dialogueState){

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
            g2.fillRect(currentX, currentY, mapSize, mapSize);
            
            if (i == mapSelectionNum) { //border highlight
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

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
