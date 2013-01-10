/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akos
 */
public class CompositeSkill extends Skill{

    private String name;
    private String image;
    private SkillProcEnum proc;
    
    private List<SimpleSkill> componentSkills;
    
    CompositeSkill(String name)    {
       super(name);
       componentSkills = new ArrayList<>();
       
        List<SkillSetting> sklist = SkillDataRegistry.getSkillHash().get(name);
        if(sklist == null || sklist.isEmpty()) return;
        
        for(int ii = 0; ii<sklist.size(); ii++)
        {
        switch(sklist.get(ii).getSettingType()){
            case image: setImage(sklist.get(ii).getSetting());
                break;
            case proc: setProc(SkillProcEnum.valueOf(sklist.get(ii).getSetting()));
                break;
            case componentSkill: componentSkills.add(new SimpleSkill(sklist.get(ii).getSetting()));
                break;
            case cooldown: this.setCooldown(Integer.valueOf(sklist.get(ii).getSetting()));
                break;
            case range: this.setRange(Integer.valueOf(sklist.get(ii).getSetting()));
                break;
            case instantCast: this.setInstantCast(Boolean.valueOf(sklist.get(ii).getSetting()));
            break;
            default: break;
            }
        }
        
        this.cooldownReset(true);
    }
    
    @Override
    public void useSkill(Unit user, WorldScreen w) {
        
        if(componentSkills.isEmpty()) return;
        
        for(int ii = 0; ii<componentSkills.size(); ii++)    {
            componentSkills.get(ii).useSkill(user, w);
        }
        
        this.cooldownReset(true);
    }



}
