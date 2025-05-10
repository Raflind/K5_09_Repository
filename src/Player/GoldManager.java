public class GoldManager {
    private int gold;

    public GoldManager() {
        this.gold = 0; 
    }

    public void addGold(int amount) {
        if (amount > 0) {
            gold += amount;
        }
    }

    public boolean spendGold(int amount) {
        if (amount > 0 && gold >= amount) {
            gold -= amount;
            return true;
        }
        return false;
    }

    public int getGold() {
        return gold;
    }
}