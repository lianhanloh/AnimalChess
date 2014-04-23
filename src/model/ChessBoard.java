package model;

/**
 * This contains the logic for manipulating the chess board.
 * It stores the positions of each chess piece
 * 
 * @author lianhanloh
 *
 */
public class ChessBoard {
    
    private final static int col = 7;
    private final static int row = 9;
    public boolean turn; // true if player 1's, false if player 2's
    public ChessPiece[][] board;
    
    
    /** constructor */
    public ChessBoard () {
        board = new ChessPiece[col][row];
    }
    
    /**
     * resets chess pieces to start position
     */
    public void reset() {
        
    }
    
    /**
     * select chess piece
     */
}
