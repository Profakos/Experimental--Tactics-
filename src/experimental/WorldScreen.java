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
public class WorldScreen implements Screen {
    
    private TileMap tmap;
    private UnitGroup playerGroup;
    private TargetUnit target;
    private Random rgen;
    private int bottomMenuHeight;
    
   /*
    * Constructor
    */ 
    WorldScreen() { 
        rgen = new Random(); 
        bottomMenuHeight = 2; 
        tmap = new TileMap(15,15);  
        playerGroup = new UnitGroup();
    
        for(int index = 0; index<playerGroup.getUnits().size(); index++) {
            tmap.getTile(playerGroup.getUnits().get(index).getLocationY(), 
            playerGroup.getUnits().get(index).getLocationX()).setOccupiedPerson(true,
            playerGroup.getUnits().get(index).getName());
        }
    
        target = new TargetUnit("target", 5, 5);
        tmap.getTile(5,5).setOccupiedPerson(true, "target");
    }

    /*
     * Draws the world screen
     */
    @Override
    public void draw(Graphics g, Viewport v) {
       drawPlayfield(g, v);
       drawMenu(g, v);
       drawCursor(g, v);
    }

    
 /*
  * Handles clicking on the screen
  * 
  */
    @Override
    public void screenClick(Viewport v) {
         if(v.getCx()!=-1 && v.getCy()!=-1 ) {
          try {
          int cy = v.getCy()/v.getTileSize();  
          int cx = v.getCx()/v.getTileSize();
          
          /*
           * if(clicked on playfield)
           * 
           * else clicked on menu
           */
          if(cy<v.getHeightInTiles()-v.getBottomMenuHeight()) {
             clickPlayfield(cy, cx, v);
          }
          else { 
             clickMenu(cy, cx, v);
          }
          v.centerOn(playerGroup.getCurrentSelected().getLocationY(), playerGroup.getCurrentSelected().getLocationX());
          }catch(NullPointerException e){}
       }  
        
    }
    
    /*
     * Clicked on the playingField half
     */
     private void clickPlayfield(int cy, int cx, Viewport v) {
         
          if(playerGroup.getCurrentSelected().getActionPoints()==0) 
              return;
         
          if(!tmap.getTile(cy + v.getOffY(), cx + v.getOffX()).isOccupied()) {
                playerGroup.moveUnit(cy + v.getOffY(), cx + v.getOffX(), this);
              }
          else {
                if(tmap.getTile(cy + v.getOffY(), cx + v.getOffX())
                        .getOccupName().equals(target.getName())) {
                     if(playerGroup.getCurrentSelected().getActionPoints()==0) 
                         return;
                     /*
                      * TODO: move this to an attack function
                      */
                    playerGroup.getCurrentSelected().attack(target, this);
                 }
              }
     }
    
     /*
      * Clicked on the Menu half
      */
    private void clickMenu(int cy, int cx, Viewport v) {
      if(cy==v.getHeightInTiles()-1)
       switch(cx){
                case (0): playerGroup.prevnextUnit(-1);
                break;
                case (1): playerGroup.prevnextUnit(1); 
                break;
                case (2): playerGroup.update(this); break;
                default: 
                    if(cx>=3) { 
                        playerGroup.getCurrentSelected().useSkill(cx-3, this); 
                    }
                    break;
                }
    }

