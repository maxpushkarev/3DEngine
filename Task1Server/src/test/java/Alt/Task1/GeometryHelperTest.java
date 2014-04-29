package Alt.Task1;

import java.util.ArrayList;

import Geometry.Face;
import Geometry.GeometryHelper;
import Geometry.Ray;
import Geometry.Scene;
import Geometry.SceneObject;
import Geometry.ScenePoint;
import junit.framework.TestCase;

public class GeometryHelperTest extends TestCase {

	public GeometryHelperTest( String testName) {
        super(testName);
    }
	
	public void testIsPointWithinHouse()
	{
		Scene scene = new Scene();
		
		scene.MaxX=10;
		scene.MaxY=10;
		scene.MaxZ=10;
			
		
		scene.MinX=-10;
		scene.MinY=-10;
		scene.MinZ=-10;
		
		assertEquals(GeometryHelper.IsPointWithinHouse((new ScenePoint(0,0,0)), scene),true);
		assertEquals(GeometryHelper.IsPointWithinHouse((new ScenePoint(20,0,0)), scene),false);
	
	}
	
	
	public void testGetIntersectionPointBetweenFaceAndRay()
	{
		Face face = new Face(
				new ScenePoint(0,0,0),
				new ScenePoint(2,0,0),
				new ScenePoint(0,2,0)
				);
		
		Ray ray = new Ray(0,new ScenePoint(0,0,1), new ScenePoint(0,0,0));
		assertEquals(GeometryHelper.GetIntersectionPointBetweenFaceAndRay(ray, face).EqualPoint(new ScenePoint(0,0,0)),true);
	}
	
	
	public void testNearestPoint()
	{
		ScenePoint origin = new ScenePoint(0,0,0);
		ArrayList<ScenePoint> list = new ArrayList<ScenePoint>();
		ArrayList<ScenePoint> list2 = new ArrayList<ScenePoint>();
		
		list.add(new ScenePoint(0,0,9));
		list.add(new ScenePoint(0,0,5));
		list.add(new ScenePoint(0,0,7));
		
		list2.add(new ScenePoint(1.0,0.5,-1.0));
		list2.add(new ScenePoint(1.0,0.5,0.0));
		
		ScenePoint nearestPoint = GeometryHelper.GetNearestPointToOrigin(origin, list);
		ScenePoint nearestPoint2 = GeometryHelper.GetNearestPointToOrigin(new ScenePoint(1.0,0.5,4.0), list2);
		
		assertEquals(nearestPoint.EqualPoint(new ScenePoint(0,0,5)),true);
		assertEquals(nearestPoint2.EqualPoint(new ScenePoint(1.0,0.5,0.0)),true);
		
	}
	
	
	
