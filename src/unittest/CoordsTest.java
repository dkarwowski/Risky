package unittest;

import static org.junit.Assert.*;

import risky.common.Coords;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class CoordsTest {
    private Coords coords;

    @Before
    public void setUp() {
        System.out.println("CoordsTest::setUp()");
        coords = new Coords(0, 0);
    }

    @After
    public void tearDown() {
        System.out.println("CoordsTest::tearDown()\n");
        coords = null;
    }

    /**
     * test whether the directions around the hex are accurate. all other movement functions are tested with this
     */
    @Test
    public void getCoordsTest() {
        System.out.println("CoordsTest::getCoordsTest()");
        for(int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                coords = new Coords(x, y);
                Coords[] answersInDir = {new Coords(x, y - 1), new Coords(x + 1, y - 1), new Coords(x + 1, y),
                                         new Coords(x, y + 1), new Coords(x - 1, y + 1), new Coords(x - 1, y)}; 
                System.out.println("\nCoordsTest::getCoordsTest():  hex: \t\t" + coords.toString());
                for(int i = 0; i < answersInDir.length; i++) {
                    assertEquals(answersInDir[i], coords.hexInDir(i));
                    System.out.println("CoordsTest::getCoordsTest():  hexInDir: " + i + " \t" + coords.hexInDir(i).toString());
                }
            }
        }
    }

    @Test
    public void getCartesianTest() {
        System.out.println("CoordsTest::getCartesianTest()");
        int[] yValuesCartesian = {0, 0, 1, 1, 2, 2};
        for(int x = 0; x < yValuesCartesian.length; x++) {
            coords = new Coords(x, 0);
            assertEquals(coords.getY(false), yValuesCartesian[x]);
            System.out.println("CoordsTest::getCartesianTest   yCart: " + coords.getY(false) + " yAxial: " + coords.getY(true));
        }
    }

}