package edu.umgc.skhalar.gui.components;

import edu.umgc.skhalar.model.ContactEntry;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;


public class CreateNewContactEntryDialog extends JDialog {
    @Serial
    private static final long serialVersionUID = 1L;
    private ContactFormPanel contactForm;
    private boolean approved = false;

    public CreateNewContactEntryDialog(Frame owner) {
        super(owner, "Add New Contact", true);
        initComponents();
    }

    private void initComponents() {
        // Create main panel with some padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add contact form
        contactForm = new ContactFormPanel(true);
        mainPanel.add(contactForm, BorderLayout.CENTER);

        // Create button panel if it's not a dialog
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(_ -> {
            final String validMessage = this.contactForm.validateContactEntry();
            if (validMessage == null || !validMessage.isEmpty()) {
                 JOptionPane.showMessageDialog(this, 
                    validMessage, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                approved = true;
                dispose();
            }
        });

        cancelButton.addActionListener(_ -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(getOwner());
    }

    public boolean isApproved() {
        return approved;
    }
    public ContactEntry createContactEntry() {
        return contactForm.createContactEntry();
    }
}
