package Alt.Task1;

import Geometry.Scene;
import junit.framework.TestCase;

public class SceneTest extends TestCase {

	public SceneTest( String testName) {
        super(testName);
    }
	
	public void testEmptyScene()
	{
		assertEquals(new Scene().SceneObjects.size(),0);
	}
	
	
}
