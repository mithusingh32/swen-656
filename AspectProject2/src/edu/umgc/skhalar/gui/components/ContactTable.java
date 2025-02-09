package edu.umgc.skhalar.gui.components;

import edu.umgc.skhalar.Constants;
import edu.umgc.skhalar.gui.interfaces.TableSelectionModificationListener;
import edu.umgc.skhalar.model.ContactEntry;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class ContactTable extends JTable {
    final List<TableSelectionModificationListener> listeners = new ArrayList<>();

    public ContactTable(final TableModel tableModel) {

        super(tableModel);
        this.getColumnModel().getColumn(Constants.FIRST_NAME_COLUMN).setResizable(false);
        this.getColumnModel().getColumn(Constants.LAST_NAME_COLUMN).setResizable(false);
        this.getColumnModel().getColumn(Constants.STREET_COLUMN).setResizable(false);
        this.getColumnModel().getColumn(Constants.CITY_COLUMN).setResizable(false);
        this.getColumnModel().getColumn(Constants.STATE_COLUMN).setResizable(false);
        this.getColumnModel().getColumn(Constants.COUNTRY_COLUMN).setResizable(false);
        this.getColumnModel().getColumn(Constants.PHONE_COLUMN).setResizable(false);

        getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = getSelectedRow();
                if (selectedRow >= 0) {
                    final String firstName = (String) getValueAt(selectedRow, Constants.FIRST_NAME_COLUMN);
                    final String lastName = (String) getValueAt(selectedRow, Constants.LAST_NAME_COLUMN);
                    final String street = (String) getValueAt(selectedRow, Constants.STREET_COLUMN);
                    final String city = (String) getValueAt(selectedRow, Constants.CITY_COLUMN);
                    final String state = (String) getValueAt(selectedRow, Constants.STATE_COLUMN);
                    final String country = (String) getValueAt(selectedRow, Constants.COUNTRY_COLUMN);
                    final String phone = (String) getValueAt(selectedRow, Constants.PHONE_COLUMN);
                    final ContactEntry entry =
                            new ContactEntry(firstName, lastName, street, city, state, country, phone);
                    notifyListeners(selectedRow, entry);
                }
            }
        });
    }

    public void addTableSelectionListener(final TableSelectionModificationListener listener) {
        this.listeners.add(listener);
    }

    private void notifyListeners(int rowIndex, final ContactEntry entry) {
        for (final TableSelectionModificationListener listener : this.listeners) {
            listener.onContactSelected(rowIndex, entry);
        }
    }
}
