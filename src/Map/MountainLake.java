package Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import main.GamePanel;

public class MountainLake {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public MountainLake(GamePanel gp){
        this.gp = gp;
        tile = new Tile[350];
        mapTileNum = new int[gp.worldwh][gp.worldwh];
        loadMap();
        getTileImage();
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image1x1.png"));
            tile[0].collision = true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image2x1.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image3x1.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image4x1.png"));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image5x1.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image6x1.png"));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image7x1.png"));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image8x1.png"));
            tile[7].collision = true;

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image9x1.png"));
            tile[8].collision = true;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image10x1.png"));
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image11x1.png"));
            tile[10].collision = true;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image12x1.png"));
            tile[11].collision = true;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image13x1.png"));
            tile[12].collision = true;

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image14x1.png"));
            tile[13].collision = true;

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image15x1.png"));
            tile[14].collision = true;

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image16x1.png"));
            tile[15].collision = true;

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image17x1.png"));
            tile[16].collision = true;

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image18x1.png"));
            tile[17].collision = true;

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image19x1.png"));
            tile[18].collision = true;

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image20x1.png"));
            tile[19].collision = true;

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image21x1.png"));
            tile[20].collision = true;

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image22x1.png"));
            tile[21].collision = true;

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image23x1.png"));
            tile[22].collision = true;

            tile[23] = new Tile();
            tile[23].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image24x1.png"));
            tile[23].collision = true;

            tile[24] = new Tile();
            tile[24].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image25x1.png"));
            tile[24].collision = true;

            tile[25] = new Tile();
            tile[25].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image26x1.png"));
            tile[25].collision = true;

            tile[26] = new Tile();
            tile[26].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image27x1.png"));
            tile[26].collision = true;

            tile[27] = new Tile();
            tile[27].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image28x1.png"));
            tile[27].collision = true;

            tile[28] = new Tile();
            tile[28].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image29x1.png"));
            tile[28].collision = true;

            tile[29] = new Tile();
            tile[29].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image30x1.png"));
            tile[29].collision = true;

            tile[30] = new Tile();
            tile[30].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image31x1.png"));
            tile[30].collision = true;

            tile[31] = new Tile();
            tile[31].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image32x1.png"));
            tile[31].collision = true;

            tile[32] = new Tile();
            tile[32].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image1x2.png"));
            tile[32].collision = true;

            tile[33] = new Tile();
            tile[33].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image2x2.png"));
            tile[33].collision = true;

            tile[34] = new Tile();
            tile[34].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image3x2.png"));
            tile[34].collision = true;

            tile[35] = new Tile();
            tile[35].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image4x2.png"));
            tile[35].collision = true;

            tile[36] = new Tile();
            tile[36].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image5x2.png"));
            tile[36].collision = true;

            tile[37] = new Tile();
            tile[37].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image6x2.png"));
            tile[37].collision = true;

            tile[38] = new Tile();
            tile[38].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image7x2.png"));
            tile[38].collision = true;

            tile[39] = new Tile();
            tile[39].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image8x2.png"));
            tile[39].collision = true;

            tile[40] = new Tile();
            tile[40].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image9x2.png"));
            tile[40].collision = true;

            tile[41] = new Tile();
            tile[41].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image10x2.png"));
            tile[41].collision = true;

            tile[42] = new Tile();
            tile[42].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image11x2.png"));
            tile[42].collision = true;

            tile[43] = new Tile();
            tile[43].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image12x2.png"));
            tile[43].collision = true;

            tile[44] = new Tile();
            tile[44].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image13x2.png"));
            tile[44].collision = true;

            tile[45] = new Tile();
            tile[45].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image14x2.png"));
            tile[45].collision = true;

            tile[46] = new Tile();
            tile[46].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image15x2.png"));
            tile[46].collision = true;

            tile[47] = new Tile();
            tile[47].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image16x2.png"));
            tile[47].collision = true;

            tile[48] = new Tile();
            tile[48].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image17x2.png"));
            tile[48].collision = true;

            tile[49] = new Tile();
            tile[49].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image18x2.png"));
            tile[49].collision = true;

            tile[50] = new Tile();
            tile[50].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image19x2.png"));
            tile[50].collision = true;

            tile[51] = new Tile();
            tile[51].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image20x2.png"));
            tile[51].collision = true;

            tile[52] = new Tile();
            tile[52].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image21x2.png"));
            tile[52].collision = true;

            tile[53] = new Tile();
            tile[53].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image22x2.png"));
            tile[53].collision = true;

            tile[54] = new Tile();
            tile[54].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image23x2.png"));
            tile[54].collision = true;

            tile[55] = new Tile();
            tile[55].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image24x2.png"));
            tile[55].collision = true;

            tile[56] = new Tile();
            tile[56].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image25x2.png"));
            tile[56].collision = true;

            tile[57] = new Tile();
            tile[57].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image26x2.png"));
            tile[57].collision = true;

            tile[58] = new Tile();
            tile[58].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image27x2.png"));
            tile[58].collision = true;

            tile[59] = new Tile();
            tile[59].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image28x2.png"));
            tile[59].collision = true;

            tile[60] = new Tile();
            tile[60].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image29x2.png"));
            tile[60].collision = true;

            tile[61] = new Tile();
            tile[61].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image30x2.png"));
            tile[61].collision = true;

            tile[62] = new Tile();
            tile[62].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image31x2.png"));
            tile[62].collision = true;

            tile[63] = new Tile();
            tile[63].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image32x2.png"));
            tile[63].collision = true;

            tile[64] = new Tile();
            tile[64].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image1x3.png"));
            tile[64].collision = true;

            tile[65] = new Tile();
            tile[65].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image2x3.png"));
            tile[65].collision = true;

            tile[66] = new Tile();
            tile[66].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image3x3.png"));
            tile[66].collision = true;

            tile[67] = new Tile();
            tile[67].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image4x3.png"));
            tile[67].collision = true;

            tile[68] = new Tile();
            tile[68].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image5x3.png"));
            tile[68].collision = true;

            tile[69] = new Tile();
            tile[69].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image6x3.png"));
            tile[69].collision = true;

            tile[70] = new Tile();
            tile[70].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image7x3.png"));
            tile[70].collision = true;

            tile[71] = new Tile();
            tile[71].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image8x3.png"));
            tile[71].collision = true;

            tile[72] = new Tile();
            tile[72].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image9x3.png"));
            tile[72].collision = true;

            tile[73] = new Tile();
            tile[73].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image10x3.png"));
            tile[73].collision = true;

            tile[74] = new Tile();
            tile[74].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image11x3.png"));
            tile[74].collision = true;

            tile[75] = new Tile();
            tile[75].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image12x3.png"));
            tile[75].collision = true;

            tile[76] = new Tile();
            tile[76].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image13x3.png"));
            tile[76].collision = true;

            tile[77] = new Tile();
            tile[77].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image14x3.png"));
            tile[77].collision = true;

            tile[78] = new Tile();
            tile[78].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image15x3.png"));
            tile[78].collision = true;

            tile[79] = new Tile();
            tile[79].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image16x3.png"));
            tile[79].collision = true;

            tile[80] = new Tile();
            tile[80].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image17x3.png"));
            tile[80].collision = true;

            tile[81] = new Tile();
            tile[81].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image18x3.png"));
            tile[81].collision = true;

            tile[82] = new Tile();
            tile[82].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image19x3.png"));
            tile[82].collision = true;

            tile[83] = new Tile();
            tile[83].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image20x3.png"));
            tile[83].collision = true;

            tile[84] = new Tile();
            tile[84].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image21x3.png"));
            tile[84].collision = true;

            tile[85] = new Tile();
            tile[85].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image22x3.png"));
            tile[85].collision = true;

            tile[86] = new Tile();
            tile[86].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image23x3.png"));
            tile[86].collision = true;

            tile[87] = new Tile();
            tile[87].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image24x3.png"));
            tile[87].collision = true;

            tile[88] = new Tile();
            tile[88].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image25x3.png"));
            tile[88].collision = true;

            tile[89] = new Tile();
            tile[89].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image26x3.png"));
            tile[89].collision = true;

            tile[90] = new Tile();
            tile[90].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image27x3.png"));
            tile[90].collision = true;

            tile[91] = new Tile();
            tile[91].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image28x3.png"));
            tile[91].collision = true;

            tile[92] = new Tile();
            tile[92].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image29x3.png"));
            tile[92].collision = true;

            tile[93] = new Tile();
            tile[93].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image30x3.png"));
            tile[93].collision = true;

            tile[94] = new Tile();
            tile[94].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image31x3.png"));
            tile[94].collision = true;

            tile[95] = new Tile();
            tile[95].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image32x3.png"));
            tile[95].collision = true;

            tile[96] = new Tile();
            tile[96].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image1x4.png"));
            tile[96].collision = true;

            tile[97] = new Tile();
            tile[97].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image2x4.png"));
            tile[97].collision = true;

            tile[98] = new Tile();
            tile[98].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image3x4.png"));
            tile[98].collision = true;

            tile[99] = new Tile();
            tile[99].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image4x4.png"));
            tile[99].collision = true;

            tile[100] = new Tile();
            tile[100].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image5x4.png"));
            tile[100].collision = true;

            tile[101] = new Tile();
            tile[101].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image6x4.png"));
            tile[101].collision = true;

            tile[102] = new Tile();
            tile[102].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image7x4.png"));
            tile[102].collision = true;

            tile[103] = new Tile();
            tile[103].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image8x4.png"));
            tile[103].collision = true;

            tile[104] = new Tile();
            tile[104].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image9x4.png"));
            tile[104].collision = true;

            tile[105] = new Tile();
            tile[105].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image10x4.png"));
            tile[105].collision = true;

            tile[106] = new Tile();
            tile[106].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image11x4.png"));
            tile[106].collision = true;

            tile[107] = new Tile();
            tile[107].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image12x4.png"));
            tile[107].collision = true;

            tile[108] = new Tile();
            tile[108].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image13x4.png"));
            tile[108].collision = true;

            tile[109] = new Tile();
            tile[109].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image14x4.png"));
            tile[109].collision = true;

            tile[110] = new Tile();
            tile[110].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image15x4.png"));
            tile[110].collision = true;

            tile[111] = new Tile();
            tile[111].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image16x4.png"));
            tile[111].collision = true;

            tile[112] = new Tile();
            tile[112].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image17x4.png"));
            tile[112].collision = true;

            tile[113] = new Tile();
            tile[113].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image18x4.png"));
            tile[113].collision = true;

            tile[114] = new Tile();
            tile[114].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image19x4.png"));
            tile[114].collision = true;

            tile[115] = new Tile();
            tile[115].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image20x4.png"));
            tile[115].collision = true;

            tile[116] = new Tile();
            tile[116].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image21x4.png"));
            tile[116].collision = true;

            tile[117] = new Tile();
            tile[117].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image22x4.png"));
            tile[117].collision = true;

            tile[118] = new Tile();
            tile[118].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image23x4.png"));
            tile[118].collision = true;

            tile[119] = new Tile();
            tile[119].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image24x4.png"));
            tile[119].collision = true;

            tile[120] = new Tile();
            tile[120].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image25x4.png"));
            tile[120].collision = true;

            tile[121] = new Tile();
            tile[121].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image26x4.png"));
            tile[121].collision = true;

            tile[122] = new Tile();
            tile[122].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image27x4.png"));
            tile[122].collision = true;

            tile[123] = new Tile();
            tile[123].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image28x4.png"));
            tile[123].collision = true;

            tile[124] = new Tile();
            tile[124].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image29x4.png"));
            tile[124].collision = true;

            tile[125] = new Tile();
            tile[125].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image30x4.png"));
            tile[125].collision = true;

            tile[126] = new Tile();
            tile[126].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image31x4.png"));
            tile[126].collision = true;

            tile[127] = new Tile();
            tile[127].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image32x4.png"));
            tile[127].collision = true;

            tile[128] = new Tile();
            tile[128].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image1x5.png"));
            tile[128].collision = true;

            tile[129] = new Tile();
            tile[129].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image2x5.png"));
            tile[129].collision = true;

            tile[130] = new Tile();
            tile[130].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image3x5.png"));
            tile[130].collision = true;

            tile[131] = new Tile();
            tile[131].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image4x5.png"));
            tile[131].collision = true;

            tile[132] = new Tile();
            tile[132].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image5x5.png"));
            tile[132].collision = true;

            tile[133] = new Tile();
            tile[133].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image6x5.png"));
            tile[133].collision = true;

            tile[134] = new Tile();
            tile[134].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image7x5.png"));
            tile[134].collision = true;

            tile[135] = new Tile();
            tile[135].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image8x5.png"));
            tile[135].collision = true;

            tile[136] = new Tile();
            tile[136].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image9x5.png"));
            tile[136].collision = true;

            tile[137] = new Tile();
            tile[137].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image10x5.png"));
            tile[137].collision = true;

            tile[138] = new Tile();
            tile[138].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image11x5.png"));
            tile[138].collision = true;

            tile[139] = new Tile();
            tile[139].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image12x5.png"));
            tile[139].collision = true;

            tile[140] = new Tile();
            tile[140].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image13x5.png"));
            tile[140].collision = true;

            tile[141] = new Tile();
            tile[141].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image14x5.png"));
            tile[141].collision = true;

            tile[142] = new Tile();
            tile[142].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image15x5.png"));
            tile[142].collision = true;

            tile[143] = new Tile();
            tile[143].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image16x5.png"));
            tile[143].collision = true;

            tile[144] = new Tile();
            tile[144].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image17x5.png"));
            tile[144].collision = true;

            tile[145] = new Tile();
            tile[145].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image18x5.png"));
            tile[145].collision = true;

            tile[146] = new Tile();
            tile[146].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image19x5.png"));
            tile[146].collision = true;

            tile[147] = new Tile();
            tile[147].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image20x5.png"));
            tile[147].collision = true;

            tile[148] = new Tile();
            tile[148].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image21x5.png"));
            tile[148].collision = true;

            tile[149] = new Tile();
            tile[149].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image22x5.png"));
            tile[149].collision = true;

            tile[150] = new Tile();
            tile[150].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image23x5.png"));
            tile[150].collision = true;

            tile[151] = new Tile();
            tile[151].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image24x5.png"));
            tile[151].collision = true;

            tile[152] = new Tile();
            tile[152].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image25x5.png"));
            tile[152].collision = true;

            tile[153] = new Tile();
            tile[153].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image26x5.png"));
            tile[153].collision = true;

            tile[154] = new Tile();
            tile[154].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image27x5.png"));
            tile[154].collision = true;

            tile[155] = new Tile();
            tile[155].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image28x5.png"));
            tile[155].collision = true;

            tile[156] = new Tile();
            tile[156].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image29x5.png"));
            tile[156].collision = true;

            tile[157] = new Tile();
            tile[157].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image30x5.png"));
            tile[157].collision = true;

            tile[158] = new Tile();
            tile[158].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image31x5.png"));
            tile[158].collision = true;

            tile[159] = new Tile();
            tile[159].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image32x5.png"));
            tile[159].collision = true;

            tile[160] = new Tile();
            tile[160].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image1x6.png"));
            tile[160].collision = true;

            tile[161] = new Tile();
            tile[161].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image2x6.png"));
            tile[161].collision = true;

            tile[162] = new Tile();
            tile[162].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image3x6.png"));
            tile[162].collision = true;

            tile[163] = new Tile();
            tile[163].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image4x6.png"));
            tile[163].collision = true;

            tile[164] = new Tile();
            tile[164].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image5x6.png"));
            tile[164].collision = true;

            tile[165] = new Tile();
            tile[165].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image6x6.png"));
            tile[165].collision = true;

            tile[166] = new Tile();
            tile[166].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image7x6.png"));
            tile[166].collision = true;

            tile[167] = new Tile();
            tile[167].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image8x6.png"));
            tile[167].collision = true;

            tile[168] = new Tile();
            tile[168].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image9x6.png"));
            tile[168].collision = true;

            tile[169] = new Tile();
            tile[169].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image10x6.png"));
            tile[169].collision = true;

            tile[170] = new Tile();
            tile[170].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image11x6.png"));
            tile[170].collision = true;

            tile[171] = new Tile();
            tile[171].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image12x6.png"));
            tile[171].collision = true;

            tile[172] = new Tile();
            tile[172].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image13x6.png"));
            tile[172].collision = true;

            tile[173] = new Tile();
            tile[173].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image14x6.png"));
            tile[173].collision = true;

            tile[174] = new Tile();
            tile[174].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image15x6.png"));
            tile[174].collision = true;

            tile[175] = new Tile();
            tile[175].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image16x6.png"));
            tile[175].collision = true;

            tile[176] = new Tile();
            tile[176].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image17x6.png"));
            tile[176].collision = true;

            tile[177] = new Tile();
            tile[177].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image18x6.png"));
            tile[177].collision = true;

            tile[178] = new Tile();
            tile[178].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image19x6.png"));
            tile[178].collision = true;

            tile[179] = new Tile();
            tile[179].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image20x6.png"));
            tile[179].collision = true;

            tile[180] = new Tile();
            tile[180].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image21x6.png"));
            tile[180].collision = true;

            tile[181] = new Tile();
            tile[181].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image22x6.png"));
            tile[181].collision = true;

            tile[182] = new Tile();
            tile[182].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image23x6.png"));
            tile[182].collision = true;

            tile[183] = new Tile();
            tile[183].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image24x6.png"));
            tile[183].collision = true;

            tile[184] = new Tile();
            tile[184].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image25x6.png"));
            tile[184].collision = true;

            tile[185] = new Tile();
            tile[185].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image26x6.png"));
            tile[185].collision = true;

            tile[186] = new Tile();
            tile[186].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image27x6.png"));
            tile[186].collision = true;

            tile[187] = new Tile();
            tile[187].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image28x6.png"));
            tile[187].collision = true;

            tile[188] = new Tile();
            tile[188].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image29x6.png"));
            tile[188].collision = true;

            tile[189] = new Tile();
            tile[189].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image30x6.png"));
            tile[189].collision = true;

            tile[190] = new Tile();
            tile[190].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image31x6.png"));
            tile[190].collision = true;

            tile[191] = new Tile();
            tile[191].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image32x6.png"));
            tile[191].collision = true;

            tile[192] = new Tile();
            tile[192].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image1x7.png"));
            tile[192].collision = true;

            tile[193] = new Tile();
            tile[193].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image2x7.png"));
            tile[193].collision = true;

            tile[194] = new Tile();
            tile[194].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image3x7.png"));
            tile[194].collision = true;

            tile[195] = new Tile();
            tile[195].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image4x7.png"));
            tile[195].collision = true;

            tile[196] = new Tile();
            tile[196].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image5x7.png"));
            tile[196].collision = true;

            tile[197] = new Tile();
            tile[197].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image6x7.png"));
            tile[197].collision = true;

            tile[198] = new Tile();
            tile[198].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image7x7.png"));
            tile[198].collision = true;

            tile[199] = new Tile();
            tile[199].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image8x7.png"));
            tile[199].collision = true;

            tile[200] = new Tile();
            tile[200].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image9x7.png"));
            tile[200].collision = true;

            tile[201] = new Tile();
            tile[201].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image10x7.png"));
            tile[201].collision = true;

            tile[202] = new Tile();
            tile[202].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image11x7.png"));
            tile[202].collision = true;

            tile[203] = new Tile();
            tile[203].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image12x7.png"));
            tile[203].collision = true;

            tile[204] = new Tile();
            tile[204].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image13x7.png"));
            tile[204].collision = true;

            tile[205] = new Tile();
            tile[205].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image14x7.png"));
            tile[205].collision = true;

            tile[206] = new Tile();
            tile[206].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image15x7.png"));
            tile[206].collision = true;

            tile[207] = new Tile();
            tile[207].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image16x7.png"));
            tile[207].collision = true;

            tile[208] = new Tile();
            tile[208].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image17x7.png"));
            tile[208].collision = true;

            tile[209] = new Tile();
            tile[209].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image18x7.png"));
            tile[209].collision = true;

            tile[210] = new Tile();
            tile[210].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image19x7.png"));
            tile[210].collision = true;

            tile[211] = new Tile();
            tile[211].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image20x7.png"));
            tile[211].collision = true;

            tile[212] = new Tile();
            tile[212].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image21x7.png"));
            tile[212].collision = true;

            tile[213] = new Tile();
            tile[213].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image22x7.png"));
            tile[213].collision = true;

            tile[214] = new Tile();
            tile[214].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image23x7.png"));
            tile[214].collision = true;

            tile[215] = new Tile();
            tile[215].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image24x7.png"));
            tile[215].collision = true;

            tile[216] = new Tile();
            tile[216].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image25x7.png"));
            tile[216].collision = true;

            tile[217] = new Tile();
            tile[217].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image26x7.png"));
            tile[217].collision = true;

            tile[218] = new Tile();
            tile[218].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image27x7.png"));
            tile[218].collision = true;

            tile[219] = new Tile();
            tile[219].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image28x7.png"));
            tile[219].collision = true;

            tile[220] = new Tile();
            tile[220].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image29x7.png"));
            tile[220].collision = true;

            tile[221] = new Tile();
            tile[221].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image30x7.png"));
            tile[221].collision = true;

            tile[222] = new Tile();
            tile[222].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image31x7.png"));
            tile[222].collision = true;

            tile[223] = new Tile();
            tile[223].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image32x7.png"));
            tile[223].collision = true;

            tile[224] = new Tile();
            tile[224].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image1x8.png"));
            tile[224].collision = true;

            tile[225] = new Tile();
            tile[225].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image2x8.png"));
            tile[225].collision = true;

            tile[226] = new Tile();
            tile[226].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image3x8.png"));
            tile[226].collision = true;

            tile[227] = new Tile();
            tile[227].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image4x8.png"));
            tile[227].collision = true;

            tile[228] = new Tile();
            tile[228].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image5x8.png"));
            tile[228].collision = true;

            tile[229] = new Tile();
            tile[229].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image6x8.png"));
            tile[229].collision = true;

            tile[230] = new Tile();
            tile[230].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image7x8.png"));
            tile[230].collision = true;

            tile[231] = new Tile();
            tile[231].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image8x8.png"));
            tile[231].collision = true;

            tile[232] = new Tile();
            tile[232].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image9x8.png"));
            tile[232].collision = true;

            tile[233] = new Tile();
            tile[233].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image10x8.png"));
            tile[233].collision = true;

            tile[234] = new Tile();
            tile[234].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image11x8.png"));
            tile[234].collision = true;

            tile[235] = new Tile();
            tile[235].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image12x8.png"));
            tile[235].collision = true;

            tile[236] = new Tile();
            tile[236].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image13x8.png"));
            tile[236].collision = true;

            tile[237] = new Tile();
            tile[237].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image14x8.png"));
            tile[237].collision = true;

            tile[238] = new Tile();
            tile[238].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image15x8.png"));
            tile[238].collision = true;

            tile[239] = new Tile();
            tile[239].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image16x8.png"));
            tile[239].collision = true;

            tile[240] = new Tile();
            tile[240].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image17x8.png"));
            tile[240].collision = true;

            tile[241] = new Tile();
            tile[241].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image18x8.png"));
            tile[241].collision = true;

            tile[242] = new Tile();
            tile[242].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image19x8.png"));
            tile[242].collision = true;

            tile[243] = new Tile();
            tile[243].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image20x8.png"));
            tile[243].collision = true;

            tile[244] = new Tile();
            tile[244].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image21x8.png"));
            tile[244].collision = true;

            tile[245] = new Tile();
            tile[245].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image22x8.png"));
            tile[245].collision = true;

            tile[246] = new Tile();
            tile[246].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image23x8.png"));
            tile[246].collision = true;

            tile[247] = new Tile();
            tile[247].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image24x8.png"));
            tile[247].collision = true;

            tile[248] = new Tile();
            tile[248].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image25x8.png"));
            tile[248].collision = true;

            tile[249] = new Tile();
            tile[249].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image26x8.png"));
            tile[249].collision = true;

            tile[250] = new Tile();
            tile[250].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image27x8.png"));
            tile[250].collision = true;

            tile[251] = new Tile();
            tile[251].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image28x8.png"));
            tile[251].collision = true;

            tile[252] = new Tile();
            tile[252].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image29x8.png"));
            tile[252].collision = true;

            tile[253] = new Tile();
            tile[253].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image30x8.png"));
            tile[253].collision = true;

            tile[254] = new Tile();
            tile[254].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image31x8.png"));
            tile[254].collision = true;

            tile[255] = new Tile();
            tile[255].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image32x8.png"));
            tile[255].collision = true;

            tile[256] = new Tile();
            tile[256].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image1x9.png"));
            tile[256].collision = true;

            tile[257] = new Tile();
            tile[257].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image2x9.png"));
            tile[257].collision = true;

            tile[258] = new Tile();
            tile[258].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image3x9.png"));
            tile[258].collision = true;

            tile[259] = new Tile();
            tile[259].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image4x9.png"));
            tile[259].collision = true;

            tile[260] = new Tile();
            tile[260].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image5x9.png"));
            tile[260].collision = true;

            tile[261] = new Tile();
            tile[261].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image6x9.png"));
            tile[261].collision = true;

            tile[262] = new Tile();
            tile[262].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image7x9.png"));
            tile[262].collision = true;

            tile[263] = new Tile();
            tile[263].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image8x9.png"));
            tile[263].collision = true;

            tile[264] = new Tile();
            tile[264].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image9x9.png"));
            tile[264].collision = true;

            tile[265] = new Tile();
            tile[265].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image10x9.png"));
            tile[265].collision = true;

            tile[266] = new Tile();
            tile[266].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image11x9.png"));
            tile[266].collision = true;

            tile[267] = new Tile();
            tile[267].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image12x9.png"));
            tile[267].collision = true;

            tile[268] = new Tile();
            tile[268].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image13x9.png"));
            tile[268].collision = true;

            tile[269] = new Tile();
            tile[269].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image14x9.png"));
            tile[269].collision = true;

            tile[270] = new Tile();
            tile[270].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image15x9.png"));
            tile[270].collision = true;

            tile[271] = new Tile();
            tile[271].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image16x9.png"));
            tile[271].collision = true;

            tile[272] = new Tile();
            tile[272].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image17x9.png"));
            tile[272].collision = true;

            tile[273] = new Tile();
            tile[273].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image18x9.png"));
            tile[273].collision = true;

            tile[274] = new Tile();
            tile[274].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image19x9.png"));
            tile[274].collision = true;

            tile[275] = new Tile();
            tile[275].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image20x9.png"));
            tile[275].collision = true;

            tile[276] = new Tile();
            tile[276].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image21x9.png"));
            tile[276].collision = true;

            tile[277] = new Tile();
            tile[277].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image22x9.png"));
            tile[277].collision = true;

            tile[278] = new Tile();
            tile[278].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image23x9.png"));
            tile[278].collision = true;

            tile[279] = new Tile();
            tile[279].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image24x9.png"));
            tile[279].collision = true;

            tile[280] = new Tile();
            tile[280].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image25x9.png"));
            tile[280].collision = true;

            tile[281] = new Tile();
            tile[281].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image26x9.png"));
            tile[281].collision = true;

            tile[282] = new Tile();
            tile[282].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image27x9.png"));
            tile[282].collision = true;

            tile[283] = new Tile();
            tile[283].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image28x9.png"));
            tile[283].collision = true;

            tile[284] = new Tile();
            tile[284].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image29x9.png"));
            tile[284].collision = true;

            tile[285] = new Tile();
            tile[285].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image30x9.png"));
            tile[285].collision = true;

            tile[286] = new Tile();
            tile[286].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image31x9.png"));
            tile[286].collision = true;

            tile[287] = new Tile();
            tile[287].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image32x9.png"));
            tile[287].collision = true;

            tile[288] = new Tile();
            tile[288].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image1x10.png"));
            tile[288].collision = true;

            tile[289] = new Tile();
            tile[289].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image2x10.png"));
            tile[289].collision = true;

            tile[290] = new Tile();
            tile[290].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image3x10.png"));
            tile[290].collision = true;

            tile[291] = new Tile();
            tile[291].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image4x10.png"));
            tile[291].collision = true;

            tile[292] = new Tile();
            tile[292].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image5x10.png"));
            tile[292].collision = true;

            tile[293] = new Tile();
            tile[293].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image6x10.png"));
            tile[293].collision = true;

            tile[294] = new Tile();
            tile[294].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image7x10.png"));
            tile[294].collision = true;

            tile[295] = new Tile();
            tile[295].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image8x10.png"));
            tile[295].collision = true;

            tile[296] = new Tile();
            tile[296].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image9x10.png"));
            tile[296].collision = true;

            tile[297] = new Tile();
            tile[297].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image10x10.png"));
            tile[297].collision = true;

            tile[298] = new Tile();
            tile[298].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image11x10.png"));
            tile[298].collision = true;

            tile[299] = new Tile();
            tile[299].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image12x10.png"));
            tile[299].collision = true;

            tile[300] = new Tile();
            tile[300].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image13x10.png"));
            tile[300].collision = true;

            tile[301] = new Tile();
            tile[301].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image14x10.png"));
            tile[301].collision = true;

            tile[302] = new Tile();
            tile[302].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image15x10.png"));
            tile[302].collision = true;

            tile[303] = new Tile();
            tile[303].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image16x10.png"));
            tile[303].collision = true;

            tile[304] = new Tile();
            tile[304].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image17x10.png"));
            tile[304].collision = true;

            tile[305] = new Tile();
            tile[305].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image18x10.png"));
            tile[305].collision = true;

            tile[306] = new Tile();
            tile[306].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image19x10.png"));
            tile[306].collision = true;

            tile[307] = new Tile();
            tile[307].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image20x10.png"));
            tile[307].collision = true;

            tile[308] = new Tile();
            tile[308].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image21x10.png"));
            tile[308].collision = true;

            tile[309] = new Tile();
            tile[309].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image22x10.png"));
            tile[309].collision = true;

            tile[310] = new Tile();
            tile[310].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image23x10.png"));
            tile[310].collision = true;

            tile[311] = new Tile();
            tile[311].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image24x10.png"));
            tile[311].collision = true;

            tile[312] = new Tile();
            tile[312].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image25x10.png"));
            tile[312].collision = true;

            tile[313] = new Tile();
            tile[313].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image26x10.png"));
            tile[313].collision = true;

            tile[314] = new Tile();
            tile[314].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image27x10.png"));
            tile[314].collision = true;

            tile[315] = new Tile();
            tile[315].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image28x10.png"));
            tile[315].collision = true;

            tile[316] = new Tile();
            tile[316].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image29x10.png"));
            tile[316].collision = true;

            tile[317] = new Tile();
            tile[317].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image30x10.png"));
            tile[317].collision = true;

            tile[318] = new Tile();
            tile[318].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image31x10.png"));
            tile[318].collision = true;

            tile[319] = new Tile();
            tile[319].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/image32x10.png"));
            tile[319].collision = true;

            tile[320] = new Tile();
            tile[320].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(320).png"));
            tile[320].collision = true;

            tile[321] = new Tile();
            tile[321].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(321).png"));
            tile[321].collision = true;

            tile[322] = new Tile();
            tile[322].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(322).png"));
            tile[322].collision = true;

            tile[323] = new Tile();
            tile[323].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(323).png"));
            tile[323].collision = true;

            tile[324] = new Tile();
            tile[324].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(324).png"));
            tile[324].collision = true;

            tile[325] = new Tile();
            tile[325].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(325).png"));
            tile[325].collision = true;

            tile[326] = new Tile();
            tile[326].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(326).png"));
            tile[326].collision = true;

            tile[327] = new Tile();
            tile[327].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(327).png"));
            tile[327].collision = true;

            tile[328] = new Tile();
            tile[328].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(328).png"));
            tile[328].collision = true;

            tile[329] = new Tile();
            tile[329].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(329).png"));
            // tile[329].collision = true;

            tile[330] = new Tile();
            tile[330].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(330).png"));
            tile[330].collision = true;

            tile[331] = new Tile();
            tile[331].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(331).png"));
            tile[331].collision = true;

            tile[332] = new Tile();
            tile[332].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(332).png"));
            tile[332].collision = true;

            tile[333] = new Tile();
            tile[333].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(333).png"));
            tile[333].collision = true;

            tile[334] = new Tile();
            tile[334].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(334).png"));
            tile[334].collision = true;

            tile[335] = new Tile();
            tile[335].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(335).png"));
            tile[335].collision = true;

            tile[336] = new Tile();
            tile[336].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(336).png"));
            tile[336].collision = true;

            tile[337] = new Tile();
            tile[337].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(337).png"));
            tile[337].collision = true;

            tile[338] = new Tile();
            tile[338].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(338).png"));
            tile[338].collision = true;

            tile[339] = new Tile();
            tile[339].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(339).png"));
            tile[339].collision = true;

            tile[340] = new Tile();
            tile[340].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(340).png"));
            tile[340].collision = true;

            tile[341] = new Tile();
            tile[341].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(341).png"));
            tile[341].collision = true;

            tile[342] = new Tile();
            tile[342].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(342).png"));
            tile[342].collision = true;

            tile[343] = new Tile();
            tile[343].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(343).png"));
            tile[343].collision = true;

            tile[344] = new Tile();
            tile[344].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(344).png"));
            // tile[344].collision = true;

            tile[345] = new Tile();
            tile[345].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/MountainLake/0(345).png"));
            tile[345].collision = true;
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("res/MountainLake/MountainLake.txt"));
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
