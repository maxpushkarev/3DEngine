package Alt.Task1;

import Geometry.ScenePoint;
import junit.framework.TestCase;

public class PointTest extends TestCase {

	public PointTest( String testName) {
        super(testName);
    }
	
	public void testCreateScenePoint()
	{
		assertEquals((new ScenePoint("10,3.678,-5")).X,10.0);
		assertEquals((new ScenePoint("10,3.678,-5")).Y,3.678);
		assertEquals((new ScenePoint("10,3.678,-5")).Z,-5.0);
	}
	
}
