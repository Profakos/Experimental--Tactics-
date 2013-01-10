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
            boolean ranged1, int y, int x, int teamNumber) {
    super(name, "", y, x, teamNumber);   
        
    characters = new TagComponentUnit[2];
    characters[0] = new TagComponentUnit(ch0, ranged0, teamNumber);
    characters[1] = new TagComponentUnit(ch1, ranged1, teamNumber);
    currentDominant = 0;
    
    Skill temp = SkillFactory.makeSkill("Transform");
    this.getSkillList().add(temp); 
    
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
    
     
       @Override
       void drawSkillMenu(Graphics g, Viewport v)
     {
     if(getSkillList().isEmpty()) return;    
     for(int ii = 0; ii<getSkillList().size(); ii++)
        {
              getSkillList().get(ii).drawSkillIcon(g, v, ii);

        }
     
     g.translate(v.getTileSize()*getSkillList().size(), 0);
     characters[(currentDominant+1)%2].drawSkillMenu(g, v);
     g.translate(-v.getTileSize()*getSkillList().size(), 0);
     
     }
       
       
    @Override
       Skill getSkill(int index) {
       
        if(index<this.getSkillList().size()) return this.getSkillList().get(index);
        
        return  characters[(currentDominant+1)%2].getSkillList().get(index-this.getSkillList().size());
              
      }
      /*
      * Uses a skill
      */
    @Override
      void useSkill(int index, WorldScreen w) {
         if(getActionPoints()==0 || getSilenceTimeLeft()>0) return;    
         if(index<this.getSkillList().size() && getSkillList().get(index).getCooldownTimeLeft()==0) 
         getSkillList().get(index).useSkill(this, w);
         else   {   
             if(index-this.getSkillList().size()>=characters[(currentDominant+1)%2].getSkillList().size())
                 return;
           
           
           if(characters[(currentDominant+1)%2].getSkillList().get(index-this.getSkillList().size())
                   .getCooldownTimeLeft()!=0)
               return;  
             
           characters[(currentDominant+1)%2].getSkillList().get(index-this.getSkillList().size()).useSkill(this, w);
         }
         
         
         setActionPoints(0); 
         
    }
    
    @Override
    void updateSkills()
    {
     for(int i = 0; i<this.getSkillList().size(); i++)    {
        this.getSkillList().get(i).cooldownReset(false); 
    }
        this.characters[0].updateSkills();
        this.characters[1].updateSkills(); 
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
