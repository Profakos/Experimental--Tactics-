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
public class TagComponentUnit extends Unit {
 
    
   
    /*
     * Constructor
     */
    TagComponentUnit(String name, boolean ranged, int teamNumber) {
       super(name, -1, -1, teamNumber);
       
       this.setBonusTileSpeed(ranged ? -2 : 0);
       this.setBonusRange(ranged ? 8 : 0);
       
       if(name.equals("blood")) {
       this.getSkillList().add(SkillFactory.makeSkill("Heal"));
       this.getSkillList().add(SkillFactory.makeSkill("Ignition")); 
       }
       if(name.equals("ghost")) {
       this.getSkillList().add(SkillFactory.makeSkill("Revenge")); 
       this.getSkillList().add(SkillFactory.makeSkill("Recall")); 
       }
       
    }
    
    /*
     * Draws this unit 
     */
    void draw(Graphics g, Viewport v, int state) {
        g.drawImage(ImageRegistry.getImage(getName() + "_Char"), 0, 0, 
        v.getTileSize(), v.getTileSize(), state*v.getTileSize(), 0, (state+1)*v.getTileSize(), 50, null);
    }
    
    
 
 
}
