package unittest;

import static org.junit.Assert.*;
import risky.Player;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class PlayerTest {
    Player player;

    @Before
    public void setUp() throws Exception {
        System.out.println("PlayerTest::setUp()\n");
        player = new Player("test 1", 0);
    }
    
    @After
    public void tearDown() throws Exception {
        System.out.println("PlayerTest::tearDown()\n");
        player = null;
    }
    
    @Test
    public void addResourceTest() {
        System.out.println("PlayerTest::addResourceTest()");
        System.out.println("PlayerTest::addResourceTest:  PlayerResources: " 
                            + player.getAvailableResources());
        assertEquals(player.getAvailableResources(), 10);
        
        player.addResources(); // function that should automatically add resource
        assertEquals(player.getAvailableResources(), 20); // should gain 10 resources

        System.out.println("PlayerTest::addResourceTest: PlayerResources: "
                            + player.getAvailableResources());
    }
}
