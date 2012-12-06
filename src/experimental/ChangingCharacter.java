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
public class ChangingCharacter {

    String name;
    
    ChangingCharacter(String name) {
       this.name = name;
    }
    
    void drawChanger(Graphics g, Viewport v, int state)
    {
       
     g.drawImage(ImageRegistry.getImage(name + "_Char"), 0, 0, 50, 50, state*v.getTileSize(), 0, (state+1)*v.getTileSize(), 50, null);
    
    }
    
}
