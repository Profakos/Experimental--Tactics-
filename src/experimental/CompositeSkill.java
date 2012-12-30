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
    }
    
    @Override
    public void useSkill(Unit user, WorldScreen w) {
        
        if(componentSkills.isEmpty()) return;
        
        for(int ii = 0; ii<componentSkills.size(); ii++)    {
            componentSkills.get(ii).useSkill(user, w);
        }
    }



}
