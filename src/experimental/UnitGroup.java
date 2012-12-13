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
    
    private int current = 0; 
    
    UnitGroup()
    {
    units = new ArrayList<>();    
    units.add(new TagteamUnit("alpha", "sound", false, "clean", false, 0, 0));
    units.add(new TagteamUnit("beta", "tech", true, "water", true, 0, 1));
    units.add(new TagteamUnit("gamma","blood", false, "ghost", true, 0, 2));
    }
    
    TagteamUnit getCurrentSelected(){  return getUnits().get(getCurrent()); }

    void draw(Graphics g, Viewport v) {
        
         g.drawImage(ImageRegistry.getImage("selectedChar"), getUnits().get(getCurrent()).getLocationX()*v.getTileSize()
           , getUnits().get(getCurrent()).getLocationY()*v.getTileSize(), null);
         
         
        for(int index = 0; index<getUnits().size(); index++)
        {
            getUnits().get(index).draw(g, v);
        }
          
    }

 
    void prevnextUnit(int dir)
    {
   
        setCurrent(getCurrent() + dir);
    
    if( getCurrent()<0) setCurrent(getUnits().size() - 1);
    else setCurrent(getCurrent() % getUnits().size());
    }

    /*
     * Moves the currently selected Unit
     */
    void moveUnit(int y, int x, WorldScreen w) {
          
        getCurrentSelected().moveUnit(y, x, w); 
    }

    /**
     * @return the units
     */
    public List<TagteamUnit> getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(List<TagteamUnit> units) {
        this.units = units;
    }

    /**
     * @return the current
     */
    public int getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(int current) {
        this.current = current;
    }
    
}
