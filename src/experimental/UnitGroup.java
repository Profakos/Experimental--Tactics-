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
    
    
    private List<Unit> units;
    private int current = 0; 
    
    /*
     * Constructor
     */
    UnitGroup() {
    units = new ArrayList<>();    
   // units.add(new TagteamUnit("alpha", "sound", false, "clean", false, 0, 0));
   // units.add(new TagteamUnit("beta", "tech", true, "water", true, 0, 1));
    units.add(new TagteamUnit("gamma","blood", false, "ghost", true, 0, 2));
    units.add(new SimpleUnit("carp", 0, 1));
    }
    
    /*
     * @return the currently active player character
     */
    Unit getCurrentSelected() {  
        return getUnits().get(current); 
    }

    /*
     * draws the units in the group
     */
    void draw(Graphics g, Viewport v) {
        
         g.drawImage(ImageRegistry.getImage("selectedChar"), 
                 (getUnits().get(getCurrent()).getLocationX()-v.getOffX())*v.getTileSize(),
                 (getUnits().get(getCurrent()).getLocationY()-v.getOffY())*v.getTileSize(), null);
         
         
        for(int index = 0; index<getUnits().size(); index++) {
            getUnits().get(index).draw(g, v);
        }
          
    }
    
    /*
     * @return a unit based on their name
     * TODO: this will need a unique id based rewrite
     */
    Unit getUnitByName(String name) {
    for(int ii = 0; ii<this.units.size(); ii++) {
        if(this.units.get(ii).getName().equals(name)) 
            return this.units.get(ii);
        }
    return null;
    }
    
    /*
     * Updates the units in this group
     */
    void update(WorldScreen w)
    {
    for(int ii = 0; ii<this.units.size(); ii++) {
            this.units.get(ii).update(w);
        } 
    }

 /*
  * Cycles through the player characters
  */
    void prevnextUnit(int dir) {
        setCurrent(getCurrent() + dir);
        if( getCurrent()<0)
            setCurrent(getUnits().size() - 1);
        else 
            setCurrent(getCurrent() % getUnits().size());
    }

    /*
     * Moves the currently selected Unit
     */
    void moveUnit(int y, int x, WorldScreen w) {
        getCurrentSelected().moveCommand(y,x,w);
    }

    /**
     * @return the units
     */
    public List<Unit> getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(List<Unit> units) {
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
