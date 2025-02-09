package edu.umgc.skhalar.aspect;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import edu.umgc.skhalar.model.ContactEntry;
import edu.umgc.skhalar.gui.interfaces.TableSelectionModificationListener;

public aspect OnDeleteAspect extends FileHandler {
    protected OnDeleteAspect() {
        super("history.txt");
    }

    /**
     * Create point cut for the onDelete method in the interface TableSelectionModificationListener. This way
     * any class extending it will have the aspect
     */
    pointcut deleteOperation(int row, ContactEntry e):
            execution(* TableSelectionModificationListener.onDelete(int, ContactEntry))
                    && args(row, e);

    /**
     * Append message to the file after onDelete will is executed
     */
    after(int row, ContactEntry e): deleteOperation(row, e) {
        this.appendMessage(e, FileAction.Delete);
    }

}