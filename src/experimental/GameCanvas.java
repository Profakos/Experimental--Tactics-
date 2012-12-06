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
     
      
     
    };
    
    void setCurx(int i){v.setCx(i);}
    void setCury(int i){v.setCy(i);}
  
    
}
