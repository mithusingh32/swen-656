package edu.umgc.skhalar.gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;

import edu.umgc.skhalar.gui.components.ContactForm;
import edu.umgc.skhalar.model.ContactTableModel;

public class ContactBookMainView {

	private JFrame frame;
	private JTable table;
	private ContactTableModel tableModel = new ContactTableModel();
	private ContactForm formPanel = new ContactForm();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactBookMainView window = new ContactBookMainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
		frame.setBounds(100, 100, 876, 589);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(0, 1));
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(panel_1);
		
		table = new JTable();
		table.setModel(this.tableModel);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.addMouseListener(new RowOnClick(this.formPanel));
		
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tableScrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		panel_1.add(tableScrollPane);
		
		frame.getContentPane().add(this.formPanel);
	}
	
	
	private class RowOnClick implements MouseListener {

		ContactForm form; 
		
		public RowOnClick(ContactForm form) {
			this.form = form;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Row clicked");
			final Vector<String> rowData = new Vector<String>();
			final JTable table = (JTable) e.getSource();
			final int columns = table.getColumnCount();
			final int row = table.getSelectedRow();
			for (int i = 0; i < columns; i++) {
				rowData.add((String) table.getValueAt(row, i));
			}
			
			this.form.updateCurrentForm(rowData);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
