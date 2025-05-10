package Exception;

/**
 * @author @hananda23
 * 
 * Kelas Execption
 */

public class NotInTheListException extends Exception {
    public NotInTheListException(){
        super();
    }

    public NotInTheListException(String message){
        super(message + " Not Available On The List");
    }
}
