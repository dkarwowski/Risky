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
	Spot spot1 = new Spot();
	Spot spot2 = new Spot();
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
        spot1 = new Spot();
        spot2 = new Spot();
    }
    
    @Test
    public void countrySpotOwnershipTest() {
    	spot1.setPlayer(player1);
    	spot2.setPlayer(player2);
    	country.setSpotsInCountry(spot1);
    	country.setSpotsInCountry(spot2);
        System.out.println("CountryTest::countrySpotOwnershipTest()");
        
        System.out.println("CountryTest::countrySpotOwnershipTest:  Ownership: False ");
        assertEquals(country.doesPlayerOwnAllSpots(), false); // should both be false
        
        spot2.setPlayer(player1);
        System.out.println("CountryTest::countrySpotOwnershipTest:  Ownership: True ");
        assertEquals(country.doesPlayerOwnAllSpots(), true); // should both be true
    }
    
    @Test
    public void countryClaimTest() {
    	spot1.setPlayer(player1);
    	spot2.setPlayer(player1);
    	country.setSpotsInCountry(spot1);
    	country.setSpotsInCountry(spot2);
        System.out.println("CountryTest::countryClaimTest()");

        country.claimCountry();
        System.out.println("CountryTest::countryClaimTest:  Country Claimed: True ");
        assertEquals(country.getOwner(), country.getSpotsInCountry().get(0).getPlayer()); // should be equal
    }
    
    @Test
    public void countryAddSpotsTest() {
    	spot1.setPlayer(player1);
    	spot2.setPlayer(player1);
        System.out.println("CountryTest::countryAddSpotsTest()");

        System.out.println("CountryTest::countryAddSpotsTest:  SpotsAdded: 0 ");
        assertEquals(country.getSpotsInCountry().size(), 0); // should be 0
        
        country.setSpotsInCountry(spot1);
        System.out.println("CountryTest::countryAddSpotsTest:  SpotsAdded: 1 ");
        assertEquals(country.getSpotsInCountry().size(), 1); // should be 1
        
        country.setSpotsInCountry(spot2);
        System.out.println("CountryTest::countryAddSpotsTest:  SpotsAdded: 2 ");
        assertEquals(country.getSpotsInCountry().size(), 2); // should be 2
    }
    
    
}
