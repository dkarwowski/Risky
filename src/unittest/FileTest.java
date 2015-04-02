package unittest;

import risky.common.Board;
import risky.common.Coords;
import risky.common.Country;
import risky.common.Spot;
import risky.fileio.FileIO;

public class FileTest 
{
	//Eventually I'll make these into tests, however, 
	//working testing this way works better for me in
	//the short term.
	private static Country country;
	private static Spot spot;
	private static Board board;

	public static void main(String[] args)
	{
		//Creating Test Board
		country = new Country("america");
		spot = new Spot(country, new Coords(0, 0));
		board = new Board("Test!!", 1, 1, new Spot[]{spot});
		
		FileIO f = new FileIO();
		f.setBoard(board);
		f.createFile("test.txt");	
		f.loadFromFile("test.txt");
		
		f.spotFromString("Player#Player");
	}	
}
