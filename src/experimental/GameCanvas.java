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
   
    Viewport v;
    World world;
    
    int curx = 0;
    int cury = 0;
    
     
    GameCanvas(World world)
      {
      this.world = world;
      this.setBackground(Color.black); 
      this.v = new Viewport();
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
     world.draw(g, v);
     
     
       
          /*
           * Draws the cursor
           */
     if(curx==-1 || cury==-1) return;
     
           try{
          int cx = curx/v.getTileSize();
          int cy = cury/v.getTileSize();  
        
          
          g.translate(cx*v.getTileSize(), cy*v.getTileSize());
           g.drawImage(ImageRegistry.getImage("cursor"),0, 0, v.getTileSize(), v.getTileSize(), this);
          g.translate(-cx*v.getTileSize(), -cy*v.getTileSize());
   
      }catch(NullPointerException e){}
     
     
    };
    
    void setCurx(int i){curx = i;}
    void setCury(int i){cury = i;}
  
    
}
