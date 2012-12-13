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
    TagteamUnit(String name, String ch0, boolean ranged0, String ch1, boolean ranged1, int y, int x)
    {
    super(name, y, x);   
        
    characters = new ChangingCharacter[2];
    
    characters[0] = new ChangingCharacter(ch0, ranged0);
    characters[1] = new ChangingCharacter(ch1, ranged1);
    
    currentDominant = 0;
 
    
    }

    /*
     * Handling the interface of draw
     */
    @Override
    public void draw(Graphics g, Viewport v) { 
        
        g.translate(v.getTileSize()*getLocationX(), v.getTileSize()*getLocationY());  
      characters[currentDominant].drawChanger(g, v, 0);
      characters[(currentDominant+1)%2].drawChanger(g, v, 1); 
      g.translate(-v.getTileSize()*getLocationX(), -v.getTileSize()*getLocationY()); 
    }
    
     

        /*
     * The Dominant character and his or her weapon changes place
     */
    @Override
    public void transformToAlternate() {
         if(getCurrentDominant()==0)
        {
        setCurrentDominant(1);
        }
        else
        setCurrentDominant(0);
    }

   

    @Override
    public void moveUnit(int y, int x, WorldScreen w) {
        
        
        if(w.getTmap().getTile(y, x).isOccupied()) return;
        w.getTmap().getTile( getLocationY(), getLocationX()).setOccupiedPerson(false, "");
       
        /*
         * Can only move 4 tiles
         * TODO: makes this based on a speed stat
         */
         if( (Math.abs(y-this.getLocationY())+Math.abs(x-this.getLocationX()))<4)
        {
         setLocationY(y);
         setLocationX(x);
        }
        
        w.getTmap().getTile( getLocationY(), getLocationX()).setOccupiedPerson(true, getName());
        
       
    }
    
      @Override
    boolean canHit(Unit target, WorldScreen w) {
       
      if(characters[(currentDominant+1)%2].isRanged()) return true;
        
      if(Math.abs(this.getLocationX()-target.getLocationX())==1 || 
       Math.abs(this.getLocationY()-target.getLocationY())==1) return true;
      
      return false;
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
