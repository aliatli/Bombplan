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
		setPreferredSize(new Dimension(960,832));
		
		//Timer initialized
		TimerListener timeListener = new TimerListener();
		timer = new Timer(300, timeListener);
		time = 0;

        engine = GameEngine.getInstance();
        map = engine.getMap();
        timer.start();
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
    
    /// TODO HANDLE THE CASE ON GAMEOVER!!
    private void gameOver(){
        
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
                    
                    try {
                        engine.update();
                    } catch (Exception e) {
                        if(e.getMessage().equalsIgnoreCase("gameover!")){
                            gameOver();
                        }
                    }
                    
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
