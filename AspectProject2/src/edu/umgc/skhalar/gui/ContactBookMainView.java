package edu.umgc.skhalar.gui;

import edu.umgc.skhalar.gui.components.ContactFormPanel;
import edu.umgc.skhalar.gui.components.ContactTable;
import edu.umgc.skhalar.gui.components.CreateNewContactEntryDialog;
import edu.umgc.skhalar.gui.interfaces.TableSelectionModificationListener;
import edu.umgc.skhalar.model.ContactEntry;
import edu.umgc.skhalar.model.ContactTableModel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ContactBookMainView implements TableSelectionModificationListener {

    private JFrame frame;
    private final ContactTableModel tableModel = new ContactTableModel();
    private final ContactTable table = new ContactTable(tableModel);
    private final ContactFormPanel formPanel = new ContactFormPanel(false);
    private int previousRow = -1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ContactBookMainView window = new ContactBookMainView();
                window.frame.setVisible(true);
            } catch (Exception e) {
               System.out.println("Error: " + e); 
            }
        });
    }

    /**
     * Create the application.
     */
    public ContactBookMainView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
       
        // Add Create new button
        final JPanel firstPanel = new JPanel();
        firstPanel.setPreferredSize(new Dimension(100, 30));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 0.0;

        final JButton newButton = new JButton("Create New Contact");
        frame.getContentPane().add(newButton);
        newButton.addActionListener(_ -> showCreateNewDialog());
        newButton.setMaximumSize(new Dimension(300, 30));
        
        frame.getContentPane().add(firstPanel, c);

        // Add the Contact Table
        c.gridy++;
        c.weighty = 1.0;
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(new GridLayout(0, 1));
        panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        frame.getContentPane().add(panel_1, c);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        tableScrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_1.add(tableScrollPane);
        
        // Add main view to listen to events
        this.table.addTableSelectionListener(this);
        this.formPanel.addListener(this);
        
        // Add the Contact Form
        c.gridy++;
        frame.getContentPane().add(this.formPanel, c);
    }

    private void showCreateNewDialog() {
        CreateNewContactEntryDialog dialog = new CreateNewContactEntryDialog(this.frame);
        dialog.setVisible(true);

        if (dialog.isApproved()) {
            // Add new contact to table model;
            this.tableModel.addContactEntry(
                    dialog.createContactEntry()
            );

            // Select the newly added row
            int newRow = this.tableModel.getRowCount() - 1;
            this.table.setRowSelectionInterval(newRow, newRow);
        }
    }

    @Override
    public void onContactSelected(int row, ContactEntry entry) {
        if (row == -1) {
            return;
        }
        
        if (this.formPanel.isDirty() && this.previousRow != row) {
            int response = JOptionPane.showConfirmDialog(
                    this.frame, 
                    "Unsaved changes detected.",
                    "Unsaved changes detected. Save changes?",
                    JOptionPane.YES_NO_CANCEL_OPTION
            );
            
            if (response == JOptionPane.YES_OPTION) {
                this.tableModel.addContactEntry(entry);
            } else if (response == JOptionPane.CANCEL_OPTION) {
                this.table.setRowSelectionInterval(this.previousRow, this.previousRow);
                return;
            }
            
            this.previousRow = row;
            this.formPanel.loadContact(
                    row,
                    this.tableModel.getContactEntry(row)
            );
        } else {
            this.formPanel.loadContact(row, this.tableModel.getContactEntry(row));
        }
    }

    @Override
    public void onDelete(int rowIndex) {
        this.tableModel.removeContactEntry(rowIndex);
    }

    @Override
    public void onUpdate(int rowIndex, ContactEntry entry) {
        this.tableModel.updateContactEntry(rowIndex, entry);
    }
}
