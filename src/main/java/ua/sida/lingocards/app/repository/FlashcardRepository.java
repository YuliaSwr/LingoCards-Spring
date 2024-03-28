package ua.sida.lingocards.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.sida.lingocards.app.model.Flashcard;

/**
 * Repository interface for accessing Flashcard entities in the database
 */
@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

    /**
     * Saves a flashcard in the database
     *
     * @param flashcard The flashcard to save
     * @return The saved flashcard
     */
    Flashcard save(Flashcard flashcard);
}