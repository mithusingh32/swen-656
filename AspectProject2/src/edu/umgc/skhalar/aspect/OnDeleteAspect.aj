package edu.umgc.skhalar.aspect;

import edu.umgc.skhalar.model.ContactEntry;

public aspect OnDeleteAspect extends AspectFileHandler {
    protected OnDeleteAspect() {
        super("history.txt");
    }

    /**
     * Create point cut for the onDelete method in the interface TableSelectionModificationListener. This way
     * any class extending it will have the aspect
     */
    pointcut deleteOperation(int row, ContactEntry newEntry, ContactEntry oldEntry):
            execution(* TableSelectionModificationListener.onDelete(int, ContactEntry, ContactEntry))
                    && args(row, newEntry, oldEntry);

    /**
     * Append message to the file after onDelete will is executed
     */
    after(int row, ContactEntry newEntry, ContactEntry oldEntry): deleteOperation(row, newEntry, oldEntry) {
        this.appendMessage(oldEntry, FileAction.Delete);
    }

}