package UserInterfaceSubsystem;

import ControllerSubsystem.*;
import ModelSubsystem.*;
import java.util.BitSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;

public class GameScreenPanel extends JPanel
{
	//Properties
	PauseMenuPanel pausePanel;
	GameEngine engine;
	GameMap    map;
	Timer timer;
	int time;
	private BitSet keyBits;
	
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
		
		pausePanel = new PauseMenuPanel();
		pausePanel.setSize(new Dimension(600,300));
		pausePanel.setLocation(180,266);
		pausePanel.setVisible(false);
		add(pausePanel);
		
        engine = GameEngine.getInstance();
        map = engine.getMap();

        timer.start();
        
        //Keys
		keyBits = new BitSet(256);
		KeyReader keyList = new KeyReader();
		addKeyListener(keyList);
		setFocusable(true);	
		requestFocusInWindow(true);	

	}
	
	//Methods
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);//Default (must)
		setFocusable(true);
		requestFocusInWindow(true);	
				
		//Draw Images
		drawImages(page);		
	}
	
	public void drawImages(Graphics g)
	{
		engine.setPaused(false);
//		timer.start();
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
				if(!engine.isPaused())
				{
					//Time increasing

					try {
						engine.update();
						life.setText("LIFE: " + engine.getMap().getPlayer().getLife());
						point.setText("POINTS: " + engine.getScore());
						level.setText("LEVEL: " + engine.getLevel());

					} catch (Exception e) {
						e.printStackTrace();
					}
					repaint();
				}									
		}
	}
	
	//Key Listener
	private class KeyReader implements KeyListener
	{
		//Functions
		public void keyPressed(KeyEvent event) 
		{
		    int keyCode = event.getKeyCode();
		    keyBits.set(keyCode);
		    
			//Escape
			if(KeyEvent.VK_ESCAPE == keyCode)
			{
				engine.setPaused(true);
				
				try 
				{	
					pausePanel.setVisible(true);
					//removeKeyListener(keyList);
				}
			   	catch (Exception e) 
				{
			    	System.out.println("Exception is catched: " + e.getMessage());//Show the message of exception
				}
			}
			
			//Directions 
			else if(isKeyPressed(KeyEvent.VK_LEFT))
			{
				try 
				{	
					engine.getMovements().add(2);
				}
			   	catch (Exception e) 
				{
			    	System.out.println("Exception is catched: " + e.getMessage());//Show the message of exception
				}
			}
			else if(isKeyPressed(KeyEvent.VK_RIGHT))
			{
				try 
				{	
					engine.getMovements().add(0);
				}
			   	catch (Exception e) 
				{
			    	System.out.println("Exception is catched: " + e.getMessage());//Show the message of exception
				}
			}
			else if(isKeyPressed(KeyEvent.VK_UP))
			{
				try 
				{	
					engine.getMovements().add(1);
				}
			   	catch (Exception e) 
				{
			    	System.out.println("Exception is catched: " + e.getMessage());//Show the message of exception
				}
			}
			else if(isKeyPressed(KeyEvent.VK_DOWN))
			{
				try 
				{	
					engine.getMovements().add(3);
				}
			   	catch (Exception e) 
				{
			    	System.out.println("Exception is catched: " + e.getMessage());//Show the message of exception
				}
			}
			else if(isKeyPressed(KeyEvent.VK_SPACE))
			{
				try 
				{	
					engine.getMovements().add(4);
				}
			   	catch (Exception e) 
				{
			    	System.out.println("Exception is catched: " + e.getMessage());//Show the message of exception
				}
			}

			else if(isKeyPressed(KeyEvent.VK_SHIFT))
			{
				try
				{
					engine.getMovements().add(5);
				}
				catch (Exception e)
				{
					System.out.println("Exception is catched: " + e.getMessage());//Show the message of exception
				}
			}
		}
	
		public void keyReleased(KeyEvent event) 
		{		
			int keyCode = event.getKeyCode();//Take key code
		        
		    keyBits.clear(keyCode);//Clear		
		}
	
		public void keyTyped(KeyEvent event) 
		{		    
			// TODO Auto-generated method stub	
		}
		
		public boolean isKeyPressed(final int keyCode) 
		{
		    return keyBits.get(keyCode);
		}
	}
	
}
