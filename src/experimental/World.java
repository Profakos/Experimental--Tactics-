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
public class World{
    
    private TileMap tmap;
    
    TagteamUnit tagtest;
    
    World()
    {
    tmap = new TileMap(13,11); 
    tagtest = new TagteamUnit();
    }

    public void draw(Graphics g, Viewport v) {
        
        getTmap().draw(g, v);
        tagtest.draw(g, v);
    }

    void update() { 
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

    void changeTagteamTemp() {
        if(tagtest.getCurrentDominant()==0)
        {
        tagtest.setCurrentDominant(1);
        }
        else
        tagtest.setCurrentDominant(0);
    }
    
}

