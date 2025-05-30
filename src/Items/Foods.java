package Items;


public class Foods extends Items {
    private int energyPoints;

    public Foods(String name, int sellPrice, int buyPrice, boolean isEdible, int itemID, int healthPoints, int energyPoints) {
        super(name, sellPrice, buyPrice, isEdible, itemID);
        this.energyPoints = energyPoints;
    }

    public int getEnergyPoints() {
        return energyPoints;
    }
}
