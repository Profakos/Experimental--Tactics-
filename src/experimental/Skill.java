/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Akos
 */
abstract public class Skill {
    
    private String name;
    private String image;
    private SkillProcEnum proc;
    
    private int cooldown = 3;
    private int cooldownTimeLeft = 0;
    
    private int range = 0;
    private boolean instantCast = false;
    
    
    Skill(String name)    {
        this.name = name;
        this.image = "";
        this.proc = null;
    }
    abstract void useSkill(Unit user, WorldScreen w);

    /*
     * Handles cooldown
     */
    public void cooldownReset(boolean reset) {
        
        if(reset) {
            this.cooldownTimeLeft = cooldown;
            return;
        }
        
        
        if(this.getCooldownTimeLeft()>0)
        this.cooldownTimeLeft-=1;
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
     * @return the proc
     */
    public SkillProcEnum getProc() {
        return proc;
    }

    /**
     * @param proc the proc to set
     */
    public void setProc(SkillProcEnum proc) {
        this.proc = proc;
    }

    /**
     * @return the cooldown
     */
    public int getCooldown() {
        return cooldown;
    }

    /**
     * @param cooldown the cooldown to set
     */
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    /**
     * @return the cooldownTimeLeft
     */
    public int getCooldownTimeLeft() {
        return cooldownTimeLeft;
    }

    /**
     * @param cooldownTimeLeft the cooldownTimeLeft to set
     */
    public void setCooldownTimeLeft(int cooldownTimeLeft) {
        this.cooldownTimeLeft = cooldownTimeLeft;
    }

    void drawSkillIcon(Graphics g, Viewport v, int ii) {
        
        if(this.image.equals(""))   {
            g.setColor(Color.blue);
             g.drawString(name, v.getTileSize()*ii, v.getTileSize()/4);
        }
        else
        g.drawImage(ImageRegistry.getImage(getImage()), v.getTileSize()*ii, 0, null);
        
        if(this.cooldownTimeLeft>0) {
            g.setColor(Color.blue);
            g.drawString(this.getCooldownTimeLeft()+".00", v.getTileSize()*ii, v.getTileSize()/2);
        }
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

    boolean canHit(Unit user, WorldScreen w) {
        if(this.range == -1) return true;
        
        int targ =  Math.abs(user.getLocationX()-user.getTargetingX()) + 
          Math.abs(user.getLocationY()-user.getTargetingY());
        return targ<=this.range;
      }

    /**
     * @return the instantFire
     */
    public boolean isInstantCast() {
        return instantCast;
    }

    /**
     * @param instantCast the instantFire to set
     */
    public void setInstantCast(boolean instantCast) {
        this.instantCast = instantCast;
    }
    
     
}
