package edu.umgc.skhalar.ui;

import edu.umgc.skhalar.ui.panels.MoveHistoryPanel;
import edu.umgc.skhalar.ui.panels.TicTacToeBoardPanel;
import edu.umgc.skhalar.ui.panels.StatsPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Window for the entire game. This will contain the tic tac toe board,
 * total wins, and move history
 */
public class GameBoard extends JFrame {
	
    private static final long serialVersionUID = 1L;
    
    /**
     * Move history panel
     */
    private MoveHistoryPanel moveHistoryPanel = new MoveHistoryPanel();
    
    /**
     * Tic Tac Toe board panel
     */
    private TicTacToeBoardPanel ticTacToeBoardPanel = new TicTacToeBoardPanel();
	
    /**
     * Stats panel
     */
    private StatsPanel totalWinsPanel = new StatsPanel();
    
    public GameBoard() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout( getContentPane(), BoxLayout.Y_AXIS));
        
        add(this.totalWinsPanel, BorderLayout.NORTH);
        add(this.ticTacToeBoardPanel, BorderLayout.CENTER);
        add(this.moveHistoryPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * Gets totalWinsPanel
     *
     * @return value of {@link GameBoard#totalWinsPanel}
     */
    public StatsPanel getTotalWinsPanel() {
        return totalWinsPanel;
    }

    /**
     * Gets ticTacToeBoardPanel
     *
     * @return value of {@link GameBoard#ticTacToeBoardPanel}
     */
    public TicTacToeBoardPanel getTicTacToeBoardPanel() {
        return ticTacToeBoardPanel;
    }

    /**
     * Gets moveHistoryPanel
     *
     * @return value of {@link GameBoard#moveHistoryPanel}
     */
    public MoveHistoryPanel getMoveHistoryPanel() {
        return moveHistoryPanel;
    }
}
