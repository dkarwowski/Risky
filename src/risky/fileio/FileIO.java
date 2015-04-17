package risky.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.regex.*;

import risky.common.Board;
import risky.common.Coords;
import risky.common.Country;
import risky.common.Player;
import risky.common.Spot;

public class FileIO 
{
	private final String BORD_REGEX = "[BORD](.+)#(\\d+)#(\\d+)";
	private final String PLYR_REGEX = "[PLYR](.+)#(\\d+)#(\\d+)#(\\d+)";
	private final String CONT_REGEX = "[CONT](.+)#(\\d+)#(\\d+)";
	private final String SPOT_REGEX = "[SPOT](.+)#(\\w+)#(\\d+)#(\\d+),(\\d+)";
	
	private Board _board;
	private Player[] _players;
	private Country[] _countries;
	private Spot[] _spots;
	
	public FileIO(Board board, Player[] players, Country[] countries, Spot[] spots)
	{
		_board = board;
		_players = players;
		_countries = countries;
		_spots = spots;
	}
	
	public void setBoard(Board board) 
	{
		_board = board;
	}
	
	public void setPlayers(Player[] players)
	{
		_players = players;
	}
	
	public void setCountries(Country[] countries)
	{
		_countries = countries;
	}
	
	public void setSpots(Spot[] spots)
	{
		_spots = spots;
	}
	
	public void createFile(String name)
	{
		try
		{
			Writer output = null;
			File file = new File(name);
			output = new BufferedWriter(new FileWriter(file));
			
			output.write(stringFromBoard(_board) + '\n');

			for(Player player : _players) {
				output.write(stringFromPlayer(player) + '\n');
			}
			
			for(Country country : _countries) {
				output.write(stringFromCountry(country) + '\n');
			}
			
			for(Spot spot : _spots) {
				output.write(stringFromSpot(spot) + '\n');
			}
			
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
		       System.out.println(line); //Iterates line by line
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
	
	//--Start Create String from Object methods----------------------------------
	
	public String stringFromBoard(Board board)
	{
		String name = board.getName();
		int width = board.getWidth();
		int height = board.getHeight();
		String string = "[BORD]" + name + "#" + width + "#" + height;
		return string;
	}
	
	public String stringFromPlayer(Player player)
	{
		String name = player.getName();
		int id = player.getID();
		int availableResources = player.getAvailableResources();
	    int resourcesPerTurn = player.getResoucesPerTurn();
	    String string = "[PLYR]" + name + "#" + id + "#" + availableResources + "#" + resourcesPerTurn;
		return string;
	}
	
	public String stringFromCountry(Country country)
	{
		String name = country.getName();
		Player player = country.getOwner();	//Will have to convert back to player
		int resources = country.getResources();
		String string;
		if(player == null) {
			string = "[CONT]" + name + "#" + "NoOwner" + "#" + resources;
		}
		else {
			String owner = player.getName();
			string = "[CONT]" + name + "#" + owner + "#" + resources;
		}
		return string;
	}
	
	public String stringFromSpot(Spot spot)
	{
		Player player = spot.getPlayer();
		String owner;
		if(player == null) {
			owner = "NoOwner";
		}
		else {
			owner = player.getName();
		}
		Country country = spot.getCountry();
		String countryIn = country.getName();
		int resources = spot.getResources();
		Coords coords = spot.getCoords();
		int x = coords.getX();
		int y = coords.getY();
		String string = "[SPOT]" + owner + "#" + countryIn + "#" + resources + "#" + x + "," + y  + "#";
		Spot[] exits = spot.getExits();
		for(int i = 0; i < exits.length; i++) {
			if(exits[i] == null)
				string = string + "0|";
			else
				string = string + "1|";
		}
		//[SPOT]playername#country#numResources#x,y#0|0|0|0|0|0| <-0 if no exit, 1 if exit
		return string;
	}
	
	//--End Create String from Object methods------------------------------------
	
	//--Start Create Object from String methods----------------------------------
	
	public Board boardFromString(String line)
	{
		String r = BORD_REGEX;
		Pattern p = Pattern.compile(r);
		Matcher m = p.matcher(line);
		
		String name = "";
		int width = 0;
		int height = 0;
		
		if(m.find()) 
		{
			name = m.group(1);
			width = Integer.parseInt(m.group(2));
			height = Integer.parseInt(m.group(3));
			
			System.out.println(name);
			System.out.println(width);
			System.out.println(height);
		}
		else
		{
			System.out.println("Not found");
		}
		
		Board board = new Board(name, width, height);
		return board;
	}
	
	public Player playerFromString(String line)
	{
		String r = PLYR_REGEX;
		Pattern p = Pattern.compile(r);
		Matcher m = p.matcher(line);
		
		String name = "";
		int id = 0;
		int availableResources = 0;
		int resourcesPerTurn = 0;
		
		if(m.find()) 
		{
			name = m.group(1);
			id = Integer.parseInt(m.group(2));
			availableResources = Integer.parseInt(m.group(3));
			resourcesPerTurn = Integer.parseInt(m.group(4));
			
			System.out.println(name);
			System.out.println(id);
			System.out.println(availableResources);
			System.out.println(resourcesPerTurn);
		}
		else
		{
			System.out.println("Not found");
		}
		
		Player player = new Player(name, id, availableResources, resourcesPerTurn);
		return player;
	}
	
	public Country countryFromString(String line) 
	{
		String r = CONT_REGEX;
		Pattern p = Pattern.compile(r);
		Matcher m = p.matcher(line);
		
		return null;
	}
	
	public Spot spotFromString(String line)	//Needs work
	{
		String r = SPOT_REGEX;
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
	
	//--End Object from String methods-------------------------------------------
}