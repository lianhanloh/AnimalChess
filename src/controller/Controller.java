package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.*;
import model.*;

/**
 * This class file listens to users' input and manipulates the ChessBoard
 * 
 * @author lianhanloh
 *
 */
public class Controller {
    
    /** class fields */
    private static ChessBoard board;
    private static BoardPanel panel;
    private static InvisibleButton[][] squares;
    
    
    /** constructor */
    public Controller (BoardPanel panel) {
        this.panel = panel;
        this.board = panel.getChessBoard();
        this.squares = panel.getSquares();
        
        addEventListeners();
    }
    
    /**
     * This method adds the mouse click listeners to each button
     */
    private static void addEventListeners () {
        for (int y = 0; y < squares[0].length; y++){
            for (int x = 0; x < squares.length; x++) {
                squares[x][y].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        processSelection(e);
                    }                  
                });
            }
        }
    }
    
    /**
     * This method deals with the action listener received by a button
     * @param e the button's action
     */
    private static void processSelection(ActionEvent e) {
        Object source = e.getSource();
        for (int y = 0; y < squares[0].length; y++){
            for (int x = 0; x < squares.length; x++) {
                // de-select previously selected button
                squares[x][y].setSelected(false);
                // selects correct button
                if (squares[x][y] == source) {
                    squares[x][y].setSelected(true);
                }
                // repaint each button
                squares[x][y].repaint();
            }
        }
    }
}
