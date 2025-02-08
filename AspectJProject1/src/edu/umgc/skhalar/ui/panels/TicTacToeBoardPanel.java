package edu.umgc.skhalar.ui.panels;

import edu.umgc.skhalar.TicTacToeGameEngine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Creates the tic tac toe board
 */
public class TicTacToeBoardPanel extends JPanel implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1787932212698696144L;
	
	/**
     * Creates the x and o buttons for the game
     */
    private JButton[][] boardButtons;

    public TicTacToeBoardPanel() {
        setSize(500, 500);
        setLayout(new GridLayout(3, 3, 10, 10));
        // Generate board
        this.buildBoard();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JButton button = (JButton) e.getSource();
        if (button.isEnabled()) {
            if (TicTacToeGameEngine.getInstance().isXMove()) {
                button.setText("X");
                button.setEnabled(false);
            } else {
                button.setText("O");
                button.setEnabled(false);
            }
            TicTacToeGameEngine.getInstance().toggleXMove();
        }
    }
    
    public JButton[][] getBoard() {
        return boardButtons;
    }
    
    /**
     * Disables the board when the game is over
     */
    public void gameOver() {
    	for (final JButton[] buttons: this.boardButtons) {
    		for (final JButton innerButtons: buttons) {
    			innerButtons.setEnabled(false);
    		}
    	}
    }
    
    public void resetBoard() {
    	this.removeAll();
    	this.buildBoard();
    	this.revalidate();
    	this.repaint();
    }
    
    /**
     * Disables the board when the game is over
     */
    private void buildBoard() {
        // Only create new array if it doesn't exist
        if (this.boardButtons == null) {
            this.boardButtons = new JButton[3][3];
        }

    	
    	this.boardButtons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton();
                button.setLayout(null);
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                button.addActionListener(this);
                button.setPreferredSize(new Dimension(160, 160));
                button.setName(i + "," + j);
                boardButtons[i][j] = button;
                add(button);
            }
        }
    }
}
