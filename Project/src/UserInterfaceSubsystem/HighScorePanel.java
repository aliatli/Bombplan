import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//Saner Turhaner

public class HighScorePanel extends SideMenuPanel 
{
	//Properties
	JTextArea scores;
	private JLabel title;
	StorageManager x;
	
	//Constructor	
	public HighScorePanel()
	{
		super();
		
		x = new StorageManager();
		
		//Label initialized
		title = new JLabel( "HighScore List" );
		title.setSize(new Dimension(400,40));
		title.setLocation(410,325);
		title.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 30));
		title.setForeground(new Color(207,54,30));
		title.setVisible(true);
		
		//Text initialized
		try
		{			
			scores = new JTextArea("No\tNickname\t\tPoint\n" + x.readFile("highScores.txt"));	
		}
		catch (IOException e)
		{						
			e.printStackTrace();
		}
		scores.setSize(new Dimension(410,255));
		scores.setLocation(290,370);
		scores.setFont(new Font("Calibri", Font.PLAIN, 18));
		scores.setLineWrap(true);
	    scores.setEditable(false);
	    scores.setVisible(true);	  
		scores.setForeground(Color.BLACK);
		scores.setBackground(Color.WHITE);
		
		//Add components to the panel
		add(title);
		add(scores);
	}
	
}
