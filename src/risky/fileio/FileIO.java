package risky.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.util.regex.*;

import risky.common.Board;
import risky.common.Coords;
import risky.common.Spot;

public class FileIO 
{
	
	Board _board;
	
	public void setBoard(Board board) {
		_board = board;
	}
	
	public void createFile(String name)
	{
		try
		{
			Writer output = null;
			File file = new File(name);
			output = new BufferedWriter(new FileWriter(file));
			
			output.write("" + _board.getName() + '\n');		//Board Name
			output.write("" + _board.getWidth() + '\n'); 	//Board Width
			output.write("" + _board.getHeight()+ '\n'); 	//Board Height
			
			
			output.close();
			System.out.println("File has been written");
			
		} catch(Exception e) {
			System.out.println("Could not create file");
		}
	}
	
	public void updateFile(String name)
	{
		
	}
	
	public void loadFromFile(String name)
	{
		File file = new File(name);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
		    while ((line = br.readLine()) != null) {
		       System.out.println(line);
		    }
		}
		catch (Exception e) {
			System.out.println("Error reading file");
		}
	}
		
	public void removeFile(String name)
	{
		File file = new File(name);
		try {
			file.delete();
		}
		catch(Exception e) {
			System.out.println("File cannot be deleted.");
		}
	}
	
	public Spot spotFromString(String line)
	{
		String r = "(.+)#(\\w+)#(\\d+)#(\\d+),(\\d+)";
		Pattern namepat = Pattern.compile(r);
		Matcher m = namepat.matcher(line);
		
		String name;
		String country;
		int resources;
		int x = -1;
		int y = -1;
		
		if(m.find()) 
		{
			name = m.group(1);
			country = m.group(2);
			resources = Integer.parseInt(m.group(3));
			x = Integer.parseInt(m.group(4));
			y = Integer.parseInt(m.group(5));
			
			System.out.println(name);
			System.out.println(country);
			System.out.println(resources);
			System.out.println(x);
			System.out.println(y);
		}
		else
		{
			System.out.println("Not found");
		}
		
		Coords coords = new Coords(x, y);
		
		Spot spot = new Spot();
		return spot;
		
	}
}
