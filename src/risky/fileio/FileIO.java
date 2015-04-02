package risky.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.util.regex.*;

import risky.common.Board;

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
		
	}
	
	public void spotFromString(String line)
	{
		Pattern namepat = Pattern.compile("^(A-Za-z0-9)+(?=\\#)");
		Matcher m = namepat.matcher(line);
		String name = m.group(1);
		System.out.println(name);
	}
}
