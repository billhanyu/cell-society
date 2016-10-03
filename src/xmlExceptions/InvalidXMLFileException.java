package xmlExceptions;

public class InvalidXMLFileException extends Exception{
    
    /**
     * Serial ID
     */
    private static final long serialVersionUID = 1L;

    public InvalidXMLFileException(){
        super();
    }
    
    public InvalidXMLFileException(String message) {
        super(message);
    }
    
    public InvalidXMLFileException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InvalidXMLFileException(Throwable cause) {
        super(cause);
    }
    
    public InvalidXMLFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
