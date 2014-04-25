package model;

import java.awt.image.BufferedImage;

/**
 * This is a Chess Piece object
 * @author lianhanloh
 *
 */

public class ChessPiece implements Comparable<ChessPiece> {

    /**
     * class fields
     */
    private boolean alive;
    private Animal animal;
    private boolean team; // true if red, false if black
    /** current x coordinate */
    private int x;
    /** current y coordinate */
    private int y;
    final private BufferedImage img;

    public ChessPiece (Animal animal, BufferedImage img, boolean team) {
        this.animal = animal;
        this.img = img;
        this.alive = true;
        this.team = team;
    }

    /** captures chess piece */
    public void capture() {
        this.alive = false;
    }

    /** revive piece (for reset purpose) */
    public void revive() {
        this.alive = true;
    }

    /** returns true if chess piece is alive, false otherwise */
    public boolean isAlive() {
        return alive;
    }

    /** return chess piece's image */
    public BufferedImage getImage () {
        return img;
    }

    /** return current x coordinate */
    public int getX() {
        return x;
    }

    /** return current y coordinate */
    public int getY() {
        return y;
    }

    /** set x coordinate */
    public void setX (int x) {
        this.x = x;
    }

    /** set y coordinate */
    public void setY (int y) {
        this.y = y;
    }

    /** return animal */
    public Animal getAnimal () {
        return animal;
    }

    /** returns team */
    public boolean getTeam() {
        return team;
    }

    /** overrides toString method */
    @Override
    public String toString() {
        String a = "";
        a = animal.toString().toLowerCase();
        if (team) {
            a = "red " + a;
        }
        else {
            a = "black " + a;
        }
        return a;
    }

    /**
     * compares the Chess Pieces in terms of animal ranking order.
     * Note: this class has a natural ordering that is inconsistent with equals.
     */
    @Override
    public int compareTo(ChessPiece other) {
        Animal o = other.getAnimal();
        return (animal.ordinal() - o.ordinal()); 
    }

    /** 
     * returns true if the Object is on the same team and is the same 
     * animal. returns false if it is not a chess piece
     */
    @Override
    public boolean equals(Object other) {

        // optimization for alias
        if (this == other) {
            return true;
        }
        if (other == null) { 
            return false; 
        }
        if (other instanceof ChessPiece) {
            ChessPiece o = (ChessPiece) other;
            return (toString().equals(o.toString()));
        }
        return false;
    }
}
