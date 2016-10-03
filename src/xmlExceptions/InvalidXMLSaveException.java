package xmlExceptions;

/***
 * 
 * @author Brian
 * Exception is thrown when there is an error encountered trying to save the current simulation to an XML file
 * 
 */

public class InvalidXMLSaveException extends Exception{
   
    /**
     * Serial ID
     */
    private static final long serialVersionUID = 1L;

    public InvalidXMLSaveException(){
        super();
    }
    
    public InvalidXMLSaveException(String message) {
        super(message);
    }
    
    public InvalidXMLSaveException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InvalidXMLSaveException(Throwable cause) {
        super(cause);
    }
    
    public InvalidXMLSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
