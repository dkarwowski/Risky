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
	private final String SPOT_REGEX = "[SPOT](.+)#(\\w+)#(\\d+)#(\\d+),(\\d+)#(1|0)|(1|0)|(1|0)|(1|0)|(1|0)|(1|0)|";
	
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
	
	public Player getPlayer(String name)
	{
		for(int i = 0; i < _players.length; i++) {
			if(_players[i].getName() == name)
				return _players[i];
		}
		System.out.println("Player does not exist");
		return null;
	}
	
	public Country getCountry(String name)
	{
		for(int i = 0; i < _countries.length; i++) {
			if(_countries[i].getName() == name)
				return _countries[i];
		}
		System.out.println("Country does not exist");
		return null;
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
		
		String name = "";
		String playerName = "";
	    Player owner = null;
	    int resources;
	    
	    if(m.find()) 
		{
			name = m.group(1);
			playerName = m.group(2);
			resources= Integer.parseInt(m.group(3));
			
			System.out.println(name);
			System.out.println(playerName);
			System.out.println(resources);
		}
		else
		{
			System.out.println("Not found");
		}
		owner = this.getPlayer(playerName);
		Country country = new Country(name);
		if(owner != null)
			owner.addCountry(country);
		//Set resources?  Can't find function
		return country;
	}
	
	public Spot spotFromString(String line)	//Needs work with Exits
	{
		String r = SPOT_REGEX;
		Pattern namepat = Pattern.compile(r);
		Matcher m = namepat.matcher(line);
		
		String name = "";
		String country = "";
		int resources = 0;
		int x = -1;
		int y = -1;
		//Need Exits
		int exit1 = 0;
		int exit2 = 0;
		int exit3 = 0;
		int exit4 = 0;
		int exit5 = 0;
		int exit6 = 0;
		
		if(m.find()) 
		{
			name = m.group(1);
			country = m.group(2);
			resources = Integer.parseInt(m.group(3));
			x = Integer.parseInt(m.group(4));
			y = Integer.parseInt(m.group(5));
			exit1 = Integer.parseInt(m.group(6));
			exit2 = Integer.parseInt(m.group(7));
			exit3 = Integer.parseInt(m.group(8));
			exit4 = Integer.parseInt(m.group(9));
			exit5 = Integer.parseInt(m.group(10));
			exit6 = Integer.parseInt(m.group(11));
			
			System.out.println(name);
			System.out.println(country);
			System.out.println(resources);
			System.out.println(x);
			System.out.println(y);
			System.out.println(exit1 + exit2 + exit3 + exit4 + exit5 + exit6);
		}
		else
		{
			System.out.println("Not found");
		}
		
		Coords coords = new Coords(x, y);
		Country countryObj = this.getCountry(country);
		
		Spot spot = new Spot();
		
		int[] exitInts = new int[]{exit1,exit2,exit3,exit4,exit5,exit6};
		for(int i = 0; i < exitInts.length; i++) {
			if(exitInts[i] == 1) {
				Coords newCoords = coords.hexInDir(i);
				
			}
		}
		
		
		return spot;	
	}
	
	//--End Object from String methods-------------------------------------------
}