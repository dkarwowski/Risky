package unittest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import risky.common.Board;
import risky.common.Coords;
import risky.common.Country;
import risky.common.Player;
import risky.common.Spot;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class FileTest2 {

	private Country[] countries;
	private Spot[] spots;
	private Board board;
	private Player[] players;
	
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
		board = new Board("Test!!", 1, 2, spots);
    }

    @After
    public void tearDown() throws Exception
    {
        
    }

    @Test
    public void addResourcesTest()
    {
        System.out.println("SpotTest::addResourceTest()");
        System.out.println("SpotTest::addResourceTest()::Current Resources: "); 
        
    }
	
	
}
