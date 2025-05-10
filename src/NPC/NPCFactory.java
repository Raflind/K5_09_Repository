package NPC;

import Exception.NotInTheListException;
import Factory.Factory;

public class NPCFactory implements Factory<NPC> {
   
    @Override
    public NPC create(String name) throws NotInTheListException{
        switch (name) {
            case "Mayor Tadi":
                return new MayorTadi();
            case "Perry":
                return new Perry();
            case "Dasco":
                return new Dasco();
            case "Emily":
                return new Emily();
            case "Caroline":
                return new Caroline();
            case "Abigail":
                return new Abigail();
            default:
                throw new NotInTheListException(name); 
        }
    }
}
