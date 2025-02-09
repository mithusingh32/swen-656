package edu.umgc.skhalar.gui.interfaces;

import edu.umgc.skhalar.model.ContactEntry;

public interface TableSelectionModificationListener {
    /**
     * Event for when a row in the table is selected
     * @param rowIndex  Selected row index
     * @param entry     Selected row's contact entry
     */
    void onContactSelected(int rowIndex, ContactEntry entry);

    /**
     * Event for when delete is performed
     * @param rowIndex  Row index of the deleted entry in the table
     * @param entry     Contact entry which is deleted
     */
    void onDelete(int rowIndex, ContactEntry entry);

    /**
     * Event for when update is performed
     * @param rowIndex  Row index of the updated in the table
     * @param newEntry     The updated Contact Entry
     */
    void onUpdate(int rowIndex, ContactEntry newEntry, ContactEntry oldEntry);
}
