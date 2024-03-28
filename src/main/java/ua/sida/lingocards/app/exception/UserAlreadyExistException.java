package ua.sida.lingocards.app.exception;

/**
 * Custom exception class to indicate that a user already exists.
 */
public class UserAlreadyExistException extends Exception {

    /**
     * Constructs a new UserAlreadyExistException with the specified detail message.
     * @param msg the detail message.
     */
    public UserAlreadyExistException(final String msg) {
        super(msg);
    }
}