package federico;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple calculator.
 */
public class SimpleCalcTest extends TestCase {

    SimpleCalc calc;

    public SimpleCalcTest( String testName ) {
        super( testName );
        calc = new SimpleCalc();
    }

    public static Test suite() {
        return new TestSuite( SimpleCalcTest.class );
    }

    public void testSimpleCalc() {

        assertEquals(4, calc.eval(2,2,"+"));
        assertTrue(5 != calc.eval(2,2,"+"));
        assertEquals(2, calc.eval(5,2,"/"));
    }
}
