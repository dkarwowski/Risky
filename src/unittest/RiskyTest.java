package unittest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import riskyold.Risky;
import riskyold.common.Coords;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class RiskyTest {
    Risky game;
    InputStream old;
    InputStream input;

    @Before
    public void setUp() throws Exception {
        System.out.println("RiskyTest::setUp ");
        String setupInput = "A\nB\n";
        this.old = System.in;

        setInput(setupInput);

        this.game = new Risky(true);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("RiskyTest::tearDown ");
        System.setIn(this.old);
        this.game = null;
        this.input = null;
        this.old = null;
    }

    /**
     * Sets the new System.in for running the game with specific input
     *
     * @param data String of input for the game
     * @throws UnsupportedEncodingException If the Input stream is wrong
     */
    private void setInput(String data) throws UnsupportedEncodingException {
        this.input = new ByteArrayInputStream(data.getBytes("UTF-8"));

        try {
            System.setIn(this.input);
            System.out.println("RiskyTest::setInput: success");
            assertEquals(true, true);
        } catch (SecurityException e) {
            System.setIn(this.old);
            System.out.println("RiskyTest::setInput: failed");
            assertEquals(true, false);
        }
    }

    @Test
    public void claimTest() throws IOException {
        System.out.println("\nRiskyTest::claimTest ");
        String claimTestString = "2 0 10\nq\n";
        setInput(claimTestString);
        System.out.println("\nRiskyTest::claimTest: running");

        this.game.consoleRun();

        System.out.println("\nRiskyTest::claimTest: "
                + this.game.getBoard().getSpot(new Coords(2, 0)).getResources());
        assertEquals(
                this.game.getBoard().getSpot(new Coords(2, 0)).getResources(),
                10);
    }
}
