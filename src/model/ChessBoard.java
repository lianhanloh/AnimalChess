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
    public boolean player; // true if red's turn, false if black's
    public ChessPiece[][] board;
    private static BufferedImage img;
    
    private boolean win; // true if one player has won, false otherwise

    /* list of animals */
    private static final Animal[] ANIMALS = {Animal.MOUSE, Animal.CAT, 
        Animal.WOLF, Animal.DOG, Animal.LEOPARD, Animal.TIGER, 
        Animal.LION, Animal.ELEPHANT};
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
        this.player = true; // red starts
        reset();
    }

    /** initializes all the chess pieces */
    private void initializePieces() {
        for (int i = 0; i < pieces.length; i++) {
            try {
                img = ImageIO.read(new File("images/pieces/" 
                        + FILE_NAMES[i] + ".png"));
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }
            pieces[i] = new ChessPiece(ANIMALS[i % 8], img, (i < 8));
        }
    }

    /** returns chess board model */
    public ChessPiece[][] getModel () {
        return board;
    }
    
    /** 
     * This method takes in a current position, and an intended position. 
     * If the move is legal, it moves the chess piece accordingly and returns 
     * true. Otherwise it does nothing and returns false.
     * @param curX current x coordinate
     * @param curY current y coordinate
     * @param nextX intended x coordinate
     * @param nextY intended y coordinate
     * @return
     */
    public boolean movePiece (int curX, int curY, int nextX, int nextY) {
        //TODO add additional legality checks 
        ChessPiece cur = board[curX][curY];
        // return false if there's no piece at current location
        if (cur == null) {
            return false;
        }
        
        // return false if next position is same as current
        if (curX == nextX && curY == nextY) {
            return false;
        }
        
        Animal a = cur.getAnimal();
        // return false if next step is not accessible by chess piece
        if (! accessible(a, curX, curY, nextX, nextY)) {
            return false;
        }
        
        // River
        // other pieces
        // ability to capture
        
        // move piece
        board[nextX][nextY] = board[curX][curY];
        // remove piece from current location
        board[curX][curY] = null;
        
        return true;
    }
    
    /**
     * Checks if next location is accessible by chess piece 
     * Note it does not take into account whether that location is occupied by
     * another chess piece
     * @param cur chess piece's animal
     * @param curX current X coordinate
     * @param curY current Y coordinate
     * @param nextX intended X coordinate
     * @param nextY intended Y coordinate
     * @return
     */
    private boolean accessible (Animal a, int curX, int curY, int nextX, 
            int nextY) {
        //TODO: implement method
        
        return true;
    }
    
    /** returns current player turn - true if red's, false if black */
    public boolean getTurn() {
        return player;
    }
    
    /** sets next player's turn */
    public void setTurn(boolean turn) {
        this.player = turn;
    }
    
    /** returns chess pieces */
    public ChessPiece[] getPieces () {
        return pieces;
    }
    
    /**
     * resets chess pieces to start position
     */
    public void reset() {
        board[0][0] = pieces[BLACK_LION];
        pieces[BLACK_LION].setX(0);
        pieces[BLACK_LION].setY(0);
        board[6][0] = pieces[BLACK_TIGER];
        pieces[BLACK_TIGER].setX(6);
        pieces[BLACK_TIGER].setY(0);
        board[1][1] = pieces[BLACK_DOG];
        pieces[BLACK_DOG].setX(1);
        pieces[BLACK_DOG].setY(1);
        board[5][1] = pieces[BLACK_CAT];
        pieces[BLACK_CAT].setX(5);
        pieces[BLACK_CAT].setY(1);
        board[0][2] = pieces[BLACK_MOUSE];
        pieces[BLACK_MOUSE].setX(0);
        pieces[BLACK_MOUSE].setY(2);
        board[2][2] = pieces[BLACK_LEOPARD];
        pieces[BLACK_LEOPARD].setX(2);
        pieces[BLACK_LEOPARD].setY(2);
        board[4][2] = pieces[BLACK_WOLF];
        pieces[BLACK_WOLF].setX(4);
        pieces[BLACK_WOLF].setY(2);
        board[6][2] = pieces[BLACK_ELEPHANT];
        pieces[BLACK_ELEPHANT].setX(6);
        pieces[BLACK_ELEPHANT].setY(2);
        board[0][6] = pieces[RED_ELEPHANT];
        pieces[RED_ELEPHANT].setX(0);
        pieces[RED_ELEPHANT].setY(6);
        board[2][6] = pieces[RED_WOLF];
        pieces[RED_WOLF].setX(2);
        pieces[RED_WOLF].setY(6);
        board[4][6] = pieces[RED_LEOPARD];
        pieces[RED_LEOPARD].setX(4);
        pieces[RED_LEOPARD].setY(6);
        board[6][6] = pieces[RED_MOUSE];
        pieces[RED_MOUSE].setX(6);
        pieces[RED_MOUSE].setY(6);
        board[1][7] = pieces[RED_CAT];
        pieces[RED_CAT].setX(1);
        pieces[RED_CAT].setY(7);
        board[5][7] = pieces[RED_DOG];
        pieces[RED_DOG].setX(5);
        pieces[RED_DOG].setY(7);
        board[0][8] = pieces[RED_TIGER];
        pieces[RED_TIGER].setX(0);
        pieces[RED_TIGER].setY(8);
        board[6][8] = pieces[RED_LION];
        pieces[RED_LION].setX(6);
        pieces[RED_LION].setY(8);
        // revive all pieces
        for (int i = 0; i < pieces.length; i++) {
            pieces[i].revive();
        }
    }
    
}
