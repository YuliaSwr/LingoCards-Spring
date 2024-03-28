package ua.sida.lingocards.app.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.sida.lingocards.app.model.Flashcard;
import ua.sida.lingocards.app.service.FlashcardService;

/**
 * Rest controller for managing flashcards
 */
@RestController
@RequestMapping("/api/flashcard")
public class FlashcardRestController {

    private final static Logger logger = LoggerFactory.getLogger(FlashcardRestController.class);

    private final FlashcardService flashcardService;

    /**
     * Constructor for FlashcardRestController
     *
     * @param flashcardService The service for managing flashcards
     */
    public FlashcardRestController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    /**
     * Endpoint for adding a new flashcard to a set
     *
     * @param setId The ID of the set to add the flashcard to
     * @param front The front side of the flashcard
     * @param back  The back side of the flashcard
     * @return ResponseEntity with the ID of added flashcard
     */
    @PostMapping("/add")
    public ResponseEntity<Long> addFlashcard(@RequestParam("set_id") Long setId,
                                             @RequestParam("front") String front,
                                             @RequestParam("back") String back) {
        logger.info("Adding new flashcard to set {} with front: '{}' and back: '{}'", setId, front, back);
        Flashcard flashcard = flashcardService.addNewFlashcard(setId, front, back);
        if (flashcard.getId() != null) {
            logger.info("Flashcard added successfully with ID: {}", flashcard.getId());
            return ResponseEntity.ok(flashcard.getId());
        }
        logger.error("Failed to add new flashcard to set {}", setId);
        return ResponseEntity.badRequest().build();
    }

    /**
     * Endpoint for deleting a flashcard
     *
     * @param flashcardId The ID of the flashcard to delete
     * @return  ResponseEntity with the ID of deleted flashcard
     */
    @PostMapping("/del")
    public ResponseEntity<Long> delFlashcards(@RequestParam("flashcard_id") long flashcardId) {
        logger.info("Deleting flashcard with ID {}", flashcardId);
        int deleted = flashcardService.deleteFlashcard(flashcardId);
        if (deleted == 1) {
            logger.info("Flashcard with ID {} deleted successfully", flashcardId);
            return ResponseEntity.ok(flashcardId);
        }
        logger.error("Failed to delete flashcard with ID {}", flashcardId);
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint for updating a flashcard
     *
     * @param flashcardId The ID of the flashcard to update
     * @param front       The updated front side of the flashcard
     * @param back        The updated back side of the flashcard
     * @return ResponseEntity with the ID of updated flashcard
     */
    @PostMapping("/update")
    public ResponseEntity<Long> updateFlashcard(@RequestParam("flashcard_id") Long flashcardId,
                                                @RequestParam("front") String front,
                                                @RequestParam("back") String back) {
        logger.info("Updating flashcard with ID {} with front: '{}' and back: '{}'", flashcardId, front, back);
        Flashcard flashcard = flashcardService.updateFlashcard(flashcardId, front, back);
        if (flashcard.getId() != null) {
            logger.info("Flashcard with ID {} updated successfully", flashcardId);
            return ResponseEntity.ok(flashcard.getId());
        }
        logger.error("Failed to update flashcard with ID {}", flashcardId);
        return ResponseEntity.notFound().build();
    }

}
