package Entity;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import main.GamePanel;

public class NPCManager {
    private GamePanel gp;
    private Map<String, NPC> npcMap = new HashMap<>();
    private NPC activeNPC;

    public NPCManager(GamePanel gp) {
        this.gp = gp;
        loadAllNPC();
    }

    // Membuat dan menyimpan semua NPC langsung dari konstruktor NPC
    private void loadAllNPC() {
        npcMap.put("Abigail", new NPC("Abigail", gp, "Blueberry, Anggur, Cranberry, Apakah aku akan mendapatkannya sekali?\nTerdengar segar sekali", "tu-turut-tu-turu~ apa yang dapat kulakukan hari ini,\nsemuanya terdengar asyik sekali", "Hey! Apa kabar kamu! Maukah ikut bertualang denganku?"));
        npcMap.put("Caroline", new NPC("Caroline", gp, "ckckc, pekan yang sulit ini terasa seperti neraka,\naku yakin aku butuh istirahat", "Hmm, akankah ada yang mengajakku kencan suatu hari\nnanti?", "Kayu-kayu hari ini rasanya dapat kubentuk menjadi sesuatu\nyang indah, aku tak sabar untuk berkreasi"));
        npcMap.put("Perry", new NPC("Perry", gp, "Di sini, di batas kota ini~\nIngin ku tuliskan surat untukmu~", "Apakah nasibku akan lebih baik di kota ini?\nKuharap demikian", "Ah, rindunya aku terhadap kota lamaku,\ntapi Spakbor Hills tak buruk juga"));
        npcMap.put("Dasco", new NPC("Dasco", gp, "Bermain kasinolah bersamaku! Khukhukhu! ", "Di negeri Spakbor ini, mereka tunduk padaku, khukhukhu", ": Khekhekhe… anak-anak muda di sini,\nmudah sekali terkena pancingan kasinoku! "));
        npcMap.put("Emily", new NPC("Emily", gp, "la~la~la~laaa.. kusuka setiap hari di\nSpakbor Hills yang menyenangkan ini…", "Hmm… kira-kira hidangan lezat apa yaa\nyang dapat kusajikan hari ini..", "Apapun yang kau butuhkan aku punya!\nMari berkunjung ke tokoku~"));
        npcMap.put("MayorTadi", new NPC("MayorTadi", gp, "Enak juga ye jadi Mayor, dikasih enak mulu setiap hari,\nhe..he..he..", "Gue liat-liat kota ini makin lama makin keren,\npasti karena gue", "Andai kate nanti gue jadi presiden,\nseneng dah lu semua he..he..hee"));
    }

    // Set NPC yang aktif (misal saat masuk rumah NPC)
    public void setActiveNPC(String name) {
        activeNPC = npcMap.get(name);
    }

    // Ambil NPC aktif
    public NPC getActiveNPC() {
        return activeNPC;
    }

    public void activeNPC(){
        if(gp.tileM.currMap.equals("Abigail")){
            setActiveNPC("Abigail");
        } else if(gp.tileM.currMap.equals("Caroline")){
            setActiveNPC("Caroline");
        } else if(gp.tileM.currMap.equals("Perry")){
            setActiveNPC("Perry");
        } else if(gp.tileM.currMap.equals("Dasco")){
            setActiveNPC("Dasco");
        } else if(gp.tileM.currMap.equals("Store")){
            setActiveNPC("Emily");
        } else if(gp.tileM.currMap.equals("Mayor")){
            setActiveNPC("MayorTadi");
        } else {
            activeNPC = null; 

        }
    }

    // Ambil NPC berdasarkan nama
    public NPC getNPC(String name) {
        return npcMap.get(name);
    }

    // Untuk draw semua NPC yang ingin ditampilkan (misal di map tertentu)
    public void drawActiveNPC(Graphics2D g2) {
        if (activeNPC != null) {
            activeNPC.draw(g2);
        }
    }

    // Untuk update semua NPC yang ingin diupdate
    public void updateActiveNPC() {
        if (activeNPC != null) {
            activeNPC.update();
        }
    }
    public Map<String, NPC> getNPCMap() {
        return npcMap;
    }
}
