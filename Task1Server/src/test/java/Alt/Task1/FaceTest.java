package Alt.Task1;

import Geometry.Face;
import Geometry.ScenePoint;
import junit.framework.TestCase;

public class FaceTest extends TestCase {

	public FaceTest( String testName) {
        super(testName);
    }
	
	public void testCreateEmptyFace()
	{
		assertEquals((new Face()).Points.size(),0);
	}
	
	
	
	
	
	
	public void testCreateEmptyFaceByPoints()
	{
		/**/
		
		assertEquals(
				(new Face(
						new ScenePoint(1,1,1,1),
						new ScenePoint(2,2,2,2),
						new ScenePoint(3,3,3,3)
						)
				).Points.get(0).Id,1);
		
		/**/
		
		assertEquals(
				(new Face(
						new ScenePoint(1,1,1,1),
						new ScenePoint(2,2,2,2),
						new ScenePoint(3,3,3,3)
						)
				).Points.get(1).X,2.0);
		
		/**/
		
		assertEquals(
				(new Face(
						new ScenePoint(1,1,1,1),
						new ScenePoint(2,2,2,2),
						new ScenePoint(3,3,3,3)
						)
				).Points.get(2).Y,3.0);
		
		/**/
		
		assertEquals(
				(new Face(
						new ScenePoint(1,1,1,1),
						new ScenePoint(2,2,2,2),
						new ScenePoint(3,3,3,-4.2)
						)
				).Points.get(2).Z,-4.2);	
	}
	
}
