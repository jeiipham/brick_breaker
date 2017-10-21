import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

public class Barrage extends JComponent implements ActionListener
{
    Missile[] mArray; //array of missiles 
    int amt; //amount of missiles 
    int mNum; //number missile currently loaded and ready to shoot 
    ImageIcon missileTex;
    public Barrage(int num)
    {
        mArray = new Missile[num+1]; //num is amount of missiles, makes an array with that amount + 1
        amt = num; //initialize  
        mNum = 1; //starts at missile #1
        missileTex = new ImageIcon("textures/missile_texture.png"); //texture of missile drawn by me in paint, same size and location of rectangle 
        for(int i = 1; i<=amt;i++)
        {
            mArray[i] = new Missile(); //initializes all missiles 
        }
    }

    public Rectangle2D.Double getRectOf(int num)
    {
        return mArray[num].getRect(); //returns rectangle of specified missile 
    }
    
    public boolean getExpOf(int num)
    {
        return mArray[num].getExp();
    }
    
    public void explode(int num)
    {
        SoundPlayer.playSound("sfx/explode.wav"); //plays explosion sound 
        mArray[num].explode(); //uses explode method 
    }

    public int getAmt()
    {
        return amt; //gets total starting amount of missiles 
    }

    public int getNumLeft()
    {
        return (amt-mNum)+1; //gets the number of missiles left by taking the total amount, minus the number currently on +1 because the array index
    }

    public int getXOf(int num)
    {
        return (int)mArray[num].getRect().x; //gets the x of (num) missile 
    }

    public int getYOf(int num)
    {
        return (int)mArray[num].getRect().y; //gets the y of (num) missile 
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g; //g2 for drawing stuff 
        for(int i = 1; i<=amt; i++)
        {
            if(mArray[i].getLaunched() && !mArray[i].getExp())
            {
                missileTex.paintIcon(this,g2,(int)mArray[i].m.x,(int)mArray[i].m.y); //paints icon over rectangle position 
            }
        }
    }

    public void launchNext(int x, int y)
    {
        if(mNum<=amt)
        {
            SoundPlayer.playSound("sfx/fire_rocket.wav"); //static method missile sound or something 
            mArray[mNum].load(x,y); //launches the missile currently on 
            mNum++; //adds the missie number 
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        for(int i = 1; i<=amt; i++)
        {
            if(mArray[i].getLaunched() && !mArray[i].getExp())mArray[i].travel(); //moves the missile if launched and not exploded
        }
    }
}
