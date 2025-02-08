package edu.umgc.skhalar.model;

/**
 * Model contain information about the player
 */
public class Player {
	/**
	 * Name of the player
	 */
	private final String name;
	
	/**
	 * total wins for the player
	 */
	private int totalWins = 0;
	
	public Player(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * @return total wins
	 */
	public int getTotalWins() {
		return totalWins;
	}
	
	/**
	 * increment the players wins;
	 */
	public void incrementWin() {
		this.totalWins++;
	}
	
	/**
	 * @return Name of the player
	 */
	public String getName() {
		return name;
	}
	
	
}
