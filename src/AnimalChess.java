/**
 * This is the main program class and runs the game
 * 
 * @author lianhanloh
 *
 */

import javax.swing.*;

import view.GUI;

public class AnimalChess implements Runnable {

    /** class fields */
    
    @Override
    public void run() {
        
        // initialize frame and set location
        JFrame frame = new JFrame ("Animal Chess");
        
        // set up location and size
        frame.setLocation(300, 100);
        frame.setResizable(false);
        
        // add GUI
        final GUI GUI = new GUI();
        frame.add(GUI);

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
