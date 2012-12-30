/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;
 
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Akos
 */
public class SkillDataRegistry {
    static private HashMap<String, List<SkillSetting>> skillHash;

    /**
     * @return the skillHash
     */
    public static HashMap<String, List<SkillSetting>> getSkillHash() {
        return skillHash;
    }

    /**
     * @param aSkillHash the skillHash to set
     */
    public static void setSkillHash(HashMap<String, List<SkillSetting>> aSkillHash) {
        skillHash = aSkillHash;
    }
    
    /*
     * Constructor
     */
    SkillDataRegistry() {
        
    skillHash = new HashMap();
    
    
    try{
            try (FileInputStream fstream = new FileInputStream("Data\\Skill\\Skills.txt")) {
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                  
                  
                  String sLine;
                  String[] splitter;
                  String tempname = "";
                  
                    while( (sLine = br.readLine()) !=null )
                  {
                     splitter = sLine.split("\t");
                     if(splitter[0].equals("NEWSKILL")) {
                         tempname = splitter[1];
                      skillHash.put(tempname, new ArrayList<SkillSetting>());
                     }
                     else
                      skillHash.get(tempname).add(new SkillSetting(SkillSettingEnum.valueOf(splitter[0]), splitter[1]));
                  }
            }
          
     }catch(Exception e){System.out.println("Error while skill loading"); System.exit(0);
        }
     
    }
}
