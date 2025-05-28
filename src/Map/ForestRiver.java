package Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import main.GamePanel;

public class ForestRiver {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public ForestRiver(GamePanel gp){
        this.gp = gp;
        tile = new Tile[67]; //variasi type yang dipake
        mapTileNum = new int[gp.worldwh][gp.worldwh];
        loadMap();
        getTileImage();
    }

    public void getTileImage(){
        try{
        this.tile[0] = new Tile();
         this.tile[0].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/0.png"));
         this.tile[1] = new Tile();
         this.tile[1].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/1.png"));
         this.tile[2] = new Tile();
         this.tile[2].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/2.png"));
         this.tile[3] = new Tile();
         this.tile[3].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/3.png"));
         this.tile[4] = new Tile();
         this.tile[4].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/4.png"));
         this.tile[5] = new Tile();
         this.tile[5].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/5.png"));
         this.tile[6] = new Tile();
         this.tile[6].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/6.png"));
         this.tile[7] = new Tile();
         this.tile[7].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/7.png"));
         this.tile[8] = new Tile();
         this.tile[8].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/8.png"));
         this.tile[9] = new Tile();
         this.tile[9].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/9.png"));
         this.tile[10] = new Tile();
         this.tile[10].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/10.png"));
         this.tile[11] = new Tile();
         this.tile[11].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/11.png"));
         this.tile[12] = new Tile();
         this.tile[12].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/12.png"));
         this.tile[13] = new Tile();
         this.tile[13].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/13.png"));
         this.tile[14] = new Tile();
         this.tile[14].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/14.png"));
         this.tile[15] = new Tile();
         this.tile[15].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/15.png"));
         this.tile[16] = new Tile();
         this.tile[16].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/16.png"));
         this.tile[16].collision = true;
         this.tile[17] = new Tile();
         this.tile[17].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/17.png"));
         this.tile[17].collision = true;
         this.tile[18] = new Tile();
         this.tile[18].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/18.png"));
         this.tile[18].collision = true;
         this.tile[19] = new Tile();
         this.tile[19].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/19.png"));
         this.tile[20] = new Tile();
         this.tile[20].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/20.png"));
         this.tile[21] = new Tile();
         this.tile[21].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/21.png"));
         this.tile[22] = new Tile();
         this.tile[22].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/22.png"));
         this.tile[23] = new Tile();
         this.tile[23].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/23.png"));
         this.tile[24] = new Tile();
         this.tile[24].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/24.png"));
         this.tile[25] = new Tile();
         this.tile[25].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/25.png"));
         this.tile[26] = new Tile();
         this.tile[26].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/26.png"));
         this.tile[27] = new Tile();
         this.tile[27].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/27.png"));
         this.tile[28] = new Tile();
         this.tile[28].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/28.png"));
         this.tile[29] = new Tile();
         this.tile[29].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/29.png"));
         this.tile[30] = new Tile();
         this.tile[30].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/30.png"));
         this.tile[31] = new Tile();
         this.tile[31].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/31.png"));
         this.tile[32] = new Tile();
         this.tile[32].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/32.png"));
         this.tile[33] = new Tile();
         this.tile[33].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/33.png"));
         this.tile[34] = new Tile();
         this.tile[34].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/34.png"));
         this.tile[35] = new Tile();
         this.tile[35].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/35.png"));
         this.tile[35].collision = true;
         this.tile[36] = new Tile();
         this.tile[36].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/36.png"));
         this.tile[37] = new Tile();
         this.tile[37].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/37.png"));
         this.tile[37].collision = true;
         this.tile[38] = new Tile();
         this.tile[38].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/38.png"));
         this.tile[38].collision = true;
         this.tile[39] = new Tile();
         this.tile[39].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/39.png"));
         this.tile[40] = new Tile();
         this.tile[40].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/40.png"));
         this.tile[41] = new Tile();
         this.tile[41].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/41.png"));
         this.tile[41].collision = true;
         this.tile[42] = new Tile();
         this.tile[42].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/42.png"));
         this.tile[42].collision = true;
         this.tile[43] = new Tile();
         this.tile[43].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/43.png"));
         this.tile[43].collision = true;
         this.tile[44] = new Tile();
         this.tile[44].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/44.png"));
         this.tile[44].collision = true;
         this.tile[45] = new Tile();
         this.tile[45].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/45.png"));
         this.tile[45].collision = true;
         this.tile[46] = new Tile();
         this.tile[46].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/46.png"));
         this.tile[47] = new Tile();
         this.tile[47].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/47.png"));
         this.tile[47].collision = true;
         this.tile[48] = new Tile();
         this.tile[48].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/48.png"));
         this.tile[48].collision = true;
         this.tile[49] = new Tile();
         this.tile[49].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/49.png"));
         this.tile[49].collision = true;
         this.tile[50] = new Tile();
         this.tile[50].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/50.png"));
         this.tile[51] = new Tile();
         this.tile[51].image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/ForestRiver/51.png"));
         this.tile[51].collision = true;
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("res/ForestRiver/ForestRiver.txt"));
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
