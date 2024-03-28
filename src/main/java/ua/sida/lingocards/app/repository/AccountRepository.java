package ua.sida.lingocards.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.sida.lingocards.app.model.Account;

import java.util.Optional;

/**
 * Repository interface for accessing Account entities in the database
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Retrieves an account by its email
     *
     * @param email The email of the account to retrieve
     * @return The Optional object of account with the specified email
     */
    Optional<Account> findByEmail(String email);

    /**
     * Checks if an account with the specified email exists in the database
     *
     * @param email The email to check for existence
     * @return True if an account with the specified email exists, false otherwise
     */
    boolean existsAccountByEmail(String email);
}
