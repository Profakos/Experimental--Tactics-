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
}
