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
    
    SimpleUnit(String name, int ly, int lx) {
    super(name, ly, lx);
    
    this.getSkillList().add(new Skill("RandomPort")); 
    }
}
