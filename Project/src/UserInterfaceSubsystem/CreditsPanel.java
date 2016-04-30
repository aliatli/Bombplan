package UserInterfaceSubsystem;

import UserInterfaceSubsystem.SideMenuPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//Saner Turhaner

public class CreditsPanel extends SideMenuPanel
{
	//Properties
	JTextArea creditsText;
	private JLabel title;
	
	//Constructor

	/**
	 *
	 */
	public CreditsPanel()
	{
		super();
		
		//Label initialized
		title = new JLabel( "Credits" );
		title.setSize(new Dimension(400,40));
		title.setLocation(440,325);
		title.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 30));
		title.setForeground(new Color(207,54,30));
		title.setVisible(true);
		
		//Text initialized
		creditsText = new JTextArea("\t Developers: \n\n Asena Rana Yozgatli \t asena.rana@gmail.com"
		+ "\n\n Berk Yurttas \t\t berk.yurttas@gmail.com \n\n Mehmet Furkan Sahin \t furkan.sahin@gmail.com"
		+ "\n\n Saner Turhaner \t saner.turhaner@gmail.com");
		creditsText.setSize(new Dimension(505,250));
		creditsText.setLocation(235,375);
		creditsText.setFont(new Font("Calibri", Font.PLAIN + Font.ITALIC, 20));
		creditsText.setLineWrap(true);
	    creditsText.setEditable(false);
	    creditsText.setVisible(true);	  
		creditsText.setForeground(Color.BLACK);
		creditsText.setBackground(Color.WHITE);
		
		//Add components to the panel
		add(creditsText);
		add(title);
	}
	//Methods
	
}
