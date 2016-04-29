package ControllerSubsystem;

import ModelSubsystem.GameMap;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

//Saner Turhaner

public class StorageManager
{
	//Properties
   	File file;
	static int saveLines;
	ArrayList<Integer> listOfScores;
	ArrayList<String> scorelines;

	//Constructor
	public StorageManager()
	{
		scorelines = new ArrayList<String>();
		listOfScores = new ArrayList<Integer>();
		saveLines = 0;
	}

	//Methods
	public String readFile(String fileName) throws IOException//Read From File
    {
   		Scanner scan;
   		String text = "";
   		String line;
   		String[] words;
   		file = new File(fileName);

   		if(fileName.equalsIgnoreCase("src/Sources/txts/savedGames.txt"))//Load games are read
   		{
   			scan = new Scanner(file);//Scanner initialized
			saveLines = 0;
			
   			while(scan.hasNextLine())
   			{
   				saveLines++;
   				
	   			line = scan.nextLine();

	    		text = text + line + "\n";//A line
	   		}

   		}
   		else if(fileName.equalsIgnoreCase("src/Sources/txts/highScores.txt"))//Highscores are read
   		{
 /*  			scan = new Scanner(file);//Scanner initialized

  
   			for(int i = 0; i < 10; i++)
   			{   			
	   			line = scan.nextLine();
	   			words = line.split(",");//Take every word
				scorelines.add(words[0]);
				listOfScores.add(Integer.parseInt(words[1]));
	    		text = text + words[0] + "\t\t" + Integer.parseInt(words[1]) + "\n";//A line
	   		}
   	*/	}

   		else if(fileName.equalsIgnoreCase("src/Sources/txts/settings.txt"))//Settings are read
   		{
   			scan = new Scanner(file);//Scanner initialized

	   		text = scan.nextLine();
   		}
   		else
   			System.out.print("Type is wrong!");

   		return text;
    }

    public void writeFile(String record, String fileName)throws IOException//Write in File
    {
		PrintWriter writer;
    	String pre = readFile(fileName);

    	try
    	{
    		file = new File(fileName);
    		writer = new PrintWriter(file);
    		
    		if(fileName.equalsIgnoreCase("src/Sources/txts/savedGames.txt"))
				writer.print(pre + record);
			else
				writer.print(record);
				
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
    }
        
	public void checkScore(int point) throws IOException//Just check if he enters the Highscores table
    {
    	int order = -1;//Temporaly
    	
    	if( point >= listOfScores.get(9) )
    	{
    		String input;
    		input = JOptionPane.showInputDialog(null,"Congratulations!\nEnter the Name: ");
    		
    		for(int i = 9; i >= 0; i--)//Scan list    		
	    	{    		
	    		if( point >= listOfScores.get(i) )
	    			order =  i;//Return s�ra number  
	    	}
	    	
	    	listOfScores.add(order, point);//Insert new score
	    	scorelines.add(order, input);
	    	
    		String record = "";//Create a record to save
    		for(int i = 0; i < 10; i++)
    			record = record + scorelines.get(i) + "," + listOfScores.get(i) + "\n";
    			
    		//Write in file
    		writeFile(record, "src/Sources/txts/highScores.txt");
    	}      		  	    					
    }
    	     
	public void saveGame(String given){
		XStream xstream = new XStream(new StaxDriver());
		File userfile = new File("src//Sources//txts//" + given + ".xml");
		File userfile2 = new File("src//Sources//txts//" + given + "_map.xml");
		Writer writer;
		try {
			if(!userfile.exists()){
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream("src//Sources//txts//" + given + ".xml"), "utf-8"));
				writer.write("");
			}
			if(!userfile2.exists()){
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream("src//Sources//txts//" + given + "_map.xml"), "utf-8"));
				writer.write("");
			}

			xstream.toXML(GameEngine.getInstance(), new FileWriter( new File("src//Sources//txts//" + given + ".xml")));
			xstream.toXML(GameEngine.getInstance().getMap(), new FileWriter( new File("src//Sources//txts//" + given + "_map.xml")));


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void generateGame(String name) {
		XStream xstream = new XStream(new StaxDriver());
		String 	data = name;

		{
			//Generate Game According to data

			GameEngine.setUniqueInstance((GameEngine) xstream.fromXML(new File("src//Sources//txts//" + data +".xml")));
			GameMap.setUniqueInstance((GameMap) xstream.fromXML(new File("src//Sources//txts//" + data +"_map.xml")));

		}
//		GameMap.getInstance().resetMap(GameEngine.getInstance().getMap());
		GameEngine.getInstance().startGameLoop();

	}
	
	public int getSaveLines()
	{
		return saveLines;
	}
	
	public ArrayList<String> readAsArray(String fileName) throws IOException//Read From File
    {
   		Scanner scan;
   		ArrayList<String> text = new ArrayList<String>();
   		String line;
   		String[] words;
   		file = new File(fileName);

   		if(fileName.equalsIgnoreCase("src/Sources/txts/savedGames.txt"))//Load games are read
   		{
   			scan = new Scanner(file);//Scanner initialized

   			while(scan.hasNextLine())
   			{
   				saveLines++;
   				
	   			line = scan.nextLine();

	    		text.add(line);
	   		}
   		}
   		
   		return text;
    }
}
