package ua.sida.lingocards.dictionary;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * Web client to make requests to a Dictionary website.
 */
@Service
public class DictionaryClient {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryClient.class);

    /**
     * Retrieve HTML code of the Dictionary page.
     *
     * @param url  website path
     * @param word word to translate
     * @return HTML code of the webpage with translated words
     */
    public String getHtml(DictionaryURL url, String word) {
        logger.info("Sending request to Dictionary website for translation of word: {}", word);

        RestClient restClient = RestClient.create();
        URI uri = UriComponentsBuilder
                .fromUriString(url.getUrl())
                .queryParam("query", word)
                .build()
                .toUri();

        String result = restClient.get()
                .uri(uri)
                .retrieve()
                .body(String.class);

        logger.info("Received HTML response from Dictionary website");
        return result;
    }
}
