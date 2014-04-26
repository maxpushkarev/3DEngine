package Alt.Task1;

import junit.framework.TestCase;

public class ValidatorTest extends TestCase {
	
	public ValidatorTest( String testName) {
        super(testName);
    }
	
	 public void testMessage()
	 {
	     assertEquals(ProtocolValidator.CheckMessage(""),false);
	     assertEquals(ProtocolValidator.CheckMessage("1;1.46,3.678,-7.5733"),false);
	     assertEquals(ProtocolValidator.CheckMessage("-5;1.46,3.678,-7.5733;44,5,6"),false);
	     assertEquals(ProtocolValidator.CheckMessage("777;1.46,3.678,-7.5733;10,3.678,-5"),true);
	     assertEquals(ProtocolValidator.CheckMessage("-777;1.46,3.678,-7.5733;10,3.678,-5"),false);
	     assertEquals(ProtocolValidator.CheckMessage("777;1.46,3.678,-7.5733;10,3.678,-5;"),false);
	 }
}
