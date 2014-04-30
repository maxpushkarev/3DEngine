package Alt.Task1;

import Geometry.Face;
import Geometry.SceneObject;
import Geometry.ScenePoint;
import junit.framework.TestCase;

public class SceneObjectTest extends TestCase {

	public SceneObjectTest( String testName) {
        super(testName);
    }
	
	public void testEmptyObject()
	{
		assertEquals(new SceneObject().Faces.size(),0);
	}
	
	public void testAddFace()
	{
		assertEquals(
				
				new SceneObject().AddFace(
						(new Face(
								new ScenePoint(1,1,1,1),
								new ScenePoint(2,2,2,2),
								new ScenePoint(3,3,3,3)
								)
						)
				).get(0).Points.get(2).Z,3.0);
	}
	

	
}
