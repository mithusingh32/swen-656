package edu.umgc.skhalar.model;

import edu.umgc.skhalar.Constants;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class ContactTableModel extends AbstractTableModel {

    final ArrayList<ContactEntry> contacts = new ArrayList<>();

    final Vector<String> columns = new Vector<String>(
            Arrays.asList("First Name", "Last Name", "Street Address", "City", "State/Providence", "Country",
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
            case Constants.COUNTRY_COLUMN -> entry.getCountry();
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
    
    public void addContactEntry(ContactEntry contactEntry) {
        this.contacts.add(contactEntry);
        fireTableRowsInserted(this.contacts.size() - 1, this.contacts.size() - 1);
    }
    
    public void removeContactEntry(final int rowIndex) {
        this.contacts.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public ContactEntry getContactEntry(int rowIndex) {
        return contacts.get(rowIndex);
    }
}
