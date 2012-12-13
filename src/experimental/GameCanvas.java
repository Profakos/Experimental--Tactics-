/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Akos
 */
public class GameCanvas extends Canvas{
   
    private Viewport v;
    private WorldScreen world;
  
    
     
    GameCanvas(WorldScreen world)
      {
      this.world = world;
      this.setBackground(Color.black); 
      
      this.v = new Viewport();
      v.setTileMaxX(world.getTmap().getWidthInTiles());
      v.setTileMaxY(world.getTmap().getHeightInTiles());
      
      }
    
    
     @Override
    public void update(Graphics g)
    { 
       Graphics offgc;
	Image offscreen;
	  
	offscreen = createImage(this.getWidth(), this.getHeight());
	offgc = offscreen.getGraphics(); 
	paint(offgc); 
	g.drawImage(offscreen, 0, 0, this);    
             
  //  paint(g);
    g.dispose();
    offgc.dispose();
    };
    
    @Override
    public void paint(Graphics g)
    {
     g.clearRect(0, 0, this.getWidth(), this.getHeight());
   
     /*
      * Draws the world
      */
        getWorld().draw(g, getV());
     
      
     
    };
    
    void setCurx(int i){getV().setCx(i);}
    void setCury(int i){getV().setCy(i);}

    /**
     * @return the v
     */
    public Viewport getV() {
        return v;
    }

    /**
     * @param v the v to set
     */
    public void setV(Viewport v) {
        this.v = v;
    }

    /**
     * @return the world
     */
    public WorldScreen getWorld() {
        return world;
    }

    /**
     * @param world the world to set
     */
    public void setWorld(WorldScreen world) {
        this.world = world;
    }
  
    
}
