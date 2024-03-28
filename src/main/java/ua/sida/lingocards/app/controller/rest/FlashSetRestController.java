package ua.sida.lingocards.app.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ua.sida.lingocards.app.model.Account;
import ua.sida.lingocards.app.model.FlashSet;
import ua.sida.lingocards.app.service.FlashSetService;

import java.io.IOException;

/**
 * Rest controller for managing flashsets
 */
@RestController
@RequestMapping("/api/flashset")
public class FlashSetRestController {

    private final Logger logger = LoggerFactory.getLogger(FlashSetRestController.class);

    private final FlashSetService flashSetService;

    public FlashSetRestController(FlashSetService flashSetService) {
        this.flashSetService = flashSetService;
    }

    /**
     * Endpoint for creating a new Flash Set
     *
     * @param currentUser The currently authenticated user
     * @return ResponseEntity with the ID of the newly created Flash Set
     */
    @PostMapping("/create")
    public ResponseEntity<Long> createFlashSet(@AuthenticationPrincipal Account currentUser) {
        FlashSet created = flashSetService.createFlashSet(currentUser);
        logger.info("New Flash Set created with ID {} for user {}", created.getId(), currentUser.getEmail());
        return ResponseEntity.ok(created.getId());
    }

    /**
     * Endpoint for deleting a FlashSet
     *
     * @param setId The ID of the FlashSet to delete
     * @return ResponseEntity with status code indicating success or failure
     */
    @PostMapping("/del")
    public ResponseEntity<Integer> delFlashSet(@RequestParam("set_id") long setId) {
        int deleted = flashSetService.deleteFlashSet(setId);
        if (deleted == 1) {
            logger.info("FlashSet with ID {} deleted successfully", setId);
            return ResponseEntity.ok().build();
        }
        logger.error("Failed to delete FlashSet with ID {}", setId);
        return ResponseEntity.badRequest().build();
    }

    /**
     * Endpoint for updating the name of a FlashSet
     *
     * @param setId   The ID of the FlashSet to update
     * @param setName The new name for the FlashSet
     * @return ResponseEntity with the ID of the updated FlashSet
     */
    @PostMapping("/update")
    public ResponseEntity<Long> updateFlashSet(@RequestParam("set_id") long setId, @RequestParam("set_name") String setName) {
        logger.info("Updating set {} name to {}", setId, setName);
        FlashSet updated = flashSetService.updateFlashsetName(setId, setName);
        return ResponseEntity.ok(updated.getId());
    }

    /**
     * Endpoint for downloading the Excel file for a FlashSet
     *
     * @param setId The ID of the FlashSet to download
     * @return ResponseEntity with the Excel file content and appropriate headers
     */
    @GetMapping(path = "/file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> downloadFile(@RequestParam("set_id") long setId) {
        try {
            byte[] excelContent = flashSetService.downloadFile(setId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename("flashcards.xlsx").build());
            logger.info("Excel file for FlashSet with ID {} downloaded successfully", setId);
            return new ResponseEntity<>(excelContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            logger.error("Error downloading file for set {}", setId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
