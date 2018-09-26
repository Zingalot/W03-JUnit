package common;

/**
 * This exception should be used to indicate that insufficient points are available.
 *
 */
public class InsufficientPointsException extends Exception {
    String message;
    public InsufficientPointsException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
