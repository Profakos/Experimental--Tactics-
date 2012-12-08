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
    
    
    private UnitGroup playerCharGroup;
    
   
    
    WorldScreen()
    {
    tmap = new TileMap(13,11);  
    playerCharGroup = new UnitGroup();
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
        
        int curx = getPlayerCharGroup().getCurrentSelected().getLocationX();
        int cury = getPlayerCharGroup().getCurrentSelected().getLocationY();
        
        for(int ii = -3; ii<4; ii++)
        {
            for(int jj = -3; jj<4; jj++)
            { 
             if(Math.abs(jj)+Math.abs(ii)>=6) continue;   
                
             if(curx+ii>=0 && cury+jj>=0 && curx+ii<v.getWidthInTiles() && cury+jj<v.getHeightInTiles()-2)
        g.drawImage(ImageRegistry.getImage("move_highlight"), (curx+ii)*v.getTileSize(), (cury+jj)*v.getTileSize(), null); 
            }
        }
        
         
        getPlayerCharGroup().draw(g, v);
             
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
          int cy = v.getCy()/v.getTileSize();  
          int cx = v.getCx()/v.getTileSize();
          
          /*
           * if(clickedonmap)
           * 
           * else clicked on menu
           */
          if(cy<tmap.getHeightInTiles())
          {
          
              /*
               * This needs to go into its own method
               */
              
                playerCharGroup.moveUnit(cy + v.getOffY(), cx + v.getOffX());
               
                
              
          }
          else
          { 
              /*
               * Menuhandling should go to its own method
               */
            switch(cx){
                case (0): playerCharGroup.prevnextUnit(-1); break;
                case (1): playerCharGroup.prevnextUnit(1); break;
                case (2): playerCharGroup.getCurrentSelected().transformToAlternate(); break;
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

    /**
     * @return the playerCharGroup
     */
    public UnitGroup getPlayerCharGroup() {
        return playerCharGroup;
    }

    /**
     * @param playerCharGroup the playerCharGroup to set
     */
    public void setPlayerCharGroup(UnitGroup playerCharGroup) {
        this.playerCharGroup = playerCharGroup;
    }
    
    
}

