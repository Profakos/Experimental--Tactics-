/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akos
 */
public abstract class Unit {
    
    private String name;
    private String image;
    private int locationY;
    private int locationX;
    
    private int range;
    private int bonusRange;
    
    private int tileSpeed;
    private int bonusTileSpeed;
    
    private int actionPoints;
    
    private int currentHealth;
    private int maxHealth;
    
    private int teamNumber;
    
    private List<Skill> skillList;
    
    private int silenceTimeLeft = 0;
    private int damageBoostTimeLeft = 0;
    private int damageBoost = 0;
    
    
    private int usingSkill = -1; //the skill you are using at the moment
    private int targetingX = -1;
    private int targetingY = -1;
    
    Unit(String name, String image, int lY, int lX, int teamNumber)
    {
    this.name = name;
    this.image = image;
    this.teamNumber = teamNumber;
    
    this.locationY = lY;
    this.locationX = lX;
    
    this.range = 1;
    this.bonusRange = 0;
    
    this.tileSpeed = 7;
    this.bonusTileSpeed = 0;
    
    this.actionPoints = 2;
    
    currentHealth = 3;
    maxHealth = 3;
    
    skillList = new ArrayList<>();
    
    
    }
    
    /*
     * draws the unit
     */
     void draw(Graphics g, Viewport v) {
     g.translate(v.getTileSize()*(getLocationX()-v.getOffX()), v.getTileSize()*(getLocationY()-v.getOffY()));   
     g.drawImage(ImageRegistry.getImage(image), 0, 0, null);
     g.translate(-v.getTileSize()*(getLocationX()-v.getOffX()), -v.getTileSize()*(getLocationY()-v.getOffY())); 
    };
    
     void drawHealthbar(Graphics g, Viewport v)
     {
     
         g.setColor(Color.red);
         g.fillRect(0, 0, (v.getTileSize()*getCurrentHealth())/getMaxHealth(), 5);
     
     };
    
     /*
      * draws the skillsMenu of the unit
      */
     void drawSkillMenu(Graphics g, Viewport v)
     {
     if(this.skillList.isEmpty()) return;    
     for(int ii = 0; ii<this.skillList.size(); ii++)
        {
        skillList.get(ii).drawSkillIcon(g, v, ii);
        }
     
     }
     
     /*
      * Get Skill
      */
      Skill getSkill(int ii) {
          return this.getSkillList().get(ii);
      }
     
     
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
    void moveCommand(int y, int x, WorldScreen w) { 
        int ty = this.getLocationY();
        int tx = this.getLocationX();
        moveUnit(y, x, w);
        
    }
    
    /*
     * moves the unit to somewhere
     */
   void moveUnit(int y, int x, WorldScreen w)
   {
      if(w.getTmap().getTile(y, x).isOccupied()) return;
        w.getTmap().getTile( getLocationY(), getLocationX()).setOccupiedPerson(false, "");
       
          
         if( (Math.abs(y-this.getLocationY())+Math.abs(x-this.getLocationX()))<=
                 getCurrentTileSpeed())
        {
         setLocationY(y);
         setLocationX(x);
         this.setActionPoints(this.getActionPoints()-1); //if moved
        }
        
        w.getTmap().getTile( getLocationY(), getLocationX()).setOccupiedPerson(true, getName());
   };
   
   /*
    * Moves the unit instantly
    */
   void teleportUnit(int y, int x, WorldScreen w) {
       
         if(w.getTmap().getTile(y, x).isOccupied()) return;
         
         w.getTmap().getTile( getLocationY(), getLocationX()).setOccupiedPerson(false, "");
         this.setLocationY(y);
         this.setLocationX(x);
         w.getTmap().getTile( getLocationY(), getLocationX()).setOccupiedPerson(true, getName());
   }
    
    

    /*
     * respawn on a random tile
     */
    
    void respawn(WorldScreen w)
    {
    this.setCurrentHealth(this.maxHealth);
    
    randomPort(w);
    }
    
    /*
     * Checks if this unit can perform an attack on the target
     */
     boolean canHit(Unit target, WorldScreen aThis) {
         
      int dist = this.getCurrentRange();
      if(Math.abs(this.getLocationX()-target.getLocationX()) + 
       Math.abs(this.getLocationY()-target.getLocationY())<=dist) 
          return true;
      
      return false;
      
    }
    
     
     /*
      * Uses a skill
      */
      void useSkill(int index, WorldScreen w) {
         if(getActionPoints()==0 || getSilenceTimeLeft()>0) return;    
         if(index<this.getSkillList().size() && getSkillList().get(index).getCooldownTimeLeft()==0)
         {  
             getSkillList().get(index).useSkill(this, w);
             setActionPoints(0); 
         }
            
    }
      
    /*
     * For units that have alternate forms
     */
    void transformToAlternate() {
    };
    
    
    
    /*
     * @Updates the character
     */
    void update(WorldScreen w)
    {
    if(this.getCurrentHealth()<=0) return;
    
    this.actionPoints = 2;
    updateSkills();
    
    if(this.getSilenceTimeLeft()>0)
    this.setSilenceTimeLeft(this.getSilenceTimeLeft()-1);
    
    if(this.getDamageBoostTimeLeft()>0) {
    this.setDamageBoostTimeLeft(this.getDamageBoostTimeLeft()-1);
    if(this.getDamageBoostTimeLeft()==0) this.setDamageBoost(0);
    }
    }
    
