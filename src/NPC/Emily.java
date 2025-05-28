package NPC;

public class Emily extends NPC implements Store {
    
    public Emily(){
        super("Emily");
    }

    @Override
    public void interact(){
        System.out.println("On-Develop");
    }

    public void selling(){

    }
    
}
