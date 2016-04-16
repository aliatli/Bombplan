import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//Saner Turhaner

public class LoadGamePanel extends SideMenuPanel 
{
	//Properties
	private JLabel title;	
	
	//Constructor	
	public LoadGamePanel()
	{
		super();
		
		//Label initialized
		title = new JLabel( "Load Game" );
		title.setSize(new Dimension(400,40));
		title.setLocation(420,325);
		title.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 30));
		title.setForeground(new Color(207,54,30));
		title.setVisible(true);
				
		//Add components to the panel
		add(title);
	}
	
	//Methods
	
}
