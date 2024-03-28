package ua.sida.lingocards.app.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.sida.lingocards.app.exception.UserAlreadyExistException;
import ua.sida.lingocards.app.model.Account;
import ua.sida.lingocards.app.service.AccountService;

/**
 * Rest controller for handling registration of new users
 */
@RestController
@RequestMapping("/api")
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private final AccountService accountService;

    public RegistrationController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Registers a new account
     *
     * @param firstName The first name of the user
     * @param lastName  The last name of the user
     * @param email     The email of the user
     * @param password  The password of the user
     * @param avatar    The avatar of the user
     * @return ResponseEntity with appropriate HTTP status code
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerNewAccount(@RequestParam("first_name") String firstName,
                                                     @RequestParam("last_name") String lastName,
                                                     @RequestParam("email") String email,
                                                     @RequestParam("password") String password,
                                                     @RequestParam("avatar") String avatar) {
        try {
            Account newAccount = accountService.addNewAccount(firstName, lastName, email, password, avatar);
            logger.info("New account registered: {}", newAccount.getEmail());
            return ResponseEntity.ok("Account registered successfully.");
        } catch (UserAlreadyExistException e) {
            logger.error("Failed to register new account. Email already exists: {}", email);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists.");
        } catch (Exception e) {
            logger.error("Failed to register new account: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register new account.");
        }
    }
}
