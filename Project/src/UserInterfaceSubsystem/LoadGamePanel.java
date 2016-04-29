package UserInterfaceSubsystem;

import ControllerSubsystem.GameEngine;
import ControllerSubsystem.StorageManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

//Saner Turhaner
	
public class LoadGamePanel extends SideMenuPanel 
{
	//Properties
	JTextArea games;
	private JLabel title;
	GameEngine engine;
	StorageManager x;
	String fileName;
	ArrayList<JButton> buttons;

	//Constructor
	public LoadGamePanel(boolean val)
	{
		super();
		
		engine = GameEngine.getInstance();
		x = engine.getStorageMan();


		buttons = new ArrayList<JButton>();
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

			games = new JTextArea("File Name\n" + x.readFile("src/Sources/txts/savedGames.txt"));
		}
		catch (IOException e)
		{						
			e.printStackTrace();
		}
		
		//Initialize required numnber of buttons
		initializeButtons();
				
		games.setSize(new Dimension(310,255));
		games.setLocation(340,370);

		games.setFont(new Font("Calibri", Font.PLAIN, 15));
		games.setLineWrap(true);
	    games.setEditable(false);
	    games.setVisible(true);	  
		games.setForeground(Color.BLACK);
		games.setBackground(Color.WHITE);
		
		//Add components to the panel
		add(title);
		add(games);
	}
	

	//Listener for Buttons
	private class ButtonListener implements ActionListener//Inner class
	{
		public void actionPerformed(ActionEvent event)//Takes event as a parameter
		{
			Object obj = event.getSource();


			try//Try it
			{
				for(int i = 0; i < buttons.size(); i++)
				{
					if(obj == buttons.get(i))
					{
						fileName = (x.readAsArray("src/Sources/txts/savedGames.txt")).get(i);
						x.generateGame(fileName);
						( ScreenView.getInstance() ).changeActivePanel( (ScreenView.getInstance()).getGame());
					}
				}

			}
			catch(Exception exc)//If there is exception (general) catch it
			{
				exc.printStackTrace();
				System.out.println("Exception is catched: " + exc.getMessage());//Show the message of exception
			}
		}

	}

	public void initializeButtons()
	{
		//Buttons for each text
		for(int i = 0; i < x.getSaveLines(); i++)
		{
			JButton newOne = new JButton("Load " + (i + 1));
			newOne.setSize(new Dimension(90,25));
			newOne.setLocation(660,380 + i*30);
			newOne.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 17));
			newOne.setForeground(Color.WHITE);
			newOne.setBackground(new Color(207,54,30));
	        newOne.setFocusPainted(false);
	        newOne.addActionListener(new ButtonListener());
	        buttons.add(newOne);
	        add(newOne);	        

		}
	}
}
