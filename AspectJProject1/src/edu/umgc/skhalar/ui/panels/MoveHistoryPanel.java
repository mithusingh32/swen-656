package edu.umgc.skhalar.ui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * A text area to keep history of the games
 */
public class MoveHistoryPanel extends JPanel {
	
    private static final long serialVersionUID = 1L;
	
    /**
     * log area to list the history
     */
    private JTextArea logArea;
    
    /**
     * scroll pane to allow for scrolling
     */
    private JScrollPane scrollPane;
    
    public MoveHistoryPanel() {
    	// Set layout to BorderLayout to make the scroll pane fill the panel
        setLayout(new BorderLayout());

        // Create text area with initial rows and columns
        logArea = new JTextArea(10, 40);
        logArea.setEditable(false);  // Make it read-only
        logArea.setLineWrap(true);   // Enable line wrapping
        logArea.setWrapStyleWord(true);  // Wrap at word boundaries

        // Create scroll pane and add text area to it
        scrollPane = new JScrollPane(logArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Add scroll pane to panel
        add(scrollPane, BorderLayout.CENTER);
    }
    
    /**
     * Append's text to the current history
     * @param text	text to append to current history
     */
    public void appendHistory(String text) {
    	logArea.append(text + "\n");
        // Auto-scroll to bottom
        logArea.setCaretPosition(logArea.getDocument().getLength());
    } 
}
