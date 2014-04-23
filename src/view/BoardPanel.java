package view;

import java.awt.*;
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
    private static int minHeight = 398;
    private static int minWidth = 300;
    private static int width;
    private static int height;
    private static final Dimension minimumSize = new Dimension(minWidth,
            minHeight);
    private static BufferedImage bg;
    
    /* model */
    private static ChessBoard board;

    public BoardPanel(ChessBoard board) {
        
        this.board = board;
        
        setMinimumSize(minimumSize);
        try {
            bg = ImageIO.read(new File(bg_file));
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
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
}
