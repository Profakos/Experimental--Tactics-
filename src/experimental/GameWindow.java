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
    public WorldScreen getWorld() {
        return world;
    }

    /**
     * @param world the world to set
     */
    public void setWorld(WorldScreen world) {
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

    /**
     * @return the skillFactory
     */
    public SkillFactory getSkillFactory() {
        return skillFactory;
    }

    /**
     * @param skillFactory the skillFactory to set
     */
    public void setSkillFactory(SkillFactory skillFactory) {
        this.skillFactory = skillFactory;
    }

    /**
     * @return the timer2
     */
    public Timer getTimer2() {
        return timer2;
    }

    /**
     * @param timer2 the timer2 to set
     */
    public void setTimer2(Timer timer2) {
        this.timer2 = timer2;
    }

 
    
    /*
     * WindowAdapter inner class
     */
    class MyWindowAdapter extends WindowAdapter{
     
        @Override
           public void windowClosing(WindowEvent e) { 
           getTimer1().stop();
           getTimer2().stop();
           Window ee = e.getWindow(); 
           ee.dispose();
         }
    }
    
    /*
     * MouseAdapter innerclass
     */
     class MyMouseListener extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e) {
            
              if(getCanvas().getMousePosition()!=null) {
            getCanvas().setCurx(getCanvas().getMousePosition().x);
            getCanvas().setCury(getCanvas().getMousePosition().y);
            } 
              else {
                getCanvas().setCurx(-1);
                getCanvas().setCury(-1);
                    }
            world.screenClick(canvas.getV(), e.getButton());
           
            }        
    }
   
     /*
      * KeyAdapter innerclass
      */
    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP: canvas.getV().scroll(Directions.N); break;
                case KeyEvent.VK_S:    
                case KeyEvent.VK_DOWN: canvas.getV().scroll(Directions.S);  break;
                case KeyEvent.VK_A:    
                case KeyEvent.VK_LEFT: canvas.getV().scroll(Directions.W); break;
                case KeyEvent.VK_D:    
                case KeyEvent.VK_RIGHT: canvas.getV().scroll(Directions.E); break;
                default: break;
            }
            canvas.repaint();
           // throw new UnsupportedOperationException("Not supported yet.");
        } 
            
    }
     
     /*
      * ActionListener innerclasses for the timers
      */
         class DisplayTimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
             updateDisplay();
        }
    }
         
       class UpdateTimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
             updateModel();
        }
    }
    
    
    Panel gamepanel;
    
    private ImageRegistry images;
    private SkillFactory skillFactory;
    
    private GameCanvas canvas;
    private WorldScreen world;
    
    private Timer timer1;
    private Timer timer2;
    
    /*
     * Constructor
     */
    GameWindow() {
        super("Experimental");
        
        init();
        
        this.addWindowListener(new MyWindowAdapter());
         
        gamepanel = new Panel();
        gamepanel.setFocusable(false);
            
        canvas = new GameCanvas(world);
        canvas.setSize(650, 650);
        canvas.setFocusable(true);
            
        gamepanel.add(canvas);
            
        this.add(gamepanel);
            
        canvas.addMouseListener(new MyMouseListener());
        canvas.addKeyListener(new MyKeyListener());
            
        this.setBounds(100, 20, 650, 650);
            
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
            
        run();
    
    }
    
    /*
     * Initializes the world
     */
    private void init() { 
        
        setImages(new ImageRegistry());
        setSkillFactory(new SkillFactory());
        
        setWorld(new WorldScreen());
        
        setTimer1(new Timer(100, new DisplayTimerListener()));  
        setTimer2(new Timer(500, new UpdateTimerListener()));  
    }
    
    /*
     * The game begins
     */
    private void run(){ 
        getTimer1().start();
        getTimer2().start();
    }
    
    
    /*
     * Updates the world, redraws the screen
     */
    void updateDisplay() {
         
     if(getCanvas().getMousePosition()!=null) {
            getCanvas().setCurx(getCanvas().getMousePosition().x);
            getCanvas().setCury(getCanvas().getMousePosition().y);
        } 
        else {
            getCanvas().setCurx(-1);
            getCanvas().setCury(-1);
       }
     
      getCanvas().repaint();
    }
    
    void updateModel() {
        this.getWorld().update();
    }
    
}
