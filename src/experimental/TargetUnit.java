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
public class TargetUnit extends Unit {

   
        TargetUnit(String name, int y, int x)
    {
    super(name, y, x);  
    }
    
    
    @Override
    public void draw(Graphics g, Viewport v) {
       
         g.translate(v.getTileSize()*(getLocationX()-v.getOffX()), v.getTileSize()*(getLocationY()-v.getOffY()));   
         g.drawImage(ImageRegistry.getImage("targetPlaceholder"), 0, 0, null);
         g.translate(-v.getTileSize()*(getLocationX()-v.getOffX()), -v.getTileSize()*(getLocationY()-v.getOffY()));   
    }

    @Override
    public void transformToAlternate() {
         
    }

    @Override
    public void moveUnit(int y, int x, WorldScreen w) {
         
        if(w.getTmap().getTile(y, x).isOccupied()) return;
        w.getTmap().getTile( getLocationY(), getLocationX()).setOccupiedPerson(false, "");
       
        /*
         * Has no range limit on movement
         */
         setLocationY(y);
         setLocationX(x); 
        
        w.getTmap().getTile( getLocationY(), getLocationX()).setOccupiedPerson(true, getName());
     }

    
    
}
