/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

/**
 *
 * @author Akos
 */
public class Viewport{
    
    private int widthInTiles = 13;
    private int heightInTiles = 13;
    private int tileSize = 50;
    
    private int offY = 0;
    private int offX = 0;
    private int curZ = 0;
    
    private int cx = 0;
    private int cy = 0;

    private int tileMaxY = 0;
    private int tileMaxX = 0;
    
    private int topMenuHeight = 0;
    private int leftMenuWidth = 0;
    private int rightMenuWidth = 0;
    private int bottomMenuHeight = 0;
    
    /*
     * Scrolling
     */
    void scroll(Directions directions) {
         switch(directions) {
        case N: this.offY = offY-1; break;
        case S: this.offY = offY+1; break;
        case E: this.offX = offX+1; break;
        case W: this.offX = offX-1; break;
        }
        if(this.offY<0)
            offY=0;
        if(this.offX<0)
            offX = 0;
     
        if(widthInTiles+offX>tileMaxX) {
            offX-=1;
        }
        if(heightInTiles-bottomMenuHeight+offY>tileMaxY) {
        offY-=1;
        }
    }
    
    /*
     * Centers the camera on these coordinates
     */
    void centerOn(int y, int x) {
      
       int centX = (int) ((this.getPfieldWidth()/2)+1); 
       int centY = (int) ((this.getPfieldHeight()/2)+1);
       
       this.offX = x-this.widthInTiles+centX;
       if(offX<0) 
           offX = 0; 
       if(offX+this.widthInTiles>this.tileMaxX) 
           offX = this.tileMaxX-this.widthInTiles; 
         
       
       this.offY = y-this.getPfieldHeight()+centY;
       if(offY<0) 
           offY = 0; 
       if(offY+this.getPfieldHeight()>this.tileMaxY) 
        offY = this.tileMaxY-this.getPfieldHeight(); 
       
    
    }
    
     /**
     * @return the Playerfield widthInTiles
     */
    public int getPfieldWidth() {
        return this.widthInTiles-this.rightMenuWidth-this.leftMenuWidth;
    }
    
     /**
     * @return the Playerfield heightInTiles
     */
    public int getPfieldHeight(){
        return this.heightInTiles-this.bottomMenuHeight-this.topMenuHeight;
    }
    
    
    /**
     * @return the widthInTiles
     */
    public int getWidthInTiles() {
        return widthInTiles;
    }

    /**
     * @param widthInTiles the widthInTiles to set
     */
    public void setWidthInTiles(int widthInTiles) {
        this.widthInTiles = widthInTiles;
    }

    /**
     * @return the heightInTiles
     */
    public int getHeightInTiles() {
        return heightInTiles;
    }

    /**
     * @param heightInTiles the heightInTiles to set
     */
    public void setHeightInTiles(int heightInTiles) {
        this.heightInTiles = heightInTiles;
    }

    /**
     * @return the tileSize
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * @param tileSize the tileSize to set
     */
    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    /**
     * @return the offY
     */
    public int getOffY() {
        return offY;
    }

    /**
     * @param offY the offY to set
     */
    public void setOffY(int offY) {
        this.offY = offY;
    }

    /**
     * @return the offX
     */
    public int getOffX() {
        return offX;
    }

    /**
     * @param offX the offX to set
     */
    public void setOffX(int offX) {
        this.offX = offX;
    }

    /**
     * @return the curZ
     */
    public int getCurZ() {
        return curZ;
    }

    /**
     * @param curZ the curZ to set
     */
    public void setCurZ(int curZ) {
        this.curZ = curZ;
    }

    /**
     * @return the cx
     */
    public int getCx() {
        return cx;
    }

    /**
     * @param cx the cx to set
     */
    public void setCx(int cx) {
        this.cx = cx;
    }

    /**
     * @return the cy
     */
    public int getCy() {
        return cy;
    }

    /**
     * @param cy the cy to set
     */
    public void setCy(int cy) {
        this.cy = cy;
    }

    /**
     * @return the tileMaxY
     */
    public int getTileMaxY() {
        return tileMaxY;
    }

    /**
     * @param tileMaxY the tileMaxY to set
     */
    public void setTileMaxY(int tileMaxY) {
        this.tileMaxY = tileMaxY;
    }

    /**
     * @return the tileMaxX
     */
    public int getTileMaxX() {
        return tileMaxX;
    }

    /**
     * @param tileMaxX the tileMaxX to set
     */
    public void setTileMaxX(int tileMaxX) {
        this.tileMaxX = tileMaxX;
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
