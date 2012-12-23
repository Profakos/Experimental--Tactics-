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
     
    private ChangingCharacter[] characters;
    private int currentDominant;
    
    /*
     * Constructor
     */
    TagteamUnit(String name, String ch0, boolean ranged0, String ch1, 
            boolean ranged1, int y, int x) {
    super(name, y, x);   
        
    characters = new ChangingCharacter[2];
    characters[0] = new ChangingCharacter(ch0, ranged0);
    characters[1] = new ChangingCharacter(ch1, ranged1);
    currentDominant = 0;
    this.getSkillList().add(new Skill()); 
    
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
     * Overrides the canHit function
     */
      @Override
    boolean canHit(Unit target, WorldScreen w) {
      if(characters[(currentDominant+1)%2].isRanged()) 
          return true;
      if(Math.abs(this.getLocationX()-target.getLocationX())<=1 && 
       Math.abs(this.getLocationY()-target.getLocationY())<=1) 
          return true;
      
      return false;
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
     int getModifiedTileSpeed(){
       return this.getTileSpeed()+characters[(currentDominant+1)%2].getBonusTileSpeed();
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
