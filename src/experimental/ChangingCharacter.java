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
       String drawname = name; 
    if(state == 0)
        {
         drawname += "_Human";
        }
        else
        {
       drawname += "_Weapon";
        }
     g.drawImage(ImageRegistry.getImage(drawname), 50, 50, null);
    
    }
    
}
