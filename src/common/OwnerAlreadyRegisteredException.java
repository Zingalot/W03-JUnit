package common;

/**
 * This exception should be used to indicate that a card owner has already been registered for use with a loyalty card.
 *
 */
public class OwnerAlreadyRegisteredException extends Exception {
    private String message;

    /**
     * Constructs a new instance of the exception.
     * @param message A message for the user
     */
    public OwnerAlreadyRegisteredException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
