package Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import main.GamePanel;

public class FarmMap {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public FarmMap(GamePanel gp){
        this.gp = gp;
        tile = new Tile[70];
        mapTileNum = new int[gp.worldwh][gp.worldwh];
        loadMap();
        getTileImage();
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/0.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/1.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/2.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/3.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/4.png"));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/5.png"));
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/6.png"));
            tile[6].collision = true;
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/7.png"));
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/8.png"));
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/9.png"));
            tile[9].collision = true;
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/10.png"));
            tile[10].collision = true;
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/11.png"));
            tile[11].collision = true;
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/12.png"));
            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/13.png"));
            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/14.png"));
            tile[14].collision = true;
            tile[15] = new Tile();
            tile[15].collision = true;
            tile[15].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/15.png"));
            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/16.png"));
            tile[16].collision = true;
            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/17.png"));
            tile[17].collision = true;
            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/18.png"));
            tile[18].collision = true;
            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/19.png"));
            tile[19].collision = true;
            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/20.png"));
            tile[20].collision = true;
            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/21.png"));
            tile[21].collision = true;
            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/22.png"));
            tile[22].collision = true;
            tile[23] = new Tile();
            tile[23].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/23.png"));
            tile[24] = new Tile();
            tile[24].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/24.png"));
            tile[24].collision = true;
            tile[25] = new Tile();
            tile[25].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/25.png"));
            tile[25].collision = true;
            tile[26] = new Tile();
            tile[26].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/26.png"));
            tile[26].collision = true;
            tile[27] = new Tile();
            tile[27].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/27.png"));
            tile[27].collision = true;
            tile[28] = new Tile();
            tile[28].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/28.png"));
            tile[28].collision = true;
            tile[29] = new Tile();
            tile[29].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/29.png"));
            tile[29].collision = true;
            tile[30] = new Tile();
            tile[30].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/30.png"));
            tile[30].collision = true;
            tile[31] = new Tile();
            tile[31].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/31.png"));
            tile[31].collision = true;
            tile[32] = new Tile();
            tile[32].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/32.png"));
            tile[32].collision = true;
            tile[33] = new Tile();
            tile[33].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/33.png"));
            tile[33].collision = true;
            tile[34] = new Tile();
            tile[34].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/34.png"));
            tile[35] = new Tile();
            tile[35].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/35.png"));
            tile[35].collision = true;
            tile[36] = new Tile();
            tile[36].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/36.png"));
            tile[36].collision = true;
            tile[37] = new Tile();
            tile[37].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/37.png"));
            tile[37].collision = true;
            tile[38] = new Tile();
            tile[38].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/38.png"));
            tile[39] = new Tile();
            tile[39].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/39.png"));
            tile[40] = new Tile();
            tile[40].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/40.png"));
            tile[40].collision = true;
            tile[41] = new Tile();
            tile[41].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/41.png"));
            tile[41].collision = true;
            tile[42] = new Tile();
            tile[42].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/42.png"));
            tile[42].collision = true;
            tile[43] = new Tile();
            tile[43].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/43.png"));
            tile[43].collision = true;
            tile[44] = new Tile();
            tile[44].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/44.png"));
            tile[44].collision = true;
            tile[45] = new Tile();
            tile[45].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/45.png"));
            tile[46] = new Tile();
            tile[46].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/46.png"));
            tile[46].collision = true;
            tile[47] = new Tile();
            tile[47].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/47.png"));
            tile[47].collision = true;
            tile[48] = new Tile();
            tile[48].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/48.png"));
            tile[48].collision = true;
            tile[49] = new Tile();
            tile[49].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/49.png"));
            tile[49].collision = true;
            tile[50] = new Tile();
            tile[50].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/50.png"));
            tile[50].collision = true;
            tile[51] = new Tile();
            tile[51].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/51.png"));
            tile[51].collision = true;
            tile[52] = new Tile();
            tile[52].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/52.png"));
            tile[52].collision = true;
            tile[53] = new Tile();
            tile[53].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/53.png"));
            tile[53].collision = true;
            tile[54] = new Tile();
            tile[54].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/54.png"));
            tile[54].collision = true;
            tile[55] = new Tile();
            tile[55].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/55.png"));
            tile[55].collision = true;
            tile[56] = new Tile();
            tile[56].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/56.png"));
            tile[57] = new Tile();
            tile[57].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/57.png"));
            tile[57].collision = true;
            tile[58] = new Tile();
            tile[58].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/58.png"));
            tile[58].collision = true;
            tile[59] = new Tile();
            tile[59].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/59.png"));
            tile[59].collision = true;
            tile[60] = new Tile();
            tile[60].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/60.png"));
            tile[60].collision = true;
            tile[61] = new Tile();
            tile[61].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/61.png"));
            tile[61].collision = true;
            tile[62] = new Tile();
            tile[62].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/62.png"));
            tile[62].collision = true;
            tile[63] = new Tile();
            tile[63].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/63.png"));
            tile[64] = new Tile();
            tile[64].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/64.png"));
            tile[65] = new Tile();
            tile[65].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/65.png"));
            tile[66] = new Tile();
            tile[66].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/FarmMap/66.png"));

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("res/FarmMap/FarmMap.txt"));
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
