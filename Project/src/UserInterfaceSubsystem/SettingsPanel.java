package UserInterfaceSubsystem;

import ControllerSubsystem.GameEngine;
import ControllerSubsystem.StorageManager;
import UserInterfaceSubsystem.SideMenuPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//Saner Turhaner

public class SettingsPanel extends SideMenuPanel
{
	//Properties
	private JLabel title;
	private JButton musicButton;
	private JButton soundButton;
	private boolean music;
	private boolean sound;
	String[] words;
	String record;
	private JLabel  musicVolume;
	private JLabel  soundVolume;
	GameEngine engine;
	StorageManager x;
	
	//Constructor	
	public SettingsPanel()
	{
		super();

		engine = GameEngine.getInstance();
		x = engine.getStorageMan();
		
		try
		{		
	   		words = ( x.readFile("src/sources/txts/settings.txt") ).split(",");//Take every word
		}
		catch (IOException e)
		{						
			e.printStackTrace();
		}
		
		music = Boolean.parseBoolean(words[0]);
		sound = Boolean.parseBoolean(words[1]);
	//	engine.setMusicEffect(music);
	//	engine.setSoundEffect(sound);
					
		//Labels initialized
		title = new JLabel( "Options" );
		title.setSize(new Dimension(400,40));
		title.setLocation(440,325);
		title.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 30));
		title.setForeground(new Color(207,54,30));
		title.setVisible(true);
				
		musicVolume = new JLabel( "" );
		musicVolume.setSize(new Dimension(400,40));
		musicVolume.setLocation(300,450);
		musicVolume.setFont(new Font("Calibri", Font.PLAIN, 24));
		musicVolume.setForeground(Color.WHITE);
		musicVolume.setVisible(true);
		
		soundVolume = new JLabel( "" );
		soundVolume.setSize(new Dimension(400,40));
		soundVolume.setLocation(580,450);
		soundVolume.setFont(new Font("Calibri", Font.PLAIN, 24));
		soundVolume.setForeground(Color.WHITE);
		soundVolume.setVisible(true);
		
		//Buttons initialized		
        musicButton = new JButton("Music");
		musicButton.setSize(new Dimension(157,45));
		musicButton.setLocation(270,380);
		musicButton.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 20));
		musicButton.setForeground(Color.WHITE);
		musicButton.setBackground(new Color(207,54,30));
        musicButton.setFocusPainted(false);
        
        soundButton = new JButton("Sound");
		soundButton.setSize(new Dimension(157,45));
		soundButton.setLocation(550,380);
		soundButton.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 20));
		soundButton.setForeground(Color.WHITE);
		soundButton.setBackground(new Color(207,54,30));
        soundButton.setFocusPainted(false);
                
		//Add listener to button
		ButtonListener listener = new ButtonListener();
		musicButton.addActionListener(listener);
		soundButton.addActionListener(listener);
		
		//Add components to the panel	
		setText();
		add(musicButton);
		add(soundButton);
		add(musicVolume);
		add(soundVolume);
		add(title);
	}
	
	//Listener for Buttons
	private class ButtonListener implements ActionListener//Inner class
	{
		public void actionPerformed(ActionEvent event)//Takes event as a parameter
		{
			Object obj = event.getSource();//Bas�lan tu�
			
			try//Try it
    		{
    			if(obj == soundButton)
				{
					if(sound)
					{
						sound = false;	
						//engine.setSoundEffect(false);						
					}
					else
					{	
						sound = true;
						//engine.setSoundEffect(true);	
					}											
				}	
				else if(obj == musicButton)
				{					
					if(music)
					{
						music = false;	
						//engine.setMusicEffect(false);
					}
					else
					{
						music = true;	
						//engine.setMusicEffect(false);
					}
				}	
	
				setText();//Set text of the label
				record = Boolean.toString(music) + "," + Boolean.toString(sound);//Write current records to the file
				x.writeFile(record, "src/sources/txts/settings.txt");
    		}	
    		catch(Exception exc)//If there is exception (general) catch it
    		{    		
    			System.out.println("Exception is catched: " + exc.getMessage());//Show the message of exception
    		}										
		}						
	}
	
	//Methods
	public void setText()
	{
		if(sound)
			soundVolume.setText("Sound On");
		else
			soundVolume.setText("Sound Off");
			
		if(music)
			musicVolume.setText("Music On");
		else
			musicVolume.setText("Music Off");
	}
	
}
