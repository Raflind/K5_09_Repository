package Entity;

import java.util.ArrayList;
import java.util.Random;

import Items.Items;
import main.GamePanel;
import Exception.*;
import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author @hananda23
 * 
 * Kelas parent untuk NPC, digunakan untuk menyimpan hasil instansiasi dari NPCFactory. 
 * Contoh:
 * NPCFactory f = new NPCFactory()
 * NPC npc = f.create("nama"), isi parameter nama dengan nama NPC yang ingin dibuat
 */


public class NPC extends Entity{

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
    private int visitedFreq = 0;
    private int giftedFreq = 0;
    private int chatFreq = 0;
    private int actionLockCounter = 0;
    private int spriteCounter = 0;
    private int spriteNum = 1;
    public String[] chatStrings = new String[3];
    public int proposalDate = 0;
    public String words;

    //Contructor
    public NPC(String name, GamePanel gp, String chatStrings1, String chatStrings2, String chatStrings3) {
        super(gp);
        this.chatStrings[0] = chatStrings1;
        this.chatStrings[1] = chatStrings2;
        this.chatStrings[2] = chatStrings3;
        this.gp = gp;
        this.name = name;
        this.lovedItems = new ArrayList<Items>();
        this.likedItems = new ArrayList<Items>();
        this.hatedItems = new ArrayList<Items>();
        heartPoints = 0;
        visitedFreq = 0;
        giftedFreq = 0;
        chatFreq = 0;
        relationshipStatus = Relationship.SINGLE;
        worldX = 15*gp.tileSize; // Posisi awal NPC, bisa diubah sesuai kebutuhan
        worldY = 15*gp.tileSize; // Posisi awal NPC, bisa diubah sesuai 
        solidArea = new Rectangle(0, 0, 48, 48);
        direction = "diam"; // Arah awal NPC
        speed = 1; // Kecepatan NPC
        // if (npcList.size() != 1) {
        //     npcList = new ArrayList<>();
        // }
        // npcList.add(this);
        loadNPCImages();
    }

    public void moveAlgo(){
         actionLockCounter++;
         if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(125)+1; 
                if (i <= 25) {
                    direction = "depan"; // depan
                }
                if (i > 25 && i <= 50) {
                    direction = "blkg"; // belakang
                }
                if (i > 50 && i <= 75) {
                    direction = "kiri";
                }
                if (i > 75 && i <= 100) {
                    direction = "kanan";
                }
                if (i > 100 && i <= 125) {
                    direction = "diam"; // diam
                }
                actionLockCounter = 0; // Reset counter setelah aksi actionLockCounter = 0; 
            }
    }

    public void update(){
        moveAlgo();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkPlayer(this);
        if(collisionOn == false){
            switch(direction){
                case "depan":
                    worldY = Math.min(worldY + speed, gp.tileM.size * gp.tileSize - 4*gp.tileSize/3);
                    break;
                case "blkg":
                    worldY = Math.max(worldY - speed, 0);
                    break;
                case "kiri":
                    worldX = Math.max(worldX - speed, 0);
                    break;
                case "kanan":
                    worldX = Math.min(worldX + speed, gp.tileM.size * gp.tileSize - gp.tileSize);
                    break;
            }
        }

        // Animasi sprite: ganti spriteNum tiap beberapa frame
        spriteCounter++;
        if(spriteCounter > 18) { // ganti angka sesuai kecepatan animasi yang diinginkan
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }
    
    public void draw(Graphics2D g2) {
        // Hitung posisi relatif terhadap layar
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        BufferedImage image = null;
        switch(direction){
            case "depan":
                image = (spriteNum==1) ? depan1 : depan2;
                break;
            case "blkg":
                image = (spriteNum==1) ? blkg1 : blkg2;
                break;
            case "kiri":
                image = (spriteNum==1) ? kiri1 : kiri2;
                break;
            case "kanan":
                image = (spriteNum==1) ? kanan1 : kanan2;
                break;
            case "diam":
                image = diam;
                break;
        }

        // IF: hanya gambar jika masih dalam layar (range screen player)
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
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
            heartPoints = getMAX_HEARTPOINTS();
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

    public void  npcResponse(String message){
        words = message;
    }
    
    public String getResponse(){
        return words;
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
            npcResponse("Maksud lo apaan ngirim beginian");
            decreaseHeartPoints(25);
        } else{
            npcResponse("Nyeh udah sering dikasih");
        }
        giftedFreq++;
        gp.player.inventory.removeItem(item);

        System.out.println("Heart Points " + getName() + " : " + getHeartPoints());
    }

    
    
    private void updateRelation(){
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
    }

    public void propose(){
        System.out.println(getHeartPoints());
        if(getHeartPoints() == getMAX_HEARTPOINTS() && gp.player.isSingle()){
            npcResponse("Mau banget aku tunangan sama kamu!");
            updateRelation();
            gp.player.updateRelation();
            proposalDate = gp.environmentStatus.getDay();
            gp.player.consumeEnergy(10);
            gp.player.setPartner(this);
        }
        else if(gp.player.isEngaged()){
            npcResponse("Kamu sudah bertunangan!");
            gp.player.consumeEnergy(20);

        }
        else{
            npcResponse("Najong ewh");
            gp.player.consumeEnergy(20);
        }

        for(int i = 0; i<11; i++){
            gp.environmentStatus.time.addFiveMinutes();
        }
        
    }

    public void marry(){
        System.out.println(gp.environmentStatus.getDay() - proposalDate);
        System.out.println(getRelationship());
        if(gp.environmentStatus.getDay() - proposalDate >= 1  && getRelationship() == Relationship.FIANCE){
            npcResponse("Mau banget aku nikah sama kamu!");
            updateRelation();
            gp.environmentStatus.marry();
        }
        else{
            npcResponse("Aduh... ngga bisa");
        }
    }

    public void chat() throws WrongUseFunctionException{
        gp.player.consumeEnergy(10);
        chatFreq++;
        Random random = new Random();
        int i = random.nextInt(chatStrings.length);
        npcResponse(chatStrings[i]);
        increaseHeartPoints(10);
        gp.environmentStatus.time.addFiveMinutes();
    }

    public void loadNPCImages() {
        String basePath = "res/" + name + "/";
        try {
            diam   = ImageIO.read(getClass().getClassLoader().getResourceAsStream(basePath + "diam.png"));
            depan1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream(basePath + "depan1.png"));
            depan2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream(basePath + "depan2.png"));
            blkg1  = ImageIO.read(getClass().getClassLoader().getResourceAsStream(basePath + "blkg1.png"));
            blkg2  = ImageIO.read(getClass().getClassLoader().getResourceAsStream(basePath + "blkg2.png"));
            kanan1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream(basePath + "kanan1.png"));
            kanan2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream(basePath + "kanan2.png"));
            kiri1  = ImageIO.read(getClass().getClassLoader().getResourceAsStream(basePath + "kiri1.png"));
            kiri2  = ImageIO.read(getClass().getClassLoader().getResourceAsStream(basePath + "kiri2.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Gagal load gambar NPC: " + name);
            e.printStackTrace();
        }
    }
}
