package unittest;

import static org.junit.Assert.*;
import risky.Player;
import risky.Country;
import risky.Spot;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class CountryTest {
    Country country;
	Spot spot1 = null;
	Spot spot2 = null;
	Player player1 = new Player("player1", 1);
	Player player2 = new Player("player2", 2);

	
    @Before
    public void setUp() throws Exception {
        System.out.println("CountryTest::setUp()\n");
        country = new Country("test 1");
    }
    
    @After
    public void tearDown() throws Exception {
        System.out.println("CountryTest::tearDown()\n");
        country = null;
    }
    
    @Test
    public void countryClaimTest() {
    	spot1.setPlayer(player1);
    	spot2.setPlayer(player2);
    	country.setSpotsInCountry(spot1);
    	country.setSpotsInCountry(spot2);
        System.out.println("CountryTest::countryClaimTest()");
        assertEquals(country.doesPlayerOwnAllSpots(), false); // should both be false
        
        spot2.setPlayer(player1);
        assertEquals(country.doesPlayerOwnAllSpots(), true); // should both be true
    }
    
}
