package ua.sida.lingocards.app.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.sida.lingocards.app.model.Account;
import ua.sida.lingocards.app.model.FlashSet;
import ua.sida.lingocards.app.service.FlashSetService;

import java.util.List;

/**
 * WebController for handling web pages related to FlashSets
 */
@Controller
public class FlashSetController {

    private final Logger logger = LoggerFactory.getLogger(FlashSetController.class);

    private final FlashSetService flashSetService;

    public FlashSetController(FlashSetService flashSetService) {
        this.flashSetService = flashSetService;
    }

    /**
     * Handler for retrieving the page displaying user's FlashSets
     *
     * @param model        The model to be populated with FlashSets
     * @param currentUser  The currently authenticated user
     * @return The name of the Thymeleaf template for displaying user's FlashSets
     */
    @GetMapping("/mysets")
    public String getMySetsPage(Model model, @AuthenticationPrincipal Account currentUser) {
        List<FlashSet> userSets = flashSetService.getAllFlashcardSetsByUserId(currentUser.getId());
        model.addAttribute("sets", userSets);
        logger.info("FlashSets retrieved successfully for user {}", currentUser.getEmail());
        return "mysets";
    }

    /**
     * Handler for retrieving the page displaying details of a specific FlashSet
     *
     * @param setId        The ID of the Flash Set to display
     * @param model        The model to be populated with Flash Set details
     * @param currentUser  The currently authenticated user
     * @return The name of the Thymeleaf template for displaying FlashSet details
     */
    @GetMapping("/set")
    public String getSetPage(@RequestParam("id") long setId, Model model, @AuthenticationPrincipal Account currentUser) {
        FlashSet flashSet = flashSetService.getFlashcardSetById(setId);
        if (flashSet.getUser().getId().equals(currentUser.getId())) {
            model.addAttribute("flashSet", flashSet);
            logger.info("FlashSet details retrieved successfully for user {}", currentUser.getEmail());
            return "setpage";
        }
        logger.error("Unauthorized attempt to access FlashSet details by user {}", currentUser.getEmail());
        return "error";
    }

    /**
     * Handler for retrieving the page for editing a FlashSet
     *
     * @param setId        The ID of the Flash Set to edit
     * @param model        The model to be populated with Flash Set details
     * @param currentUser  The currently authenticated user
     * @return The name of the Thymeleaf template for editing Flash Sets
     */
    @GetMapping("/edit")
    public String getEditPage(@RequestParam("id") long setId, Model model, @AuthenticationPrincipal Account currentUser) {
        FlashSet flashSet = flashSetService.getFlashcardSetById(setId);
        if (flashSet.getUser().getId().equals(currentUser.getId())) {
            model.addAttribute("flashSet", flashSet);
            logger.info("Editing Flash Set with ID {} by user {}", setId, currentUser.getEmail());
            return "edit-page";
        }
        logger.error("Unauthorized attempt to access FlashSet details by user {}", currentUser.getEmail());
        return "error";
    }
}
