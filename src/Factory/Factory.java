package Factory;

import Exception.NotInTheListException;
import main.GamePanel;

/**
 * @author @hananda23
 *  T nya diisi sama factory ini mau dipake buat apa nanti jadi "public class subclassFactory implements Factory<tipe>"
 */
public abstract class Factory<T> {
    private GamePanel gp;

    public Factory(GamePanel gp){
        this.gp = gp;        
    }

    public GamePanel getGP(){
        return gp;
    }
    
    /**
     * Membuat object pada sebuah environment
     * 
     * @param name nama dari object yang mau dibuat
     * @return tipe object nya sesuaikan sama tipe object superclassnya
     */
    public abstract T create(String name) throws NotInTheListException;
}
