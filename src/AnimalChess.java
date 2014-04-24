/**
 * This is the main program class and runs the game
 * 
 * @author lianhanloh
 *
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.Controller;
import model.ChessBoard;
import view.BoardPanel;

public class AnimalChess implements Runnable {

    /** class fields */
    private static final Dimension dim = new Dimension(500,636);
    
    @Override
    public void run() {
        
        // initialize frame and set location
        JFrame frame = new JFrame ("Animal Chess");
        
        // set up location and size
        frame.setLocation(300, 100);
        frame.setMinimumSize(dim);
        frame.setResizable(false);
        
        // add Panels
        final BoardPanel panel = new BoardPanel(new ChessBoard());
        frame.add(panel);
       
        // initialize controller
        final Controller controller = new Controller(panel);

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
