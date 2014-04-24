package model;

import java.awt.image.BufferedImage;

/**
 * This contains the logic for manipulating the chess board.
 * It stores the positions of each chess piece
 * 
 * @author lianhanloh
 *
 */
public class ChessBoard {
    
    private static final int col = 7;
    private static final int row = 9;
    public boolean turn; // true if player 1's, false if player 2's
    public ChessPiece[][] board;
    private static BufferedImage img;
    /* list of animals */
    private static Animal[] animals = {Animal.MOUSE, Animal.CAT, Animal.WOLF, 
        Animal.DOG, Animal.LEOPARD, Animal.TIGER, Animal.ELEPHANT};
    /* chess pieces */
    private static ChessPiece[] pieces = new ChessPiece[16];
    
    /* image file names */
    private static final String[] FILE_NAMES = {"red_mouse", "red_cat", 
        "red_wolf", "red_dog", "red_leopard", "red_tiger", "red_lion",
        "red_elephant", "black_mouse", "black_cat", "black_wolf", "black_dog", 
        "black_leopard", "black_tiger", "black_lion", "black_elephant"};    
    
    /** constructor */
    public ChessBoard () {
        board = new ChessPiece[col][row];
        initializePieces();
    }
    
    /**
     * resets chess pieces to start position
     */
    public void reset() {
        
    }
    
    /**
     * select chess piece
     */
    
    /** initializes all the chess pieces */
    private void initializePieces() {
        for (int i = 0; i < FILE_NAMES.length; i++) {
            
        }
    }
}
