import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;
public class Brick
{
    Rectangle2D.Double b; //rectangle to use methods from rectagularshape or something class
    int lives; //number of lives 
    public Brick(int x, int y, int width, int height)
    {
        b = new Rectangle2D.Double(x,y,width,height); //initialize with parameter above 
        lives = 4; //4(2) lives, because the program subtracts approx. 2 lives each time the ball interacts with a block 
    }
    
    public Rectangle2D.Double getRect()
    {
        return b; //gets the rectangle of the brick 
    }

    public int getLives()
    {
        return lives; //returns the amoutn of lives remaining 
    }

    public boolean hit() //when a rocket hits a block
    {
        if(lives>0) //if there are lives remaining 
        {
            lives=0; //1 shot 1 kill(brick)
            return true; //returns true 
        }
        return false; //obvious 
    }
    
    public boolean hitTop(int x, int y)
    {
        if((x>=b.x) && (x<b.x+b.width) && ((y==b.y)||(y==b.y+1)) && lives>0) //if the x given to the method is between the left and right of the brick and the y is the top of the brick 
        {
            lives--; //detects contact with a brick approximatey 2 times each bounce thats why 4 lives is equal to 2 bounces  
            return true; //returns true 
        }
        return false; //obvious 
    }

    public boolean hitBot(int x, int y)
    {
        if((x>=b.x) && (x<b.x+b.width) && ((y==b.y+b.height)||(y==b.y+b.height-1)) && lives>0) //same logic 
        {
            lives--; //same logic 
            return true; //same logic 
        }
        return false; //same logic
    }

    public boolean hitLeft(int x, int y)
    {
        if(((x==b.x)||(x==b.x+1)) && (y>b.y) && (y<b.y+b.height) && lives>0) //same logic
        {
            lives--; //same logic
            return true; //same logic
        }
        return false; //same logic
    }

    public boolean hitRight(int x, int y)
    {
        if(((x==b.x+b.width)||(x==b.x+b.width-1)) && (y>b.y) && (y<b.y+b.height) && lives>0) //same logic
        {
            lives--; //same logic
            return true; //same logic
        }
        return false; //same logic
    }
}
