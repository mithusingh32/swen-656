package edu.umgc.skhalar.model;

import java.awt.List;
import java.io.Serial;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class ContactTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Serial
	
	
	final Vector<String> columns = new Vector<String>(Arrays.asList("First Name", "Last Name", "Street Address", "City", "State/Providence", "Country", "Phone Number"));
	final Vector<String> rowData = new Vector<String>(Arrays.asList("One", "Two", null, null, null, null, null));
	
	public ContactTableModel() {
		super();
		this.setColumnIdentifiers(columns);
		this.addRow(rowData);
		}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}
