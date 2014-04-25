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
    /** Associated Chess Board */
    private static ChessBoard board;
    /** Associated Board Panel */
    private static BoardPanel panel;
    /** 2D array of invisible buttons */
    private static InvisibleButton[][] squares;
    /** chess board model */
    private static ChessPiece[][] model;
    /** current select mode */
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
                if (selected == null) {
                    return;
                }
                // else go to STEP mode
                mode = Mode.STEP;
                // de-select all other buttons
                for (int j = 0; j < squares[0].length; j++){
                    for (int i = 0; i < squares.length; i++) {
                        squares[i][j].setSelected(false);
                        squares[i][j].setBorderPainted(false);
                    }
                }
                // highlight this square
                squares[x][y].setSelected(true);
                break;
            case STEP:
                int origX = selected.getX();
                int origY = selected.getY();
                // get model to move piece if legal
                if (board.movePiece (origX, origY, x, y)) {
                    // remove piece from original location if it
                    // was moved successfully
                    squares[origX][origY].removePiece();
                    // update it's x, y coordinates
                    selected.setX(x);
                    selected.setY(y);
                    // return to select mode
                    mode = Mode.SELECT;
                    // select the square the piece moved to 
                    squares[x][y].setSelected(true);
                    // set selected piece to null
                    selected = null;
                    // de-select initially chosen square
                    squares[origX][origY].setSelected(false);
                    squares[origX][origY].setBorderPainted(false);
                    // update turn to next player's turn
                    board.setTurn(! board.getTurn());
                }
                break;
            }
        }
        panel.repaint();
    }
}
