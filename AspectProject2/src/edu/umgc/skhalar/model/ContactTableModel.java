package edu.umgc.skhalar.model;

import edu.umgc.skhalar.Constants;

import javax.swing.table.AbstractTableModel;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class ContactTableModel extends AbstractTableModel {
    @Serial
    private static final long serialVersionUID = 1L;
    final ArrayList<ContactEntry> contacts = new ArrayList<>();

    final Vector<String> columns = new Vector<>(
            Arrays.asList("First Name", "Last Name", "Street Address", "City", "State/Providence", "Zip Code",
                    "Phone Number"));

    public ContactTableModel() {
        super();
    }

    @Override
    public int getRowCount() {
        return contacts.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final ContactEntry entry = contacts.get(rowIndex);
        return switch (columnIndex) {
            case Constants.FIRST_NAME_COLUMN -> entry.getFirstName();
            case Constants.LAST_NAME_COLUMN -> entry.getLastName();
            case Constants.STREET_COLUMN -> entry.getStreetAddress();
            case Constants.CITY_COLUMN -> entry.getCity();
            case Constants.STATE_COLUMN -> entry.getState();
            case Constants.ZIP_CODE -> entry.getZipcode();
            case Constants.PHONE_COLUMN -> entry.getPhoneNumber();
            default -> "";
        };
    }

    @Override
    public String getColumnName(int column) {
        return this.columns.get(column);
    }

    /**
     * Update row in the table
     * @param rowIndex      Row to update
     * @param contactEntry  Contact entry to place in that row
     */
    public void updateContactEntry(int rowIndex, ContactEntry contactEntry) {
        if (rowIndex >= 0 && rowIndex < this.contacts.size()) {
            this.contacts.set(rowIndex, contactEntry);
            fireTableRowsUpdated(rowIndex, rowIndex);
        }
    }

    /**
     * Add entry to the table
     * @param contactEntry  Contact Entry to add into the table
     */
    public void addContactEntry(ContactEntry contactEntry) {
        this.contacts.add(contactEntry);
        fireTableRowsInserted(this.contacts.size() - 1, this.contacts.size() - 1);
    }

    /**
     * Remove the contact entry from the table
     * @param rowIndex  Row index to remove
     */
    public void removeContactEntry(final int rowIndex) {
        this.contacts.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    /**
     * Get the contact entry for row index
     * @param rowIndex  Row index
     * @return  Contact entry for the row index
     */
    public ContactEntry getContactEntry(int rowIndex) {
        return contacts.get(rowIndex);
    }
}
