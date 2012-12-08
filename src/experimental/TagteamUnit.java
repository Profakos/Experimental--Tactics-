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
public class TagteamUnit implements Unit {
     
    private int locationY;
    private int locationX;
    
    private ChangingCharacter char0;
    private ChangingCharacter char1;
    
    private int currentDominant;
    
    TagteamUnit(String ch0, String ch1, int y, int x)
    {
    char0 = new ChangingCharacter(ch0);
    char1 = new ChangingCharacter(ch1);
    
    currentDominant = 1;

    locationY = y;
    locationX = x;
    
    }

    /*
     * Handling the interface of draw
     */
    @Override
    public void draw(Graphics g, Viewport v) { 
        
        if(getCurrentDominant()==0)
        { 
        drawTeam(g, v, getChar0(), getChar1()); 
        }
        else
        { 
        drawTeam(g, v, getChar1(), getChar0()); 
        }
    }
    
     /*
     * Drawing the tagteam
     */
    
    public void drawTeam(Graphics g, Viewport v, ChangingCharacter wielder, ChangingCharacter weapon)
    { 
         
      g.translate(v.getTileSize()*locationX, v.getTileSize()*locationY);  
      wielder.drawChanger(g, v, 0);
      weapon.drawChanger(g, v, 1); 
      g.translate(-v.getTileSize()*locationX, -v.getTileSize()*locationY);
    }

    /**
     * @return the char0
     */
    public ChangingCharacter getChar0() {
        return char0;
    }

    /**
     * @param char0 the char0 to set
     */
    public void setChar0(ChangingCharacter char0) {
        this.char0 = char0;
    }

    /**
     * @return the char1
     */
    public ChangingCharacter getChar1() {
        return char1;
    }

    /**
     * @param char1 the char1 to set
     */
    public void setChar1(ChangingCharacter char1) {
        this.char1 = char1;
    }

    /**
     * @return the currentDominant
     */
    public int getCurrentDominant() {
        return currentDominant;
    }

    /**
     * @param currentDominant the currentDominant to set
     */
    public void setCurrentDominant(int currentDominant) {
        this.currentDominant = currentDominant;
    }

    @Override
    public void transformToAlternate() {
         if(getCurrentDominant()==0)
        {
        setCurrentDominant(1);
        }
        else
        setCurrentDominant(0);
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

    @Override
    public void moveUnit(int y, int x) {
        
        if( (Math.abs(y-this.locationY)+Math.abs(x-this.locationX))<6)
        {
         setLocationY(y);
         setLocationX(x);
        }
    }
}
