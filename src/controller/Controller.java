package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JButton;
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
    private static JButton[][] squares;
    
    
    /** constructor */
    public Controller (BoardPanel panel) {
        this.panel = panel;
        this.board = panel.getChessBoard();
        this.squares = panel.getSquares();
        
        
    }
    
}
