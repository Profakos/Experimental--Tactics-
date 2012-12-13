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

    private int tiletype;
    
    private boolean occupied = false;
    
    private String occupName;
    
    Tile(int tiletype)
    {
    this.occupName = "";    
    this.tiletype = tiletype;
    }
    
    
    /*
     * Sets the person on the tile, and its occupiedness
     */
    public void setOccupiedPerson(Boolean b, String s)
    {
    this.setOccupied(b);
    this.setOccupName(s); 
    }
    
    
    /*
     * draws the tile
     */
    public void draw(Graphics g, int tilesize) {
        
        String s = "grass";
        g.drawImage(ImageRegistry.getImage(s), 0, 0, null);
        g.setColor(Color.black);
        g.drawRect(0, 0, tilesize, tilesize);
    }
    
    

    /**
     * @return the tiletype
     */
    public int getTiletype() {
        return tiletype;
    }

    /**
     * @param tiletype the tiletype to set
     */
    public void setTiletype(int tiletype) {
        this.tiletype = tiletype;
    }

    /**
     * @return the occupied
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * @param occupied the occupied to set
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * @return the occupName
     */
    public String getOccupName() {
        return occupName;
    }

    /**
     * @param occupName the occupName to set
     */
    public void setOccupName(String occupName) {
        this.occupName = occupName;
    }

  
}