	public void testGetDistanceBetweenTwoPoints()
	{
		ScenePoint p1 = new ScenePoint(0,0,0);
		ScenePoint p2 = new ScenePoint(2,0,0);
		
		ScenePoint p3 = new ScenePoint(1.0,0.5,-1.0);
		ScenePoint p4 = new ScenePoint(1.0,0.5,0.0);
		
		ScenePoint p5 = new ScenePoint(1.0,0.5,4.0);
		
		assertEquals(GeometryHelper.GetDistanceBetweenTwoPoints(p1, p2),2.0);
		assertEquals(GeometryHelper.GetDistanceBetweenTwoPoints(p5, p4),4.0);
		assertEquals(GeometryHelper.GetDistanceBetweenTwoPoints(p5, p3),5.0);
		
	}
	
	
	public void testGetIntersectionPointBetweenObjAndRay()
	{
		
		ScenePoint sp1 = new ScenePoint(0.0,0.0,-1.0);
		ScenePoint sp2 = new ScenePoint(2.0,0.0,-1.0);
		ScenePoint sp3 = new ScenePoint(0.0,2.0,-1.0);
		
		ScenePoint sp4 = new ScenePoint(0.0,0.0,0.0);
		ScenePoint sp5 = new ScenePoint(2.0,0.0,0.0);
		ScenePoint sp6 = new ScenePoint(0.0,2.0,0.0);
		
		Face face1 = new Face(sp1,sp2,sp3);
		Face face2 = new Face(sp4,sp5,sp6);
		
		SceneObject obj = new SceneObject();
		obj.AddFace(face2);
		obj.AddFace(face1);
	
		
		Ray ray1 = new Ray(0, new ScenePoint(1.0,0.5,4.0), new ScenePoint(1.0,0.5,3.0));
		
		/*turn over 180 degrees*/
		Ray ray2 = new Ray(0, new ScenePoint(1.0,0.5,3.0), new ScenePoint(1.0,0.5,4.0));
		
		ScenePoint intersect1 = GeometryHelper.GetIntersectionPointBetweenFaceAndRay(ray1, face1);
		ScenePoint intersect2 = GeometryHelper.GetIntersectionPointBetweenFaceAndRay(ray1, face2);
		ScenePoint nearest = GeometryHelper.GetIntersectionPointBetweenObjAndRay(ray1, obj);
		ScenePoint nearest2 = GeometryHelper.GetIntersectionPointBetweenObjAndRay(ray2, obj);
		
		assertEquals(intersect1.EqualPoint(new ScenePoint(1.0,0.5,-1.0)),true);
		assertEquals(intersect2.EqualPoint(new ScenePoint(1.0,0.5,0.0)),true);
		
		/*nearest intersection*/
		assertEquals(nearest.EqualPoint(new ScenePoint(1.0,0.5,0.0)),true);
		/*no intersection*/
		assertEquals((nearest2 == null),true);
	
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
	
	
	public void testIntersectionObj()
	{
		/*Pyramid from Example1.xml*/
		ScenePoint sp1 = new ScenePoint(1,-1.8,-1.8,0);
		ScenePoint sp2 = new ScenePoint(2,1.8,-1.8,0.0);
		ScenePoint sp3 = new ScenePoint(3,0.0,1.8,0.0);
		ScenePoint sp4 = new ScenePoint(4,0.0,0.0,1.2);
		
		Face face1 = new Face(1,sp1,sp2,sp3);
		Face face2 = new Face(2,sp1,sp2,sp4);
		Face face3 = new Face(3,sp1,sp4,sp3);
		Face face4 = new Face(4,sp4,sp2,sp3);
		
		SceneObject sobj = new SceneObject();
		sobj.AddFace(face1);
		sobj.AddFace(face2);
		sobj.AddFace(face3);
		sobj.AddFace(face4);
		
		
		/*tests from task*/
		Ray ray1 = new Ray("1;1,-0.5,5;0,0,-1");
		Ray ray2 = new Ray("2;-1,-0.5,5;0,0,-100");
		
		//vertical ray (no intersection)
		Ray ray3 = new Ray("3;0.0,0.5,10;0.0,50,10");
		
		assertEquals(GeometryHelper.IsIntersectionRayAndObj(ray1, sobj),true);
		assertEquals(GeometryHelper.IsIntersectionRayAndObj(ray2, sobj),true);
		assertEquals(GeometryHelper.IsIntersectionRayAndObj(ray3, sobj),false);
		
	}
	
	
	public void testIsIntersectionRayAndHouse()
	{
		
		Scene scene = new Scene();
		
		scene.MaxX=10;
		scene.MaxY=10;
		scene.MaxZ=10;
			
		scene.MinX=-10;
		scene.MinY=-10;
		scene.MinZ=-10;
		
		
		//parallel
		Ray ray = new Ray(0, 
				new ScenePoint(0.0,0.0,130.0), 
				new ScenePoint(55.0,0.0,130.0)
		);
		
		//cross
		Ray ray1 = new Ray(0, 
				new ScenePoint(-55.0,0.0,-130.0), 
				new ScenePoint(55.0,0.0,130.0)
		);
		
		
		assertEquals(GeometryHelper.IsIntersectionRayAndHouse(ray, scene),false);
		assertEquals(GeometryHelper.IsIntersectionRayAndHouse(ray1, scene),true);
		
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
