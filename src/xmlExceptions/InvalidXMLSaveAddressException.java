package xmlExceptions;

public class InvalidXMLSaveAddressException extends Exception{
    public InvalidXMLSaveAddressException(){
        super();
    }
    
    public InvalidXMLSaveAddressException(String message) {
        super(message);
    }
    
    public InvalidXMLSaveAddressException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InvalidXMLSaveAddressException(Throwable cause) {
        super(cause);
    }
    
    public InvalidXMLSaveAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
