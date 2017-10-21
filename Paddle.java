import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Paddle extends JComponent
{
    Rectangle2D.Double p; //rectangle for using intersects method 
    ImageIcon pTex; //texture of the paddle with an ImageIcon
    public Paddle(int x, int y, int width, int height)
    {
        p = new Rectangle2D.Double(x,y,width,height); //creates rectangle with explicit parameter of constructor  
        pTex = new ImageIcon("textures/paddle_texture.png"); //initialize the texture (imageicon)
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g; //graphics2d object g2 initialized as g typcasted as a graphics2d
        pTex.paintIcon(this,g2,(int)p.x,(int)p.y); //paints icon with the painticon method with the location and size of the rectangle using this component as the jcomponent and g2 as the graphics2d object 
    }

    public void setX(int x)
    {
        p.x=x-(p.width/2); //sets the x of the rectangle 
        repaint(); //runs paintcomponent 
    }
}
