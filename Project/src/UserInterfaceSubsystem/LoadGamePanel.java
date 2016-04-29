package UserInterfaceSubsystem;

import ControllerSubsystem.GameEngine;
import ControllerSubsystem.StorageManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

import static java.lang.Thread.sleep;

//Saner Turhaner

public class LoadGamePanel extends SideMenuPanel 
{
	//Properties
	JTextArea games;
	private JLabel title;
	GameEngine engine;
	StorageManager x;
	
	//Constructor	
	public LoadGamePanel(boolean val)
	{
		super();

		engine = GameEngine.getInstance();
		x = engine.getStorageMan();
		
		//Label initialized
		title = new JLabel( "Load Game" );
		title.setSize(new Dimension(400,40));
		title.setLocation(420,325);
		title.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 30));
		title.setForeground(new Color(207,54,30));
		title.setVisible(true);
		
		//Text initialized
		try
		{			
			games = new JTextArea("Nickname\tDate\t\tLevel\tScore\n" + x.readFile("src/Sources/txts/savedGames.txt"));
		}
		catch (IOException e)
		{						
			e.printStackTrace();
		}
		games.setSize(new Dimension(510,255));
		games.setLocation(240,370);
		games.setFont(new Font("Calibri", Font.PLAIN, 15));
		games.setLineWrap(true);
	    games.setEditable(false);
	    games.setVisible(true);	  
		games.setForeground(Color.BLACK);
		games.setBackground(Color.WHITE);
		
		//Add components to the panel
		add(title);
		add(games);

//		if (val)
//			x.generateGame("savedGame");

	}
	
	//Methods
	
}
