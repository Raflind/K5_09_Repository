package Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[32];
        mapTileNum = new int[gp.worldwh][gp.worldwh];
        loadMap();
        getTileImage();
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/0.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/1.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/3.png"));
            tile[3].collision = true;
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/4.png"));
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/6.png"));
            tile[6].collision = true;
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/7.png"));
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/9.png"));
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/10.png"));
            tile[10].collision = true;
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/11.png"));
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/12.png"));
            tile[12].collision = true;
            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/13.png"));
            tile[13].collision = true;
            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/14.png"));
            tile[14].collision = true;
            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/15.png"));
            tile[15].collision = true;
            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/16.png"));
            tile[16].collision = true;
            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/17.png"));
            tile[17].collision = true;
            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/18.png"));
            tile[18].collision = true;
            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/19.png"));
            tile[19].collision = true;
            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/20.png"));
            tile[20].collision = true;
            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/21.png"));
            tile[21].collision = true;
            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/22.png"));
            tile[22].collision = true;
            tile[23] = new Tile();
            tile[23].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/23.png"));
            tile[24] = new Tile();
            tile[24].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/24.png"));
            tile[25] = new Tile();
            tile[25].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/25.png"));
            tile[26] = new Tile();
            tile[26].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/26.png"));
            tile[27] = new Tile();
            tile[27].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/27.png"));
            tile[28] = new Tile();
            tile[28].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/28.png"));
            tile[29] = new Tile();
            tile[29].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/29.png"));
            tile[30] = new Tile();
            tile[30].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/30.png"));
            tile[31] = new Tile();
            tile[31].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Ocean/31.png"));

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("res/Ocean/Ocean.txt"));
            int col = 0;
            int row = 0;
            while(col<gp.worldwh && row<gp.worldwh){
                String ln = br.readLine();
                while(col<gp.worldwh){
                    String num[] = ln.split(" ");
                    int number = Integer.parseInt(num[col]);
                    mapTileNum[col][row]=number;
                    col++;
                }
                if(col==gp.worldwh){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
        while(worldCol<gp.worldwh && worldRow<gp.worldwh){
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol*gp.tileSize;
            int worldY = worldRow*gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if(worldX+gp.tileSize>gp.player.worldX-gp.player.screenX && worldX-gp.tileSize<gp.player.worldX+gp.player.screenX && worldY+gp.tileSize>gp.player.worldY-gp.player.screenY && worldY-gp.tileSize<gp.player.worldY+gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if(worldCol==gp.worldwh){
                worldCol = 0;
                worldRow++;
            }

        }
    }
}
