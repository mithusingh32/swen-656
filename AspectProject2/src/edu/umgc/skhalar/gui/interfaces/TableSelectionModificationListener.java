package edu.umgc.skhalar.gui.interfaces;

import edu.umgc.skhalar.model.ContactEntry;

public interface TableSelectionModificationListener {
    void onContactSelected(int rowIndex, ContactEntry entry);
    void onDelete(int rowIndex);
    void onUpdate(int rowIndex, ContactEntry entry);
}
