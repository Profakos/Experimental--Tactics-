/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

/**
 *
 * @author Akos
 */
public class SkillFactory {
    SkillDataRegistry skilldata;
    
    SkillFactory() {
      skilldata = new SkillDataRegistry();
    }
    
    /*
     * returns a new skill
     */
    static Skill makeSkill(String name) {
        /*
      String temp =  SkillDataRegistry.getSkillHash().get(name).get(0).getSetting();
      if(temp.equals("simple")) return new SimpleSkill(name);  
      if(temp.equals("composite")) return new CompositeSkill(name); 
      */
        return new SimpleSkill(name); //returns a dummy skill
    }
}
