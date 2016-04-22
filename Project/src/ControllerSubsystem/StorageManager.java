
import java.io.*;
import java.util.*;

//Saner Turhaner

public class StorageManager
{
	//Properties
   	File file;
	ArrayList<String> scorelines;

	//Constructor
	public StorageManager()
	{
		scorelines 		= new ArrayList<String>();
	}

	//Methods
	public String readFile(String fileName) throws IOException//Read From File
    {
   		Scanner scan;
   		String text = "";
   		String line;
   		String[] words;
   		file = new File(fileName);

   		if(fileName.equalsIgnoreCase("savedGames.txt"))//Load games are read
   		{
   			scan = new Scanner(file);//Scanner initialized

   			while(scan.hasNextLine())
   			{
	   			line = scan.nextLine();
	   			words = line.split(",");//Take every word

	    		text = text + words[0] + "\t" + words[1] + "\t"  + Integer.parseInt(words[2]) + "\t" + Integer.parseInt(words[3]) + "\n";//A line
	   		}
   		}
   		else if(fileName.equalsIgnoreCase("highScores.txt"))//Highscores are read
   		{
   			scan = new Scanner(file);//Scanner initialized

   			while(scan.hasNextLine())
   			{
	   			line = scan.nextLine();
	   			words = line.split(",");//Take every word

	    		text = text + Integer.parseInt(words[0]) + ".\t" + words[1] + "\t\t" + Integer.parseInt(words[2]) + "\n";//A line
	   		}
   		}
   		else if(fileName.equalsIgnoreCase("settings.txt"))//Settings are read
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

    	try
    	{
    		file = new File(fileName);
    		writer = new PrintWriter(file);
			writer.print(record);
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
    }

}
