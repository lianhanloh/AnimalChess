package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This contains the logic for manipulating the chess board.
 * It stores the positions of each chess piece
 * 
 * @author lianhanloh
 *
 */
public class ChessBoard {

    private static final int COL = 7;
    private static final int ROW = 9;
    public boolean turn; // true if red, false if black
    public ChessPiece[][] board;
    private static BufferedImage img;
    
    private boolean win; // true if one player has won, false otherwise

    /* list of animals */
    private static final Animal[] ANIMALS = {Animal.MOUSE, Animal.CAT, Animal.WOLF, 
        Animal.DOG, Animal.LEOPARD, Animal.TIGER, Animal.ELEPHANT};
    /* chess pieces */
    private static ChessPiece[] pieces = new ChessPiece[16];

    /* image file names */
    private static final String[] FILE_NAMES = {"red_mouse", "red_cat", 
        "red_wolf", "red_dog", "red_leopard", "red_tiger", "red_lion",
        "red_elephant", "black_mouse", "black_cat", "black_wolf", "black_dog", 
        "black_leopard", "black_tiger", "black_lion", "black_elephant"};    

    /* match index to particular chess piece */
    private static final int RED_MOUSE = 0;
    private static final int RED_CAT = 1;
    private static final int RED_WOLF = 2;
    private static final int RED_DOG = 3;
    private static final int RED_LEOPARD = 4;
    private static final int RED_TIGER = 5;
    private static final int RED_LION = 6;
    private static final int RED_ELEPHANT = 7;
    private static final int BLACK_MOUSE = 8;
    private static final int BLACK_CAT = 9;
    private static final int BLACK_WOLF = 10;
    private static final int BLACK_DOG = 11;
    private static final int BLACK_LEOPARD = 12;
    private static final int BLACK_TIGER = 13;
    private static final int BLACK_LION = 14;
    private static final int BLACK_ELEPHANT = 15;

    /** constructor */
    public ChessBoard () {
        board = new ChessPiece[COL][ROW];
        initializePieces();
        reset();
    }

    /**
     * resets chess pieces to start position
     */
    public void reset() {
        board[0][0] = pieces[BLACK_LION];
        board[6][0] = pieces[BLACK_TIGER];
        board[1][1] = pieces[BLACK_DOG];
        board[5][1] = pieces[BLACK_CAT];
        board[0][2] = pieces[BLACK_MOUSE];
        board[2][2] = pieces[BLACK_LEOPARD];
        board[4][2] = pieces[BLACK_WOLF];
        board[6][2] = pieces[BLACK_ELEPHANT];
        board[0][6] = pieces[RED_ELEPHANT];
        board[2][6] = pieces[RED_WOLF];
        board[4][6] = pieces[RED_LEOPARD];
        board[6][6] = pieces[RED_MOUSE];
        board[1][7] = pieces[RED_CAT];
        board[5][7] = pieces[RED_DOG];
        board[0][8] = pieces[RED_TIGER];
        board[6][8] = pieces[RED_LION];
    }

    /**
     * select chess piece
     */

    /** initializes all the chess pieces */
    private void initializePieces() {
        for (int i = 0; i < pieces.length; i++) {
            try {
                img = ImageIO.read(new File("images/pieces/" 
                        + FILE_NAMES[i] + ".png"));
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }
            pieces[i] = new ChessPiece(ANIMALS[i % 7], img, (i < 8));
        }
    }

    /** returns chess board model */
    public ChessPiece[][] getModel () {
        return board;
    }
    
    /** 
     * This method takes in a chess piece, it's current position, and it's 
     * intended position. If the move is legal, it moves the chess piece 
     * accordingly and returns true. Otherwise it does nothing and 
     * returns false.
     * @param p
     * @param curX current x coordinate
     * @param curY current y coordinate
     * @param nextX intended x coordinate
     * @param nextY intended y coordinate
     * @return
     */
    public boolean movePiece (ChessPiece p, int curX, int curY, int nextX,
            int nextY) {
        //TODO add additional legality checks 
        // River
        // other pieces
        // ability to capture
        
        // move piece
        board[nextX][nextY] = board[curX][curY];
        // remove piece from current location
        board[curX][curY] = null;
        
        return true;
    }
    
}
