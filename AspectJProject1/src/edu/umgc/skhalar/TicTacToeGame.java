package edu.umgc.skhalar;

import edu.umgc.skhalar.ui.GameBoard;

import javax.swing.*;

/**
 * Main class and method for running the game
 */
public class TicTacToeGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameBoard();                
            }
        });
    }
}
