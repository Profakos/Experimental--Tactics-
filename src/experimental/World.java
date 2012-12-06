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
public class World{
    
    private TileMap tmap;
    
    TagteamUnit tagtest;
    
    World()
    {
    tmap = new TileMap(13,11); 
    tagtest = new TagteamUnit();
    }

    /*
     * Draw the world
     */
    public void draw(Graphics g, Viewport v) {
        
        
        /*
         * draw the scenery
         */
         getTmap().draw(g, v);
         
         /*
          * draw the characters
          */
         tagtest.draw(g, v);
        
        
        
          /*
           * Draws the cursor if it is not offscreen
           */
     if(v.getCx()!=-1 && v.getCy()!=-1 )  
     {
           try{
          int cx = v.getCx()/v.getTileSize();
          int cy = v.getCy()/v.getTileSize();  
          
         g.translate(cx*v.getTileSize(), cy*v.getTileSize());
         g.drawImage(ImageRegistry.getImage("cursor"),0, 0, v.getTileSize(), v.getTileSize(), null);
         g.translate(-cx*v.getTileSize(), -cy*v.getTileSize());
         
            }catch(NullPointerException e){}
     }  
      
    
    
    }

    void update() { 
    }

    /**
     * @return the tmap
     */
    public TileMap getTmap() {
        return tmap;
    }

    /**
     * @param tmap the tmap to set
     */
    public void setTmap(TileMap tmap) {
        this.tmap = tmap;
    }

    void changeTagteamTemp() {
        if(tagtest.getCurrentDominant()==0)
        {
        tagtest.setCurrentDominant(1);
        }
        else
        tagtest.setCurrentDominant(0);
    }
    
}

