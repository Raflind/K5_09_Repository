package Items;

public class Seeds extends Items {
    private int daystoHarvest;
    private boolean isWatered;
    private int dayelapsed;

    public Seeds(String name, int sellPrice, int buyPrice, boolean isEdible, int itemID, int daystoHarvest) {
        super(name, sellPrice, buyPrice, isEdible, itemID);
        this.daystoHarvest = daystoHarvest;
        this.isWatered = false;
        this.dayelapsed = 0;
    }

    public void water() {
        this.isWatered = true;
    }

    public void unwater() {
        this.isWatered = false;
    }
    public boolean isWatered() {
        return isWatered;
    }
    public int getDaystoHarvest() {
        return daystoHarvest;
    }
    public int getDayelapsed() {
        return dayelapsed;
    }
    public void incrementDayElapsed() {
        this.dayelapsed++;
    }
    public boolean isHarvestable() {
        return dayelapsed >= daystoHarvest && isWatered;
    }
}
