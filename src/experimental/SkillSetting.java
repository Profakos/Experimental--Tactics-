/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

/**
 *
 * @author Akos
 */
public class SkillSetting {
    private SkillSettingEnum settingType;
    private String setting;
    
    /** 
     * Constructor
     */
    SkillSetting(SkillSettingEnum settingType, String setting) {
        this.settingType = settingType;
        this.setting = setting;
    }

    /**
     * @return the settingType
     */
    public SkillSettingEnum getSettingType() {
        return settingType;
    }

    /**
     * @param settingType the settingType to set
     */
    public void setSettingType(SkillSettingEnum settingType) {
        this.settingType = settingType;
    }

    /**
     * @return the setting
     */
    public String getSetting() {
        return setting;
    }

    /**
     * @param setting the setting to set
     */
    public void setSetting(String setting) {
        this.setting = setting;
    }
}
