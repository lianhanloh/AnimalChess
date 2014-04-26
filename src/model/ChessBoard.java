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
     * true. It also updates win variable if game was won with that move.
     * Otherwise it does nothing and returns false.
     * @param curX current x coordinate
     * @param curY current y coordinate
     * @param nextX intended x coordinate
     * @param nextY intended y coordinate
     * @return
     */
    public boolean movePiece (int curX, int curY, int nextX, int nextY) {
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

        // capture target if square is occupied by opponent piece and can be 
        // captured by cur piece. return false if target square is occupied
        // and capture is invalid
        if (board[nextX][nextY] != null 
                && ! capture(curX, curY, nextX, nextY, cur)) {
            return false;
        }
        
        // set win to true if move will place chess piece in opponent's den
        if (inDen(cur, nextX, nextY)) {
            this.win = true;
        }
        
        // move piece and remove from current location
        board[nextX][nextY] = board[curX][curY];
        board[curX][curY] = null;

        return true;
    }

    /**
     * Given the current position of a chess piece and the position of it's 
     * target, captures piece returns true if capture is valid. returns false
     * and does nothing if capture is invalid
     * @param curX x coordinate of chess piece
     * @param curY y coordinate of chess piece
     * @param nextX x coordinate of target piece
     * @param nextY y coordinate of target piece
     * @param cur current chess piece
     * @return true if piece was captured, false if capture is not valid
     */
    private boolean capture(int curX, int curY, int nextX, int nextY,
            ChessPiece cur) {
        Animal a = cur.getAnimal();
        ChessPiece target = board[nextX][nextY];
        Animal b = target.getAnimal();
        // return false if target chess piece has higher rank
        // unless cur is mouse or unless target is in trap
        if (cur.compareTo(target) < 0 && ! a.toString().equals("MOUSE")
                && ! inTrap(target, nextX, nextY)) {
            return false;
        }
        // mouse can only capture elephant and mouse
        // note mouse cannot capture elephant if either mouse is in river
        if ((a.toString().equals("MOUSE")
                && !b.toString().equals("ELEPHANT") 
                && !b.toString().equals("MOUSE"))
                || inRiver(curX, curY) || inRiver(nextX, nextY)){
            return false;
        }
        // elephant cannot capture mouse
        if (a.toString().equals("ELEPHANT") 
                && b.toString().equals("MOUSE")) {
            return false;
        }
        // capture target piece
        target.capture();
        return true;
    }

    /**
     * Checks if next location is accessible by chess piece 
     * Note it does not take into account whether that location is occupied by
     * another chess piece
     * @param a chess piece's animal
     * @param curX current X coordinate
     * @param curY current Y coordinate
     * @param nextX intended X coordinate
     * @param nextY intended Y coordinate
     * @return
     */
    private boolean accessible (Animal a, int curX, int curY, int nextX, 
            int nextY) {
        // return false if intended square is more than one step away
        // make exception for lion and tiger if next to river
        if (!oneStepAway(curX, curY, nextX, nextY) 
                && !jumpException(a, curX, curY, nextX, nextY)) {
            return false;
        }
        // returns false if intended square is in river, and animal is not mouse
        if (inRiver(nextX, nextY) && ! a.toString().equals("MOUSE")) {
            return false;
        }
        return true;
    }

    /** returns true if square is one step away */
    private boolean oneStepAway(int curX, int curY, int nextX, int nextY) {
        return ((Math.abs(nextX - curX) == 1 && nextY == curY) 
                || (Math.abs(nextY - curY) == 1 && nextX == curX));
    }

    /** returns true if square is in river. takes in its x,y coordinates */
    private boolean inRiver(int x, int y) {
        return (((x > 0 && x < 3) || (x > 3 && x < 6)) && (y > 2 && y < 6));
    }

    /** returns true if chess piece will be in opponent's den, given a piece and 
     * it's target x and y coordinates
     */
    private boolean inDen (ChessPiece cp, int x, int y) {
        return (cp.getTeam() && x == 3 && y == 0) || (cp.getTeam() 
                && x == 3 && y == 8);
    }

    /** returns true if chess piece is in trap, given a piece and it's current
     * x and y coordinates
     */
    private boolean inTrap (ChessPiece cp, int x, int y) {
        if (cp.getTeam()) {
            return (x == 2 && y == 0) || (x == 3 && y == 1) || (x == 4 
                    && y == 0);
        }
        else {
            return (x == 2 && y == 8) || (x == 3 && y == 7) || (x == 4 
                    && y == 8);
        }
    }

    /** 
     * returns true if exception should be made because lions and tigers
     * can jump over river, and only if there's no rat in their way
     */
    private boolean jumpException(Animal a, int curX, int curY, int nextX, 
            int nextY) {
        // return false if animal is not lion or tiger
        if (a.toString() != "LION" && a.toString() != "TIGER") {
            return false;
        }
        // check if lion/tiger is on left or right side of river
        // and is meant to jump to the other side
        if ((curX == 0 || curX == 3 || curX == 6) && (curY > 2 && curY < 6)) {
            // return true if intended position is across the river
            if (Math.abs(nextX - curX) == 3 && (nextY == curY)) {
                // check if there's a mouse in the river blocking the jump
                for (int i = 1; i < 3; i++) {
                    int sign = (nextX - curX) % 2;
                    if (board[curX + sign * i][curY] != null) {
                        return false;
                    }
                }
                return true;
            }
        }
        // check if lion/tiger is above or below river and meant to jump to
        // the other side
        if ((curY == 2 || curY == 6) && (curX == 1 || curX == 2 || curX == 4
                || curX == 5)) {
            if (Math.abs(nextY - curY) == 4 && (nextX == curX)) {
                // check if there's a mouse in the river blocking the jump
                for (int i = 1; i < 4; i++) {
                    int sign = (nextY - curY) % 3;
                    if (board[curX][curY + sign * i] != null) {
                        return false;
                    }
                }
                return true;
            }
        }
        // else return false
        return false;
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
        // clear board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = null;
            }
        }
        // place pieces in starting positions
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