package UserInterfaceSubsystem;

import ControllerSubsystem.*;
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
	private JRadioButton musicBoxOn;
	private JRadioButton musicBoxOff;
	private JRadioButton soundBoxOn;
	private JRadioButton soundBoxOff;
	
	//Buttons
	JButton newgame;
	JButton backtogame;
	JButton exittomenu;
	JButton saveGame;
	
	//Booleans
	boolean isSound;
	boolean isMusic; 
		
	//Engine
	GameEngine engine;
	StorageManager x;
	String[] words;
	String fileName;
			
	//Constructor
	public PauseMenuPanel()
	{		
		engine = GameEngine.getInstance();
		x = engine.getStorageMan();
		
		//Panel constructed
		setLayout(null);
		setPreferredSize(new Dimension(600,350));
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
		bottom.setLocation(0,337);
		bottom.setBackground( Color.RED );
		bottom.setVisible(true);
			
    	//Check boxes Initialized
   		musicBoxOn = new JRadioButton("On");
		musicBoxOn.setSize(new Dimension(50,25));
		musicBoxOn.setLocation(170,210);
		musicBoxOn.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 16));
		musicBoxOn.setForeground(Color.WHITE);
		musicBoxOn.setBackground(Color.BLACK);
        musicBoxOn.setFocusPainted(false);
        
   		musicBoxOff = new JRadioButton("Off");
		musicBoxOff.setSize(new Dimension(50,25));
		musicBoxOff.setLocation(220,210);
		musicBoxOff.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 16));
		musicBoxOff.setForeground(Color.WHITE);
		musicBoxOff.setBackground(Color.BLACK);
        musicBoxOff.setFocusPainted(false);
   		
		//Collective Work
		ButtonGroup group1 = new ButtonGroup();//Ýkisinden biri seçilebilir
        group1.add(musicBoxOn);
        group1.add(musicBoxOff);
        
   		soundBoxOn = new JRadioButton("On");
		soundBoxOn.setSize(new Dimension(50,25));
		soundBoxOn.setLocation(310,210);
		soundBoxOn.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 16));
		soundBoxOn.setForeground(Color.WHITE);
		soundBoxOn.setBackground(Color.BLACK);
        soundBoxOn.setFocusPainted(false);
   		
   		soundBoxOff = new JRadioButton("Off");
		soundBoxOff.setSize(new Dimension(50,25));
		soundBoxOff.setLocation(360,210);
		soundBoxOff.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 16));
		soundBoxOff.setForeground(Color.WHITE);
		soundBoxOff.setBackground(Color.BLACK);
        soundBoxOff.setFocusPainted(false);
        
		//Collective Work
        ButtonGroup group2 = new ButtonGroup();//Ýkisinden biri seçilebilir
        group2.add(soundBoxOn);
		group2.add(soundBoxOff);
		
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
	       
	    saveGame = new JButton("Save Game");
		saveGame.setSize(new Dimension(200,40));
		saveGame.setLocation(200,290);
		saveGame.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 20));
		saveGame.setForeground(Color.BLACK);
        saveGame.setFocusPainted(false);
	    	
		//Booleans
		try
		{		
	   		words = ( x.readFile("src/sources/txts/settings.txt") ).split(",");//Take every word
		}
		catch (IOException e)
		{						
			e.printStackTrace();
		}
		
		isMusic = Boolean.parseBoolean(words[0]);
		isSound = Boolean.parseBoolean(words[1]);
					
		//Add listener to buttons
		newgame.addActionListener(new ButtonListener());
		exittomenu.addActionListener(new ButtonListener());
		backtogame.addActionListener(new ButtonListener());
		saveGame.addActionListener(new ButtonListener());
		
		//Listener for gender radio buttons
		RadioButtonListener1 listener1 = new RadioButtonListener1();
		musicBoxOn.addActionListener(listener1);
		musicBoxOff.addActionListener(listener1);
		
		//Listener for age radio buttons
		RadioButtonListener2 listener2 = new RadioButtonListener2();
		soundBoxOn.addActionListener(listener2);
		soundBoxOff.addActionListener(listener2);
									
		//Add components in panel
		add(title);
		add(musicL);
		add(soundL);
		add(top);
		add(bottom);
		add(musicBoxOn);
		add(musicBoxOff);
		add(soundBoxOn);
		add(soundBoxOff);
		add(newgame);	
		add(exittomenu);	
		add(backtogame);
		add(saveGame);				
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
			Object obj = event.getSource();//Basï¿½lan tuï¿½
			
			try//Try it
    		{
    			if(obj == newgame)
				{		
					setVisible(false);
					engine.restart();
				}				
    			if(obj == exittomenu)
				{		
					setVisible(false);	
					engine.restart();
					( ScreenView.getInstance() ).changeActivePanel( (ScreenView.getInstance()).getMain() );
				}
				
    			if(obj == backtogame)
				{	
					setVisible(false);
					engine.startGameLoop();
				}
				
				if(obj == saveGame)
				{					
					//Ask file name	
					fileName = JOptionPane.showInputDialog(null,"Enter a name for the saved game");
					x.saveGame(fileName);
					x.writeFile(fileName, "src/Sources/txts/savedGames.txt");
				}
    		}	
    		catch(Exception exc)//If there is exception (general) catch it
    		{    		
    			exc.printStackTrace();
    			System.out.println("Exception is catched: " + exc.getMessage());//Show the message of exception
    		}										
		}
						
	}
	
   	public class RadioButtonListener1 implements ActionListener//Inner class for JRadioButton objects
	{
		public void actionPerformed(ActionEvent event)//For music
		{
			Object source = event.getSource();//Get source information
						
			if(source == musicBoxOn)
				isMusic = true;			
			else
				isMusic = false;
				
			audioStatus();					
		}	
	}
	
	public class RadioButtonListener2 implements ActionListener//Inner class for JRadioButton objects
	{
		public void actionPerformed(ActionEvent event)//For sound
		{
			Object source = event.getSource();//Get source information
			
			if(source == soundBoxOn)
				isSound	= true;
			else
				isSound = false;
				
			audioStatus();			
		}	
	}
	
	public void audioStatus()
	{
		//Record
		String record = Boolean.toString(isMusic) + "," + Boolean.toString(isSound);//Write current records to the file
		
		try
		{			
			x.writeFile(record, "src/sources/txts/settings.txt");
		}
		catch(Exception e)
		{
			System.out.print("Exception is catched: " + e.getMessage());
		}
		
		if(isMusic)
			engine.setMusicEffect(true);
		else
			engine.setMusicEffect(false);
		
		if(isSound)
			engine.setSoundEffect(true);
		else
			engine.setSoundEffect(false);			
	}
   			
}

    	
    