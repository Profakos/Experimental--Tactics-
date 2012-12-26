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
public class TagteamUnit extends Unit{
     
    private TagComponentUnit[] characters;
    private int currentDominant;
    
    /*
     * Constructor
     */
    TagteamUnit(String name, String ch0, boolean ranged0, String ch1, 
            boolean ranged1, int y, int x) {
    super(name, y, x);   
        
    characters = new TagComponentUnit[2];
    characters[0] = new TagComponentUnit(ch0, ranged0);
    characters[1] = new TagComponentUnit(ch1, ranged1);
    currentDominant = 0;
    
    this.getSkillList().add(new Skill("Transform")); 
    
    
    }

    /*
     * Overrides the draw function
     */
    @Override
    public void draw(Graphics g, Viewport v) { 
        
      g.translate(v.getTileSize()*(getLocationX()-v.getOffX()), v.getTileSize()*(getLocationY()-v.getOffY()));   
      characters[currentDominant].draw(g, v, 0);
      characters[(currentDominant+1)%2].draw(g, v, 1); 
      g.translate(-v.getTileSize()*(getLocationX()-v.getOffX()), -v.getTileSize()*(getLocationY()-v.getOffY()));  
    }
    
     

    /*
     * The Dominant character and his or her weapon changes place
     */
    @Override
    public void transformToAlternate() {
         if(getCurrentDominant()==0) {
            setCurrentDominant(1);
         }
            else
            setCurrentDominant(0);
    }
  
 
    /*
     * @return the status for the menu
     */
    @Override
    String menuDisplay()
    {
    return "Team " + this.getName() + " WIELDER: " +
          "\n" + characters[currentDominant].getName() +
          "\n" + " WEAPON: "+characters[(currentDominant+1)%2].getName();
    }  
      
    /*
     * Overrides the modified tilespeed function
     */ 
    @Override
     public int getCurrentTileSpeed(){
       return this.getTileSpeed() + this.getBonusTileSpeed()+characters[(currentDominant+1)%2].getBonusTileSpeed();
    }
    
     
     /*
     * Returns the modified range
     */
    @Override
    public int getCurrentRange() {
        return this.getRange() + this.getBonusRange() + characters[(currentDominant+1)%2].getBonusRange();
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

   

    
  
}
