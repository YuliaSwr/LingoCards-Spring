package ua.sida.lingocards.app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.sida.lingocards.app.exception.UserAlreadyExistException;
import ua.sida.lingocards.app.model.Account;
import ua.sida.lingocards.app.repository.AccountRepository;

/**
 * Service class for managing accounts
 */
@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for AccountService
     *
     * @param accountRepository Repository for accessing account data
     * @param passwordEncoder   Password encoder for encrypting passwords
     */
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Loads a user by their email address
     *
     * @param email The email address of the user
     * @return The user details
     * @throws UsernameNotFoundException If the user with the specified email address is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    /**
     * Adds a new account to the database
     *
     * @param firstName The first name of the account holder
     * @param lastName  The last name of the account holder
     * @param email     The email address of the account
     * @param password  The password of the account
     * @param avatar    The URL of the avatar image associated with the account
     * @return The created account
     * @throws UserAlreadyExistException If an account with the specified email address already exists
     */
    public Account addNewAccount(String firstName, String lastName, String email, String password, String avatar) throws UserAlreadyExistException {
        if (accountRepository.existsAccountByEmail(email)) {
            throw new UserAlreadyExistException("An account with email " + email + " already exists.");
        }
        String encodedPassword = passwordEncoder.encode(password);
        Account account = new Account(null, firstName, lastName, email, encodedPassword, avatar);
        return accountRepository.save(account);
    }
}
