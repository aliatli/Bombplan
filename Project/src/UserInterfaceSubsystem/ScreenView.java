package UserInterfaceSubsystem;

import UserInterfaceSubsystem.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//Saner Turhaner

public class ScreenView 
{
	//Properties
	private JFrame frame;
	private JPanel activePanel;
	private static ScreenView uniqueInstance = null;
	private MainMenuPanel mainP;
	private CreditsPanel  creditsP;
	private LoadGamePanel loadP;
	private HighScorePanel highP;
	private SettingsPanel settingsP;
	private HelpPanel helpP;
	private GameScreenPanel gameP;
	
	//Constructor
	private ScreenView()
	{
		//Frame initialized
		frame = new JFrame("Bombplan");
        frame.setSize(960,900);
		frame.setResizable(false);//Not changable
        frame.setVisible(true);			
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Panels initialized
		mainP 	  = new MainMenuPanel();
		creditsP  = new CreditsPanel();
		loadP 	  = new LoadGamePanel();
		highP 	  = new HighScorePanel();
		settingsP = new SettingsPanel();
		helpP 	  = new HelpPanel();
		gameP 	  = new GameScreenPanel();
		
		//Active Panel initialized
		activePanel = mainP;		
    	frame.add(activePanel);//Add active panel to frame
    	frame.pack();//Size	
	}
	
	//Methods
	public static ScreenView getInstance()
	{
		if(uniqueInstance == null)
			uniqueInstance = new ScreenView();
		return uniqueInstance;
	}
	
	public void changeActivePanel(JPanel panel)//Change active panel
	{
	    frame.getContentPane().removeAll();
	    frame.getContentPane().add(panel);
	    frame.getContentPane().revalidate();
	    frame.getContentPane().repaint();
	}
		
	public JFrame getFrame()
	{
		return frame;
	}
	
	public JPanel newGame()
	{
		return gameP;
	}
	
	public JPanel getMain()
	{
		return mainP;
	}
	
	public JPanel getCredits()
	{
		return creditsP;
	}
	
	public JPanel getLoad()
	{
		return loadP;
	}
	
	public JPanel getHighScores()
	{
		return highP;
	}
	
	public JPanel getSettings()
	{
		return settingsP;
	}
	
	public JPanel getHelp()
	{
		return helpP;
	}
}
