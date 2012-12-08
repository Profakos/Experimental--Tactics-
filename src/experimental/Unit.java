/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

import java.awt.Graphics;

/**
 *
 * @author Akos
 */
public interface Unit {
    
    void draw(Graphics g, Viewport v);
    
    /*
     * For units that have alternate forms
     */
    void transformToAlternate();
    
    void moveUnit(int y, int x);
}
