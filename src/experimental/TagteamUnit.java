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
     
    
    private ChangingCharacter char0;
    private ChangingCharacter char1;
    
    private int currentDominant;
    
    TagteamUnit()
    {
    char0 = new ChangingCharacter("blood");
    char1 = new ChangingCharacter("ghost");
    
    currentDominant = 1;
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
      wielder.drawChanger(g, v, 0);
      weapon.drawChanger(g, v, 1); 
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
}
