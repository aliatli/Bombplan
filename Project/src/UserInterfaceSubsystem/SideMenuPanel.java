import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//Saner Turhaner

public class SideMenuPanel extends MenuPanel
{
	//Properties
	private JButton backButton;
	
	//Constructor
	public SideMenuPanel()
	{		
		super();
		
		//Back button
        backButton = new JButton("Back");
		backButton.setSize(new Dimension(157,45));
		backButton.setLocation(790,580);
		backButton.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN + Font.BOLD, 20));
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(new Color(207,54,30));
        backButton.setFocusPainted(false);
        
		//Add listener to button
		ButtonListener listener = new ButtonListener();
		backButton.addActionListener(listener);
		add(backButton);
	}	
	
	//Methods
	public void returnToMainMenu()
	{
		( ScreenView.getInstance() ).changeActivePanel( (ScreenView.getInstance()).getMain() );
	}	
		
	//Listener for Buttons
	private class ButtonListener implements ActionListener//Inner class
	{
		public void actionPerformed(ActionEvent event)//Takes event as a parameter
		{
			Object obj = event.getSource();//Basýlan tuþ
			
			try//Try it
    		{
    			if(obj == backButton)
				{
					returnToMainMenu();
				}	
    		}	
    		catch(Exception exc)//If there is exception (general) catch it
    		{    		
    			System.out.println("Exception is catched: " + exc.getMessage());//Show the message of exception
    		}										
		}
						
	}
}
