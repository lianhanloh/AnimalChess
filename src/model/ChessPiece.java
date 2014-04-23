package model;

import java.awt.image.BufferedImage;

/**
 * This is a Chess Piece object
 * @author lianhanloh
 *
 */
public class ChessPiece {
    
    /**
     * class fields
     */
    private boolean selected;
    private boolean alive;
    private Animal animal;
    final private BufferedImage img;
    
    public ChessPiece (Animal animal, BufferedImage img) {
        this.animal = animal;
        this.img = img;
        this.alive = true;
        this.selected = false;
    }
    
    /**
     * highlights chess piece (when it is selected
     */
    
    
    /** selects chess piece */
    public void select() {
        selected = true;
    }
    
    /** captures chess piece */
    public void capture() {
        alive = false;
    }
    
    /** revive piece (for reset purpose) */
    public void revive() {
        this.alive = true;
    }
}
