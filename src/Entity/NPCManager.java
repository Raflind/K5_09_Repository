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
        npcMap.put("Abigail", new NPC("Abigail", gp));
        npcMap.put("Caroline", new NPC("Caroline", gp));
        npcMap.put("Perry", new NPC("Perry", gp));
        npcMap.put("Dasco", new NPC("Dasco", gp));
        npcMap.put("Emily", new NPC("Emily", gp));
        npcMap.put("MayorTadi", new NPC("MayorTadi", gp));
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
}
