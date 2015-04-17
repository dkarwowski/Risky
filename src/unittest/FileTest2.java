package unittest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import risky.common.Board;
import risky.common.Coords;
import risky.common.Country;
import risky.common.Player;
import risky.common.Spot;
import risky.fileio.FileIO;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class FileTest2 {

	private Country[] countries;
	private Spot[] spots;
	private Board board;
	private Player[] players;
	private FileIO file;
	
	@Before
    public void setUp() throws Exception 
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
		player1.addCountry(america);
		player1.addSpot(spot01);
		america.addSpot(spot01);
		board = new Board("Test!!", 1, 2, spots);
		file = new FileIO(board, players, countries, spots);
    }

    @After
    public void tearDown() throws Exception
    {
        
    }

    @Test
    public void boardToStringTest()
    {
        System.out.println("FileTest::boardToStringTest()");
        String string1 = "[BORD]Test!!#1#2";
        String string2 = file.stringFromBoard(board);
        System.out.println("FileTest::boardToStringTest::" + string2);
        assertEquals(string1, string2);
    }
    
    @Test
    public void playerToStringTest()
    {
    	System.out.println("FileTest::playerToStringTest()");
        String string1 = "[PLYR]Jim#1#10#10";
        String string2 = file.stringFromPlayer(players[0]);
        System.out.println("FileTest::playerToStringTest::" + string2);
        assertEquals(string1, string2);
    }
    
    @Test
    public void countryToStringTest()
    {
    	System.out.println("FileTest::countryToStringTest()");
        String string1 = "[CONT]America#NoOwner#5";
        Country country = countries[0];
        String string2 = file.stringFromCountry(country);
        System.out.println("FileTest::countryToStringTest::" + string2);
        assertEquals(string1, string2);
    }
    
    @Test
    public void spotToStringTest()
    {
    	System.out.println("FileTest::spotToStringTest()");
        String string1 = "[SPOT]NoOwner#America#0#0,0";
        Spot spot = spots[0];
        String string2 = file.stringFromSpot(spot);
        System.out.println("FileTest::spotToStringTest::" + string2);
        assertEquals(string1, string2);
    }
    
	
}
