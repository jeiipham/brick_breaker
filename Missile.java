import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

public class Missile
{
    Rectangle2D.Double m; //rectangle in position of missile 
    boolean launched; //represents whether or not the missile is "launched"
    boolean exp; //represents whether or not the misssile has exploded

    public Missile()
    {
        m = new Rectangle2D.Double(1000,1000,14,30); //initialize out of the screen (so no1 can see)
        launched = false; //not launched 
        exp = false; //not exploded

    }
    
    public Rectangle2D.Double getRect()
    {
        return m; //returns the rectangle of the missile 
    }
    
    public boolean getLaunched()
    {
        return launched; //returns whether launched or not 
    }

    public boolean getExp()
    {
        return exp; //returns whether exploded or not 
    }

    public void load(int x, int y)
    {
        m.x=x; //sets x to parameter
        m.y=y; //sets y to parameter 
        launched = true; //launched to true 
    }

    public void travel()
    {
        m.y=m.y-5; //moves up 5 pixels every time this method called 
    }
    
    public void explode()
    {
        exp = true; //obvious 
    }
}
