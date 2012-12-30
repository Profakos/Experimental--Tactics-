/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Akos
 */
public class UnitGroup {
    
    private List<String> names;
    
    private HashMap<String, Unit> units;
    private int current = 0; 
    
    private int teamNumber;
    /*
     * Constructor
     */
    UnitGroup(int teamNumber) {
    this.teamNumber = teamNumber;
    units = new HashMap<>();    
    if(teamNumber==0){
   // units.add(new TagteamUnit("alpha", "sound", false, "clean", false, 0, 0));
   // units.add(new TagteamUnit("beta", "tech", true, "water", true, 0, 1));
    units.put("gamma", new TagteamUnit("gamma","blood", false, "ghost", true, 0, 2, teamNumber));
    units.put("carp", new SimpleUnit("carp", 0, 1, teamNumber));
    }
    else {
        units.put("target", new TargetUnit("target", 5, 5, teamNumber));
        }
    
    names = new ArrayList<>();
    
    if(!units.isEmpty()) {
        names.addAll(units.keySet());
        }
    }
    
    
    /*
     * @return the currently active player character
     */
    Unit getCurrentSelected() {  
        return getUnits().get(names.get(current)); 
    }

    /*
     * draws the units in the group
     */
    void draw(Graphics g, Viewport v) {
        
         g.drawImage(ImageRegistry.getImage("selectedChar"), 
                 (getCurrentSelected().getLocationX()-v.getOffX())*v.getTileSize(),
                 (getCurrentSelected().getLocationY()-v.getOffY())*v.getTileSize(), null);
         
        
        for(int index = 0; index<getNames().size(); index++) {
            getUnits().get(names.get(index)).draw(g, v);
        }
          
    }
    
    /*
     * @return if the unit exists in this team
     */
    boolean containsUnit(String name) {
        return getUnits().containsKey(name); 
    }
    
    /*
     * @return a unit based on their name
     * TODO: this will need a unique id based rewrite
     */
    Unit getUnit(String name) {
     return getUnits().get(name);
    }
    
    /*
     * Updates the units in this group
     */
    void update(WorldScreen w)
    {
    for(int ii = 0; ii<this.getUnits().size(); ii++) {
            getUnits().get(names.get(ii)).update(w);
        } 
    }

 /*
  * Cycles through the player characters
  */
    void prevnextUnit(int dir) {
        setCurrent(getCurrent() + dir);
        if( getCurrent()<0)
            setCurrent(getNames().size() - 1);
        else 
            setCurrent(getCurrent() % getNames().size());
    }

    /*
     * Moves the currently selected Unit
     */
    void moveUnit(int y, int x, WorldScreen w) {
        getCurrentSelected().moveCommand(y,x,w);
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

    /**
     * @return the team
     */
    public int getTeamNumber() {
        return teamNumber;
    }

    /**
     * @param team the team to set
     */
    public void setTeamNumber(int team) {
        this.teamNumber = team;
    }

    /**
     * @return the names
     */
    public List<String> getNames() {
        return names;
    }

    /**
     * @param names the names to set
     */
    public void setNames(List<String> names) {
        this.names = names;
    }

    /**
     * @return the units
     */
    public HashMap<String, Unit> getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(HashMap<String, Unit> units) {
        this.units = units;
    }
    
}
