package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.*;

/**
 * This class displays the state of the current board to the user
 * 
 * @author lianhanloh
 *
 */

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

    /** class fields */
    private static final String BG_FILE = "images/dou_shou_qi_board.png";
    private static final int MIN_HEIGHT = 636;
    private static final int MIN_WIDTH = 500;
    private static int width;
    private static int height;
    private static final Dimension MIN_SIZE = new Dimension(MIN_WIDTH,
            MIN_HEIGHT);
    private static BufferedImage bg;
    private static final int COL = 7;
    private static final int ROW = 9;

    /** model */
    private ChessBoard board;
    private ChessPiece[][] model;
    
    /** 
     * array of buttons 
     * 0,0 is the top left square
     */
    private static final InvisibleButton[][] squares = 
            new InvisibleButton[COL][ROW];

    /** 
     * Constructs a Board Panel
     * @param board a chess board model
     */
    public BoardPanel(ChessBoard board) {

        super();
        // set up grid layout 
        setLayout(new GridLayout(ROW, COL));
        this.board = board;  
        this.model = board.getModel();
        setMinimumSize(MIN_SIZE);
        try {
            bg = ImageIO.read(new File(BG_FILE));
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        // sets up the squares of buttons
        setUpSquares();
        
    }

    @Override
    public void paintComponent (Graphics g) {
        width = getWidth();
        height = getHeight();
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, width, height, null);
        for (int j = 0; j < squares[0].length; j++){
            for (int i = 0; i < squares.length; i++) {
                // repaint each button
                squares[i][j].repaint();
            }
        }
        drawPieces();
    }
    
    /** returns minimum size as the preferred size */
    @Override
    public Dimension getPreferredSize() {
        return MIN_SIZE;
    }

    /** returns chess board */
    public ChessBoard getChessBoard () {
        return board;
    }

    /** initialize squares */
    private void setUpSquares () {
        for (int y = 0; y < ROW; y++) {
            for (int x = 0; x < COL; x++) {
                squares[x][y] = new InvisibleButton();
                squares[x][y].setSelected(false);
                add(squares[x][y]);
            }
        }
    }

    /** returns buttons */
    public InvisibleButton[][] getSquares() {
        return squares;
    }

    /** draws chess pieces */
    private void drawPieces () {
        for (int y = 0; y < ROW; y++) {
            for (int x = 0; x < COL; x++) {
                if (model[x][y] != null) {
                    squares[x][y].showPiece(model[x][y]);
                }
            }
        }
    }
    
}
