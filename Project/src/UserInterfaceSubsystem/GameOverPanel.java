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

    /**
     *
     * @param point
     */
    public GameOverPanel(int point)
    {
        //Panel constructed
        setLayout(null);
        setPreferredSize(new Dimension(960,900));
        setBackground(Color.BLACK);

        //Label initialized
        title = new JLabel( "Game Over, your score is: " + point);
        title.setSize(new Dimension(800,50));
        title.setLocation(232,400);
        title.setFont(new Font("Calibri", Font.PLAIN + Font.BOLD, 40));
        title.setForeground(new Color(207,54,30));
        title.setVisible(true);

        //Mouse Listener
        addMouseListener(new MyMouseListener());

        add(title);
    }

    //Page

    /**
     *
     * @param g
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);//Default
    }

    //Mouse Listener

    private class MyMouseListener extends MouseAdapter implements MouseListener//Listener as a inner class implements MouseListener
    {
        /**
         *
         * @param e
         */
        public void mouseReleased(MouseEvent e)
        {
            try
            {
				GameEngine.getInstance().getStorageMan().checkScore(GameEngine.getInstance().getScore());	
                (ScreenView.getInstance()).changeActivePanel((ScreenView.getInstance()).getMain());
            }
            catch(Exception exception)
            {
            	System.out.print(exception.getMessage());
            }
        }
    }

}
