package ua.sida.lingocards.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.sida.lingocards.app.model.FlashSet;

import java.util.List;

/**
 * Repository interface for accessing FlashSet entities in the database
 */
@Repository
public interface FlashSetRepository extends JpaRepository<FlashSet, Long> {

    /**
     * Retrieves all flashSets associated with a user, fetching the associated user entity
     *
     * @param userId The ID of the user whose flashSets are to be retrieved
     * @return A list of flash sets associated with the specified user
     */
    @Query("SELECT fs FROM FlashSet fs JOIN FETCH fs.user WHERE fs.user.id = :userId")
    List<FlashSet> findAllByUserWithUserFetch(@Param("userId") Long userId);

    /**
     * Saves a flash set in the database
     *
     * @param flashSet The flash set to save
     * @return The saved flash set
     */
    FlashSet save(FlashSet flashSet);
}
