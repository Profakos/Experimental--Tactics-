package experimental;


import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Akos
 */
public class ImageRegistry {
    
     static private HashMap<String, Image> imageHash;
 
    ImageRegistry(){
      
        imageHash = new HashMap<>();
        
    try
      {
    String path = "Data//Images//"; 
 
        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles(); 
 
        List<String> tlist = new ArrayList<>();
        
        for (int i = 0; i < listOfFiles.length; i++) 
        {
 
            if (listOfFiles[i].isFile()) 
            {
            files = listOfFiles[i].getName();
            if (files.endsWith(".png") || files.endsWith(".png"))
                {
            files = files.substring(0, files.length()-4);
            addImage(files);
                }
            }
        }
        
       
        
     }catch(Exception e){
         Logger.getLogger(ImageRegistry.class.getName()).log(Level.SEVERE, null, e);
        System.exit(0);
                        }
    
    
    
        }

   private void addImage(String s)
    {
    
     Image i ;   
        try {
            i = ImageIO.read(new File("Data//Images//" + s +".png"));
            getImageHash().put(s, i);
        } catch (IOException ex) {
            Logger.getLogger(ImageRegistry.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
      
    
    }

 static Image getImage(String s)
 {
     try
     {
 return imageHash.get(s);
     }catch(Exception e ){ return null;}
 }

    /**
     * @return the imageHash
     */
    public HashMap<String, Image> getImageHash() {
        return imageHash;
    }

    /**
     * @param imageHash the imageHash to set
     */
    public void setImageHash(HashMap<String, Image> imageHash) {
        ImageRegistry.imageHash = imageHash;
    }

}


