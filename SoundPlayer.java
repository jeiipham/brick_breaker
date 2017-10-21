import javax.sound.sampled.*;
import java.io.File;

public class SoundPlayer //class containing static methods only, object of it does not have to be created to use the methods inside
{
    Clip sound; //the sound clip 
    public SoundPlayer(String wav)//constructor in case you want to stop playing a specific sound because you cant do it with a static method 
    {
        try 
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(wav).getAbsoluteFile()); //creates an audioinputstreamobject using static getaudioinputstream method of the audiosystem class which takes in a file object, which takes in a string of the filename/location, then the getAbsolutefile method is called on the new file which returns the actual file from the path and sets this to the audioinputstream object
            sound = AudioSystem.getClip(); //creates a clip from the static getclip method of the audiosystem class
            sound.open(audioInputStream); //calls open method on clip taking in explicit parameter audioinputstream(the actual file)
        }
        catch(Exception e) //will run if the file is not found, catch required for getabsolutefile method
        {
        }
    }

    public void playSound(){sound.start();} //starts sound clip created in constructor 

    public void loopSound(){sound.loop(2147483647);} //loops it (max int) times 

    public void stopSound(){sound.stop();} //stops it 

    public static void playSound(String wav) //method that uses play() on a sound file specified in explicit parameter
    {
        try 
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(wav).getAbsoluteFile()); //creates an audioinputstreamobject using static getaudioinputstream method of the audiosystem class which takes in a file object, which takes in a string of the filename/location, then the getAbsolutefile method is called on the new file which returns the actual file from the path and sets this to the audioinputstream object
            Clip clip = AudioSystem.getClip(); //creates a clip from the static getclip method of the audiosystem class
            clip.open(audioInputStream); //calls open method on clip taking in explicit parameter audioinputstream(the actual file)
            clip.start(); //calls start method on the clip which plays the sound 
        }
        catch(Exception e) //will run if the file is not found, catch required for getabsolutefile method
        {
        }
    }

    public static void loopSound(String wav) //method that uses loop() on a sound file specified in explicit parameter
    {
        try 
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(wav).getAbsoluteFile()); //same as playsound method above
            Clip clip = AudioSystem.getClip(); //same
            clip.open(audioInputStream); //same
            clip.loop(2147483647); //instead of using start method which plays clip once, uses loop method which plays it continously 2147483647(max integer) times, in other words a LONG time
        }
        catch(Exception e) //same
        {
        }
    }
}
