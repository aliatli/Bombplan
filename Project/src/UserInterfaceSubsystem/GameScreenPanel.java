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
	Timer timer;
	int time;

	JLabel life;
	JLabel point;
	JLabel level;
	JLabel timer_label;

	//Constructor
	public GameScreenPanel()
	{

		//Panel constructured
		setLayout(null);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(960,832));
		
		//Timer initialized
		TimerListener timeListener = new TimerListener();
		timer = new Timer(25, timeListener);
		time = 0;
		
		pausePanel = new PauseMenuPanel();
		pausePanel.setSize(new Dimension(600,350));
		pausePanel.setLocation(180,266);
		pausePanel.setVisible(false);
		add(pausePanel);

		//Labels initialized
		life = new JLabel( "LIFE:" );
		life.setSize(new Dimension(260,30));
		life.setLocation(60,850);
		life.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 30));
		life.setForeground(new Color(207,54,30));
		life.setVisible(true);
		point = new JLabel( "POINTS:" );
		point.setSize(new Dimension(260,30));
		point.setLocation(270,850);
		point.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 30));
		point.setForeground(new Color(207,54,30));
		point.setVisible(true);
		level = new JLabel( "LEVEL:" );
		level.setSize(new Dimension(260,30));
		level.setLocation(480,850);
		level.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 30));
		level.setForeground(new Color(207,54,30));
		level.setVisible(true);
		timer_label = new JLabel( "LEVEL:" );
		timer_label.setSize(new Dimension(260,30));
		timer_label.setLocation(690,850);
		timer_label.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 30));
		timer_label.setForeground(new Color(207,54,30));
		timer_label.setVisible(true);


        timer.start();
        
        //Keys
		KeyReader keyList = new KeyReader();
		addKeyListener(keyList);
		setFocusable(true);	
		requestFocusInWindow(true);

		add(life);
		add(level);
		add(point);
		add(timer_label);

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
		GameEngine.getInstance().setPaused(false);
		GameEngine.getInstance().getMap().drawAll(g);
	}
    
    /// TODO HANDLE THE CASE ON GAMEOVER!!
    private void gameOver(){
        
    }
	
	//TimerListener	
	private class TimerListener implements ActionListener//Listener for timer
	{
		public void actionPerformed(ActionEvent event)//Time passing
		{
				if(!GameEngine.getInstance().isPaused())
				{
					//Time increasing

					try {
						GameEngine.getInstance().update();
						life.setText("LIFE: " + GameEngine.getInstance().getMap().getPlayer().getLife());
						point.setText("POINTS: " + GameEngine.getInstance().getScore());
						level.setText("LEVEL: " + GameEngine.getInstance().getLevel());
						timer_label.setText("TIME: " + GameEngine.getInstance().getTime());

					} catch (Exception e) {
						if (e.getMessage().equalsIgnoreCase("gameover!")){
							( ScreenView.getInstance() ).changeActivePanel( (ScreenView.getInstance()).saveScore() );                        }
					}
					repaint();
				}									
		}
	}
	
	//Key Listener
	private class KeyReader implements KeyListener
	{
		private BitSet keyBits = new BitSet(256);
		//Functions
		public void keyPressed(KeyEvent event) 
		{
		    int keyCode = event.getKeyCode();
			keyBits.set(keyCode);
			//Escape
			if(KeyEvent.VK_ESCAPE == keyCode)
			{
				GameEngine.getInstance().setPaused(true);
				
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
					GameEngine.getInstance().getMovements().add(2);
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
					GameEngine.getInstance().getMovements().add(0);
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
					GameEngine.getInstance().getMovements().add(1);
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
					GameEngine.getInstance().getMovements().add(3);
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
					GameEngine.getInstance().getMovements().add(4);
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
					GameEngine.getInstance().getMovements().add(5);
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
