package NPC;

//import java.util.ArrayList;

/**
 * @author @hananda23
 * 
 * Kelas parent untuk NPC, digunakan untuk menyimpan hasil instansiasi dari NPCFactory. 
 * Contoh:
 * NPCFactory f = new NPCFactory()
 * NPC npc = f.create("nama"), isi parameter nama dengan nama NPC yang ingin dibuat
 */


public abstract class NPC{
    
    //Attribute
    private String name;
    private int heartPoints;
    private static int MAX_HEARTPOINTS = 150;
    /*private ArrayList<String> lovedItems;
    private ArrayList<String> likedItems;
    private ArrayList<String> hatedItems;*/
    private Relationship relationshipStatus;

    //Contructor
    public NPC(String name /*ArrayList<String> lovedItems, ArrayList<String> likedItems, ArrayList<String> hatedItems */){
        this.name = name;
        //this.lovedItems = lovedItems;
        //this.likedItems = likedItems;
        //this.hatedItems = hatedItems;
        heartPoints = 0;
        relationshipStatus = Relationship.SINGLE;
    }

    //Getter
    public String getName(){
        return name;
    }

    public int getHeartPoints(){
        return heartPoints;
    }

    /*public ArrayList<String> getLovedItems(){
        return lovedItems;
    }

    public ArrayList<String> getLikeddItems(){
        return likedItems;
    }

    public ArrayList<String> getHateItems(){
        return hatedItems;
    }*/

    public Relationship getRelationship(){
        return relationshipStatus;
    }

    //Setter
    public void setName(String name){
        this.name = name;
    }

    private void setRelationshipStatus(Relationship status){
        relationshipStatus = status;
    }

    private void setHeartPoints(int heartPoints) { //throw kalau lebih adari 150
        if (heartPoints < MAX_HEARTPOINTS) {
            this.heartPoints = heartPoints;
        }else if(heartPoints > MAX_HEARTPOINTS){
            this.heartPoints = MAX_HEARTPOINTS;
        } else if(heartPoints < 0){
            this.heartPoints = 0;
        }
    }

    //Methods
    /**
     * Method respon interaksi dengan player, tiap NPC memiliki definisi
     * 
     */
    public abstract void interact();

    /*private Boolean isLovedItem(String item){
        return lovedItems.contains(item);
    }

    private Boolean isLikedItem(String item){
        return likedItems.contains(item);
    }

    private Boolean isHatedItem(String item){
        return hatedItems.contains(item);
    }*/

    public void recieveGifts(String player, String item){
        System.out.println("Nunggu Itemnya");

        /*
         * 
         * 
         *  
         */
    }
}
