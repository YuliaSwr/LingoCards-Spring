package ua.sida.lingocards.dictionary;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for parsing translations from Dictionary webpage HTML
 */
@Service
public class DictionaryParser {

    /**
     * Parses the HTML content to extract translations
     *
     * @param html The HTML content of the dictionary webpage
     * @return A list of translations extracted from the HTML
     */
    public List<String> parseHtml(String html) {
        List<String> translations = new ArrayList<>();

        Document doc = Jsoup.parse(html);
        Elements translationDescs = doc.select("div.lemma_content div.translation_desc");

        int count = 0;

        for (Element desc : translationDescs) {
            Element link = desc.selectFirst("a.dictLink");
            if (link != null) {
                translations.add(link.text());
                count++;

                if (count >= 10)
                    break;
            }
        }

        return translations;
    }
}
