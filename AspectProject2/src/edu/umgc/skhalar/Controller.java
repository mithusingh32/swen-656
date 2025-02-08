package edu.umgc.skhalar;

import java.util.Vector;

import edu.umgc.skhalar.gui.components.ContactForm;
import edu.umgc.skhalar.model.ContactTableModel;

public final class Controller {
	ContactTableModel model; 
	ContactForm form; 
	
	Vector<String> currentSelectedRow;
	
	private Controller() {}

	
	public void registerModelAndTable(ContactTableModel model, ContactForm form) {
		this.model = model;
		this.form = form;
	}
	
	
}
