package edu.umgc.skhalar.gui.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Objects;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ContactForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JTextField firstNameTextField;
	private final JTextField lastNameTextField;
	private boolean isDirty = false;
	private String originalFirstName;
	private String originalLastName;

	public ContactForm() {
		super();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		this.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("First Name");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		this.add(lblNewLabel, gbc_lblNewLabel);

		firstNameTextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		this.add(firstNameTextField, gbc_textField);
		firstNameTextField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Last Name");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		this.add(lblNewLabel_1, gbc_lblNewLabel_1);

		lastNameTextField = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		this.add(lastNameTextField, gbc_textField_1);
		lastNameTextField.setColumns(10);
	}

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

		firstNameTextField.getDocument().addDocumentListener(documentListener);
		lastNameTextField.getDocument().addDocumentListener(documentListener);
	}

	private void checkForChanges() {
		if (originalFirstName != null && originalLastName != null) {
			isDirty = !Objects.equals(firstNameTextField.getText(), originalFirstName)
					|| !Objects.equals(lastNameTextField.getText(), originalLastName);
		}
	}

	public void loadContact(String firstName, String lastName) {
		originalFirstName = firstName;
		originalLastName = lastName;
		firstNameTextField.setText(firstName);
		lastNameTextField.setText(lastName);
		isDirty = false;
	}

	public String getFirstName() {
		return firstNameTextField.getText();
	}

	public String getLastName() {
		return lastNameTextField.getText();
	}

	public boolean isDirty() {
		return isDirty;
	}

	public void clearDirty() {
		isDirty = false;
		originalFirstName = firstNameTextField.getText();
		originalLastName = lastNameTextField.getText();
	}
}
