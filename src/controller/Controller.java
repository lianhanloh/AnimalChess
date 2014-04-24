package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
    private static ChessPiece[][] model;
    private static Mode mode;

    /** selection modes */
    public enum Mode {
        SELECT, STEP
    }

    /** store coordinates of selected square */
    private static int x;
    private static int y;

    /** store selected chess piece */
    private static ChessPiece selected;

    /** constructor */
    public Controller (BoardPanel panel) {
        this.panel = panel;
        this.board = panel.getChessBoard();
        this.squares = panel.getSquares();
        this.model = board.getModel();
        this.mode = Mode.SELECT;

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
        int x = -1;
        int y = -1;
        // find out which square was selected 
        Object source = e.getSource();
        for (int j = 0; j < squares[0].length; j++){
            for (int i = 0; i < squares.length; i++) {
                // de-select previously selected button
                squares[i][j].setSelected(false);
                squares[i][j].setBorderPainted(false);
                // selects correct button
                if (squares[i][j] == source) {
                    x = i;
                    y = j;
                }
            }
        }
        // process selection
        if (x != -1 && y != -1) {
            switch (mode) {
            case SELECT:
                selected = model[x][y];
                // returns if there is no chess piece at location
                if (selected == null) return;
                // otherwise, go to STEP mode
                mode = Mode.STEP;
                squares[x][y].setSelected(true);
                break;
            case STEP:
                //TODO: only if legal
                if (board.movePiece (selected.getX(), selected.getY(), x, y)) {
                    squares[x][y].removePiece();
                    // update x, y coordinates
                    selected.setX(x);
                    selected.setY(y);
                    mode = Mode.SELECT;
                    squares[x][y].setSelected(true);
                    selected = null;
                }
                break;
            }
        }
        panel.repaint();
    }
}
