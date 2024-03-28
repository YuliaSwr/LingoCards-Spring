package ua.sida.lingocards.dictionary;

import lombok.Getter;

/**
 * Enumeration representing URLs for different language translations
 */
public enum DictionaryURL {

    DE_EN("https://www.linguee.com/german-english/search", "DE", "EN"),
    DE_ES("https://www.linguee.com/german-spanish/search", "DE", "ES"),
    EN_DE("https://www.linguee.com/english-german/search", "EN", "DE"),
    EN_ES("https://www.linguee.com/english-spanish/search", "EN", "ES"),
    ES_DE("https://www.linguee.com/spanish-german/search", "ES", "DE"),
    ES_EN("https://www.linguee.com/spanish-english/search", "ES", "EN");

    @Getter
    private final String url;
    private final String fromLang;
    private final String toLang;

    /**
     * Constructor for initializing DictionaryURL with URL and language codes
     *
     * @param url      The URL for translation
     * @param fromLang The source language code
     * @param toLang   The target language code
     */
    DictionaryURL(String url, String fromLang, String toLang) {
        this.url = url;
        this.fromLang = fromLang;
        this.toLang = toLang;
    }

    /**
     * Detects the appropriate URL for translation based on source and target languages
     *
     * @param fromLang The source language code
     * @param toLang   The target language code
     * @return The DictionaryURL corresponding to the translation from source to target language
     */
    public static DictionaryURL detectURL(String fromLang, String toLang) {
        for (DictionaryURL dictionaryURL : values()) {
            if (dictionaryURL.fromLang.equalsIgnoreCase(fromLang)
                    && dictionaryURL.toLang.equalsIgnoreCase(toLang)) {
                return dictionaryURL;
            }
        }
        return null;
    }
}
