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
		assertEquals((new Face()).Points.length,3);
		assertEquals((new Face()).Points[0],null);
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
				).Points[0].Id,1);
		
		/**/
		
		assertEquals(
				(new Face(
						new ScenePoint(1,1,1,1),
						new ScenePoint(2,2,2,2),
						new ScenePoint(3,3,3,3)
						)
				).Points[1].X,2.0);
		
		/**/
		
		assertEquals(
				(new Face(
						new ScenePoint(1,1,1,1),
						new ScenePoint(2,2,2,2),
						new ScenePoint(3,3,3,3)
						)
				).Points[2].Y,3.0);
		
		/**/
		
		assertEquals(
				(new Face(
						new ScenePoint(1,1,1,1),
						new ScenePoint(2,2,2,2),
						new ScenePoint(3,3,3,-4.2)
						)
				).Points[2].Z,-4.2);	
	}
	
}
