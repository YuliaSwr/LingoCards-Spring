package ua.sida.lingocards.app.service;

import org.springframework.stereotype.Service;
import ua.sida.lingocards.app.filebuilder.FileBuilder;
import ua.sida.lingocards.app.model.Account;
import ua.sida.lingocards.app.model.FlashSet;
import ua.sida.lingocards.app.repository.FlashSetRepository;

import java.io.IOException;
import java.util.List;

/**
 * Service class for managing flashcard sets
 */
@Service
public class FlashSetService {

    private final FlashSetRepository flashSetRepository;
    private final FileBuilder fileBuilder;

    /**
     * Constructor for FlashSetService
     *
     * @param flashSetRepository The repository for accessing flashcard set data
     * @param fileBuilder        The file builder for creating files associated with flashcard sets
     */
    public FlashSetService(FlashSetRepository flashSetRepository, FileBuilder fileBuilder) {
        this.flashSetRepository = flashSetRepository;
        this.fileBuilder = fileBuilder;
    }

    /**
     * Retrieves all flashcard sets belonging to a user
     *
     * @param userId The ID of the user
     * @return A list of flashcard sets belonging to the user
     */
    public List<FlashSet> getAllFlashcardSetsByUserId(Long userId) {
        return flashSetRepository.findAllByUserWithUserFetch(userId);
    }

    /**
     * Retrieves a flashcard set by its ID
     *
     * @param id The ID of the flashcard set
     * @return The flashcard set with the specified ID
     * @throws IllegalArgumentException if no flashcard set exists with the specified ID
     */
    public FlashSet getFlashcardSetById(Long id) {
        return flashSetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No flashcard set found with ID: " + id));
    }

    /**
     * Deletes a flashcard set by its ID
     *
     * @param setId The ID of the flashcard set to delete
     * @return 0 if deletion fails, 1 if deletion succeeds
     */
    public int deleteFlashSet(long setId) {
        flashSetRepository.deleteById(setId);
        return flashSetRepository.existsById(setId) ? 0 : 1;
    }

    /**
     * Creates a new flashcard set for the current user
     *
     * @param currentUser The current user
     * @return The newly created flashcard set
     */
    public FlashSet createFlashSet(Account currentUser) {
        FlashSet flashSet = new FlashSet(0L, "New flashset", currentUser, null);
        return flashSetRepository.save(flashSet);
    }

    /**
     * Updates the name of a flashcard set
     *
     * @param setId   The ID of the flashcard set to update
     * @param setName The new name for the flashcard set
     * @return The updated flashcard set
     */
    public FlashSet updateFlashsetName(long setId, String setName) {
        FlashSet flashSet = flashSetRepository.findById(setId)
                .orElseThrow(() -> new IllegalArgumentException("No flashcard set found with ID: " + setId));
        flashSet.setName(setName);
        return flashSetRepository.save(flashSet);
    }

    /**
     * Downloads a file associated with a flashcard set
     *
     * @param setId The ID of the flashcard set
     * @return The byte array representing the downloaded file
     * @throws IOException if an I/O error occurs
     */
    public byte[] downloadFile(long setId) throws IOException {
        FlashSet flashSet = flashSetRepository.findById(setId)
                .orElseThrow(() -> new IllegalArgumentException("No flashcard set found with ID: " + setId));
        return fileBuilder.buildFile(flashSet);
    }
}
