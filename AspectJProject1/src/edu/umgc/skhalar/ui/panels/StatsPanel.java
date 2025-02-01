package edu.umgc.skhalar.ui.panels;

import javax.swing.*;

import edu.umgc.skhalar.TicTacToeGameEngine;
import edu.umgc.skhalar.model.Player;
import edu.umgc.skhalar.ui.components.AutoSizeLabel;

import java.awt.*;
import java.io.Serial;

/**
 * Panel that contains the players total wins and show's who's move it is
 */
public class StatsPanel extends JPanel {
	@Serial
	private static final long serialVersionUID = 1L;
	
	/**
	 * Current player label
	 */
	final AutoSizeLabel currentPlayer;

	/**
	 * Player X's Wins label
	 */
	final AutoSizeLabel playerXWins;
	
	/**
	 * Player Y's Wins label
	 */
	final AutoSizeLabel playerYWins;
	
	public StatsPanel() {
        setLayout(new GridLayout(1, 3));
		this.currentPlayer = new AutoSizeLabel().setTextWithAutoSize("");
        this.playerXWins = new AutoSizeLabel().setTextWithAutoSize(
        		TicTacToeGameEngine.getInstance().getPlayerX().getName() + " Wins: " 
        		+ TicTacToeGameEngine.getInstance().getPlayerX().getTotalWins()
        );
        this.playerYWins = new AutoSizeLabel().setTextWithAutoSize(
        		TicTacToeGameEngine.getInstance().getPlayerO().getName() + " Wins: " 
        		+ TicTacToeGameEngine.getInstance().getPlayerO().getTotalWins()
        );
        add(this.playerXWins);
        add(this.playerYWins);
        add(this.currentPlayer);
        setMaximumSize(new Dimension(500, 200));
    }
	
	/**
	 * set the current player's label
	 * @param player The current player
	 */
	public void setCurrentPlayer(Player player) {
		this.currentPlayer.setTextWithAutoSize("X's Move (" + player.getName() + ")");
	}
	
	/**
	 * Toggle the current player
	 * @param xMove 	is it X's move
	 */
	public void togglePlayer(boolean xMove) {
		if (xMove) {
			this.currentPlayer.setText("X's Move (" 
					+ TicTacToeGameEngine.getInstance().getPlayerX().getName()
					+ ")");
		} else {
			this.currentPlayer.setText("O's Move ("
					+ TicTacToeGameEngine.getInstance().getPlayerO().getName()
					+ ")");
		}
	}
	
	/**
	 * Update total win labels
	 */
	public void updateWins() {
		this.playerXWins.setTextWithAutoSize(
        		TicTacToeGameEngine.getInstance().getPlayerX().getName() + " Wins: " 
        		+ TicTacToeGameEngine.getInstance().getPlayerX().getTotalWins()
        );
        this.playerYWins.setTextWithAutoSize(
        		TicTacToeGameEngine.getInstance().getPlayerO().getName() + " Wins: " 
        		+ TicTacToeGameEngine.getInstance().getPlayerO().getTotalWins()
        );
		
	}
}
