package unittest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	PlayerTest.class, 
	CountryTest.class, 
	SpotTest.class, 
	BoardTest.class,
	CoordsTest.class,
	FileTest2.class})
public class JunitTestSuite {

}
