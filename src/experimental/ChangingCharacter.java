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

    private String name;
    private boolean ranged;
    private int bonusTileSpeed;
    
   
    /*
     * Constructor
     */
    ChangingCharacter(String name, boolean ranged) {
       this.name = name;
       this.ranged = ranged;
       bonusTileSpeed  = ranged ? 0 : 2;
    }
    
    /*
     * Draws this unit 
     */
    void draw(Graphics g, Viewport v, int state) {
        g.drawImage(ImageRegistry.getImage(getName() + "_Char"), 0, 0, 
        v.getTileSize(), v.getTileSize(), state*v.getTileSize(), 0, (state+1)*v.getTileSize(), 50, null);
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
     * @return the ranged
     */
    public boolean isRanged() {
        return ranged;
    }

    /**
     * @param ranged the ranged to set
     */
    public void setRanged(boolean ranged) {
        this.ranged = ranged;
    }

    /**
     * @return the bonusMoveSpeed
     */
    public int getBonusTileSpeed() {
        return bonusTileSpeed;
    }

    /**
     * @param bonusMoveSpeed the bonusMoveSpeed to set
     */
    public void setBonusTileSpeed(int bonusTileSpeed) {
        this.bonusTileSpeed = bonusTileSpeed;
    }

  
    
}