    void updateSkills() {
       for(int i = 0; i<this.skillList.size(); i++)    {
        this.skillList.get(i).cooldownReset(false);
    } 
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

    /**
     * @return the currentHealth
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * @param currentHealth the currentHealth to set
     */
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     * @return the maxHealth
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * @param maxHealth the maxHealth to set
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    
    public void modifyHealth(int health)
    {
    this.setCurrentHealth(this.getCurrentHealth()+health);
    if(currentHealth>maxHealth) this.setCurrentHealth(this.getMaxHealth());
    if(currentHealth<0) this.setCurrentHealth(0); 
    }

    /**
     * @return the skillList
     */
    public List<Skill> getSkillList() {
        return skillList;
    }

    /**
     * @param skillList the skillList to set
     */
    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the buffedTileSpeed
     */
    public int getBonusTileSpeed() {
        return bonusTileSpeed;
    }

    /**
     * @param buffedTileSpeed the buffedTileSpeed to set
     */
    public void setBonusTileSpeed(int buffedTileSpeed) {
        this.bonusTileSpeed = buffedTileSpeed;
    }
    
    /*
     * Returns the modified tilespeed
     */
    public int getCurrentTileSpeed() {
        return this.getTileSpeed() + this.getBonusTileSpeed();
    }

    /**
     * @return the range
     */
    public int getRange() {
        return range;
    }

    /**
     * @param range the range to set
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * @return the bonusrange
     */
    public int getBonusRange() {
        return bonusRange;
    }

    /**
     * @param bonusrange the bonusrange to set
     */
    public void setBonusRange(int bonusrange) {
        this.bonusRange = bonusrange;
    }
    
     /*
     * Returns the modified range
     */
    public int getCurrentRange() {
        return this.getRange() + this.getBonusRange();
    }

    /*
     * randomly teleports on the map
     */
    public void randomPort(WorldScreen w) {
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
    
    w.getTmap().getTile( getLocationY(), getLocationX()).setOccupiedPerson(true, getName());
    
    teleportUnit(newy, newx, w);
    }

    /*
     * Attacks the target
     */
    void attack(Unit target, WorldScreen w) {
         if(canHit(target, w)) {  
             
                         setActionPoints(0);
                          
                         target.modifyHealth(-1 + getDamageBoost()); 
                         
                         
                        if(target.getCurrentHealth()<=0) 
                        target.procSkill(w, SkillProcEnum.onDeath);
            }
    }

    /*
     * Procs Skills
     */
    public void procSkill(WorldScreen w, SkillProcEnum sProc) {
       // target.respawn(w);
        if(this.skillList.isEmpty()) return;
        for(int i = 0; i<this.skillList.size(); i++)
            if(skillList.get(i).getProc()==sProc) skillList.get(i).useSkill(this, w);
    }

    /**
     * @return the teamNumber
     */
    public int getTeamNumber() {
        return teamNumber;
    }

    /**
     * @param teamNumber the teamNumber to set
     */
    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    /**
     * @return the silenceTimeLeft
     */
    public int getSilenceTimeLeft() {
        return silenceTimeLeft;
    }

    /**
     * @param silenceTimeLeft the silenceTimeLeft to set
     */
    public void setSilenceTimeLeft(int silenceTimeLeft) {
        this.silenceTimeLeft = silenceTimeLeft;
    }

  

    void think(WorldScreen w) {
       moveCommand(getLocationY()+w.getRgen().nextInt(3)-1, 
               getLocationX()+w.getRgen().nextInt(3)-1, w);
    }

    /**
     * @return the damageBoost
     */
    public int getDamageBoost() {
        return damageBoost;
    }

    /**
     * @param damageBoost the damageBoost to set
     */
    public void setDamageBoost(int damageBoost) {
        this.damageBoost = damageBoost;
    }

    /**
     * @return the damageBoostTimeLeft
     */
    public int getDamageBoostTimeLeft() {
        return damageBoostTimeLeft;
    }

    /**
     * @param damageBoostTimeLeft the damageBoostTimeLeft to set
     */
    public void setDamageBoostTimeLeft(int damageBoostTimeLeft) {
        this.damageBoostTimeLeft = damageBoostTimeLeft;
    }

    /**
     * @return the usingSkill
     */
    public int getUsingSkill() {
        return usingSkill;
    }

    /**
     * @param usingSkill the usingSkill to set
     */
    public void setUsingSkill(int usingSkill) {
        this.usingSkill = usingSkill;
    }

    /**
     * @return the targetingX
     */
    public int getTargetingX() {
        return targetingX;
    }

    /**
     * @param targetingX the targetingX to set
     */
    public void setTargetingX(int targetingX) {
        this.targetingX = targetingX;
    }

    /**
     * @return the targetingY
     */
    public int getTargetingY() {
        return targetingY;
    }

    /**
     * @param targetingY the targetingY to set
     */
    public void setTargetingY(int targetingY) {
        this.targetingY = targetingY;
    }
    
}
