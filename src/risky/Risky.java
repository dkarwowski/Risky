package risky;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import risky.common.Board;
import risky.common.Coords;
import risky.common.Country;
import risky.common.Player;
import risky.common.Spot;
import risky.common.StateContext;
import risky.common.StatePlayer;
import risky.common.Statelike;

public class Risky {
	private Board board;
	private StateContext stateContext;

	private Player[] players;
	private Statelike[] playerStates;
	private int currentState;
	
	private Player playerWon = null;
	
	public Risky() {
	}
	
	public Risky(Board _board, Statelike[] _playerStates) {
		this.board = _board;
		this.playerStates = _playerStates;
		this.stateContext = new StateContext(this.playerStates[0]);
		this.currentState = 0;
	}
	
	public boolean checkPlayerWon() {
		playerWon = this.board.playerOwnsAll();
		return (playerWon != null);
	}
	
	public Player getWinner() {
		return playerWon;
	}
	
	@Override
	public String toString() {
		String result = "";
		result += board.toString() + "\n";
		return (result);
	}
	
	public void replaceBoard(ArrayList<Spot> spots) {
		for (Spot spot : spots) {
			this.board.setSpot(spot);
		}
	}
	
	// TODO: see if this class needed
	public boolean spotFree(int x, int y) {
		return (this.board.spotFree(x, y));
	}
	
	// TODO: see if this class needed
	public void claimSpot(int x, int y, int resources) {
		this.board.claimSpot(this.stateContext.getPlayer(), x, y, resources);
	}
	
	// TODO: see if this class needed
	public void claimCountries() {
		this.board.claimCountries();
	}
	
	// TODO: see if this class needed
	public int getPlayerResources() {
		return (this.stateContext.getPlayer().getAvailableResources());
	}
	
	// TODO: see if this class needed
	public int getPlayerID() {
		return (this.stateContext.getPlayer().getID());
	}
	
	// TODO: see if this class needed
	public void switchPlayer() {
		this.currentState = (this.currentState + 1) % this.playerStates.length;
		this.stateContext.setState(this.playerStates[this.currentState]);
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter Player 1 Name: ");
		Player p1 = new Player(in.next(), 0);
		System.out.print("Enter Player 2 Name: ");
		Player p2 = new Player(in.next(), 1);
		
		Statelike[] playerStates = new Statelike[2];
		playerStates[0] = new StatePlayer(p1);
		playerStates[1] = new StatePlayer(p2);
		
		String boardName;
		int width, height;
		String[] board;
		try {
			Scanner loadBoard = new Scanner(new File("data/test.map"));
			boardName = loadBoard.next();
			width = loadBoard.nextInt();
			height = loadBoard.nextInt();
			
			board = new String[width*height];
			int i = 0;
			while (loadBoard.hasNext())
				board[i++] = loadBoard.nextLine();
			while (i < width * height)
				board[i++] = null;
		} catch (FileNotFoundException e) {
			// TODO(david): Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		// TODO: move the state context stuff to a better method of creating it
		Risky game = new Risky(
				new Board(boardName, width, height), 
				playerStates);
		
		ArrayList<Country> countries = new ArrayList<Country>();
		ArrayList<Spot> spots = new ArrayList<Spot>();
		for (int i = 0; i < width * height; ++i) {
			if (board[i] == null) break;
			if (board[i].length() < 1) continue;
			String[] pieces = board[i].split(" ");
			int xCart = Integer.parseInt(pieces[0]);
			int yCart = Integer.parseInt(pieces[1]);
			String countryName = pieces[2];
			Country thisCountry = new Country(countryName);
			
			boolean switched = false;
			for (Country country : countries) {
				if (country.equals(thisCountry)) {
					thisCountry = country;
					switched = true;
					break;
				}
			}
			
			if (!switched)
				countries.add(thisCountry);
			
			// TODO: have spots update countries they're attached to
			Spot spot = new Spot(thisCountry, new Coords(xCart, yCart, false));
			thisCountry.addSpot(spot);

			spots.add(spot);
		}
		
		game.replaceBoard(spots);
		
		System.out.println(game.toString());

		boolean gameRunning = true; 
		while(gameRunning) {
			// TODO: make first move unique for both players
			// TODO: make moves only possible to consecutive spots
			// TODO: determine how to connect disjointed countries
			System.out.println("Free resources: " + game.getPlayerResources());
			System.out.println("Player " + game.getPlayerID() + ": Enter Coordinates to take over with resources [enter as '1 1 10']: ");
			int x = in.nextInt();
			int y = in.nextInt();
			int r = in.nextInt();
			in.nextLine();
			
			// TODO: deal with combat
			if (!game.spotFree(x, y)) {
				continue;
			}
			
			// TODO: make player lose resources
			// TODO: give player resources every turn
			game.claimSpot(x, y, r);
			game.claimCountries();

			// TODO: test that this works
			if (game.checkPlayerWon())
				gameRunning = false;
			
			System.out.println(game.toString());
			game.switchPlayer();
		}
		
		in.close();
	}
}
