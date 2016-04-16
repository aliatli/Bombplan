import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//Saner Turhaner

public class HelpPanel extends SideMenuPanel 
{
	//Properties	
	private JTextArea helpText;
	private JLabel title;
	
	//Constructor
	public HelpPanel()
	{
		super();
		
		//Label initialized
		title = new JLabel( "Game Rules and Instructions" );
		title.setSize(new Dimension(400,40));
		title.setLocation(312,325);
		title.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 30));
		title.setForeground(new Color(207,54,30));
		title.setVisible(true);
		
		//Text initialized
		helpText = new JTextArea("\t Game Rules: \n1. The main character (from now on,"
		+ "it will be referred as bomber) will have only the ability to plant a"
		+ "bomb. \n2. A bomb has a range of one wall block from 4 sides. The waiting"
		+ "time for the explosion of the bomb is 3 seconds, default. The bomb"
		+ "features can be changed by the taken bonuses that are randomly"
		+ "distributed inside of the walls on the map.\n3. Bomb explosion can be made"
		+ "up to the user. By the help of a taken special bonus, bombs do not explode"
		+ " themselves in 3 seconds; instead, they wait for the user to explode it." 
		+ "\n4. The range of the bomb is 1 wall block from 4 sides -default-, but it can be"
		+ "extended by a special bonus. After the bonus, the range increases 1 more wall"
		+ "block in depth from 4 sides. Since, user can take from that special bonus more"
		+ "than once, at some point, range can be 4 wall blocks in depth from 4 sides."
		+ "\n5. Normally, user cannot plant one more bomb if there is already a planted bomb."
		+ "However, by the help of another bonus, user can plant multiple bombs. Each bonus"
		+ "increases 1 the maximum number of the bomb that can be planted at once.");
		helpText.setSize(new Dimension(505,250));
		helpText.setLocation(235,375);
		helpText.setFont(new Font("Calibri", Font.PLAIN + Font.ITALIC, 18));
		helpText.setLineWrap(true);
	    helpText.setEditable(false);
	    helpText.setVisible(true);	  
		helpText.setForeground(Color.BLACK);
		helpText.setBackground(Color.WHITE);
		
		//Add components to the panel
		add(helpText);
		add(title);
	}
	
	//Methods
	
}
