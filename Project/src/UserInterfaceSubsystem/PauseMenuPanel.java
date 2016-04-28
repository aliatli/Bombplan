package UserInterfaceSubsystem;

import ControllerSubsystem.GameEngine;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Scanner;
import java.io.*;

//Author: Saner Turhaner
//JLabel Bounds: Label.setBounds(600,15,800,35) or
//************** Label.setSize(100,90);	
//************** Label.setLocation(640,100); //and use for button

public class PauseMenuPanel extends JPanel
{				
	//Label
	JLabel title;
	JLabel musicL;
	JLabel soundL;
	
	//Panels
	JPanel top;
	JPanel bottom;
	
	//Sliders
	JSlider sound;
	JSlider music;
	
	//Buttons
	JButton newgame;
	JButton backtogame;
	JButton exittomenu;
	
	//Booleans
	boolean isSound;
	boolean isMusic; 
		
	//Engine
	GameEngine engine;
			
	//Constructor
	public PauseMenuPanel()
	{		
		engine = GameEngine.getInstance();

		
		//Panel constructed
		setLayout(null);
		setPreferredSize(new Dimension(600,300));
		setBackground( Color.BLACK );
		
		//Label initialized
		title = new JLabel( "PAUSE" );
		title.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 26));
		title.setForeground(Color.WHITE);
		title.setBounds(250,30,100,26);
		title.setVisible(true);
		
		musicL = new JLabel( "Music Volume" );
		musicL.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 20));
		musicL.setForeground(Color.WHITE);
		musicL.setBounds(170,180,120,20);
		musicL.setVisible(true);
		
		soundL = new JLabel( "Sound Volume" );
		soundL.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 20));
		soundL.setForeground(Color.WHITE);
		soundL.setBounds(310,180,140,20);
		soundL.setVisible(true);
		
		//Panels Initialized
		top = new JPanel();
		top.setSize(new Dimension(600,38));
		top.setLocation(0,22);
		top.setBackground( Color.RED );
		top.setVisible(true);
		
		bottom = new JPanel();
		bottom.setSize(new Dimension(600,6));
		bottom.setLocation(0,287);
		bottom.setBackground( Color.RED );
		bottom.setVisible(true);
			
    	//Sliders Initialized
   		music = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);//Dikey
		music.setSize(new Dimension(120,20));
		music.setLocation(170,210);
   		music.setMaximum(1);
   		music.setMinimum(0);
   		music.setValue(1);
   		music.setPaintLabels(true);//Set visible
   		music.setPaintTicks(true);
   		
   		sound = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);//Dikey
		sound.setSize(new Dimension(120,20));
		sound.setLocation(310,210);
   		sound.setMaximum(1);
   		sound.setMinimum(0);
   		sound.setValue(1);
   		sound.setPaintLabels(true);//Set visible
   		sound.setPaintTicks(true);
   		
		//JButtons initialized
		newgame = new JButton("New Game");
		newgame.setSize(new Dimension(200,40));
		newgame.setLocation(200,80);
		newgame.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 20));
		newgame.setForeground(Color.BLACK);
        newgame.setFocusPainted(false);
        	
		exittomenu = new JButton("Exit to Menu");
		exittomenu.setSize(new Dimension(200,40));
		exittomenu.setLocation(200,130);
		exittomenu.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 20));
		exittomenu.setForeground(Color.BLACK);
        exittomenu.setFocusPainted(false);
        
		backtogame = new JButton("Back to Game");
		backtogame.setSize(new Dimension(200,40));
		backtogame.setLocation(200,240);
		backtogame.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 20));
		backtogame.setForeground(Color.BLACK);
        backtogame.setFocusPainted(false);
	        	
		//Booleans
		isSound = true;
		isMusic = true; 
		
		//Add listener to buttons
		newgame.addActionListener(new ButtonListener());
		exittomenu.addActionListener(new ButtonListener());
		backtogame.addActionListener(new ButtonListener());
		
   		SlideListener listener = new SlideListener();
		music.addChangeListener(listener);
		sound.addChangeListener(listener);
									
		//Add components in panel
		add(title);
		add(musicL);
		add(soundL);
		add(top);
		add(bottom);
		add(music);
		add(sound);
		add(newgame);	
		add(exittomenu);	
		add(backtogame);				
	}
	
	public void paintComponent(Graphics page)//Drawing cards
	{
		super.paintComponent(page);//Default (must)
	}
	
	//Listener for Buttons
	private class ButtonListener implements ActionListener//Inner class
	{
		public void actionPerformed(ActionEvent event)//Takes event as a parameter
		{
			Object obj = event.getSource();//Bas�lan tu�
			
			try//Try it
    		{
    			if(obj == newgame)
				{		
					setVisible(false);	

					//engine.restart();			
				}				
    			if(obj == exittomenu)
				{		
					setVisible(false);
					//engine.stopMusic();
					( ScreenView.getInstance() ).changeActivePanel( (ScreenView.getInstance()).getMain() );		
				}
				
    			if(obj == backtogame)
				{	
					setVisible(false);
					engine.startGameLoop();
				}
    		}	
    		catch(Exception exc)//If there is exception (general) catch it
    		{    		
    			System.out.println("Exception is catched: " + exc.getMessage());//Show the message of exception
    		}										
		}
						
	}
	
   	private class SlideListener implements ChangeListener//Listener class (inner)
   	{
   		public void stateChanged(ChangeEvent event)//only method for ChangeListener interface
   		{
   			//Change music volume
   			if(music.getValue() == 0)
   				isMusic = false;
   			else
   				isMusic = true;   				
   			
   			//Change sound volume
   			if(sound.getValue() == 0)
   				isSound = false;   			
   			else
   				isSound = true;   	
   		}	
   	}	
   			
}

    	
    