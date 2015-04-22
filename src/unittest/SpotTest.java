package unittest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import risky.common.Coords;
import risky.common.Country;
import risky.common.Spot;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class SpotTest 
{	
    Spot spot;
    Country country = new Country("Test Country");
    Coords coordinates;	

    @Before
    public void setUp() throws Exception 
    {
        System.out.println("SpotTest::setUp()\n");
        coordinates = new Coords(1,2);
        spot = new Spot(country, coordinates);  
    }

    @After
    public void tearDown() throws Exception
    {
        System.out.println("SpotTest::tearDown()\n");
        spot = null;
        country = null;
    }

    @Test
    public void addResourcesTest()
    {
        System.out.println("SpotTest::addResourceTest()");
        System.out.println("SpotTest::addResourceTest()::Current Resources: " + 
                spot.getResources());
        assertEquals(spot.getResources(), 0);
        spot.setResources(15);
        assertEquals(spot.getResources(), 15);
        System.out.println("SpotTest::addResourceTest()::Current Resources: " + 
                spot.getResources());
    }

    @Test
    public void checkCountryTest()
    {
        System.out.println("SpotTest::checkCountryTest()");
        System.out.println("SpotTest::checkCountryTest()::Country: " + 
                country.getName());
        assertEquals(spot.getCountry(), country);
        ArrayList<Spot> spotArray = country.getSpotsInCountry();
        Boolean inSpotArray = false;
        country.addSpot(spot);
        if (spotArray.contains(spot))
        {
            inSpotArray = true;
        }
        System.out.println("SpotTest::checkCountryTest()::InCountry: " + 
                inSpotArray);		
    }

    @Test
    public void checkCoordsTest()
    {
        System.out.println("SpotTest::checkCoordsTest()");
        System.out.println("SpotTest::checkCoordsTest()::Coordinates: " +
                spot.getX(false) + "," + spot.getY(false));
        int[] testCoords = new int[2];
        testCoords[0] = 1;
        testCoords[1] = 2;
        assertEquals(spot.getX(false), testCoords[0]);
        assertEquals(spot.getY(false), testCoords[1]);
    }
}
