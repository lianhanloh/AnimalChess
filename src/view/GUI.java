package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.Controller;
import model.ChessBoard;

/**
 * This class lays out the game's GUI 
 * 
 * @author lianhanloh
 *
 */
@SuppressWarnings("serial")
public class GUI extends JPanel {

    private static BoardPanel panel;
    private static Controller controller;
    private static JFrame instructions;

    public GUI () {
        setLayout(new BorderLayout());
        // initialize chess board and it's controller
        panel = new BoardPanel(new ChessBoard());
        createInstructions();
        controller = new Controller(panel);

        //TODO: indicate current player's turn

        // add Chess board 
        add(panel, BorderLayout.CENTER);
        // add options tool bar at the top
        add (createOptionsToolbar(), BorderLayout.NORTH);
    }

    /** create instructions JFrame */
    private JFrame createInstructions () {
        // initialize JFrame
        instructions = new JFrame("Instructions");
        instructions.setLayout(new BorderLayout());
        // set up location and size
        instructions.setLocation(400, 100);
        instructions.setResizable(true);
        // add instructions
        ImageIcon img = new ImageIcon("images/instructions.png");
        JLabel instructionLabel = new JLabel(img);
        Dimension instructionDim = new Dimension(300, 400);
        instructionLabel.setPreferredSize(instructionDim);
        instructions.add(instructionLabel, BorderLayout.CENTER);
        // add button to close instructions frame
        JPanel options = new JPanel();
        JButton closeInstructions = new JButton("Okay");
        closeInstructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setVisible(false);
            }
        });
        options.add(closeInstructions);
        instructions.add(options, BorderLayout.SOUTH);
        // Set instructions frame invisible by default
        instructions.pack();
        instructions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        instructions.setVisible(false);
        return instructions;
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
        // show instructions button
        final JButton showInstructions = new JButton("Instructions");
        showInstructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                instructions.setVisible(true);
            }
        });
        toolbar.add(quit);
        toolbar.add(rematch);
        toolbar.add(showInstructions);

        return toolbar;
    }

    /** resets panel */
    private static void reset() {
        controller.reset();
    }

}
