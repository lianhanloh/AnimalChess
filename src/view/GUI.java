package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.Controller;
import model.ChessBoard;

/**
 * This class layouts the GUI for the game
 * 
 * @author lianhanloh
 *
 */
@SuppressWarnings("serial")
public class GUI extends JPanel {
    
    private static BoardPanel panel;
    private static Controller controller;
    
    public GUI () {
        setLayout(new BorderLayout());
        // initialize chess board and it's controller
        panel = new BoardPanel(new ChessBoard());
        controller = new Controller(panel);
        
        //TODO: indicate current player's turn
        
        // add Chess board 
        add(panel, BorderLayout.CENTER);
        // add options tool bar at the top
        add (createOptionsToolbar(), BorderLayout.NORTH);
    }
    
    /** creates options tool bar */
    private static JPanel createOptionsToolbar() {
        final JPanel toolbar = new JPanel();
        toolbar.setLayout(new FlowLayout());
        // quit button
        final JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        // re-match button
        final JButton rematch = new JButton("Re-match");
        rematch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                reset();
            }
        });
        toolbar.add(quit);
        toolbar.add(rematch);
        
        return toolbar;
    }
    
    /** resets panel */
    private static void reset() {
        controller.reset();
    }
  
}
