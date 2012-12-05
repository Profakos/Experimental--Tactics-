/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.Window;
import java.awt.event.*;
import javax.swing.Timer;

/**
 *
 * @author Akos
 */
class GameWindow extends Frame {

    /**
     * @return the images
     */
    public ImageRegistry getImages() {
        return images;
    }

    /**
     * @param images the images to set
     */
    public void setImages(ImageRegistry images) {
        this.images = images;
    }

    /**
     * @return the canvas
     */
    public GameCanvas getCanvas() {
        return canvas;
    }

    /**
     * @param canvas the canvas to set
     */
    public void setCanvas(GameCanvas canvas) {
        this.canvas = canvas;
    }

    /**
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    /**
     * @param world the world to set
     */
    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * @return the timer1
     */
    public Timer getTimer1() {
        return timer1;
    }

    /**
     * @param timer1 the timer1 to set
     */
    public void setTimer1(Timer timer1) {
        this.timer1 = timer1;
    }
    
   
    class MainWindowAdapter extends WindowAdapter
    {
     
        @Override
           public void windowClosing(WindowEvent e) { 
            getTimer1().stop();
         Window ee = e.getWindow(); 
         ee.dispose();
         }
        
    
    }
    
     class MyMouseListener extends MouseAdapter
    {

        @Override
        public void mousePressed(MouseEvent e) {
            world.changeTagteamTemp();
           
            } 
              
    }
   
         class TimerListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
             update();
        }
    
    
    }
    
    
    Panel gamepanel;
    
    private ImageRegistry images;
    
    private GameCanvas canvas;
    private World world;
    
    private Timer timer1;
    
    GameWindow()
    {
        super("Experimental");
        
         init();
        
         this.addWindowListener(new MainWindowAdapter());
         
            gamepanel = new Panel();
            
            canvas = new GameCanvas(world);
            canvas.setSize(650, 650);
        
            gamepanel.add(canvas);
            
            this.add(gamepanel);
            
            canvas.addMouseListener(new MyMouseListener());
            
            this.setBounds(100, 20, 650, 650);
            
            this.setResizable(false);
            this.setVisible(true);
            this.pack();
            
            timer1.start();
    
    }
    
    private void init()
    { 
        setWorld(new World());
        setImages(new ImageRegistry());
        setTimer1(new Timer(100, new TimerListener()));  
    }
    
    void run(){ getTimer1().start();}
    
    void update()
    {
        getWorld().update();
        
        
     if(getCanvas().getMousePosition()!=null)
        {
            getCanvas().setCurx(getCanvas().getMousePosition().x);
            getCanvas().setCury(getCanvas().getMousePosition().y);
        } 
     else
     {
            getCanvas().setCurx(-1);
            getCanvas().setCury(-1);
     }
     
        getCanvas().repaint();
    }
    ;
}
