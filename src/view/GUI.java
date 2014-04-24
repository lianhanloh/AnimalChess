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
    
    public GUI () {
        setLayout(new BorderLayout());
        // initialize chess board and it's controller
        final BoardPanel panel = new BoardPanel(new ChessBoard());
        final Controller controller = new Controller(panel);
        
        // add Chess board 
        add(panel, BorderLayout.CENTER);
        // add options tool bar at the top
        add (createOptionsToolbar(), BorderLayout.NORTH);
    }
    
    /** creates options toolbar */
    private static JPanel createOptionsToolbar() {
        final JPanel toolbar = new JPanel();
        toolbar.setLayout(new FlowLayout());
        // add quit button
        final JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        toolbar.add(quit);
        
        return toolbar;
    }
  
}
