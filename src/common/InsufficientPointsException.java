package common;

/**
 * This exception should be used to indicate that insufficient points are available.
 *
 */
public class InsufficientPointsException extends Exception {
    private String message;

    /**
     * Constructs a new instance of the exception.
     * @param message A message for the user
     */
    public InsufficientPointsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
