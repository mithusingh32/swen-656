package edu.umgc.skhalar;

import edu.umgc.skhalar.model.Player;

/**
 * Game engine to handle and store information about the game. 
 */
public class TicTacToeGameEngine {
	/**
	 * Static instance of the game engine
	 */
	private static TicTacToeGameEngine INSTANCE;

	/**
	 * Player X and O
	 */
	private Player playerX, playerO;
	
	/**
	 * indicates if X's move
	 */
	private boolean xMove = false;
	
	/**
	 * The current player that's making a move
	 */
	private Player currentPlayer;

	public static TicTacToeGameEngine getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TicTacToeGameEngine();
		}
		return INSTANCE;
	}

	/**
	 * Gets playerOne
	 *
	 * @return value of {@link TicTacToeGameEngine#playerX}
	 */
	public Player getPlayerX() {
		return playerX;
	}

	/**
	 * Sets {@link TicTacToeGameEngine}
	 *
	 * @param playerX New value of {@link TicTacToeGameEngine#playerX}
	 */
	public void setPlayerX(Player playerX) {
		this.playerX = playerX;
	}

	/**
	 * Gets playerTwo
	 *
	 * @return value of {@link TicTacToeGameEngine#playerO}
	 */
	public Player getPlayerO() {
		return playerO;
	}
	
	/**
	 * Sets {@link TicTacToeGameEngine}
	 *
	 * @param playerTwo New value of {@link TicTacToeGameEngine#playerO}
	 */
	public void setPlayerO(Player playerTwo) {
		this.playerO = playerTwo;
	}

	/**
	 * @return Current player
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Set Player O as the current player
	 */
	public void setPlayerOAsCurrent() {
		this.currentPlayer = this.playerO;
	}

	/**
	 * Set Player X as the current player
	 */
	public void setPlayerXAsCurrent() {
		this.currentPlayer = this.playerX;
	}
	
	/**
	 * @return true if it's X's move
	 */
	public boolean isXMove() {
		return xMove;
	}
	
	/**
	 * Set if it's X's move
	 * @param xMove		X's move
	 */
	public void setXMove(boolean xMove) {
		this.xMove = xMove;
	}

	/**
	 * Toggle xMove and the current player
	 */
	public void toggleXMove() {
		xMove = !xMove;
		if (this.currentPlayer.getName().equals(this.playerO.getName())) {
			this.setPlayerXAsCurrent();
		} else {
			this.setPlayerOAsCurrent();
		}
	}
}
