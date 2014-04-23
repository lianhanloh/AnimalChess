package controller;

import java.util.EventListener;

import javax.swing.JComponent;

import view.BoardPanel;
import model.ChessBoard;

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
    
    
    /** constructor */
    public Controller (BoardPanel panel) {
        this.panel = panel;
        this.board = panel.getChessBoard();
        
        addEventListeners();
    }
    
    /**
     * This method adds the mouse click listeners to each button
     */
    private static void addEventListeners () {
        //TODO: add event listeners
    }

    /**
     * This method creates an event listener for each square in the chess board
     */
    private static EventListener createLocationListener () {
        //TODO: create event listener
        
        return null;
    }
}
