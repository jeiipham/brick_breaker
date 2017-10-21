import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameEngine
{
    final static int WIDTH = 600; //width of frame
    final static int HEIGHT = 600; //height of frame
    public static void main(String[] args)
    {
        StartScreen ss = new StartScreen(); //the start screen 

        final Paddle paddle = new Paddle(WIDTH/2,HEIGHT-(HEIGHT/10),90,15); //puts paddle in middle of screen or something a tenth of the height above the bottom 
        final Ball ball = new Ball(WIDTH/2,HEIGHT-(HEIGHT/5),25,25,3,false); //puts ball somewhere above paddle, doesnt matter because its attached anyways 
        BrickGroup bricks = new BrickGroup(8,8); //8x8 = 64 blocks 

        final Barrage barrage = new Barrage(25); //25 missiles for 64 blocks, good luck

        JFrame frame = new JFrame("Breakout - Coded by Jeff"); //initialize jframe with title 
        frame.setSize(WIDTH,HEIGHT); //set size
        frame.setLocation(400,200); //set location in screen 
        frame.getContentPane().setBackground(Color.BLACK); //set background of content pane(panel)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closing terminates the program 

        JLabel ballsLabel = new JLabel("Balls: " + ball.getBallsNum(),SwingConstants.LEFT); //jlabel for amount of balls(lives) with left horizonal alignment 
        ballsLabel.setFont(new Font("",Font.PLAIN,25)); //set font 
        ballsLabel.setVerticalAlignment(SwingConstants.NORTH); //set location to north 
        ballsLabel.setForeground(Color.WHITE); //set color to white

        JLabel ammoLabel = new JLabel("Rockets: " + barrage.getNumLeft(),SwingConstants.RIGHT); //same concept
        ammoLabel.setFont(new Font("",Font.PLAIN,25)); //same concept
        ammoLabel.setVerticalAlignment(SwingConstants.NORTH); //same concept
        ammoLabel.setForeground(Color.WHITE); //same concept

        JLabel winLabel = new JLabel("GG EASY",SwingConstants.CENTER); //label that appears when you win 
        winLabel.setFont(new Font("",Font.PLAIN,75)); //set font 
        winLabel.setForeground(Color.WHITE); //set color

        JLabel gameOverLabel = new JLabel("GAME OVER",SwingConstants.CENTER); //same concept
        gameOverLabel.setFont(new Font("",Font.PLAIN,75)); //same concept
        gameOverLabel.setForeground(Color.WHITE); //same concept

        class PaddleListener implements MouseMotionListener
        {
            public void mouseDragged(MouseEvent e){}

            public void mouseMoved(MouseEvent e)
            {
                paddle.setX(e.getX()); //sets the paddle according to mouse location in screen 
            }
        }
        PaddleListener pListener = new PaddleListener(); //object of class above
        frame.addMouseMotionListener(pListener); //adding listener to frame 
        class LaunchListener implements MouseListener
        {
            public void mouseClicked(MouseEvent e)
            {
                if(!ball.attached)barrage.launchNext((int)paddle.p.getCenterX(),(int)paddle.p.getY()); //if the ball is attached, will launch the ball at paddle location, if not will shoot missile at paddle location 
                else ball.launch(); //explained above 
            }

            public void mouseEntered(MouseEvent e) {}

            public void mouseExited(MouseEvent e) {}

            public void mousePressed(MouseEvent e){}

            public void mouseReleased(MouseEvent e) {}
        }
        LaunchListener lListener = new LaunchListener(); //object of class above 
        frame.addMouseListener(lListener); //adding to frame

        boolean stuffAddedToFrameAlready=false; //boolean to prevent stuff from being added twice 
        Timer t = new Timer(8,ball); //timer activates actionperformed in ball
        t.addActionListener(barrage); //timer runs actionperformed in barrage

        SoundPlayer bgm = new SoundPlayer("sfx/bgm3.wav"); //plays cheesy background music or something, by creating a soundplayer so i can stop the sound 
        bgm.loopSound(); //loops the sound
        
        ss.started=true;
        
        while(true)
        {
            if(ss.ifExited())System.exit(0); //if exit button is clicked in ss(start screen) will exit the program
            while(ss.ifStarted()) //if startbutton clicked (started==true), it will == false when game ends 
            {
                if(!stuffAddedToFrameAlready)//only runs once
                {
                    ss.setVisible(false); //no more start screen
                    ss.stopTimer(); //stops the timer on the start screen 
                    frame.add(barrage); //adds stuff to frame
                    frame.setVisible(true); //sets frame visible 
                    frame.add(ballsLabel); //same concept
                    frame.setVisible(true); //same concept
                    frame.add(ammoLabel); //same concept
                    frame.setVisible(true); //same concept
                    frame.add(paddle); //same concept
                    frame.setVisible(true); //same concept
                    frame.add(ball); //same concept
                    frame.setVisible(true); //same concept
                    frame.add(bricks); //same concept
                    frame.setVisible(true); //same concept

                    t.start(); //starts timer
                    stuffAddedToFrameAlready=true; //sets this to true so this block of code isnt run again 
                }
                ballsLabel.setText("Balls: "+ball.getBallsNum()); //updates labe;
                ammoLabel.setText("Rockets: "+barrage.getNumLeft()); //same thing 
                if(!bricks.ifBricksLeft()) //if no bricks left
                { 
                    bgm.stopSound(); //ends bgm 
                    SoundPlayer.playSound("sfx/victory.wav"); //plays cheesy victory sound 
                    frame.add(winLabel); //adds this label to the screen
                    frame.setVisible(true); //sets visible to true
                    frame.removeMouseMotionListener(pListener); //removes listeners so you cant keep playing 
                    frame.removeMouseListener(lListener); //explained above
                    t.stop(); //stops the timer
                    ss.endGame(); //runs endgame method
                    break; //breaks loop 
                }
                if(ball.getBallsNum()==0)
                {   
                    bgm.stopSound(); //same concept
                    SoundPlayer.playSound("sfx/game_over.wav"); //same concept
                    frame.add(gameOverLabel); //same concept
                    frame.setVisible(true); //same concept
                    frame.removeMouseMotionListener(pListener); //same concept
                    frame.removeMouseListener(lListener); //same concept
                    t.stop(); //same concept
                    ss.endGame(); //same concept
                    break; //same concept
                }

                for(int i = 1; i<=barrage.getAmt();i++)
                {
                    if(!barrage.getExpOf(i) && bricks.checkIntersect(barrage.getRectOf(i)))barrage.explode(i); //checks if all the non exploded missiles are intersecting any blocks 
                }

                int brickWallHit = bricks.checkBricks((int)ball.b.getCenterX(),(int)ball.b.getCenterY()); //returns int depending on which wall hit 
                if(brickWallHit==1){SoundPlayer.playSound("sfx/hit_brick.wav");ball.bounce(2);} //if the top side of brick is hit, will bounce(2) which makes the ball go upwards
                else if(brickWallHit==2){SoundPlayer.playSound("sfx/hit_brick.wav");ball.bounce(1);} //same thing
                else if(brickWallHit==3){SoundPlayer.playSound("sfx/hit_brick.wav");ball.bounce(4);} //same thing
                else if(brickWallHit==4){SoundPlayer.playSound("sfx/hit_brick.wav");ball.bounce(3);} //same thing 
                if(paddle.p.intersects(ball.b.getBounds())) //if the ball intersects the paddle)
                {
                    SoundPlayer.playSound("sfx/hit_paddle.wav"); //plays sound of an impact
                    if(ball.b.getCenterX()>paddle.p.getX() && ball.b.getCenterX()<paddle.p.getX()+paddle.p.getWidth()/2)ball.hitLeft(); //if the ball hits left of paddle
                    else if(ball.b.getCenterX()>paddle.p.getWidth()/2 && ball.b.getCenterX()<paddle.p.getX()+paddle.p.getWidth())ball.hitRight(); //ball hits right of paddle
                }
                else if(ball.ifAttached()) //if the ball is attached
                {
                    ball.moveTo((int)(paddle.p.getCenterX()-12),HEIGHT-HEIGHT/5); //moves balls to middle of paddle 
                }
            }
        }
    }
}