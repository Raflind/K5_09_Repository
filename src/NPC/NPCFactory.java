package NPC;

import Factory.Factory;

public class NPCFactory implements Factory<NPC> {
   
    @Override
    public NPC create(String nameString){
        switch (nameString) {
            case "":
                return new NPC();
        
            default:
                return new NPC();
        }
    }
}
