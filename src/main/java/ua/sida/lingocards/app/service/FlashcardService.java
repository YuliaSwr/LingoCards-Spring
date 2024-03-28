package ua.sida.lingocards.app.service;

import org.springframework.stereotype.Service;
import ua.sida.lingocards.app.exception.FlashcardNotFoundException;
import ua.sida.lingocards.app.model.FlashSet;
import ua.sida.lingocards.app.model.Flashcard;
import ua.sida.lingocards.app.repository.FlashcardRepository;

/**
 * Service class for managing flashcards
 */
@Service
public class FlashcardService {

    private final FlashcardRepository flashcardRepository;
    private final FlashSetService flashSetService;

    /**
     * Constructor for FlashcardService
     *
     * @param flashcardRepository Repository for accessing flashcard data
     * @param flashSetService     Service for managing flashcard sets
     */
    public FlashcardService(FlashcardRepository flashcardRepository, FlashSetService flashSetService) {
        this.flashcardRepository = flashcardRepository;
        this.flashSetService = flashSetService;
    }

    /**
     * Adds a new flashcard to the database
     *
     * @param userId The ID of the user to whom the flashcard belongs
     * @param front  The content on the front side of the flashcard
     * @param back   The content on the back side of the flashcard
     * @return The created flashcard
     */
    public Flashcard addNewFlashcard(long userId, String front, String back) {
        FlashSet flashSet = flashSetService.getFlashcardSetById(userId);
        Flashcard flashcard = new Flashcard(null, front, back, flashSet);
        return flashcardRepository.save(flashcard);
    }

    /**
     * Deletes a flashcard from the database
     *
     * @param flashcardId The ID of the flashcard to delete
     * @return 0 if deletion fails, 1 if deletion succeeds
     */
    public int deleteFlashcard(long flashcardId) {
        flashcardRepository.deleteById(flashcardId);
        return flashcardRepository.existsById(flashcardId) ? 0 : 1;
    }

    /**
     * Updates an existing flashcard in the database
     *
     * @param flashcardId The ID of the flashcard to update
     * @param front       The updated content on the front side of the flashcard
     * @param back        The updated content on the back side of the flashcard
     * @return The updated flashcard
     */
    public Flashcard updateFlashcard(long flashcardId, String front, String back) {
        Flashcard flashcard = flashcardRepository.findById(flashcardId)
                .orElseThrow(() -> new FlashcardNotFoundException("Flashcard not found with id: " + flashcardId));
        flashcard.setFront(front);
        flashcard.setBack(back);
        return flashcardRepository.save(flashcard);
    }
}
