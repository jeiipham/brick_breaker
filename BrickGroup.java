import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;

public class BrickGroup extends JComponent
{
    final int FW = 600; //width of playing field 
    final int FH = 600; //height of playing field 
    Brick[][] brickArray; //array of bricks 
    int cols; //amount of columns 
    int rows; //amount of rows

    public BrickGroup(int cs, int rs)
    {
        cols = cs; //same 
        rows = rs; //value from above parameter 
        brickArray = new Brick[cols][rows]; //array with above dimensions 
        int wD = (FW)/cols; //width of playing field divided by the columns
        int hD = ((FH)/rows)/2; //height of playing field divided by rows 
        for(int c = 0;c<cols;c++) //nested for loops goes through all bricks in 2d array 
        {
            for(int r = 0;r<rows;r++)
            {
                brickArray[c][r] = new Brick(c*wD,r*hD,wD-10,hD-10); //sets x according to the wd(width distance) times number column and y according to hd(height distance) times number row 
            }
        }
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g; //g2 for drawing stuff 
        for(int c = 0;c<cols;c++) //nested for loops goes through all bricks in 2d array 
        {
            for(int r = 0;r<rows;r++)
            {
                boolean alive = brickArray[c][r].getLives()>0; //if the brick is has lives alive is true 
                if(alive)
                {
                    if(brickArray[c][r].getLives()==4)g2.setColor(Color.CYAN); //if has 4(2 hits) lives, color is cyan 
                    else if(brickArray[c][r].getLives()<4)g2.setColor(Color.GRAY); //if has less than 4(2 hits) lives color is gray 
                    g2.fill(brickArray[c][r].getRect()); //fills rectangle of brick accordingly 
                }
            }
        }
    }

    public boolean checkIntersect(Rectangle2D.Double rect)
    {
        for(int c = 0;c<cols;c++) //nested for loops goes through all bricks in 2d array 
        {
            for(int r = 0;r<rows;r++)
            {
                if(brickArray[c][r].getRect().intersects(rect))return brickArray[c][r].hit(); //checks if any bricks intersect the rectangle given, if does, uses hit method and will return true if there are any lives remaining 
            }
        }
        return false; //returns false if nothing intersects
    }

    public int checkBricks(int x, int y) //ball coordinates 
    {
        for(int c = 0;c<cols;c++) //nested for loops goes through all bricks in 2d array 
        {
            for(int r = 0;r<rows;r++)
            {
                if(brickArray[c][r].hitTop(x,y))return 1; //checks if point touches brick on the top
                else if(brickArray[c][r].hitBot(x,y))return 2; //same thing
                else if(brickArray[c][r].hitLeft(x,y))return 3; //same thing
                else if(brickArray[c][r].hitRight(x,y))return 4; //same thing 
            }
        }
        return 0; //if no contact with any bricks returns zero 
    }

    public boolean ifBricksLeft()
    {
        for(int c = 0;c<cols;c++) //nested for loops goes through all bricks in 2d array 
        {
            for(int r = 0;r<rows;r++)
            {
                if(brickArray[c][r].getLives()>0)return true; //returns true of there are any bricks with lives left 
            }
        }
        return false; //if could not find any lives on bricks returns false 
    }
}
