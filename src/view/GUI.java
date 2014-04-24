package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
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
        BoardPanel panel = new BoardPanel(new ChessBoard());
        // initialize controller
        final Controller controller = new Controller(panel);
        
        // add panel to GUI
        add(panel, BorderLayout.CENTER);
        
        add (createOptionsToolbar(), BorderLayout.NORTH);
    }
    
    /** creates options toolbar */
    private static JPanel createOptionsToolbar() {
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new FlowLayout());
        // add quit button
        JButton quit = new JButton("Quit");
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
