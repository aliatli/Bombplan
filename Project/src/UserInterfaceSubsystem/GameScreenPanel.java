package UserInterfaceSubsystem;

import ControllerSubsystem.*;
import ModelSubsystem.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class GameScreenPanel extends JPanel
{
	//Properties
	GameEngine engine;
	GameMap    map;
	Timer timer;
	int time;
	
	//Constructor
	public GameScreenPanel()
	{
		//Panel constructured
		setLayout(null);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(1200,900));
		
		//Timer initialized
		TimerListener timeListener = new TimerListener();
		timer = new Timer(300, timeListener);
		time = 0;
		
		engine = GameEngine.getInstance();
		map = engine.getMap();
					
	}
	
	//Methods
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);//Default (must)
		
		//Draw Images
		drawImages(page);		
	}
	
	public void drawImages(Graphics g)
	{
		map.drawAll(g);
	}	
	
	//TimerListener	
	private class TimerListener implements ActionListener//Listener for timer
	{
		public void actionPerformed(ActionEvent event)//Time passing
		{
			if(true)//Always
			{	
				if(!engine.isPaused())
				{
					//Time increasing
					time++;
									
					//Draw
					if(time%1 == 0)//PlayerShip icon
					{	
						//drawImages();	
						repaint();	
					}	
											
					repaint();
				}									
			}
		}	
	}
	
}
