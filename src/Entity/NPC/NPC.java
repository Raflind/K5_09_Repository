package Entity.NPC;

import java.util.ArrayList;
import Entity.Entity;
import Entity.Player;
import Items.Items;
import main.GamePanel;
import Exception.*;

/**
 * @author @hananda23
 * 
 * Kelas parent untuk NPC, digunakan untuk menyimpan hasil instansiasi dari NPCFactory. 
 * Contoh:
 * NPCFactory f = new NPCFactory()
 * NPC npc = f.create("nama"), isi parameter nama dengan nama NPC yang ingin dibuat
 */


public abstract class NPC extends Entity{

    public enum Relationship {
        SINGLE,
        FIANCE,
        SPOUSE
    }

    //Attribute
    private String name;
    private int heartPoints;
    private static int MAX_HEARTPOINTS = 150;
    private ArrayList<Items> lovedItems;
    private ArrayList<Items> likedItems;
    private ArrayList<Items> hatedItems;
    private Relationship relationshipStatus;
    private static ArrayList<NPC> npcList;
    private int visitedFreq;
    private int giftedFreq;
    private int chatFreq;

    //Contructor
    public NPC(String name, GamePanel gp){
        super(gp);
        this.name = name;
        this.lovedItems = new ArrayList<Items>();
        this.likedItems = new ArrayList<Items>();
        this.hatedItems = new ArrayList<Items>();
        heartPoints = 0;
        visitedFreq = 0;
        giftedFreq = 0;
        chatFreq = 0;
        relationshipStatus = Relationship.SINGLE;
        if (npcList.size() != 1) {
            npcList = new ArrayList<>();
        }
        npcList.add(this);
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

    public ArrayList<Items> getLovedItems(){
        return lovedItems;
    }

    public ArrayList<Items> getLikedItems(){
        return likedItems;
    }

    public ArrayList<Items> getHateItems(){
        return hatedItems;
    }

    public Relationship getRelationship(){
        return relationshipStatus;
    }

    public int getVisitingFreq(){
        return visitedFreq;
    }

    public int getChattingFreq(){
        return chatFreq;
    }

    public int getGiftingFreq(){
        return giftedFreq;
    }

    

    //Setter
    public void setName(String name){
        this.name = name;
    }

    public void addLovedItem(Items item){
        lovedItems.add(item);
    }

    public void addLikedItem(Items item){
        likedItems.add(item);
    }

    public void addHatedItem(Items item){
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

    public void visited(){
        visitedFreq++;
    }

    //Methods
    private Boolean isLovedItem(Items item){
        return lovedItems.contains(item);
    }

    private Boolean isLikedItem(Items item){
        return likedItems.contains(item);
    }

    private Boolean isHatedItem(Items item){
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
    public void recieveGifts(Player player, Items item) throws WrongUseFunctionException{
        
        //System Calling
        System.out.println(getName() + " menerima barang yang diberikan " + player.getName());

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
        giftedFreq++;

        System.out.println("Heart Points " + getName() + " : " + getHeartPoints());
    }

    
    
    private void updateRelation(){
        switch (getRelationship()) {
            case SINGLE:
                setRelationshipStatus(Relationship.FIANCE);
            case FIANCE:
                setRelationshipStatus(Relationship.SPOUSE);
            case SPOUSE:
                setRelationshipStatus(Relationship.SINGLE);
            default:
                break;
        }
    }

    public void propose(){
        updateRelation();

    }

    public void marry(){
        updateRelation();
    }

    public void breakUp(){
        updateRelation();

    }

    public void chat() throws WrongUseFunctionException{
        chatFreq++;
        increaseHeartPoints(10);
    }

    public void ask(){

    }


    public void interact(Player player){
        String name = player.getName();
        npcResponse("Halo " + name + " sudah lama kita tidak berjumpa!");
        npcResponse("apakah ada yang bisa saya bantu?");
        int opt = 0; //harusnya string intinya pilihan optionnya
        Boolean isInteract = true;
    }
}
