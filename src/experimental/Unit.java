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
    private int tileSpeed;
    
    private int actionPoints;
    
    Unit(String name, int lY, int lX)
    {
    this.name = name;
    this.locationY = lY;
    this.locationX = lX;
    
    this.tileSpeed = 6;
    this.actionPoints = 2;
    }
    
    /*
     * draws the unit
     */
    abstract void draw(Graphics g, Viewport v);
    
    /*
     * A string for the menu to display when selected
     */
    String menuDisplay()
    {
    return name;
    };
    
    /*
     * handles action of the move command. Pathfinding and action points
     */
    void moveCommand(int y, int x, WorldScreen w)
    {
    moveUnit(y, x, w);
    }
    
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
    
    /*
     * The Distance it can move in tiles, modified by effects
     */
    int getModifiedTileSpeed()
    {
    return 0;
    };
    
    /*
     * @Updates the character
     */
    void update(WorldScreen w)
    {
    this.actionPoints = 2;
    }
    
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

    /**
     * @return the moveSpeed
     */
    public int getTileSpeed() {
        return tileSpeed;
    }

    /**
     * @param moveSpeed the moveSpeed to set
     */
    public void setTileSpeed(int moveSpeed) {
        this.tileSpeed = moveSpeed;
    }

    /**
     * @return the actionPoints
     */
    public int getActionPoints() {
        return actionPoints;
    }

    /**
     * @param actionPoints the actionPoints to set
     */
    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }
}
