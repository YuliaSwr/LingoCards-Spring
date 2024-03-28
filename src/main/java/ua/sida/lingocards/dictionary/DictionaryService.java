package ua.sida.lingocards.dictionary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.sida.lingocards.app.exception.WrongLanguageException;

import java.util.List;

/**
 * Service for interacting with Dictionary
 */
@Service
public class DictionaryService {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryParser.class);

    private final DictionaryClient client;
    private final DictionaryParser parser;

    /**
     * Constructor to initialize DictionaryService with required dependencies.
     *
     * @param client The web client to fetch dictionary webpages.
     * @param parser The parser to extract information from dictionary webpages.
     */
    public DictionaryService(DictionaryClient client, DictionaryParser parser) {
        this.client = client;
        this.parser = parser;
    }

    /**
     * Get translation for a given word from one language to another.
     *
     * @param fromLang The language to translate from.
     * @param toLang   The language to translate to.
     * @param word     The word to translate.
     * @return A list of translations for the given word.
     */
    public List<String> getTranslate(String fromLang, String toLang, String word) throws WrongLanguageException {
        DictionaryURL dictionaryURL = DictionaryURL.detectURL(fromLang, toLang);
        if (dictionaryURL == null) {
            logger.warn("Unable to determine translation URL for {} from {} to {}", word, fromLang, toLang);
            throw new WrongLanguageException("Unable to determine translation languages");
        }

        logger.info("Translating: {} from {} to {} using URL: {}", word, fromLang, toLang, dictionaryURL.getUrl());
        String html = client.getHtml(dictionaryURL, word);
        return parser.parseHtml(html);
    }

}
