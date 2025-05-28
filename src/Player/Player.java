import java.util.HashMap;
import java.util.Map;

import Entity.PlayerNeeds.ShippingBin;
import Items.Inventory;   

public class Player {
    private String name;
    private String gender;
    private String farmName;
    private int energy;

    private Inventory inventory;
    private GoldManager goldManager;
    private ShippingBin shippingBin;

    private int xPos;
    private int yPos;

    public Player (String name, String gender, String farmName) {
        this.name = name;
        this.gender = gender;
        this.farmName = farmName;
        this.energy = 100;

        this.inventory = new Inventory();
        this.goldManager = new GoldManager();  
        this.shippingBin = new ShippingBin();

        this.xPos = 0;
        this.yPos = 0;

        this.heartPoints = new HashMap<>();
        this.relationship = Relationship.SINGLE;
        this.partner = null;

    }

    public void doAction(Action action) {
        action.execute(this);
    }

    public void changeStamina(int amount) {
        this.energy += amount;
        if (this.energy > 100) {
            this.energy = 100;
        } else if (this.energy < 0) {
            this.energy = 0;
        }
    }

    public void moveTo(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public GoldManager getGoldManager() {
        return goldManager;
    }

    public ShippingBin getShippingBin() {
        return shippingBin;
    }

    public int getEnergy() {
        return energy;
    }

    public Map<String, Integer> getHeartPoints() {
        return heartPoints;
    }

    public void setHeartPoints(NPC npc, int value) {
        heartPoints.put(npc, value);
    }

    public relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public NPC getPartner() {
        return partner;
    }

    public void setPartner(NPC partner) {
        this.partner = partner;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getFarmName() {
        return farmName;
    }
}