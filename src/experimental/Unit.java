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
public abstract class Unit {
    
    private String name;
    private int locationY;
    private int locationX;
    
    Unit(String name, int lY, int lX)
    {
    this.name = name;
    this.locationY = lY;
    this.locationX = lX;
    }
    
    /*
     * draws the unit
     */
    abstract void draw(Graphics g, Viewport v);
    
    
    /*
     * moves the unit to somewhere
     */
    abstract void moveUnit(int y, int x, WorldScreen w);

    /*
     * respawn on a random tile
     */
    
    void respawn(WorldScreen w)
    {
    
    int newy;
    int newx;
    
    /*
     * randomizes the new location at least once
     */
    do
    {
    newy = w.getRgen().nextInt(w.getTmap().getHeightInTiles());
    newx = w.getRgen().nextInt(w.getTmap().getWidthInTiles()); 
    }
    while(w.getTmap().getTile(newy, newx).isOccupied());
    
    moveUnit(newy, newx, w);
    
    }
    
    /*
     * Checks if this unit can perform an attack on the target
     */
     boolean canHit(Unit target, WorldScreen aThis) {
     return true;  
    }
    
    
    
    /*
     * For units that have alternate forms
     */
    abstract void transformToAlternate();
    
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the locationY
     */
    public int getLocationY() {
        return locationY;
    }

    /**
     * @param locationY the locationY to set
     */
    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    /**
     * @return the locationX
     */
    public int getLocationX() {
        return locationX;
    }

    /**
     * @param locationX the locationX to set
     */
    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }
}
