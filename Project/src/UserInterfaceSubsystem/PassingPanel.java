package UserInterfaceSubsystem;

import ControllerSubsystem.*;
import ModelSubsystem.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;

//Author: Saner Turhaner
//JLabel Bounds: Label.setBounds(600,15,800,35) or
//************** Label.setSize(100,90);	
//************** Label.setLocation(640,100); //and use for button

public class PassingPanel extends JPanel
{
	//Properties
	int levelNo;
	JLabel title;
    
	GameEngine engine;
	StorageManager x;
	ScreenView screen;
	
	//Constructor
	public PassingPanel(int level)
	{		
		//Level No
		levelNo = level;

		//Panel constructed
		setLayout(null);
		setPreferredSize(new Dimension(960,900));
		setBackground(Color.BLACK);

		//Label initialized
		title = new JLabel( "Level " + levelNo );
		title.setSize(new Dimension(400,40));
		title.setLocation(312,325);
		title.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 30));
		title.setForeground(new Color(207,54,30));
		title.setVisible(true);
			
		//Mouse Listener
		addMouseListener(new MyMouseListener());
		
		add(title);				
	}
	
	//Page
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);//Default
	}
	
	//Mouse Listener
	private class MyMouseListener extends MouseAdapter implements MouseListener//Listener as a inner class implements MouseListener
 	{
		public void mouseReleased(MouseEvent e)
		{			
			try
			{
				(ScreenView.getInstance()).changeActivePanel((ScreenView.getInstance()).getGame());
			}	
			catch(Exception exception)
			{
				//Whatever
			}							
		}
 	}
 			
}
