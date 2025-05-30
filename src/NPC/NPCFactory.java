package NPC;

import Exception.NotInTheListException;
import Factory.Factory;
import main.GamePanel;

public class NPCFactory extends Factory<NPC> {

    public NPCFactory(GamePanel gp){
        super(gp);
    }
   
    @Override
    public NPC create(String name) throws NotInTheListException{
        switch (name) {
            case "Mayor Tadi":
                return new MayorTadi(getGP());
            case "Perry":
                return new Perry(getGP());
            case "Dasco":
                return new Dasco(getGP());
            case "Emily":
                return new Emily(getGP());
            case "Caroline":
                return new Caroline(getGP());
            case "Abigail":
                return new Abigail(getGP());
            default:
                throw new NotInTheListException(name); 
        }
    }
}
