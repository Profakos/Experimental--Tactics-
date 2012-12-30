/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

/**
 *
 * @author Akos
 */
abstract public class Skill {
    
    private String name;
    private String image;
    private SkillProcEnum proc;
    
    Skill(String name)    {
        this.name = name;
        this.image = "";
        this.proc = null;
    }
    abstract void useSkill(Unit user, WorldScreen w);

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
    
     
}
