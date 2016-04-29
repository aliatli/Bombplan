package ControllerSubsystem;

import ModelSubsystem.GameMap;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;
import java.util.*;

//Saner Turhaner

public class StorageManager
{
	//Properties
   	File file;
	ArrayList<String> scorelines;
	static int saveLines;
	//Constructor
	public StorageManager()
	{
		scorelines = new ArrayList<String>();
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

   			while(scan.hasNextLine())
   			{
				saveLines++;
	   			line = scan.nextLine();
				text = text + line + "\n";//A line
			}
   		}
   		else if(fileName.equalsIgnoreCase("src/Sources/txts/highScores.txt"))//Highscores are read
   		{
   			scan = new Scanner(file);//Scanner initialized
			saveLines = 0;

   			while(scan.hasNextLine())
   			{
	   			line = scan.nextLine();
	   			words = line.split(",");//Take every word

	    		text = text + Integer.parseInt(words[0]) + ".\t" + words[1] + "\t\t" + Integer.parseInt(words[2]) + "\n";//A line
	   		}
   		}
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
			writer.print( pre + record);
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
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
		boolean fileExists = true;
		XStream xstream = new XStream(new StaxDriver());
		String 	data = name;
		FileWriter userFile = null;
		File userfile =new File("src//Sources//txts//savedGame.xml");

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
