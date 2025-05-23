package Items;

public class Items {
    private String name;
    private int sellPrice;
    private int buyPrice;
    private boolean isEdible;
    private int itemID;


    public Items(String name, int sellPrice, int buyPrice, boolean isEdible, int itemID){
        this.name = name;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.isEdible = isEdible;
        this.itemID = itemID;
    }
    public String getName() {
        return name;
    }  
    public int getSellPrice() {
        return sellPrice;
    }
    public int getBuyPrice() {
        return buyPrice;
    }
    public boolean isEdible() {
        return isEdible;
    }
    public int getItemID() {
        return itemID;
    }
    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }
}

