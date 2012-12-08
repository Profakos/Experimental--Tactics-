/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akos
 */
public class UnitGroup {
    
    
    private List<TagteamUnit> units;
    
    int current = 0; 
    
    UnitGroup()
    {
    units = new ArrayList<>();    
    units.add(new TagteamUnit("sound", "clean", 0, 0));
    units.add(new TagteamUnit("tech", "water", 0, 1));
    units.add(new TagteamUnit("blood", "ghost", 0, 2));
    }
    
    TagteamUnit getCurrentSelected(){  return units.get(current); }

    void draw(Graphics g, Viewport v) {
        
         g.drawImage(ImageRegistry.getImage("selectedChar"), units.get(current).getLocationX()*v.getTileSize()
           , units.get(current).getLocationY()*v.getTileSize(), null);
         
         
        for(int index = 0; index<units.size(); index++)
        {
        units.get(index).draw(g, v);
        }
          
    }

 
    void prevnextChar(int dir)
    {
   
    current  = (current + dir);
    
    if(current<0) current = units.size()-1;
    else current %= units.size();
    }
    
}
