package ua.sida.lingocards.app.filebuilder;

import ua.sida.lingocards.app.model.FlashSet;

import java.io.IOException;

/**
 * Interface for building files associated with flashcard sets
 */
public interface FileBuilder {

    /**
     * Builds a file associated with the given flashcard set
     *
     * @param flashSet The flashcard set for which the file should be built
     * @return The byte array representing the built file
     * @throws IOException If an I/O error occurs during file building
     */
    byte[] buildFile(FlashSet flashSet) throws IOException;
}

