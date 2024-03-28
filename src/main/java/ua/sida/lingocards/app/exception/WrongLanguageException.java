package ua.sida.lingocards.app.exception;

/**
 * Exception thrown when an unsupported language is provided
 */
public class WrongLanguageException extends Exception {

    public WrongLanguageException(String msg) {
        super(msg);
    }
}
