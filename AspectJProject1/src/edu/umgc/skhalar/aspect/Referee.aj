package edu.umgc.skhalar.aspect;

import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import edu.umgc.skhalar.TicTacToeGameEngine;
import edu.umgc.skhalar.model.Player;
import edu.umgc.skhalar.ui.dialogs.IntroDialog;
import edu.umgc.skhalar.ui.panels.MoveHistoryPanel;
import edu.umgc.skhalar.ui.panels.TicTacToeBoardPanel;
import edu.umgc.skhalar.ui.panels.StatsPanel;

/**
 * Referee aspect to monitor game. It will also get the players name, record all moves, and determine who the winner is.
 */
public aspect Referee {
	/**
	 * String for NO_WINNER
	 */
	private static final String NO_WINNER = "NO_WINNER";
	
	/**
	 * Tic Tac Toe board panel
	 */
	private TicTacToeBoardPanel gameBoard;
	
	/**
	 * Move history panel
	 */
	private MoveHistoryPanel history;
	
	/**
	 * Stats panel
	 */
	private StatsPanel statsPanel;

	/**
	 * Set Stats Panel when constructor is called
	 */
	pointcut newStatsPanel(StatsPanel panel):
		(execution(StatsPanel.new(..))) && this(panel);
	
	after(StatsPanel panel): newStatsPanel(panel) {
		this.statsPanel = panel;
		this.statsPanel.setCurrentPlayer(
				TicTacToeGameEngine.getInstance().getCurrentPlayer()
		);
	}
	
	/**
	 * Set History panel when constructor is called
	 * @param panel
	 */
	pointcut newHistoryPanel(MoveHistoryPanel panel):
		(execution(MoveHistoryPanel.new(..))) && this(panel);
	
	after(MoveHistoryPanel panel): newHistoryPanel(panel) {
		this.history = panel;
	}
	
	/**
	 * Set TicTacToe Board when constructor is called
	 */
	pointcut newGame(TicTacToeBoardPanel board):
                (execution(TicTacToeBoardPanel.new(..))) && this(board);

	/**
	 * Show dialog asking for The players names and setting them in the game engine. It will set the player's information
	 * and randomly pick one of the players to start first.
	 */
	after(TicTacToeBoardPanel newGame): newGame(newGame) {
		this.history.appendHistory("New Game Started");
		gameBoard = newGame;
		final String[] playerNames = IntroDialog.getPlayerNames();
		TicTacToeGameEngine.getInstance().setPlayerX(new Player(
				playerNames[0]));
		TicTacToeGameEngine.getInstance().setPlayerO(new Player(
				playerNames[1]));
		boolean isXMove = new Random().nextBoolean();
		TicTacToeGameEngine.getInstance().setXMove(isXMove);
		if (isXMove) {
			TicTacToeGameEngine.getInstance().setPlayerXAsCurrent();
		} else {
			TicTacToeGameEngine.getInstance().setPlayerOAsCurrent();
		}
	}

	/**
	 * Pointcut when tic tac toe board button is clicked by the player
	 */
	pointcut buttonClicked(ActionEvent e): (execution(* actionPerformed(ActionEvent)) && args(e));

	/**
	 * After a move is made:
	 * - check if see if the player has won
	 * -- if the player is won, show dialog mentioning the player who won and ask if they wanted to play another
	 * -  toggle the current player in the game engine
	 */
	after(ActionEvent e) : buttonClicked(e) {
		this.history.appendHistory(TicTacToeGameEngine.getInstance().getCurrentPlayer().getName()
				+ " Played " + ((JButton) e.getSource()).getName());
		String results = checkWinner(gameBoard);
		if (results != null) {
			final String message;
			if (results.equals(NO_WINNER)) {
				message = "Game over! No winners.";
			} else {
				TicTacToeGameEngine.getInstance().getCurrentPlayer().incrementWin();
				message = "Game won by: " + TicTacToeGameEngine.getInstance().getCurrentPlayer().getName();
				statsPanel.updateWins();
			}
			
			this.history.appendHistory(message);
			this.gameBoard.gameOver();
			 int resp = JOptionPane.showConfirmDialog(
					 this.gameBoard,
					 message + "\nWould you like to play another?",
					 message,
					 JOptionPane.YES_NO_OPTION);
			 if (resp == 0) {
				 System.out.println("Resetting board");
				 this.gameBoard.resetBoard();
				 this.history.appendHistory("Starting new game.");
			 } else {
				System.exit(0);
			 }
		}
		this.statsPanel.togglePlayer(TicTacToeGameEngine.getInstance().isXMove());
		
	}

	/**
	 * Check if there is a winner or a draw.
	 * @param board	TicTacToe board
	 * @return string of the winning button, null if there's no winner or draw and NO_WINNER if there is a draw.
	 */
	private String checkWinner(TicTacToeBoardPanel board) {
		final JButton[][] grid = board.getBoard();

		// Check rows
		for (int i = 0; i < 3; i++) {
			if (!grid[i][0].getText().isEmpty() 
					&& grid[i][0].getText().equals(grid[i][1].getText())
					&& grid[i][0].getText().equals(grid[i][2].getText())) {
				return grid[i][0].getText();
			}
		}

		// Check columns
		for (int j = 0; j < 3; j++) {
			if (!grid[0][j].getText().isEmpty() 
					&& grid[0][j].getText().equals(grid[1][j].getText())
					&& grid[0][j].getText().equals(grid[2][j].getText())) {
				return grid[0][j].getText();
			}
		}

		// Check diagonals
		if (!grid[0][0].getText().isEmpty() 
				&& grid[0][0].getText().equals(grid[1][1].getText())
				&& grid[0][0].getText().equals(grid[2][2].getText())) {
			return grid[0][0].getText();
		}

		if (!grid[0][2].getText().isEmpty() 
				&& grid[0][2].getText().equals(grid[1][1].getText())
				&& grid[0][2].getText().equals(grid[2][0].getText())) {
			return grid[0][2].getText();
		}
		
		
		if (isDraw(grid)) {
			return NO_WINNER;
		}

		return null;
	}
	
	/**
	 * Check if the game is a draw
	 * @param buttons	buttons from tictactoe board
	 * @return	true if the game is a draw
	 */
	public boolean isDraw(final JButton[][] buttons) {
	    // Check if all cells are filled
	    for (JButton[] row : buttons) {
	        for (JButton button : row) {
	            if (button.getText().isEmpty() || button.isEnabled()) {
	                // If any cell is empty or still enabled, game is not a draw
	                return false;
	            }
	        }
	    }

	    // If we get here, all cells are filled and there's no winner
	    return true;
	}
}