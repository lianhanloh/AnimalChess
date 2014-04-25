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

    /** store selected chess piece */
    private static ChessPiece selected;

    /** constructor */
    public Controller (BoardPanel panel) {
        Controller.panel = panel;
        Controller.board = panel.getChessBoard();
        Controller.squares = Controller.panel.getSquares();
        Controller.model = board.getModel();
        Controller.mode = Mode.SELECT;

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
                // returns if piece does not belong to current player
                if (selected.getTeam() != board.getTurn()) {
                    return;
                }
                // else go to STEP mode
                mode = Mode.STEP;
                updateSelection(x, y);
                break;
            case STEP:
                // change selected piece if on same team
                if ((model[x][y] != null) && 
                        (model[x][y].getTeam() == board.getTurn())) {
                    // de-select all other buttons and highlight this square
                    updateSelection(x, y);
                    // update selected
                    selected = model[x][y];
                    break;
                }
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
                    board.setTurn(!board.getTurn());
                }
                break;
            }
        }
        panel.repaint();
    }

    /**
     * de-selects all square and updates square if given
     * does not update square if -1, -1 is passed in as an argument
     * @param x x-coordinate of square
     * @param y y-coordinate of square
     */
    private static void updateSelection(int x, int y) {
        // de-select all squares
        for (int j = 0; j < squares[0].length; j++){
            for (int i = 0; i < squares.length; i++) {
                squares[i][j].setSelected(false);
                squares[i][j].setBorderPainted(false);
            }
        }
        // highlight this square if needed
        if (x != -1 && y != -1) {
            squares[x][y].setSelected(true);
        }
    }

    /** resets board panel and model */
    public void reset() {
        // reset mode to select and de-select all selected squares
        mode = Mode.SELECT;
        updateSelection(-1, -1);
        // red team starts first
        board.setTurn(true);
        // reset model board
        board.reset();
        // clear all squares
        for (int j = 0; j < squares[0].length; j++){
            for (int i = 0; i < squares.length; i++) {
                squares[i][j].removePiece();
            }
        }
        // repaint panel
        panel.repaint();
    }
}
