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
      
    /*
     * Constructor
     */
    SimpleSkill (String name) {
      
        super(name);
        
        calledFunctionEnum = null;
        
        List<SkillSetting> sklist = SkillDataRegistry.getSkillHash().get(name);
        
        if(sklist == null || sklist.isEmpty()) return;
        
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
        } 
        
        String tempname;
        for(int ii = 0-this.radius; ii<=this.radius; ii++) {
            for(int jj = 0-this.radius; jj<=this.radius; jj++) {
            
                if(ii+user.getLocationX()<0 || jj+user.getLocationY()<0 || 
                        ii+user.getLocationX()>=w.getTmap().getWidthInTiles() ||
                        jj+user.getLocationY()>=w.getTmap().getHeightInTiles()) continue;
               /*
                * Placeholder solution, ugh, so ugly
                */
                
             tempname = w.getTmap().getTile(jj+user.getLocationY(), ii+user.getLocationX())
                        .getOccupName();
             if(tempname.equals("")) continue;
             
             if(tempname.equals(user.getName()) && this.affectCaster) {
                 user.modifyHealth(-damage);
                 continue;
             }
             
             /* TODO
              * Will need to make this more compact
              */
             if(w.getPlayerGroup().containsUnit(tempname)) {
                 if(user.getTeamNumber()==w.getPlayerGroup().getTeamNumber()
                         && affectAllies==false)
                     continue;
                 w.getPlayerGroup().getUnit(tempname).modifyHealth(-damage);
                 continue;
             }
             
             if(w.getEnemyGroup().containsUnit(tempname)) {
                 if(user.getTeamNumber()==w.getEnemyGroup().getTeamNumber()
                       &&  affectAllies==false)
                     continue;
                 w.getEnemyGroup().getUnit(tempname).modifyHealth(-damage);
                 continue;
             }
             
            }
        }
        
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
    
     
    
    
    
}
