import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

public class Ball extends JComponent implements ActionListener
{
    Ellipse2D.Double b; //ellipse for the use of the intersects method
    double sx; //slope of x plus or minus 1
    double sy; //slope of y plus or minus 1
    final static int TOP=1,BOT=2,LEFT=3,RIGHT=4;
    boolean attached; //boolean whether or not the ball is attached to the paddle
    ImageIcon ballTex; //texture of the ball same size of ellipse, drawn over it 
    ImageIcon dBallTex; //texture of the ball when dead 
    int balls; //amount of balls "lives"
    boolean infiniteBounce; //infinitebounce mode meaning it can bounce off the bottom without dying 
    public Ball(int x, int y, int width, int height, int ballsNum, boolean k)
    {
        b = new Ellipse2D.Double(x,y,width,height); //ellipse with the constructors explicit parameters 
        sx = 1; //default slope 
        sy = -1; //default slope 
        if(k)attached=false; //if infinitebounce mode then dont start attached 
        else attached = true; //if not infinitebounce mode then start attached 
        ballTex = new ImageIcon("textures/ball_texture.png"); //initialize textures 
        dBallTex = new ImageIcon("textures/dead_ball_texture.png"); //initialize textures 
        balls = ballsNum; //initialize
        infiniteBounce = k; //initialize 
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g; //graphics2d object g2 initialized as g typcasted as a graphics2d
        if(attached) 
        {
            dBallTex.paintIcon(this,g2,(int)b.x,(int)b.y); //if the ball is attached paint the texture of the dead ball 
        }
        else         ballTex.paintIcon(this,g2,(int)b.x,(int)b.y); //if ball is not attached paint normal texture 
    }

    public void actionPerformed(ActionEvent e)
    {
        if(!attached)
        {
            int newX = (int)(b.x+(2*sx)); //moves the ball 2 pixels left or right depending on the slope of x
            int newY = (int)(b.y+(2*sy)); //moves the ball 2 pixels up or down depending on the slope of y 
            if(newX < 0){SoundPlayer.playSound("sfx/hit_wall.wav");bounce(LEFT);} //if the x value of the ball is lower than zero, this means it hit the left wall, calls ball bounce(final static variable for left int), and uses static method to play sound
            else if(newY < 0){SoundPlayer.playSound("sfx/hit_wall.wav");bounce(TOP);} //same logic 
            else if(newX > GameEngine.WIDTH-35){SoundPlayer.playSound("sfx/hit_wall.wav");bounce(RIGHT);} //same logic 
            else if(newY > GameEngine.HEIGHT-60) //if ball hits the bottom  
            {
                if(infiniteBounce){SoundPlayer.playSound("sfx/hit_wall.wav");bounce(BOT);} //if infinitebounce mode, ball does not respawn but keeps bounces, same logic as above 
                else hitBottom(); //calls hitbottom method 
            }
            else moveTo(newX,newY); //if ball doesnt hit anything moves it to new position 
        }
        repaint(); //calls paintcomponent 
    }

    public void hitBottom()
    {
        if(balls>0) //if there are any balls left
        {
            balls--; //subtract from ballsnum
            if(balls>0)SoundPlayer.playSound("sfx/die.wav"); //plays death sound 
        }
        attached = true; //sets attached to true 
    }

    public void moveTo(int x, int y)
    {
        b.x = x; //changes x to desired location 
        b.y = y; //changes y to desired location 
        repaint(); //repaints 
    }

    public void bounce(int wall)
    {
        if(wall==TOP)sy=1; //changes slope of y to 1 making ball go down 
        else if(wall==BOT)sy=-1; //changes sy to -1 making ball go up 
        else if(wall==LEFT)sx=1; //changes sx to 1 making ball go right
        else if(wall==RIGHT)sx=-1; //changes sy to -1 making ball go left 
    }

    public void hitLeft()
    {
        sx=-1; //changes slopes so the ball bounces upleft 
        sy=-1;
    }

    public void hitRight()
    {
        sx=1; //changes slopes so the ball bounces upright 
        sy=-1;
    }

    public boolean ifAttached()
    {
        return attached; //checks if the ball is attached (to paddle)
    }

    public void launch()
    {
        if(balls>0) //launches if any lives left 
        {
            attached = false; //changes attached 
            sx = 0; //does not move horizontally 
            sy = -1; //goes up 
        }
    }

    public int getBallsNum()
    {
        return balls; //gets balls or lives 
    }
}
