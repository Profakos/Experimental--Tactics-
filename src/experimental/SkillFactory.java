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
        return new SimpleSkill(name);
    }
}
