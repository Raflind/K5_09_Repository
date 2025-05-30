package Entity.NPC;

import Entity.Player;
import main.GamePanel;

public class Emily extends NPC implements Store {
    
    public Emily(GamePanel gp){
        super("Emily", gp);
    }

    @Override
    public void interact(Player player){
        System.out.println("On-Develop");
    }

    public void selling(){

    }
    
}
