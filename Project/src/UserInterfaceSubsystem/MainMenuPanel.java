package UserInterfaceSubsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//Saner Turhaner

public class MainMenuPanel extends MenuPanel
{
	//Properties
	private JButton newGameButton;
	private JButton helpButton;
	private JButton loadGameButton;
	private JButton optionsButton;
	private JButton highScoreButton;
	private JButton creditsButton;
	private JButton exitButton;
		
	//Constructor
	public MainMenuPanel()
	{
		super();
				
		//JButtons initialized
		newGameButton = new JButton("Start Game");
		newGameButton.setSize(new Dimension(157,45));
		newGameButton.setLocation(110,340);
		newGameButton.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 20));
		newGameButton.setForeground(Color.WHITE);
		newGameButton.setBackground(new Color(207,54,30));
        newGameButton.setFocusPainted(false);
		newGameButton.setOpaque(true);


		helpButton = new JButton("Help");
		helpButton.setSize(new Dimension(157,45));
		helpButton.setLocation(110,400);
		helpButton.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 20));
		helpButton.setForeground(Color.WHITE);
		helpButton.setBackground(new Color(207,54,30));
        helpButton.setFocusPainted(false);
		helpButton.setOpaque(true);

		loadGameButton = new JButton("Load Game");
		loadGameButton.setSize(new Dimension(157,45));
		loadGameButton.setLocation(110,460);
		loadGameButton.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 20));
		loadGameButton.setForeground(Color.WHITE);
		loadGameButton.setBackground(new Color(207,54,30));
        loadGameButton.setFocusPainted(false);
		loadGameButton.setOpaque(true);

		optionsButton = new JButton("Options");
		optionsButton.setSize(new Dimension(157,45));
		optionsButton.setLocation(110,520);
		optionsButton.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 20));
		optionsButton.setForeground(Color.WHITE);
		optionsButton.setBackground(new Color(207,54,30));
        optionsButton.setFocusPainted(false);
		optionsButton.setOpaque(true);

		highScoreButton = new JButton("Highscores");
		highScoreButton.setSize(new Dimension(157,45));
		highScoreButton.setLocation(300,375);
		highScoreButton.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 20));
		highScoreButton.setForeground(Color.WHITE);
		highScoreButton.setBackground(new Color(207,54,30));
        highScoreButton.setFocusPainted(false);
		highScoreButton.setOpaque(true);

		creditsButton = new JButton("Credits");
		creditsButton.setSize(new Dimension(157,45));
		creditsButton.setLocation(300,435);
		creditsButton.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 20));
		creditsButton.setForeground(Color.WHITE);
		creditsButton.setBackground(new Color(207,54,30));
        creditsButton.setFocusPainted(false);
		creditsButton.setOpaque(true);

		exitButton = new JButton("Exit");
		exitButton.setSize(new Dimension(157,45));
		exitButton.setLocation(300,495);
		exitButton.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 20));
		exitButton.setForeground(Color.WHITE);
		exitButton.setBackground(new Color(207,54,30));
        exitButton.setFocusPainted(false);
		exitButton.setOpaque(true);


		//Add listener to buttons
		ButtonListener listener = new ButtonListener();
		newGameButton.addActionListener(listener);
		helpButton.addActionListener(listener);
		loadGameButton.addActionListener(listener);
		optionsButton.addActionListener(listener);
		highScoreButton.addActionListener(listener);
		creditsButton.addActionListener(listener);
		exitButton.addActionListener(listener);

		try {
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} catch (Exception e) {
			e.printStackTrace();
		}
        //Add components in panel
		add(newGameButton);
		add(helpButton);
		add(loadGameButton);
		add(optionsButton);
		add(highScoreButton);
		add(creditsButton);
		add(exitButton);
	}	
		
	//Methods
	/*public String getChoice()
	{
		
	}*/
	
	//Listener for Buttons
	private class ButtonListener implements ActionListener//Inner class
	{
		public void actionPerformed(ActionEvent event)//Takes event as a parameter
		{
			Object obj = event.getSource();//Bas�lan tu�
			
			try//Try it
    		{
    			if(obj == newGameButton)
				{
					( ScreenView.getInstance() ).changeActivePanel( (ScreenView.getInstance()).newGame() );
				}
				else if(obj == helpButton)
				{
					( ScreenView.getInstance() ).changeActivePanel( (ScreenView.getInstance()).getHelp() );
				}	
				else if(obj == loadGameButton)
				{
					( ScreenView.getInstance() ).changeActivePanel( (ScreenView.getInstance()).getLoad() );					
				}
				else if(obj == optionsButton)
				{
					( ScreenView.getInstance() ).changeActivePanel( (ScreenView.getInstance()).getSettings() );							
				}	
				else if(obj == highScoreButton)
				{
					( ScreenView.getInstance() ).changeActivePanel( (ScreenView.getInstance()).getHighScores() );						
				}
				else if(obj == creditsButton)
				{
					( ScreenView.getInstance() ).changeActivePanel( (ScreenView.getInstance()).getCredits() );	
				}	
				else if(obj == exitButton)
				{
					( ( ScreenView.getInstance() ).getFrame() ).dispose();//Exit from frame
				}	
    		}	
    		catch(Exception exc)//If there is exception (general) catch it
    		{    		
    			System.out.println("Exception is catched: " + exc.getMessage());//Show the message of exception
    		}										
		}
						
	}
		
}
