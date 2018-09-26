package common;

/**
 * This exception should be used to indicate that a card owner has not been registered.
 *
 */

public class OwnerNotRegisteredException extends Exception {
    String message;
    public OwnerNotRegisteredException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

