package model;

import java.awt.image.BufferedImage;

/**
 * This is a card object class that stores information about a poker card
 * @author lianhanloh
 *
 */

public class Card implements Comparable<Card> {
    
    
    /**
     * class fields
     */
    private int num;
    private Suit suit;
    private BufferedImage img;
    
    /**
     * Constructor
     * @param suit card's suit
     * @param num  card number
     */
    public Card (Suit suit, int num, BufferedImage img) {
        this.suit = suit;
        this.num = num;
        this.img = img;
    }
    
    
    /** Declares enum type suit */

    public enum Suit {
        Heart,
        Club,
        Spade,
        Diamond        
    }
    
    @Override
    public int compareTo(Card arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    
}
