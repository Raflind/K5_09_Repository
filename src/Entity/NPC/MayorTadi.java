package Entity.NPC;

import Entity.Player;
import main.GamePanel;

public class MayorTadi extends NPC {

    public MayorTadi(GamePanel gp){
        super("Mayor Tadi", gp);
    }

    @Override
    public void interact(Player player){
        super.interact(player);
        System.out.println("On-Develop");
    }
    
}
