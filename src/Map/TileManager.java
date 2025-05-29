package Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] oceanTile;
    public Tile[] farmTile;
    public Tile[] mountainTile;
    public Tile[] forestTile;
    public Tile[] storeTile;
    public Tile[] abigail;
    public Tile[] caroline;
    public Tile[] dasco;
    public Tile[] mayor;
    public Tile[] perry;
    public Tile[] housePlayer; 
    public int mapTileNum[][];
    public int worldX;
    public int worldY;
    BufferedReader br;
    public String currMap;
    public int size;

    public TileManager(GamePanel gp, String map){
        this.gp = gp;
        this.currMap = map;
        mapTileNum = new int[gp.worldwh][gp.worldwh];
        if(map.equals("Ocean")) {
            worldX = 11; // sumbu x di mana character bakal spawn
            worldY = 16; // sumbu y di mana character bakal spawn
            size=32; // ukuran map (kalo house otomatis 24x24)
            loadOcean(); // fungsi load mapnya
        } else if(map.equals("Farm")) {
            size=32;
            worldX = 12;
            worldY = 16;
            loadFarm();
        } else if(map.equals("Mountain")) {
            worldX = 5;
            worldY = 11;
            size=32;
            loadMountain();
        } else if(map.equals("Forest")) {
            worldX = 9;
            worldY = 8;
            size=32;
            loadForest();
        } else if(map.equals("Store")) {
            worldX = 15;
            worldY = 11;
            size=16;
            loadStore();
        } else if(map.equals("Abigail")) {
            worldX = 5;
            worldY = 23;
            size = 24;
            loadAbigail();
        } else if(map.equals("Caroline")) {
            worldX = 5;
            worldY = 23;
            size = 24;
            loadCaroline();
        } else if(map.equals("Dasco")) {
            worldX = 5;
            worldY = 23;
            size = 24;
            loadDasco();
        } else if(map.equals("Mayor")) {
            worldX = 5;
            worldY = 23;
            size = 24;
            loadMayor();
        } else if(map.equals("Perry")) {
            worldX = 5;
            worldY = 23;
            size = 24;
            loadPerry();
        }
        else if (map.equals("HousePlayer")) {
            worldX = 5;
            worldY = 13;
            size=24;
            loadHousePlayer();
        }
    }

    public void loadOcean(){
        try{
            oceanTile = new Tile[32]; // jumlah variasi tilenya (banyak file .png)
            for(int i = 0; i <= 31; i++){  // ini banyak filenya, kalo di Ocean itu urut semua png dari 0-31, tapi kalo di house hrsnya ngga gitu, disimpen di array aja dulu png png yang ada di folder
                if(i==2 || i==5 || i==8){ // ini file yg ngga ada pngnya, tp kalo di house kan banyak jdnya jgn pake kek gini
                    continue;
                }
                oceanTile[i] = new Tile(); // inisiasi tilenya
                oceanTile[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/" + i + ".png")); // load gambar tilenya
            }
            int[] collisionIdx = {3, 6, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22}; // ini index tile yg collision, jadi tile yg ada di array oceanTile yg collisionnya true
            for(int idx : collisionIdx){ // yauda ini ngesetting collision true
                if(oceanTile[idx] != null) oceanTile[idx].collision = true;
            }
            br = new BufferedReader(new FileReader("res/Ocean/Ocean.txt")); // ini ngebaca file txtnya
        }
        catch(IOException e){
            e.printStackTrace();
        }
        loadMap();
    }

    public void loadForest(){
        try{
            forestTile = new Tile[52]; 
            for(int i = 0; i <= 51; i++){
                forestTile[i] = new Tile();
                forestTile[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/ForestRiver/" + i + ".png"));
            }
            int[] collisionIdx = {16, 17, 18, 35, 37, 38, 41, 42, 43, 44, 45, 47, 48, 49, 51};
            for(int idx : collisionIdx){
                if(forestTile[idx] != null) forestTile[idx].collision = true;
            }
            br = new BufferedReader(new FileReader("res/ForestRiver/ForestRiver.txt"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        loadMap();
    }

    public void loadFarm(){
        try{
            farmTile = new Tile[67];
            for(int i = 0; i <= 66; i++){
                farmTile[i] = new Tile();
                farmTile[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/" + i + ".png"));
            }
            int[] collisionIdx = {
                6, 9, 10, 11, 14, 15, 16, 17, 18, 19, 20, 21, 22, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 35, 36, 37,
                40, 41, 42, 43, 44, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 57, 58, 59, 60, 61, 62
            };
            for(int idx : collisionIdx){
                if(farmTile[idx] != null) farmTile[idx].collision = true;
            }
            br = new BufferedReader(new FileReader("res/FarmMap/FarmMap.txt"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        loadMap();
    }
    
    public void loadMountain(){
        try{
            mountainTile = new Tile[346];
            for(int i = 0; i <= 345; i++){
                mountainTile[i] = new Tile();
                String imgName;
                if(i <= 319) {
                    int row = i / 32 + 1;
                    int col = i % 32 + 1;
                    imgName = "image" + col + "x" + row + ".png";
                } else {
                    imgName = "0(" + i + ").png";
                }
                mountainTile[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/" + imgName));
                if(i != 329 && i != 344) {
                    mountainTile[i].collision = true;
                }
            }
            br = new BufferedReader(new FileReader("res/MountainLake/MountainLake.txt"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        loadMap();
    }

    public void loadStore(){
        try{
            storeTile = new Tile[30];
            for(int i = 0; i <= 29; i++){
                storeTile[i] = new Tile();
                String path = "res/Store/" + i + ".png";
                java.io.InputStream is = getClass().getClassLoader().getResourceAsStream(path);
                if(is != null){
                    storeTile[i].image = ImageIO.read(is);
                } else {
                    // Pakai gambar default jika file tidak ada
                    storeTile[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Store/default.png"));
                }
                if(i != 0) storeTile[i].collision = true; // Semua collision kecuali 0
            }
            br = new BufferedReader(new FileReader("res/Store/Store.txt"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        loadMap();
    }


    public void loadAbigail(){
        try{
            abigail= new Tile[139];
            int[] notCollisionIdx = {15, 50, 51, 53, 54, 55, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 
                67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87,
                88, 89, 90, 91, 92, 93, 94, 95, 96, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107,
                110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123};
            for(int i = 0; i <= 138; i++){
                abigail[i] = new Tile();
                abigail[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Interior_1/" + i + ".png"));
                boolean isNotCollision = false;
                for(int idx : notCollisionIdx){
                    if(i == idx){
                        isNotCollision = true;
                        break;
                    }
                }
                if(isNotCollision){
                    abigail[i].collision = false;
                } else {
                    abigail[i].collision = true;
                }
            }
            br = new BufferedReader(new FileReader("res/Interior_1/Interior_1_cropped.txt"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        loadMap();
    }

    public void loadCaroline() {
    try {
        int[] tileIdx = {
            0, 9, 10, 12, 14, 19, 20, 21, 22, 23, 26, 34, 36, 37, 38, 39, 40, 45, 47, 48, 49, 50, 52,
            53, 62, 63, 64, 65, 66, 67, 72, 73, 74, 75, 76, 78, 80, 81, 82, 105, 106, 107, 108, 188,
            189, 191, 192, 193, 215, 216, 217, 218, 219, 224, 235, 240, 241, 242, 243, 244, 245, 246,
            247, 248, 249, 250, 266, 267, 269, 270, 271, 272, 273, 274, 275, 276, 293, 294, 295, 296,
            297, 298, 299, 300, 302, 303, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 335, 346,
            347, 348, 349, 350, 351, 352, 353, 354, 355, 375, 376, 377, 378, 380, 402, 403, 404, 405,
            406, 490, 512, 536, 537, 538, 547, 552, 553, 555, 562, 563, 564, 567, 568, 569, 570, 571,
            573
        };

        int[] notCollisionIdx = {
            67, 188, 189, 191, 192, 193, 215, 216, 217, 218, 219, 240, 241, 242, 243, 244, 245, 247,
            248, 249, 250, 266, 267, 269, 273, 274, 275, 276, 293, 294, 295, 299, 300, 302, 303, 319,
            320, 325, 326, 327, 328, 347, 348, 349, 350, 351, 352, 353, 354, 355, 375, 376, 377, 378,
            380, 402, 403, 404, 405, 406
        };

        caroline = new Tile[tileIdx.length];
        for (int i = 0; i < tileIdx.length; i++) {
            int idx = tileIdx[i];
            caroline[i] = new Tile();
            caroline[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Interior_2/" + idx + ".png"));
            
            boolean isNotCollision = false;
            for (int notCol : notCollisionIdx) {
                if (idx == notCol) {
                    isNotCollision = true;
                    break;
                }
            }
            caroline[i].collision = !isNotCollision;
        }

        br = new BufferedReader(new FileReader("res/Interior_2/Interior_2_fix.txt"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    loadMap();
}


    public void loadDasco(){
        try{
            dasco = new Tile[119];
            int[] notCollisionIdx = {21, 55, 56, 57, 58, 62, 65, 66, 70, 73, 77, 80, 81, 86, 87, 88, 89};
            for(int i = 0; i <= 118; i++){
                dasco[i] = new Tile();
                dasco[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Interior_3/" + i + ".png"));
                boolean isNotCollision = false;
                for(int idx : notCollisionIdx){
                    if(i == idx){
                        isNotCollision = true;
                        break;
                    }
                }
                if(isNotCollision){
                    dasco[i].collision = false;
                } else {
                    dasco[i].collision = true;
                }
            }
            br = new BufferedReader(new FileReader("res/Interior_3/Interior_3_cropped.txt"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        loadMap();
    }

    public void loadMayor(){
        try{
            mayor = new Tile[137];
            int[] notCollisionIdx = {14, 48, 49, 51, 52, 53, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 
                65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 
                86, 87, 88, 89, 90, 91, 92, 93, 94, 96, 97, 98, 99, 100, 101, 102, 103, 104, 107, 
                108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121};
            for(int i = 0; i <= 136; i++){
                mayor[i] = new Tile();
                mayor[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Interior_4/" + i + ".png"));
                boolean isNotCollision = false;
                for(int idx : notCollisionIdx){
                    if(i == idx){
                        isNotCollision = true;
                        break;
                    }
                }
                if(isNotCollision){
                    mayor[i].collision = false;
                } else {
                    mayor[i].collision = true;
                }
            }
            br = new BufferedReader(new FileReader("res/Interior_4/Interior_4_cropped.txt"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        loadMap();
    }

    public void loadPerry(){
        try{
            perry = new Tile[143];
            int[] notCollisionIdx = {25, 66, 67, 68, 69, 73, 76, 82, 85, 96, 99, 110, 111, 112, 113};
            for(int i = 0; i <= 142; i++){
                perry[i] = new Tile();
                perry[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Interior_5/" + i + ".png"));
                boolean isNotCollision = false;
                for(int idx : notCollisionIdx){
                    if(i == idx){
                        isNotCollision = true;
                        break;
                    }
                }
                if(isNotCollision){
                    perry[i].collision = false;
                } else {
                    perry[i].collision = true;
                }
            }
            br = new BufferedReader(new FileReader("res/Interior_5/Interior_5_cropped.txt"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        loadMap();
    }

    public void loadHousePlayer(){
        try{
            housePlayer = new Tile[140];
            int[] notCollisionIdx = {16, 32, 33, 34, 35, 36, 37, 42, 43, 44, 45, 54, 59, 69, 74, 81, 82, 83, 84, 85, 86, 91, 92, 93, 94};
            for(int i = 0; i <= 130; i++){
                housePlayer[i] = new Tile();
                housePlayer[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Interior_6/" + i + ".png"));
                boolean isNotCollision = false;
                for(int idx : notCollisionIdx){
                    if(i == idx){
                        isNotCollision = true;
                        break;
                    }
                }
                if(isNotCollision){
                    housePlayer[i].collision = false;
                } else {
                    housePlayer[i].collision = true;
                }
            }
            br = new BufferedReader(new FileReader("res/Interior_6/Interior_6_cropped.txt"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        loadMap();
    }

    public void loadMap(){
        try{
            for(int row = 0; row < size; row++){
                String ln = br.readLine();
                if(ln == null) break;
                String[] num = ln.trim().split("\\s+");
                for(int col = 0; col < size && col < num.length; col++){
                    mapTileNum[col][row] = Integer.parseInt(num[col]);
                }
            }
            br.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g, Tile[] tile){
        int worldCol = 0;
        int worldRow = 0;
        while(worldCol<size && worldRow<size){
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol*gp.tileSize;
            int worldY = worldRow*gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if(worldX+gp.tileSize>gp.player.worldX-gp.player.screenX && worldX-gp.tileSize<gp.player.worldX+gp.player.screenX && worldY+gp.tileSize>gp.player.worldY-gp.player.screenY && worldY-gp.tileSize<gp.player.worldY+gp.player.screenY){
                g.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if(worldCol==size){
                worldCol = 0;
                worldRow++;
            }

        }
    }

    public Tile[] getActiveTileArray() {
        if (currMap.equals("Ocean")) return oceanTile;
        else if (currMap.equals("Farm")) return farmTile;
        else if (currMap.equals("Mountain")) return mountainTile;
        else if (currMap.equals("Forest")) return forestTile;
        else if (currMap.equals("Store")) return storeTile;
        else if (currMap.equals("Abigail")) return abigail;
        else if (currMap.equals("Caroline")) return caroline;
        else if (currMap.equals("Dasco")) return dasco;
        else if (currMap.equals("Mayor")) return mayor;
        else if (currMap.equals("Perry")) return perry;
        else if (currMap.equals("HousePlayer")) return housePlayer;
        return null;
    }
}
