package unittest;

import risky.common.Board;
import risky.common.Coords;
import risky.common.Country;
import risky.common.Player;
import risky.common.Spot;
import risky.fileio.FileIO;

public class FileTest 
{
	//Eventually I'll make these into tests, however, 
	//working testing this way works better for me in
	//the short term.
	private static Country[] countries;
	private static Spot[] spots;
	private static Board board;
	private static Player[] players;

	public static void main(String[] args)
	{
		//Creating Test Board
		Country america = new Country("America");
		Country canada = new Country("Canada");
		Country mexico = new Country("Mexico");
		countries = new Country[]{america,canada,mexico};
		Spot spot01 = new Spot(america, new Coords(0, 0));
		Spot spot02 = new Spot(canada, new Coords(1, 0));
		Spot spot03 = new Spot(mexico, new Coords(2, 0));
		spots = new Spot[]{spot01,spot02,spot03};
		Player player1 = new Player("Jim", 1);
		Player player2 = new Player("Pam", 2);
		players = new Player[]{player1,player2};
		board = new Board("Test!!", 1, 2, spots);
		
		FileIO f = new FileIO(board, players, countries, spots);
		f.createFile("test.txt");	
		f.loadFromFile("test.txt");
	}	
}
