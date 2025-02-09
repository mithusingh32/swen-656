package edu.umgc.skhalar.aspect;

import edu.umgc.skhalar.model.ContactEntry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Abstract class to handle appending messages to a file
 */
abstract class FileHandler {
    /**
     * Enum indicating if it's a delete or update
     */
    protected enum FileAction {
        Update,
        Delete
    }

    /**
     * file name which to append message
     */
    final String fileName;
    
    protected FileHandler(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Append message to file
     * @param entry     Entry which action was performed on
     * @param action    Action performed on entry
     */
    protected void appendMessage(final ContactEntry entry, final FileAction action) {
        final String message = "Record " + action.toString() + ": ";
        final File file = new File(this.fileName);
        System.out.println(file.getAbsoluteFile());
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    throw new RuntimeException("Could not create file");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        try {
            Files.write(Paths.get(file.getAbsolutePath()), message.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.println("Error opening history file\n" + ex);
        }
    }
    
}
