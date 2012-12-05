/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Akos
 */
public class Tile {

    int tiletype;
    
    Tile(int tiletype)
    {
    this.tiletype = tiletype;
    }
    
    
    public void draw(Graphics g, int tilesize) {
        
        String s = "grass";
        g.drawImage(ImageRegistry.getImage(s), 0, 0, null);
        g.setColor(Color.black);
        g.drawRect(0, 0, tilesize, tilesize);
    }

  
}
