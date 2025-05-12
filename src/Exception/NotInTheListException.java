package Exception;

/**
 * @author @hananda23
 * 
 * Kelas Execption untuk name objek yang tidak ada di list
 */

public class NotInTheListException extends Exception {

    public NotInTheListException(String message){
        super(message + " Not Available On The List");
    }
}
