package edu.umgc.skhalar.ui.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog to get player's names
 */
public class IntroDialog {
	    public static String[] getPlayerNames() {
	        JTextField player1Field = new JTextField(10);
	        JTextField player2Field = new JTextField(10);

	        JPanel panel = new JPanel(new GridLayout(5, 1));
	        panel.add(new JLabel("Welcome! Please enter the player names."));
	        panel.add(new JLabel("Enter Player 1 (X) name:"));
	        panel.add(player1Field);
	        panel.add(new JLabel("Enter Player 2 (O) name:"));
	        panel.add(player2Field);

	        while (true) {
	            int result = JOptionPane.showConfirmDialog(
	                null,
	                panel,
	                "Player Names",
	                JOptionPane.OK_CANCEL_OPTION,
	                JOptionPane.QUESTION_MESSAGE
	            );

	            if (result == JOptionPane.CANCEL_OPTION) {
	                System.exit(0);;
	            }

	            String player1Name = player1Field.getText().trim();
	            String player2Name = player2Field.getText().trim();

	            /**
	             * ensure that both fields are populated
	             */
	            if (player1Name.isEmpty() || player2Name.isEmpty()) {
	                JOptionPane.showMessageDialog(
	                    null,
	                    "Player names cannot be empty!",
	                    "Error",
	                    JOptionPane.ERROR_MESSAGE
	                );
	                continue;
	            }

	            return new String[]{player1Name, player2Name};
	        }
	    }
	
}
