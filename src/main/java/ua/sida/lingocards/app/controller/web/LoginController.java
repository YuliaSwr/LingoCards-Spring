package ua.sida.lingocards.app.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.sida.lingocards.app.service.AccountService;

/**
 * Controller for handling login and registration pages.
 */
@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final AccountService accountService;

    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Handler for displaying the login page
     *
     * @param model   The model to be populated with attributes
     * @param request The HTTP servlet request
     * @return The name of the Thymeleaf template for the login page
     */
    @GetMapping("/login")
    public String loginPage(Model model, HttpServletRequest request) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrfToken != null) {
            model.addAttribute("_csrf", csrfToken);
        }
        logger.info("Login page accessed");
        return "login";
    }

    /**
     * Handler for displaying the registration page
     *
     * @return The name of the Thymeleaf template for the registration page
     */
    @GetMapping("/register")
    public String registrationPage() {
        logger.info("Registration page accessed");
        return "registration";
    }
}
