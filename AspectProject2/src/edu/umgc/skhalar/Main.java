package edu.umgc.skhalar;

import edu.umgc.skhalar.gui.ContactBookMainView;

import java.awt.*;

public class Main {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ContactBookMainView window = new ContactBookMainView();
                window.frame.setVisible(true);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        });
    }
}
