/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akos
 */
public class TileMap{

    private int widthInTiles;
    private int heightInTiles; 
    
    private List<Tile> tiles;
    
    TileMap(int widthInTiles, int heightInTiles)
    {
    this.widthInTiles = widthInTiles;
    this.heightInTiles = heightInTiles;
    
    
    tiles = new ArrayList<>();
    
    for(int i = 0; i<widthInTiles*heightInTiles; i++)
        tiles.add(new Tile(0));
    
    }
    
    
    public Tile getTile(int y, int x)
    {
        try
        {
    return getTiles().get(x+this.getWidthInTiles()*y);
        }
        catch(Exception e){  
            System.out.println("Y" + y + ",X" + x+") tile returned with a null in TileMap.getTiles");
            return null;
        }
        
    }
    
    
    public void draw(Graphics g, Viewport v) {
       
          /*
           * Draws a viewport sized chunk of tiles
           */ 
          
        for(int i = 0; i<Math.min(getHeightInTiles(), v.getHeightInTiles()-v.getBottomMenuHeight()); i++)
        {
            for(int j=0; j<Math.min(getWidthInTiles(), v.getWidthInTiles()); j++)
            { 
             
                
              g.translate(j*50, i*50);  
              try{
              getTile(i+v.getOffY(), j+v.getOffX()).draw(g, v.getTileSize());   
              }catch(Exception e){  }
              g.translate(j*-50, i*-50); 
            } 
             
        }
       
        
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
     * @return the tiles
     */
    public List<Tile> getTiles() {
        return tiles;
    }

    /**
     * @param tiles the tiles to set
     */
    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }
    
    
}