package edu.umgc.skhalar.gui.components;

import edu.umgc.skhalar.gui.interfaces.TableSelectionModificationListener;
import edu.umgc.skhalar.model.ContactEntry;

import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ContactFormPanel extends JPanel {
    final ArrayList<TableSelectionModificationListener> listenerList = new ArrayList<>();

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Text fields in the form
     */
    private final JTextField firstNameTextField;
    private final JTextField lastNameTextField;
    private final JTextField streetTextField;
    private final JTextField cityTextField;
    private final JTextField stateTextField;
    private final JTextField zipCodeTextField;
    private final JTextField phoneTextField;

    /**
     * Save and update buttons
     */
    private final JButton saveButton = new JButton("Save");
    private final JButton deleteButton = new JButton("Delete");

    /**
     * Flag indicating the form fields have been modified
     */
    private boolean isDirty = false;

    /**
     * The row index associated with the selected contact entry
     */
    private int originalRowIndex = -1;

    /**
     * Selected contact entry
     */
    private ContactEntry selectedContactEntry;

    /**
     * flag indicating the form is shown in a dialog
     */
    private final boolean isDialog;

    public ContactFormPanel(final boolean isDialog) {
        super();
        this.isDialog = isDialog;
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        this.setLayout(gbl_panel);

        int gridy = 0;
        this.firstNameTextField = buildFormField("First Name", gridy++);
        this.lastNameTextField = buildFormField("Last Name", gridy++);
        this.streetTextField = buildFormField("Street Address", gridy++);
        this.cityTextField = buildFormField("City", gridy++);
        this.stateTextField = buildFormField("State/Providence", gridy++);
        this.zipCodeTextField = buildFormField("Zip Code", gridy++);
        this.phoneTextField = buildFormField("Phone", gridy++);

        // Create a panel for buttons only if it's not a dialog
        if (!isDialog) {
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

            this.saveButton.setEnabled(false);
            
            // On Click of the save button, emit onUpdate event to listener
            // and update the selectedRow to the newly updated Contact Entry
            this.saveButton.addActionListener(e -> {
                for (TableSelectionModificationListener listener : this.listenerList) {
                    final ContactEntry entry = createContactEntry();
                    listener.onUpdate(this.originalRowIndex, entry);
                    this.selectedContactEntry = entry;
                }
            });

            this.deleteButton.setEnabled(false);
            this.deleteButton.addActionListener(e -> onDeleteConfirmation());

            // Add buttons to the panel
            buttonPanel.add(this.saveButton);
            buttonPanel.add(this.deleteButton);

            // Add button panel to the main panel
            GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
            gbc_buttonPanel.gridx = 0;
            gbc_buttonPanel.gridy = gridy;
            gbc_buttonPanel.gridwidth = 2;
            gbc_buttonPanel.anchor = GridBagConstraints.LINE_END;
            gbc_buttonPanel.insets = new Insets(10, 0, 5, 0);
            this.add(buttonPanel, gbc_buttonPanel);
        }
        setupListeners();
    }

    private void onDeleteConfirmation() {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this contact?",
                "Delete Contact?", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            for (TableSelectionModificationListener listener : this.listenerList) {
                listener.onDelete(this.originalRowIndex, this.selectedContactEntry);
            }

            // Reset all fields
            this.firstNameTextField.setText("");
            this.lastNameTextField.setText("");
            this.streetTextField.setText("");
            this.cityTextField.setText("");
            this.stateTextField.setText("");
            this.zipCodeTextField.setText("");
            this.phoneTextField.setText("");

            // Disable all fields
            this.firstNameTextField.setEnabled(false);
            this.lastNameTextField.setEnabled(false);
            this.streetTextField.setEnabled(false);
            this.cityTextField.setEnabled(false);
            this.stateTextField.setEnabled(false);
            this.zipCodeTextField.setEnabled(false);
            this.phoneTextField.setEnabled(false);
            this.saveButton.setEnabled(true);
            this.deleteButton.setEnabled(true);
        }
    }

    /**
     * Add listener to the events of this form
     *
     * @param listener Listener of the events
     */
    public void addListener(TableSelectionModificationListener listener) {
        this.listenerList.add(listener);
    }

    /**
     * Build form fields
     *
     * @param columnName Column name of the form field
     * @param gridY      Location of the form field on the Y axis
     * @return The newly created textfield
     */
    private JTextField buildFormField(final String columnName, int gridY) {
        final JTextField textField;
        JLabel lblNewLabel = new JLabel(columnName);
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = gridY;
        this.add(lblNewLabel, gbc_lblNewLabel);

        textField = new JTextField();
        textField.setEnabled(isDialog);
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 0);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = gridY;
        this.add(textField, gbc_textField);
        textField.setColumns(10);
        return textField;
    }

    /**
     * Set up listeners for the entire document. When any changes occurs to the text fields,
     * the dirty flag will be marked as true. If the changes are reverted, the flag will be false
     */
    private void setupListeners() {
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkForChanges();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkForChanges();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkForChanges();
            }
        };

        this.firstNameTextField.getDocument().addDocumentListener(documentListener);
        this.lastNameTextField.getDocument().addDocumentListener(documentListener);
    }

    /**
     * Determine if any of the text fields have been modified from the original entry
     */
    private void checkForChanges() {
        if (selectedContactEntry != null) {
            this.isDirty = !Objects.equals(this.firstNameTextField.getText(), this.selectedContactEntry.getFirstName())
                    || !Objects.equals(this.lastNameTextField.getText(), this.selectedContactEntry.getLastName())
                    || !Objects.equals(this.streetTextField.getText(), this.selectedContactEntry.getStreetAddress())
                    || !Objects.equals(this.cityTextField.getText(), this.selectedContactEntry.getCity())
                    || !Objects.equals(this.stateTextField.getText(), this.selectedContactEntry.getState())
                    || !Objects.equals(this.zipCodeTextField.getText(), this.selectedContactEntry.getZipcode())
                    || !Objects.equals(this.phoneTextField.getText(), this.selectedContactEntry.getPhoneNumber());
        }

        this.saveButton.setEnabled(isDirty);
    }

    /**
     * Load a new contract entry into the form
     *
     * @param row   Row index in the table
     * @param entry Contact entry to load
     */
    public void loadContact(int row, final ContactEntry entry) {
        this.deleteButton.setEnabled(true);
        this.originalRowIndex = row;
        this.selectedContactEntry = entry;
        this.firstNameTextField.setText(entry.getFirstName());
        this.firstNameTextField.setEnabled(true);

        this.lastNameTextField.setText(entry.getLastName());
        this.lastNameTextField.setEnabled(true);

        this.streetTextField.setText(entry.getStreetAddress());
        this.streetTextField.setEnabled(true);

        this.cityTextField.setText(entry.getCity());
        this.cityTextField.setEnabled(true);

        this.stateTextField.setText(entry.getState());
        this.stateTextField.setEnabled(true);

        this.zipCodeTextField.setText(entry.getZipcode());
        this.zipCodeTextField.setEnabled(true);

        this.phoneTextField.setText(entry.getPhoneNumber());
        this.phoneTextField.setEnabled(true);

        this.isDirty = false;
    }

    /**
     * Create a new contact entry from the form field
     *
     * @return Newly create Contact Entry
     */
    public ContactEntry createContactEntry() {
        return new ContactEntry(
                this.firstNameTextField.getText(),
                this.lastNameTextField.getText(),
                this.streetTextField.getText(),
                this.cityTextField.getText(),
                this.stateTextField.getText(),
                this.zipCodeTextField.getText(),
                this.phoneTextField.getText()
        );
    }

    /**
     * Validate form
     *
     * @return String message of the missing fields
     */
    public String validateContactEntry() {
        String message = "";
        message += validateTextFields(this.firstNameTextField, "First Name");
        message += validateTextFields(this.lastNameTextField, "Last Name");
        message += validateTextFields(this.streetTextField, "Street Address");
        message += validateTextFields(this.cityTextField, "City");
        message += validateTextFields(this.stateTextField, "State/Providence");
        message += validateTextFields(this.zipCodeTextField, "Zip Code");
        message += validateTextFields(this.phoneTextField, "Phone Number");
        return message;
    }

    /**
     * Validate a text field. If it's empty, it'll return a string indicating that field is empty
     *
     * @param textField JTextfield to validate
     * @param fieldName Name of that text field
     * @return String indicating if the field if empty
     */
    private String validateTextFields(final JTextField textField, final String fieldName) {
        if (Objects.equals(textField.getText(), "") || Objects.equals(textField.getText(), null)) {
            return fieldName + " is required\n";
        }
        return "";
    }

    /**
     * @return dirty flag indicating the form field has been modified from the original
     */
    public boolean isDirty() {
        return this.isDirty;
    }
}
