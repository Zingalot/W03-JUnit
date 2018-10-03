package common;

/**
 * This exception should be used to indicate that a card owner has not been registered.
 *
 */

public class OwnerNotRegisteredException extends Exception {
    private String message;

    /**
     * Constructs a new instance of the exception.
     * @param message A message for the user
     */
    public OwnerNotRegisteredException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

