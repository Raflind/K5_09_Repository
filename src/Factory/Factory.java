package Factory;

import Exception.NotInTheListException;

/**
 * @author @hananda23
 *  T nya diisi sama factory ini mau dipake buat apa nanti jadi "public class subclassFactory implements Factory<tipe>"
 */
public interface Factory<T> {
    
    /**
     * Membuat object pada sebuah environment
     * 
     * @param name nama dari object yang mau dibuat
     * @return tipe object nya sesuaikan sama tipe object superclassnya
     */
    public T create(String name) throws NotInTheListException;
}
