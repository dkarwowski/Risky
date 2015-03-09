package unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import risky.common.Board;
import risky.common.Coords;
import risky.common.Country;
import risky.common.Spot;

public class BoardTest {
	private Country country;
	private Spot spot;
	private Board board;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("BoardTest::setUp()");
		country = new Country("america");
		spot = new Spot(country, new Coords(0, 0));
		board = new Board("Test", 1, 1, new Spot[]{spot});
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("BoardTest::tearDown()");
		country = null;
		spot = null;
		board = null;
	}
	
	@Test
	public void initTest() {
		System.out.println("BoardTest::initTest()");
		assertEquals(board.getName(), "Test");
		assertEquals(board.getWidth(), 1);
		assertEquals(board.getHeight(), 1);
		assertEquals(board.getSpot(0, 0), spot);
		assertEquals(board.getCountry(0, 0), country);
	}
	
	@Test
	public void containsTest() {
		System.out.println("BoardTest::containsTest()");
		assertEquals(board.contains(0, 0), true);
		assertEquals(board.contains(1, 1), false);
	}
}
