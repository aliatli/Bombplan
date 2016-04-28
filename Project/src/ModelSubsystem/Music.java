package ModelSubsystem;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;

//Saner Turhaner

public class Music
{	
	//Sound
	File yourFile;
    AudioInputStream stream;
    AudioFormat format;
    DataLine.Info info;
    Clip clip;
	
	//isPlay
	boolean isPlay;
	
	//Constructor
	public Music(String given)
	{		
		//Sound
		try 
		{
    		yourFile = new File(given);
    		stream = AudioSystem.getAudioInputStream(yourFile);
    		format = stream.getFormat();
    		info = new DataLine.Info(Clip.class, format);
    		clip = (Clip) AudioSystem.getLine(info);
    		clip.open(stream);
		}
		catch (Exception e) 
		{
    		System.out.println("Exception is catched: " + e.getMessage());//Show the message of exception
		}	
		
		//IsPlay			
		isPlay = false;		
	}
	
	//Necessary Method
	public void playAsSound()
	{
		isPlay = true;
		
		clip.loop(1);	
	}
	
	public void playMusic()
	{
		isPlay = true;
		
		clip.loop(1000000);	
	}
	
	public void stopMusic()
	{
		clip.stop();	
			
		isPlay = false;	
	}
	
	public boolean isRunning()
	{
		return clip.isRunning();
	}	
		
	public boolean getIsPlay()//Getter
	{
		return isPlay;	
	}		
	
	public void setIsPlay(boolean given)//Setter
	{
		isPlay = given;
	}	
			
}