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
public class Skill {

    private String name;
    private String image;
    
    private UnitFunctionEnum calledFunctionEnum;
    /*
     * Constructor
     */
    Skill (String name) {
      
        this.name = name;
        this.image = "";
        
        calledFunctionEnum = null;
        
        List<SkillSetting> sklist = SkillRegistry.getSkillHash().get(name);
        
        if(sklist == null || sklist.isEmpty()) return;
        
        for(int ii = 0; ii<sklist.size(); ii++)
        {
        switch(sklist.get(ii).getSettingType()){
            case callfunction : calledFunctionEnum = UnitFunctionEnum.valueOf(sklist.get(ii).getSetting());
                break;
            case image: image = sklist.get(ii).getSetting();
                break;
            default: break;
            }
        }
        
    }
    
    /*
     * Using the skill
     */
    void useSkill(Unit user, WorldScreen w) {
        
        if(getCalledFunctionEnum()!=null)
        switch(getCalledFunctionEnum())
        {
            case transformToAlternate: user.transformToAlternate(); 
                break;
            case randomPort: user.randomPort(w);
                break;
        }  
   
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
    
     
    
    
    
}
