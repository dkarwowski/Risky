package risky;

import java.util.Scanner;

import risky.common.Board;
import risky.common.Country;
import risky.common.Player;
import risky.common.StateContext;
import risky.common.StatePlayer;
import risky.common.Statelike;

public class Risky {
	private Board board;
	private StateContext stateContext;

	private Player[] players;
	private Statelike[] playerStates;
	
	private Player playerWon = null;
	
	public Risky() {
	}
	
	public Risky(Board _board, Statelike[] _playerStates, StateContext _stateContext) {
		this.board = _board;
		this.playerStates = _playerStates;
		this.stateContext = _stateContext;
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
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter Player 1 Name: ");
		Player p1 = new Player(in.next(), 0);
		System.out.print("Enter Player 2 Name: ");
		Player p2 = new Player(in.next(), 1);
		
		Statelike[] playerStates = new Statelike[2];
		playerStates[0] = new StatePlayer(p1);
		playerStates[1] = new StatePlayer(p2);
		
		Risky game = new Risky(new Board(10, 6), playerStates, new StateContext(playerStates[0]));
		
		System.out.println(game.toString());

		boolean gameRunning = false;
		while(gameRunning) {
			if (game.checkPlayerWon())
				gameRunning = false;
			
			System.out.println(game.toString());
		}
	}
}
