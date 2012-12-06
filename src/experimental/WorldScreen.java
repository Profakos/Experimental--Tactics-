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
public class WorldScreen implements Screen{
    
    private TileMap tmap;
    
    TagteamUnit tagtest;
    
    WorldScreen()
    {
    tmap = new TileMap(13,11); 
    tagtest = new TagteamUnit();
    }

    /*
     * Draw the world
     */
    @Override
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
            * Draws the menu
            * 
            */ 
        g.setColor(Color.gray);
        g.fillRect(0, v.getTileSize()*(v.getHeightInTiles()-2), v.getTileSize()*v.getWidthInTiles(), v.getTileSize()*2);
        g.setColor(Color.black);
        g.drawRect(0, v.getTileSize()*(v.getHeightInTiles()-2), v.getTileSize()*v.getWidthInTiles(), v.getTileSize()*2);
           
        /*
         * Draws the menu icons
         */
        
        g.drawImage(ImageRegistry.getImage("PREVCHAR_Icon"), 0, v.getTileSize()*(v.getHeightInTiles()-2), null);
        g.drawImage(ImageRegistry.getImage("NEXTCHAR_Icon"), v.getTileSize(), v.getTileSize()*(v.getHeightInTiles()-2), null);
        g.drawImage(ImageRegistry.getImage("CHANGE_Icon"), v.getTileSize()*2, v.getTileSize()*(v.getHeightInTiles()-2), null);
    
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

 /*
  * Handles clicking on the screen
  */

    @Override
    public void screenClick(Viewport v) {
          
         if(v.getCx()!=-1 && v.getCy()!=-1 )  
     {
           try{
          int cx = v.getCx()/v.getTileSize();
          int cy = v.getCy()/v.getTileSize(); 
          
          if(cy>=tmap.getHeightInTiles())
          { 
            switch(cx){
                case (2): tagtest.transformToAlternate(); break;
                default: break;
            } 
          }
              }catch(NullPointerException e){}
         
     }  
       // changeTagteamTemp();
    }

    @Override
    public void update() {
      //  throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}

