package NPC;



import java.util.ArrayList;

import Exception.*;

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
    private ArrayList<String> lovedItems;
    private ArrayList<String> likedItems;
    private ArrayList<String> hatedItems;
    private Relationship relationshipStatus;

    //Contructor
    public NPC(String name){
        this.name = name;
        this.lovedItems = new ArrayList<String>();
        this.likedItems = new ArrayList<String>();
        this.hatedItems = new ArrayList<String>();
        heartPoints = 0;
        relationshipStatus = Relationship.SINGLE;
    }

    //Getter
    public String getName(){
        return name;
    }

    public static int getMAX_HEARTPOINTS(){
        return MAX_HEARTPOINTS;
    }

    public int getHeartPoints(){
        return heartPoints;
    }

    public ArrayList<String> getLovedItems(){
        return lovedItems;
    }

    public ArrayList<String> getLikeddItems(){
        return likedItems;
    }

    public ArrayList<String> getHateItems(){
        return hatedItems;
    }

    public Relationship getRelationship(){
        return relationshipStatus;
    }

    //Setter
    public void setName(String name){
        this.name = name;
    }

    public void addLovedItem(String item){
        lovedItems.add(item);
    }

    public void addLikedItem(String item){
        likedItems.add(item);
    }

    public void addHatedItem(String item){
        hatedItems.add(item);
    }

    private void setRelationshipStatus(Relationship status){
        relationshipStatus = status;
    }

    private void increaseHeartPoints(int plusHeartPoints) throws WrongUseFunctionException{ 
        if (getHeartPoints() + plusHeartPoints < getMAX_HEARTPOINTS()) {
            heartPoints += plusHeartPoints;
        }else if(getHeartPoints() + plusHeartPoints >= getMAX_HEARTPOINTS()){
            heartPoints = getHeartPoints();
        } else if(plusHeartPoints < 0){
            throw new WrongUseFunctionException("gunakan decreaseHeartPoints() untuk mengurangi");
        }
    }

    private void decreaseHeartPoints(int minHeartPoints) throws WrongUseFunctionException{
        if (getHeartPoints() - minHeartPoints <= 0) {
            heartPoints = 0;
        }else if (getHeartPoints() - minHeartPoints > 0) {
            heartPoints -= minHeartPoints;
        }else if (minHeartPoints < 0) {
            throw new WrongUseFunctionException("Tidak perlu menggunakan nilai negatif");
        }
    }

    //Methods
    private Boolean isLovedItem(String item){
        return lovedItems.contains(item);
    }

    private Boolean isLikedItem(String item){
        return likedItems.contains(item);
    }

    private Boolean isHatedItem(String item){
        return hatedItems.contains(item);
    }

    public void npcResponse(String message){
        System.out.println(getName() + ": " + message);
    }

    
    /**
     * buat Action Gifting bisa pake method ini, isinya udah ada kondisional penambahan heartpoints
     * cuman belum sampe keseluruhan logika dari Action Gifting, nanti bisa manggil ini buat pengganti
     * method mengubah nilai heartpoints si npc
     * 
     * @param playerName isinya nama player nya buat dipake jadi parameter ngeluarin pesan
     * @param item item yang mau dikasih ke si npc ini
     */
    public void recieveGifts(String playerName, String item) throws WrongUseFunctionException{
        
        //System Calling
        System.out.println(getName() + " menerima barang yang diberikan " + playerName);

        if (isLovedItem(item)) {
            npcResponse("I reallyy love this, makaasihh");
            increaseHeartPoints(25);
        } else if (isLikedItem(item)) {
            npcResponse("I liked this one, makasih");
            increaseHeartPoints(20);
        } else if(isHatedItem(item)){
            npcResponse("masuk lo apaan ngirim beginian");
            decreaseHeartPoints(25);
        }

        System.out.println("Heart Points " + getName() + " : " + getHeartPoints());
    }

    
    /**
    public void updateRelation(){
        switch (getRelationship()) {
            case Relationship.SINGLE:
                setRelationshipStatus(Relationship.FIANCE);
                
                break;
            
            case Relationship.FIANCE:
                setRelationshipStatus(Relationship.SPOUSE);
                break;

            case Relationship.SPOUSE:
                setRelationshipStatus(Relationship.SINGLE);
                break;
        
            default:
                break;
        }
    } */

    public abstract void interact();
}
