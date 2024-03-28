package ua.sida.lingocards.app.controller.rest;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.sida.lingocards.app.exception.WrongLanguageException;
import ua.sida.lingocards.dictionary.DictionaryService;

import java.util.List;

/**
 * Rest controller for handling translation requests
 */
@RestController
@RequestMapping("/api")
public class TranslationRestController {

    private final DictionaryService dictionaryService;

    /**
     * Constructor for TranslationRestController
     *
     * @param dictionaryService The service for translating words
     */
    public TranslationRestController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    /**
     * Endpoint for translating a word from one language to another
     *
     * @param from The source language
     * @param to   The target language
     * @param word The word to translate
     * @return ResponseEntity with the translation or appropriate status
     */
    @GetMapping("/translate")
    @Cacheable(cacheNames = "translations", key = "{#from, #to, #word}")
    public ResponseEntity<List<String>> getTranslation(@RequestParam("from") String from,
                                       @RequestParam("to") String to,
                                       @RequestParam("word") String word) {
        try {
            List<String> translation = dictionaryService.getTranslate(from, to, word);
            return ResponseEntity.ok(translation);
        } catch (WrongLanguageException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
