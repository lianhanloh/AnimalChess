/**
 * This is the main program class and runs the game
 * @author lianhanloh
 *
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class AnimalChess implements Runnable {

    @Override
    public void run() {
        
        // initialize frame and set location
        JFrame frame = new JFrame ("Animal Chess");
        frame.setLocation(300, 300);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * main method to start and run the game. No arguments necessary
     * 
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new AnimalChess());
    }

}
