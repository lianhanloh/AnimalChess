package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.ChessBoard;

/**
 * This class displays the state of the current board to the user
 * 
 * @author lianhanloh
 *
 */

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

    private static final String bg_file = "images/dou_shou_qi_board.png";
    private static int minHeight = 636;
    private static int minWidth = 500;
    private static int width;
    private static int height;
    private static final Dimension minimumSize = new Dimension(minWidth,
            minHeight);
    private static BufferedImage bg;
    private static final int col = 7;
    private static final int row = 9;
    
    /* model */
    private static ChessBoard board;
        
    /* 
     * array of buttons 
     * 0,0 is the top left square
     */
    private static final InvisibleButton[][] squares = 
            new InvisibleButton[col][row];

    public BoardPanel(ChessBoard board) {
        
        super();
        setLayout(new GridLayout(row, col));
        BoardPanel.board = board;  
        setMinimumSize(minimumSize);
        try {
            bg = ImageIO.read(new File(bg_file));
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
       
        setUpSquares();
        addEventListeners();
    }


    public void paintComponent (Graphics g) {
        width = getWidth();
        height = getHeight();
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, width, height, null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(minWidth, minHeight);
    }
    
    /** returns chess board */
    public ChessBoard getChessBoard () {
        return board;
    }
    
    /** initialize squares */
    private void setUpSquares () {
        //TODO: make buttons transparent. maybe don't use buttons but set event
        // listeners in each empty component
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                squares[x][y] = new InvisibleButton();
                add(squares[x][y]);
            }
        }
    }
    
    /** returns buttons */
    public InvisibleButton[][] getSquares() {
        return squares;
    }
    
    /**
     * This method adds the mouse click listeners to each button
     */
    private static void addEventListeners () {
        for (int y = 0; y < squares[0].length; y++){
            for (int x = 0; x < squares.length; x++) {
                if (x == 0 && y == 0) {
                    squares[x][y].addActionListener(new ActionListener() {
                        
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                        }
                    });
                }
            }
        }
    }
}
