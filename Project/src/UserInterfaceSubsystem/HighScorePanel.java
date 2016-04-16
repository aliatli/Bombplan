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
	
	//Constructor	
	public HighScorePanel()
	{
		super();
		
		//Label initialized
		title = new JLabel( "HighScore List" );
		title.setSize(new Dimension(400,40));
		title.setLocation(410,325);
		title.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 30));
		title.setForeground(new Color(207,54,30));
		title.setVisible(true);
		
		//Text initialized
		scores = new JTextArea("\t User Name \t Level \t Score \n 1. \n 2. \n 3. \n 4. \n 5. \n 6. \n 7. \n 8. \n 9. \n 10. ");
		scores.setSize(new Dimension(390,240));
		scores.setLocation(300,370);
		scores.setFont(new Font("Calibri", Font.PLAIN, 16));
		scores.setLineWrap(true);
	    scores.setEditable(false);
	    scores.setVisible(true);	  
		scores.setForeground(Color.BLACK);
		scores.setBackground(Color.WHITE);
		
		//Add components to the panel
		add(title);
		add(scores);
	}
	
	//Methods
	
}
