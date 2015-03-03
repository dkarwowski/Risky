package unittest;

import risky.common.StateContext;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class StateTest {
	final StateContext sc = new StateContext();

    @Before
    public void setUp() throws Exception {
        System.out.println("CountryTest::setUp()\n");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("CountryTest::tearDown()\n");
    }

    @Test
    public void stateExampleTest() {
        sc.writeName("Monday");
        sc.writeName("Tuesday");
        sc.writeName("Wednesday");
        sc.writeName("Thursday");
        sc.writeName("Friday");
        sc.writeName("Saturday");
        sc.writeName("Sunday");
    }
}

