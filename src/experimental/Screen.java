/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental;

import java.awt.Graphics;

/**
 *
 * @author Akos
 */
public interface Screen {
    public void draw(Graphics g, Viewport v);
    
    public void update();
    
    public void screenClick(Viewport v, int button);
}
