package dal.settings;

/**
 * Exception for Technical error (if the database connection is not established)
 */
public class TechnicalException extends RuntimeException{
    public TechnicalException() {
        super();
    }

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
