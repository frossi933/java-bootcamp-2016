package federico;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple calculator.
 */
public class SimpleCalcTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SimpleCalcTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SimpleCalcTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testSimpleCalc()
    {
        assertTrue( true );
    }
}