    /*
     * Draws the playing field
     */
    private void drawPlayfield(Graphics g, Viewport v) {
         /*
         * draw the scenery
         */
        getTmap().draw(g, v); 
         /*
          * draw the characters
          */ 
        int curx = getPlayerGroup().getCurrentSelected().getLocationX();
        int cury = getPlayerGroup().getCurrentSelected().getLocationY();
        
         if(getPlayerGroup().getCurrentSelected().getActionPoints()!=0) {
             /*
              * TODO: Collapse these two into a single for cycle
              */
            int dist =  getPlayerGroup().getCurrentSelected().getCurrentTileSpeed();
            for(int ii = -dist; ii<dist+1; ii++) {
                for(int jj = -dist; jj<dist+1; jj++) { 
             if(Math.abs(jj)+Math.abs(ii)>dist) continue;   
              g.drawImage(ImageRegistry.getImage("move_highlight"), 
                      (curx+ii-v.getOffX())*v.getTileSize(), (cury+jj-v.getOffY())*v.getTileSize(), null); 
                    }
                }
            
            
              dist =  getPlayerGroup().getCurrentSelected().getCurrentRange();
            for(int ii = -dist; ii<dist+1; ii++) {
                for(int jj = -dist; jj<dist+1; jj++) { 
             if(Math.abs(jj)+Math.abs(ii)!=dist) continue;   
              g.drawImage(ImageRegistry.getImage("range_highlight"), 
                      (curx+ii-v.getOffX())*v.getTileSize(), (cury+jj-v.getOffY())*v.getTileSize(), null); 
                    }
                }
         }
        getPlayerGroup().draw(g, v);
        this.target.draw(g, v);
    }
    
     
            /*
            * Draws the menu
            * 
            */ 
    private void drawMenu(Graphics g, Viewport v) {
        int bh = v.getBottomMenuHeight();
        g.translate(0, v.getTileSize()*(v.getHeightInTiles()-bh));
        g.setColor(Color.gray);
        g.fillRect(0, 0, v.getTileSize()*v.getWidthInTiles(), v.getTileSize()*2);
        g.setColor(Color.black);
        g.drawRect(0, 0, v.getTileSize()*v.getWidthInTiles(), v.getTileSize()*2);
        g.drawString(this.playerGroup.getCurrentSelected().menuDisplay(), 0, 20);
        
        /*
         * Universal Icons
         */
        g.drawImage(ImageRegistry.getImage("PREVCHAR_Icon"), 0, v.getTileSize(), null);
        g.drawImage(ImageRegistry.getImage("NEXTCHAR_Icon"), v.getTileSize(), v.getTileSize(), null);
        g.drawImage(ImageRegistry.getImage("ENDTURN_Icon"), v.getTileSize()*2, v.getTileSize(), null);
        
        /*
         * Character specific skill icons
         */
        g.translate(v.getTileSize()*3, v.getTileSize());
        playerGroup.getCurrentSelected().drawSkillMenu(g, v);
        g.translate(-v.getTileSize()*3, -v.getTileSize());
        g.translate(0, -v.getTileSize()*(v.getHeightInTiles()-bh));
    }
    /*
     * Draws the cursor, tooltips and such items
     */
    private void drawCursor(Graphics g, Viewport v) {
        try {        
            /*
           * Draws the cursor if it is not offscreen
           */
            if(v.getCx()!=-1 && v.getCy()!=-1 ) {
            int cx = v.getCx()/v.getTileSize();
            int cy = v.getCy()/v.getTileSize();  
            g.translate(cx*v.getTileSize(), cy*v.getTileSize());
            g.drawImage(ImageRegistry.getImage("cursor"),0, 0, v.getTileSize(), v.getTileSize(), null);
            if(cx<v.getPfieldWidth() && cy<v.getPfieldHeight()){
            /*
            * drawing healthbar
            */
            if(tmap.getTile(cy+v.getOffY(), cx+v.getOffX()).isOccupied()==true) {
            String occupant = tmap.getTile(cy+v.getOffY(), cx+v.getOffX()).getOccupName();
            if(occupant.equals(target.getName())) 
            target.drawHealthbar(g, v);
            else {
                /*
                 * This will look better once it will be a Hash with an ID
                 */
            Unit temp = this.playerGroup.getUnitByName(occupant);
            if(temp!=null)
                temp.drawHealthbar(g, v);
            }
         }
         
         }
         
         g.translate(-cx*v.getTileSize(), -cy*v.getTileSize());
         
     }
    }catch(NullPointerException e){}
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
    public UnitGroup getPlayerGroup() {
        return playerGroup;
    }

    /**
     * @param playerCharGroup the playerCharGroup to set
     */
    public void setPlayerGroup(UnitGroup playerCharGroup) {
        this.playerGroup = playerCharGroup;
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
    
    /**
     * @return the bottomMenuHeight
     */
    public int getBottomMenuHeight() {
        return bottomMenuHeight;
    }

    /**
     * @param bottomMenuHeight the bottomMenuHeight to set
     */
    public void setBottomMenuHeight(int bottomMenuHeight) {
        this.bottomMenuHeight = bottomMenuHeight;
    }

}

