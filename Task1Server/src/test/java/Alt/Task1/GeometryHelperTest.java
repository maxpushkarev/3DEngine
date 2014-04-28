package Alt.Task1;

import Geometry.Face;
import Geometry.GeometryHelper;
import Geometry.Ray;
import Geometry.ScenePoint;
import junit.framework.TestCase;

public class GeometryHelperTest extends TestCase {

	public GeometryHelperTest( String testName) {
        super(testName);
    }
	
	public void testTriangleSquare()
	{
		assertEquals(GeometryHelper.CalculateAreaOfFace(new ScenePoint(0, 0, 0),new ScenePoint(2.0, 0, 0),new ScenePoint(0, 2.0, 0) ),2.0);
	}
	
	public void testIsInFace()
	{
		Face face = new Face(
				new ScenePoint(0,0,0),
				new ScenePoint(2,0,0),
				new ScenePoint(0,2,0)
				);
		
		assertEquals(GeometryHelper.IsPointInsideFace(new ScenePoint(0.5,0.5,0),face),true);
		assertEquals(GeometryHelper.IsPointInsideFace(new ScenePoint(66.676,0.5,0),face),false);
	}
	
	
	public void testIsIntersectionRayAndFace()
	{
		//test face
		Face face = new Face(
				new ScenePoint(0,0,0),
				new ScenePoint(2,0,0),
				new ScenePoint(0,2,0)
				);
		
		//towards
		Ray ray = new Ray(0, 
				new ScenePoint(0.5,0.5,1.0), 
				new ScenePoint(0.5,0.5,-2.0)
		);
		
		//parallel
		Ray ray1 = new Ray(0, 
				new ScenePoint(0.5,0.5,1.0), 
				new ScenePoint(0.5,6.5,1.0)
		);
		
		//backward
				Ray ray2 = new Ray(0, 
						new ScenePoint(0.5,0.5,-2.0), 
						new ScenePoint(0.5,0.5,-3.0)
				);
		
		assertEquals(GeometryHelper.IsIntersectionRayAndFace(ray, face),true);
		assertEquals(GeometryHelper.IsIntersectionRayAndFace(ray1, face),false);
		assertEquals(GeometryHelper.IsIntersectionRayAndFace(ray2, face),false);
	}
}
