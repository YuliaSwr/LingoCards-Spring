package ua.sida.lingocards.app.exception;

/**
 * Exception to be thrown when a flashcard is not found
 */
public class FlashcardNotFoundException extends RuntimeException {

    public FlashcardNotFoundException(String message) {
        super(message);
    }
}
