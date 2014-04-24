package view;

import java.awt.Graphics;

import javax.swing.JButton;

/**
 * This is an invisible button which has the same functionality as a button
 * but does not appear to the viewer
 * 
 * @author lianhanloh
 *
 */

@SuppressWarnings("serial")
public class InvisibleButton extends JButton {
    
    public InvisibleButton () {
        super();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        // do nothing
    }
    
}