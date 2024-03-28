package ua.sida.lingocards.app.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.sida.lingocards.app.model.Account;
import ua.sida.lingocards.app.model.FlashSet;
import ua.sida.lingocards.app.service.FlashSetService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

/**
 * Controller for handling search page
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    private final Logger logger = LoggerFactory.getLogger(SearchController.class);

    private final FlashSetService flashSetService;

    public SearchController(FlashSetService flashSetService) {
        this.flashSetService = flashSetService;
    }

    /**
     * Handler for displaying the search page
     *
     * @param model        The model to be populated with attributes
     * @param currentUser  The currently authenticated user
     * @return The name of the Thymeleaf template for the search page
     */
    @GetMapping
    public String getSearchPage(Model model, @AuthenticationPrincipal Account currentUser) {
        List<FlashSet> userSets = flashSetService.getAllFlashcardSetsByUserId(currentUser.getId());
        model.addAttribute("sets", userSets);
        logger.info("Search page accessed");
        return "searchwords";
    }
}
