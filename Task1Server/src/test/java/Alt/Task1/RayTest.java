package Alt.Task1;

import Geometry.Ray;
import junit.framework.TestCase;

public class RayTest extends TestCase {

	public RayTest( String testName) {
        super(testName);
    }
	
	public void testCreateRay()
	{
		//Id test
		assertEquals((new Ray("777;1.46,3.678,-7.5733;10,3.678,-5")).getId(),777);
		
		//Origin test
		assertEquals((new Ray("777;1.46,3.678,-7.5733;10,3.678,-5")).getOrigin().X,1.46);
		assertEquals((new Ray("777;1.46,3.678,-7.5733;10,3.678,-5")).getOrigin().Y,3.678);
		assertEquals((new Ray("777;1.46,3.678,-7.5733;10,3.678,-5")).getOrigin().Z,-7.5733);
		
		//Direction test
		assertEquals((new Ray("777;1.46,3.678,-7.5733;10,3.678,-5")).getDirection().X,10.0);
		assertEquals((new Ray("777;1.46,3.678,-7.5733;10,3.678,-5")).getDirection().Y,3.678);
		assertEquals((new Ray("777;1.46,3.678,-7.5733;10,3.678,-5")).getDirection().Z,-5.0);
	}
}
