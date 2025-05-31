package Items;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public enum ItemMiscList implements ItemCreator<Items> {
    ProposalRing("Proposal Ring", 10, 15, false, null),
    Coal("Coal", 2, 3, false, null),
    Firewood("Firewood", 1, 2, false, null);

    private final String name;
    private final int sellPrice;
    private final int buyPrice;
    private final boolean isEdible;
    public final BufferedImage image;

    private ItemMiscList(String name, int sellPrice, int buyPrice, boolean isEdible, BufferedImage image) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.isEdible = isEdible;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    /**
     * @pakai : Items item = ItemMiscList.(nama si item).create()
     * @Contoh contoh Items item = ItemMiscList.Coal.create() 
     * @return Items: tipe item
     * 
     */
    public Items create(){
        BufferedImage image = null;
        try{
            image = ImageIO.read(ItemMiscList.class.getClassLoader().getResourceAsStream("res/Items/" + name + ".png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return new Items(name, sellPrice, buyPrice, isEdible, image);
    }
}


