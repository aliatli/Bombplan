package UserInterfaceSubsystem;

import ControllerSubsystem.*;
import ModelSubsystem.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;

//Author: Mehmet Furkan Sahin

public class GameOverPanel extends JPanel
{
    //Properties
    JLabel title;

    //Constructor
    public GameOverPanel(int point)
    {

        //Panel constructed
        setLayout(null);
        setPreferredSize(new Dimension(960,900));
        setBackground(Color.BLACK);

        //Label initialized
        title = new JLabel( "Game Over, your score is: " + point);
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
                (ScreenView.getInstance()).changeActivePanel((ScreenView.getInstance()).getMain());
            }
            catch(Exception exception)
            {
                //Whatever
            }
        }
    }

}
