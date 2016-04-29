package UserInterfaceSubsystem;

import ControllerSubsystem.GameEngine;

import javax.swing.*;

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
	private SaveScorePanel saveScoreP;
	private PassingPanel[] passingP;
	private GameOverPanel gameOverP;

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
		loadP 	  = new LoadGamePanel(false);
		highP 	  = new HighScorePanel();
		settingsP = new SettingsPanel();
		helpP 	  = new HelpPanel();
		gameP 	  = new GameScreenPanel();
		saveScoreP= new SaveScorePanel();
		passingP  = new PassingPanel[4];

		for (int i = 0; i < passingP.length; i++){
			passingP[i] = new PassingPanel(i+1);
		}

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
		try{
			GameEngine.getInstance().restart();
		}
		catch(Exception e)
		{
			System.out.print("Exception is catched: " + e.getMessage());
		}
		return gameP;
	}

	public JPanel startGame()
	{
		try{
			GameEngine.getInstance().getStorageMan().generateGame("savedGame");
//			engine.startGameLoop();
		}
		catch(Exception e)
		{
			System.out.print("Exception is catched: " + e.getMessage());
		}
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

	public void setLoad(boolean val)
	{
		loadP = new LoadGamePanel(val);
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

	public JPanel getPassing(int i){
		return passingP[i];
	}

	public JPanel saveScore()
	{
		return this.saveScoreP;
	}

	public JPanel getGame(){
		return gameP;
	}

	public JPanel getGameOver(int score){
		gameOverP = new GameOverPanel(score);
		return gameOverP;
	}
}
