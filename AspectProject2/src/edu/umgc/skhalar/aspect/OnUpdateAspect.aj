package edu.umgc.skhalar.aspect;

import edu.umgc.skhalar.model.ContactEntry;

public aspect OnUpdateAspect extends AspectFileHandler {
	public OnUpdateAspect() {
		super("history.txt");
	}

	/**
	 * Create point cut for the onUpdate method in the interface TableSelectionModificationListener. This way
	 * any class extending it will have the aspect
	 */
	pointcut updateOperation(int row, ContactEntry newEntry, ContactEntry oldEntry):
            execution(* TableSelectionModificationListener.onDelete(int, ContactEntry, ContactEntry)) 
            && args(row, newEntry, oldEntry);

	/**
	 * Append message to the file after update is performed
	 */
	after(int row, ContactEntry newEntry, ContactEntry oldEntry): updateOperation(row, newEntry, oldEntry) {
		this.appendMessage(oldEntry, FileAction.Update);
	}
}