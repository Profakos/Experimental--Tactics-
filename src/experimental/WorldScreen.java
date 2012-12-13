/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Akos
 */
public class WorldScreen implements Screen{
    
    private TileMap tmap;
    private UnitGroup playerCharGroup;
    private  TargetUnit target;
    private Random rgen;
    
   /*
    * Constructor
    */
    
    WorldScreen()
    {
    
    rgen = new Random();
        
    tmap = new TileMap(15,15);  
    playerCharGroup = new UnitGroup();
    
    for(int index = 0; index<playerCharGroup.getUnits().size(); index++)
    {
        tmap.getTile(playerCharGroup.getUnits().get(index).getLocationY(), 
        playerCharGroup.getUnits().get(index).getLocationX()).setOccupiedPerson(true,
        playerCharGroup.getUnits().get(index).getName());
    }
    
    target = new TargetUnit("target", 5, 5);
    tmap.getTile(5,5).setOccupiedPerson(true, "target");
    
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
        
        int dist =  getPlayerCharGroup().getCurrentSelected().getModifiedTileSpeed();
        
        for(int ii = -dist; ii<dist+1; ii++)
        {
            for(int jj = -dist; jj<dist+1; jj++)
            { 
             if(Math.abs(jj)+Math.abs(ii)>dist) continue;   
                
          g.drawImage(ImageRegistry.getImage("move_highlight"), (curx+ii-v.getOffX())*v.getTileSize(), (cury+jj-v.getOffY())*v.getTileSize(), null); 
            }
        }
        
         
        getPlayerCharGroup().draw(g, v);
        
        this.target.draw(g, v);
             
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

    
 /*
  * 
  * Handles clicking on the screen
  * 
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
          if(cy<v.getHeightInTiles()-2)
          {
          
              /*
               * This needs to go into its own method
               */ 
              if(!tmap.getTile(cy + v.getOffY(), cx + v.getOffX()).isOccupied())
              {
                playerCharGroup.moveUnit(cy + v.getOffY(), cx + v.getOffX(), this);
              }
              else
              {
                if(tmap.getTile(cy + v.getOffY(), cx + v.getOffX()).getOccupName().equals(target.getName()))
                 {
                     if(playerCharGroup.getCurrentSelected().canHit(target, this)){  target.respawn(this);}  
                 }
              }
                
              
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
        
    }

    /*
     * Updates the screen
     */
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

    /**
     * @return the rgen
     */
    public Random getRgen() {
        return rgen;
    }

    /**
     * @param rgen the rgen to set
     */
    public void setRgen(Random rgen) {
        this.rgen = rgen;
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
}

