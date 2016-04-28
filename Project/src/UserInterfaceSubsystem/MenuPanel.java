package UserInterfaceSubsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//Saner Turhaner

public class MenuPanel extends JPanel
{
	//Properties
	private ImageIcon logo;
	
	//Constructor
	public MenuPanel()
	{
		//Panel Constructured
		setLayout(null);
		setPreferredSize(new Dimension(960,832));
		setBackground(Color.BLACK);
		
		//Background
		logo = new ImageIcon("src/Sources/Images/logo.jpg");
	}
	
	//Methods
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);//Default (must)
		
		//Draw Logo
		logo.paintIcon(null,page,0,0);		
	}
}
