package xmlExceptions;

public class InvalidSimulationTypeException extends Exception {
    
    /**
     * Serial ID
     */
    private static final long serialVersionUID = 1L;

    public InvalidSimulationTypeException(){
        super();
    }
    
    public InvalidSimulationTypeException(String message) {
        super(message);
    }
    
    public InvalidSimulationTypeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InvalidSimulationTypeException(Throwable cause) {
        super(cause);
    }
    
    public InvalidSimulationTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
