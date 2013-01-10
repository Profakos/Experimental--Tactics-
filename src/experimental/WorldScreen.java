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
    private UnitGroup enemyGroup;
    private UnitGroup playerGroup;
    private Random rgen;
    private int bottomMenuHeight = 0;
    private int topMenuHeight = 0;
    private int leftMenuWidth = 0;
    private int rightMenuWidth = 0;
    
    private int currentGroup = 0;
   /*
    * Constructor
    */ 
    WorldScreen() { 
        rgen = new Random(); 
        bottomMenuHeight = 2; 
        tmap = new TileMap(15,15);  
        playerGroup = new UnitGroup(0);
        enemyGroup = new UnitGroup(1);
        
        
    
        /*
         * duplicate code, should be collapsed
         */
        for(Unit u: playerGroup.getUnits().values()) {
            tmap.getTile(u.getLocationY(), 
            u.getLocationX()).setOccupiedPerson(true,
            u.getName());
        }
        
         for(Unit u: enemyGroup.getUnits().values()) {
            tmap.getTile(u.getLocationY(), 
            u.getLocationX()).setOccupiedPerson(true,
            u.getName());
        }
    
        
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
    public void screenClick(Viewport v, int button) {
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
             clickPlayfield(cy, cx, v, button);
          }
          else { 
             clickMenu(cy, cx, v, button);
          }
          v.centerOn(playerGroup.getCurrentSelected().getLocationY(), playerGroup.getCurrentSelected().getLocationX());
          }catch(NullPointerException e){}
       }  
        
    }
     
    /*
     * Clicked on the playingField half
     */
     private void clickPlayfield(int cy, int cx, Viewport v, int button) {
        
         if(playerGroup.getCurrentSelected().getActionPoints()==0) 
              return;
          
         if(button==3) {
             int usingSkill = playerGroup.getCurrentSelected().getUsingSkill();
             
             if(usingSkill==-1) return; 
             
             playerGroup.getCurrentSelected().setTargetingY(cy + v.getOffY());
             playerGroup.getCurrentSelected().setTargetingX(cx + v.getOffX());
             
             if(!playerGroup.getCurrentSelected().getSkill(usingSkill)
                     .canHit(playerGroup.getCurrentSelected(), this)) return;
             
             playerGroup.getCurrentSelected().useSkill(playerGroup.getCurrentSelected().getUsingSkill(), this); 
             return;
         }
         
         playerGroup.getCurrentSelected().setUsingSkill(-1);
         
         
         
          if(!tmap.getTile(cy + v.getOffY(), cx + v.getOffX()).isOccupied()) {
                playerGroup.moveUnit(cy + v.getOffY(), cx + v.getOffX(), this);
              }
          else {
              String tname = tmap.getTile(cy + v.getOffY(), cx + v.getOffX())
                        .getOccupName();
                if(enemyGroup.containsUnit(tname)){
                     if(playerGroup.getCurrentSelected().getActionPoints()==0) 
                         return;
                     
                    playerGroup.getCurrentSelected().attack(enemyGroup.getUnit(tname), this);
                 }
              }
     }
    
     /*
      * Clicked on the Menu
      */
    private void clickMenu(int cy, int cx, Viewport v, int button) {
      if(cy==v.getHeightInTiles()-1)
       switch(cx){
                case (0): playerGroup.prevnextUnit(-1);
                break;
                case (1): playerGroup.prevnextUnit(1); 
                break;
                case (2): currentGroup = 1;
                    enemyGroup.update(this); 
                    break;
                default: 
                    if(cx>=3) { 
                        if(playerGroup.getCurrentSelected().getSkill(cx-3).isInstantCast())
                            playerGroup.getCurrentSelected().useSkill(cx-3, this);
                        playerGroup.getCurrentSelected().setUsingSkill(cx-3); 
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
              * TODO: Collapse these two into a single for cycle, possibly
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
        getEnemyGroup().draw(g, v);
        
        if(currentGroup==0)
        g.drawImage(ImageRegistry.getImage("selectedChar"), 
                 (getPlayerGroup().getCurrentSelected().getLocationX()-v.getOffX())*v.getTileSize(),
                 (getPlayerGroup().getCurrentSelected().getLocationY()-v.getOffY())*v.getTileSize(), null);
        else
        g.drawImage(ImageRegistry.getImage("selectedChar"), 
                 (getEnemyGroup().getCurrentSelected().getLocationX()-v.getOffX())*v.getTileSize(),
                 (getEnemyGroup().getCurrentSelected().getLocationY()-v.getOffY())*v.getTileSize(), null);
            
         
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
        
        
        /*
         * draw the menu only during a player turn
         */
        if(this.currentGroup==0) {
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
        }
        else {
            g.drawString("ENEMY ACTIVITY", v.getTileSize(), v.getTileSize()/2);
        }
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
            
            
            
            if(playerGroup.containsUnit(occupant)) 
                        playerGroup.getUnit(occupant).drawHealthbar(g, v); 
            else {
            if(enemyGroup.containsUnit(occupant)) 
                        enemyGroup.getUnit(occupant).drawHealthbar(g, v); 
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
       if(currentGroup==0) return;
       
       if(enemyGroup.getCurrentSelected().getActionPoints()!=0) {
          
           enemyGroup.getCurrentSelected()
                   .think(this);
       }  
       else {
       enemyGroup.prevnextUnit(1);
       
       if(enemyGroup.getCurrent()==0) {
             currentGroup=0;
              playerGroup.update(this); 
       } 
       }
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

 

    /**
     * @return the enemyGroup
     */
    public UnitGroup getEnemyGroup() {
        return enemyGroup;
    }

    /**
     * @param enemyGroup the enemyGroup to set
     */
    public void setEnemyGroup(UnitGroup enemyGroup) {
        this.enemyGroup = enemyGroup;
    }

    /**
     * @return the currentGroup
     */
    public int getCurrentGroup() {
        return currentGroup;
    }

    /**
     * @param currentGroup the currentGroup to set
     */
    public void setCurrentGroup(int currentGroup) {
        this.currentGroup = currentGroup;
    }

    /**
     * @return the topMenuHeight
     */
    public int getTopMenuHeight() {
        return topMenuHeight;
    }

    /**
     * @param topMenuHeight the topMenuHeight to set
     */
    public void setTopMenuHeight(int topMenuHeight) {
        this.topMenuHeight = topMenuHeight;
    }

    /**
     * @return the leftMenuWidth
     */
    public int getLeftMenuWidth() {
        return leftMenuWidth;
    }

    /**
     * @param leftMenuWidth the leftMenuWidth to set
     */
    public void setLeftMenuWidth(int leftMenuWidth) {
        this.leftMenuWidth = leftMenuWidth;
    }

    /**
     * @return the rightMenuWidth
     */
    public int getRightMenuWidth() {
        return rightMenuWidth;
    }

    /**
     * @param rightMenuWidth the rightMenuWidth to set
     */
    public void setRightMenuWidth(int rightMenuWidth) {
        this.rightMenuWidth = rightMenuWidth;
    }

}

