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
    private static JLabel score;
    private static JFrame winFrame;
    private static JLabel winMessage;
    private static final JPanel toolbar = new JPanel();
    private static final JButton rematch = new JButton("Re-match");
    private static final JButton quit = new JButton("Quit");
    private static final JButton showInstructions = new JButton("Instructions");

    public GUI () {
        setLayout(new BorderLayout());
        // initialize chess board
        panel = new BoardPanel(new ChessBoard());
        // add Chess board 
        add(panel, BorderLayout.CENTER);
        // add options tool bar at the top
        add (createToolbar(), BorderLayout.NORTH);
        setUpWinFrame();
        // initialize controller
        controller = new Controller(panel, winFrame, winMessage, score);

        // set up instructions frame
        createInstructions();
    }

    /** create instructions JFrame */
    private JFrame createInstructions () {
        // initialize JFrame
        instructions = new JFrame("Instructions");
        instructions.setLayout(new BorderLayout());
        // set up location 
        instructions.setLocation(400, 100);
        instructions.setResizable(false);
        // add instructions
        Instructions instructionLabel = new Instructions();
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
    private static JPanel createToolbar() {
        toolbar.setLayout(new FlowLayout());
        // add action listeners to quit, rematch, and show instruction buttons
        addActionListeners();
        // add buttons to toolbar
        toolbar.add(quit);
        toolbar.add(rematch);
        toolbar.add(showInstructions);
        // add score display
        score = new JLabel("Red: 0 Black: 0");
        toolbar.add(score);
        return toolbar;
    }

    /** sets up frame to be displayed when one player wins */
    private void setUpWinFrame() {
        winFrame = new JFrame("Congratulations");
        // add message label
        winMessage = new JLabel();
        winMessage.setHorizontalAlignment(SwingConstants.CENTER);
        winMessage.setPreferredSize(new Dimension(250,20));
        winFrame.add(winMessage);

        // set frame properties
        winFrame.setLocation(400,300);
        winFrame.pack();
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winFrame.setVisible(false);
    }

    private static void addActionListeners() {
        rematch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                reset();
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        showInstructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                instructions.setVisible(true);
            }
        });
    }

    /** resets panel */
    private static void reset() {
        controller.reset();
    }

}
