package Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

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
    public Tile[] houseNPC;
    public Tile[] housePlayer;
    public int mapTileNum[][];
    BufferedReader br;
    String currMap;
    int size;

    public TileManager(GamePanel gp, String map){
        this.gp = gp;
        this.currMap = map;
        mapTileNum = new int[gp.worldwh][gp.worldwh];
        if(map.equals("Ocean")) {
            oceanTile = new Tile[32];
            size=32;
            loadOcean();
        } else if(map.equals("Farm")) {
            farmTile = new Tile[70];
            size=32;
            loadFarm();
        } else if(map.equals("Mountain")) {
            mountainTile = new Tile[350];
            size=32;
            loadMountain();
        } else if(map.equals("Forest")) {
            forestTile = new Tile[67];
            size=32;
            loadForest();
        } /*else if(map.equals("Store")) {
            storeTile = new Tile[32];
            loadStore();
        } else if(map.equals("Abigail")) {
            house = new Tile[32];
            loadAbigail();
        } else if(map.equals("Caroline")) {
            house = new Tile[32];
            loadCaroline();
        } else if(map.equals("Dasco")) {
            house = new Tile[32];
            loadDasco();
        } else if(map.equals("Mayor")) {
            house = new Tile[32];
            loadMayor();
        } else if(map.equals("Perry")) {
            house = new Tile[32];
            loadPerry();
        }*/
    }

    public void loadOcean(){
        try{
            for(int i = 0; i <= 31; i++){
                oceanTile[i] = new Tile();
                oceanTile[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/" + i + ".png"));
            }
            int[] collisionIdx = {3, 6, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
            for(int idx : collisionIdx){
                if(oceanTile[idx] != null) oceanTile[idx].collision = true;
            }
            br = new BufferedReader(new FileReader("res/Ocean/Ocean.txt"));
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

    // public void loadAbigail(){
    //     try{
    //         int[] abigailIdx = {
    //             0, 9, 10, 12, 14, 19, 20, 21, 22, 23, 26, 34, 36, 37, 38, 39, 40, 45, 47, 48, 49, 50,
    //             51, 52, 53, 61, 62, 63, 64, 65, 66, 72, 73, 74, 75, 76, 78, 80, 81, 82, 105, 106,
    //             107, 108, 188, 189, 191, 192, 193, 215, 216, 217, 218, 219, 240, 241, 242, 243, 244,
    //             245, 247, 248, 249, 250, 266, 267, 269, 270, 271, 272, 273, 274, 275, 276, 293, 294,
    //             295, 296, 297, 298, 299, 300, 302, 303, 319, 320, 321, 322, 323, 324, 325, 326, 327,
    //             328, 329, 335, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 375, 376, 377,
    //             378, 380, 402, 403, 404, 405, 406, 536, 537, 538, 552, 553, 555, 567, 568, 569, 570,
    //             571, 573
    //         };
    //         int maxIdx = 0;
    //         for(int idx : abigailIdx) if(idx > maxIdx) maxIdx = idx;
    //         houseNPC = new Tile[maxIdx + 1];
    //         for(int idx : abigailIdx){
    //             houseNPC[idx] = new Tile();
    //             houseNPC[idx].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/interior_2/" + idx + ".png"));
    //         }

    //         br = new BufferedReader(new FileReader("res/HouseNPC/interior_2/interior_2.txt"));
    //     }
    //     catch(IOException e){
    //         e.printStackTrace();
    //     }
    // }

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
        return null;
    }
}
