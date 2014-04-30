package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This class extends JPanel and displays the instructions for the game
 * @author lianhanloh
 *
 */

@SuppressWarnings("serial")
public class Instructions extends JPanel {

    /** class fields */
    private static final String BG_FILE = "images/instructions.png";
    private static final int MIN_HEIGHT = 540;
    private static final int MIN_WIDTH = 629;
    private static int width;
    private static int height;
    private static final Dimension MIN_SIZE = new Dimension(MIN_WIDTH,
            MIN_HEIGHT);
    private static BufferedImage bg;
    
    public Instructions() {
        super();
        setMinimumSize(MIN_SIZE);
        try {
            bg = ImageIO.read(new File(BG_FILE));
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    @Override
    public void paintComponent (Graphics g) {
        width = getWidth();
        height = getHeight();
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, width, height, null);
    }
    
    /** returns minimum size as the preferred size */
    @Override
    public Dimension getPreferredSize() {
        return MIN_SIZE;
    }

}
