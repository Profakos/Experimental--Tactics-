/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

import java.util.List;


/**
 *
 * @author Akos
 */
public class SimpleSkill extends Skill{

  
    
    private UnitFunctionEnum calledFunctionEnum;
    
    private int damage = 0;
    private int radius = 0;
    
    private boolean affectAllies = false;
    private boolean affectCaster = true;
    private boolean affectEnemies = true;
    
    private int silence = 0;
    private int doubleDamageTime = 0;
      
    /*
     * Constructor
     */
    SimpleSkill (String name) {
      
        super(name);
        
        calledFunctionEnum = null;
        
        List<SkillSetting> sklist = SkillDataRegistry.getSkillHash().get(name);
        
        if(sklist == null || sklist.isEmpty()) return;
        
        //no compostie inside a composite
        if(sklist.get(0).getSetting().equals("composite")) return;
        
        for(int ii = 0; ii<sklist.size(); ii++)
        {
        switch(sklist.get(ii).getSettingType()){
            case callfunction : calledFunctionEnum = UnitFunctionEnum.valueOf(sklist.get(ii).getSetting());
                break;
            case image: setImage(sklist.get(ii).getSetting());
                break;
            case proc: setProc(SkillProcEnum.valueOf(sklist.get(ii).getSetting()));
                break;
            case damage: damage = Integer.valueOf(sklist.get(ii).getSetting());
                break;
            case radius: radius = Integer.valueOf(sklist.get(ii).getSetting());
                break;
            case affectCaster: affectCaster = Boolean.valueOf(sklist.get(ii).getSetting());
                break;
            case affectAllies: affectAllies = Boolean.valueOf(sklist.get(ii).getSetting());
                break;
            case affectEnemies: affectEnemies = Boolean.valueOf(sklist.get(ii).getSetting());
                break;
            case cooldown: this.setCooldown(Integer.valueOf(sklist.get(ii).getSetting()));
                break;
            case silence: silence = Integer.valueOf(sklist.get(ii).getSetting());
                break;
            case doubleDamageTime: doubleDamageTime = Integer.valueOf(sklist.get(ii).getSetting());
                break;
            default: break;
            }
        }
        
    }
    
    /*
     * Using the skill
     */
    @Override
    public void useSkill(Unit user, WorldScreen w) {
        
        if(getCalledFunctionEnum()!=null)
        switch(getCalledFunctionEnum())
        {
            case transformToAlternate: user.transformToAlternate(); 
                break;
            case randomPort: user.randomPort(w);
                break;
            case respawn: user.respawn(w);
                break;
            case refreshSkills: user.updateSkills();
                break;
        } 
        
        String tempname;
        for(int ii = 0-this.radius; ii<=this.radius; ii++) {
            for(int jj = 0-this.radius; jj<=this.radius; jj++) {
            
                if(ii+user.getLocationX()<0 || jj+user.getLocationY()<0 || 
                        ii+user.getLocationX()>=w.getTmap().getWidthInTiles() ||
                        jj+user.getLocationY()>=w.getTmap().getHeightInTiles()) continue;
              
                
             tempname = w.getTmap().getTile(jj+user.getLocationY(), ii+user.getLocationX())
                        .getOccupName();
             if(tempname.equals("")) continue;
             
             if(tempname.equals(user.getName()) && this.affectCaster) {
                 this.affectUnit(user, w);
                 continue;
             }
             
             /* 
              * TODO  Will need to make this more compact
              */
             if(w.getPlayerGroup().containsUnit(tempname)) {
                 if(user.getTeamNumber()==w.getPlayerGroup().getTeamNumber()
                         && affectAllies==false)
                     continue;
                 
                 this.affectUnit( w.getPlayerGroup().getUnit(tempname), w);  
                 
                 continue;
             }
             
             if(w.getEnemyGroup().containsUnit(tempname)) {
                 if(user.getTeamNumber()==w.getEnemyGroup().getTeamNumber()
                       &&  affectEnemies==false)
                     continue;
             this.affectUnit( w.getEnemyGroup().getUnit(tempname), w);  
                 continue;
             }
             
            }
        }
        this.cooldownReset(true);
    }
    
    /*
     * affects a creature
     */
    void    affectUnit(Unit u, WorldScreen w)  {
             u.setSilenceTimeLeft(this.getSilence());
             u.setDoubledamageTimeLeft(this.getDoubleDamageTime());
             u.modifyHealth(-damage);
             if(u.getCurrentHealth()<=0) 
                 u.procSkill(w, SkillProcEnum.onDeath);
    }

    /**
     * @return the calledFunctionEnum
     */
    public UnitFunctionEnum getCalledFunctionEnum() {
        return calledFunctionEnum;
    }

    /**
     * @param calledFunctionEnum the calledFunctionEnum to set
     */
    public void setCalledFunctionEnum(UnitFunctionEnum calledFunctionEnum) {
        this.calledFunctionEnum = calledFunctionEnum;
    }

    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * @return the raidus
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @param raidus the raidus to set
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * @return the affectCaster
     */
    public boolean isAffectCaster() {
        return affectCaster;
    }

    /**
     * @param affectCaster the affectCaster to set
     */
    public void setAffectCaster(boolean affectCaster) {
        this.affectCaster = affectCaster;
    }

    /**
     * @return the silence
     */
    public int getSilence() {
        return silence;
    }

    /**
     * @param silence the silence to set
     */
    public void setSilence(int silence) {
        this.silence = silence;
    }

    /**
     * @return the doubleDamageTime
     */
    public int getDoubleDamageTime() {
        return doubleDamageTime;
    }

    /**
     * @param doubleDamageTime the doubleDamageTime to set
     */
    public void setDoubleDamageTime(int doubleDamageTime) {
        this.doubleDamageTime = doubleDamageTime;
    }
    
     
    
    
    
}
