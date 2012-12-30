/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

/**
 *
 * @author Akos
 */
public class SimpleUnit extends Unit{
    
    SimpleUnit(String name, int ly, int lx, int teamNumber) {
    super(name, ly, lx, teamNumber);
    
    Skill temp = SkillFactory.makeSkill("RandomPort");
    this.getSkillList().add(temp); 
    Skill temp1 = SkillFactory.makeSkill("Heal");
    this.getSkillList().add(temp1); 
    Skill temp2 = SkillFactory.makeSkill("Explode");
    this.getSkillList().add(temp2);  
    }
}
