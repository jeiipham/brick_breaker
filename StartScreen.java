import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartScreen
{
    final static int WIDTH = 600; //width of frame
    final static int HEIGHT = 600; //height of frame 
    JFrame frame; //frame 
    JLabel title; //title label 
    JLabel subtitle; //rest of instance fields are self explainitory
    JButton start; //self explainitory
    JButton instructions; //self explainitory
    JButton exit; //self explainitory
    boolean started; //self explainitory
    boolean exited; //self explainitory
    JPanel buttonPanel; //self explainitory
    Ball ball; //self explainitory
    Ball ball2; //self explainitory
    Ball ball3; //self explainitory
    Ball ball4; //self explainitory
    Timer t; //self explainitory
    public StartScreen()
    {
        JOptionPane.showMessageDialog(null,"End all memory heavy processes for a better experience. \n   -Jeff","Notice",JOptionPane.INFORMATION_MESSAGE); //because the less memory this game can use the slower it can process meaning the ball can glitch around 
        frame = new JFrame("Welcome"); //initialize with welcome as title 
        frame.setSize(WIDTH,HEIGHT); //width and height 
        frame.setLocation(400,200); //position on screen 
        frame.setLayout(new BorderLayout()); //sets layout of content pane of frame
        frame.getContentPane().setBackground(Color.BLACK); //sets panel of frame to black 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //default close (terminates program)

        title = new JLabel("BREAKOUT",SwingConstants.CENTER); //initialize to horizontal center 
        title.setFont(new Font("",Font.PLAIN,50)); //setting font 
        title.setForeground(Color.CYAN); //setting color 
        subtitle = new JLabel("Jeffrey Pham",SwingConstants.CENTER); //same thing 
        subtitle.setFont(new Font("",Font.PLAIN,25)); //same thing 
        subtitle.setForeground(Color.CYAN); //same thing 

        start = new JButton("Start"); //start button initalized to have start text 
        instructions = new JButton("Instructions"); //same thing 
        exit = new JButton("Exit"); //same thing 
        started=false; //not started yet
        exited=false; //not started yet
        JPanel buttonPanel = new JPanel(new GridLayout(1,3)); //1 column, 3 rows 
        ball = new Ball(500,500,25,25,3,true); //4 balls (can be kinda laggy) different parts of screen 
        ball2 = new Ball(100,100,25,25,3,true); //same thing 
        ball3 = new Ball(500,100,25,25,3,true); //same thing 
        ball4 = new Ball(100,500,25,25,3,true); //same thing 
        class ButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==start)started=true; //if start button clicked started = true
                else if(e.getSource()==instructions){JOptionPane.showMessageDialog(null,
                        "GOAL: Destroy all blocks with balls and rockets (if you run out of balls you lose!)\nCONTROLS: \n   To Control Paddle: move mouse \n   To Launch Ball: click mouse \n   To Fire Rockets: click mouse",
                        "Instructions",JOptionPane.QUESTION_MESSAGE);} //displays instructions using static showmessagedialog method 
                else if(e.getSource()==exit)exited=true; //if exit button clicked...
            }
        }
        start.addActionListener(new ButtonListener()); //add actionlisteners to buttons 
        instructions.addActionListener(new ButtonListener()); //same thing 
        exit.addActionListener(new ButtonListener()); //same thing 

        buttonPanel.add(start); //add buttons to button panel 
        buttonPanel.add(instructions); //same thing 
        buttonPanel.add(exit); //same thing 

        t = new Timer(8,ball); //timer to run actionperformed method in balls 
        t.addActionListener(ball2); //add another ball(actionlistener) to timer
        t.addActionListener(ball3); //same thing 
        t.addActionListener(ball4); //same thing 
        t.start(); //starts timer 

        frame.add(ball); //adds balls to frame
        frame.setVisible(true); //sets visible 
        frame.add(ball2); //same thing 
        frame.setVisible(true); //same thing 
        frame.add(ball3); //same thing 
        frame.setVisible(true); //same thing 
        frame.add(ball4); //same thing 
        frame.setVisible(true); //same thing 

        frame.add(title,BorderLayout.NORTH); //puts title in the north of the borderlayout 
        frame.add(subtitle,BorderLayout.CENTER); //puts subtitle in center
        frame.add(buttonPanel,BorderLayout.SOUTH); //bottom  
    }

    public boolean ifStarted()
    {
        return started; //tells wether started button clicked 
    }

    public void endGame()
    {
        started = false; //if the game is over started will = false 
    }

    public boolean ifExited()
    {
        return exited; //checks if exited button clicked 
    }

    public JFrame getFrame()
    {
        return frame; //gets the frame 
    }

    public void setVisible(boolean b)
    {
        frame.setVisible(b); //sets the frame visible/invisible 
    }

    public void stopTimer()
    {
        t.stop(); //stops the timer (reduce lag)
    }
}
