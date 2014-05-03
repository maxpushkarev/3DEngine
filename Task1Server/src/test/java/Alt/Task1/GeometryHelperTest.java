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
	
	
	
	public void testIsPlaneFaceTangentLine()
	{
		Face plane = new Face( 
				new ScenePoint(-1,0,0),  
				new ScenePoint(1,0,0), 
				new ScenePoint(0,0,1) );
		
		Face polygon = new Face(
				
				new ScenePoint(0,0,0),
				new ScenePoint(0.5,0,0),
				new ScenePoint(10,10,10)
				
				);
		
		
		assertEquals(GeometryHelper.IsPlaneFaceTangentLine(plane,polygon),true);
		polygon.Points.get(0).Y = -1;
		assertEquals(GeometryHelper.IsPlaneFaceTangentLine(plane,polygon),false);
	
	}
	
	
	public void testIsPlaneFaceTangentPoint()
	{
		Face plane = new Face( 
				new ScenePoint(-1,0,0),  
				new ScenePoint(1,0,0), 
				new ScenePoint(0,0,1) );
		
		Face polygon = new Face(
				
				new ScenePoint(0,0,0),
				new ScenePoint(10,10,0),
				new ScenePoint(10,10,10)
				
				);
		
		assertEquals(GeometryHelper.IsPlaneFaceTangentPoint(plane,polygon),true);
		polygon.Points.get(0).Y = -155;
		assertEquals(GeometryHelper.IsPlaneFaceTangentPoint(plane,polygon),false);
		
	}
	
	
	public void testIsPointWithinPlane()
	{
		Face splitter = new Face(
				
				new ScenePoint(-147.22,-56.034, -283.94),
				new ScenePoint(-166.12,-51.83, -338.93),
				new ScenePoint(-125.27,-44.67, -335.54)
				
				);
		
		
Face splitter1 = new Face(
				
				new ScenePoint(893.72, 178.29, 412.84),
				new ScenePoint(893.72, 121.29, 354.45),
				new ScenePoint(871.85, 121.29, 412.84)
				
				);
		

		assertEquals(GeometryHelper.IsPointWithinPlane(splitter1, new ScenePoint(909.18, 161.59, 354.45)),true);
		assertEquals(GeometryHelper.IsPointWithinPlane(splitter, new ScenePoint(-125.27,-44.67, -335.54)),true);
		assertEquals(GeometryHelper.IsPointWithinPlane(splitter, new ScenePoint(-147.22,-56.034, -283.94)),true);
		assertEquals(GeometryHelper.IsPointWithinPlane(splitter, new ScenePoint(-166.12,-51.83, -338.93)),true);
	}
	
	public void testIsPolygonFrontOfPlane()
	{
		Face plane = new Face( 
				new ScenePoint(-1,0,0),  
				new ScenePoint(1,0,0), 
				new ScenePoint(0,0,1) );
		
		Face frontFace =  new Face( 
				new ScenePoint(-1,-1,0),  
				new ScenePoint(1,-1,0), 
				new ScenePoint(0,-1,1) );
		
		Face backFace =  new Face( 
				new ScenePoint(-1,1,0),  
				new ScenePoint(1,1,0), 
				new ScenePoint(0,1,1) );
		
		Face splitFace =  new Face( 
				new ScenePoint(-1,1,0),  
				new ScenePoint(1,-1,0), 
				new ScenePoint(0,1,1) );
		
		Face tangentPointFace = new Face( 
				new ScenePoint(-1,-1,0),  
				new ScenePoint(1,0,0), 
				new ScenePoint(0,-1,1) );
		
		Face tangentPointFaceBack = new Face( 
				new ScenePoint(-1,601,0),  
				new ScenePoint(1,0,0), 
				new ScenePoint(0,3453451,1) );
		
		Face tangentLineFace = new Face( 
				new ScenePoint(-1,0,0),  
				new ScenePoint(1,0,0), 
				new ScenePoint(0,-5,1) );
		
		Face tangentLineFaceBack = new Face( 
				new ScenePoint(-1,0,0),  
				new ScenePoint(1,0,0), 
				new ScenePoint(0,5,1) );
		
		
		
		assertEquals(GeometryHelper.IsPolygonBackOfPlane(plane, backFace),true);
		
		assertEquals(GeometryHelper.IsPolygonFrontOfPlane(plane, frontFace),true);
		assertEquals(GeometryHelper.IsPolygonFrontOfPlane(plane, backFace),false);
		assertEquals(GeometryHelper.IsPolygonFrontOfPlane(plane, splitFace),false);
		assertEquals(GeometryHelper.IsPolygonFrontOfPlane(plane, tangentPointFace),true);
		assertEquals(GeometryHelper.IsPolygonFrontOfPlane(plane, tangentPointFaceBack),false);
		assertEquals(GeometryHelper.IsPolygonFrontOfPlane(plane, tangentLineFace),true);
		assertEquals(GeometryHelper.IsPolygonFrontOfPlane(plane, tangentLineFaceBack),false);
	}
	
	
	public void testIsRayFrontOfPlane()
	{
		Face plane = new Face( new ScenePoint(-1,0,0),  new ScenePoint(1,0,0), new ScenePoint(0,0,1) );
		
		Ray rayFront = new Ray(0, new ScenePoint(0,2,0), new ScenePoint(0,1,0));
		Ray rayBack = new Ray(0, new ScenePoint(0,1,0), new ScenePoint(0,2,0));
		
		assertEquals(GeometryHelper.IsRayFrontOfPlane(plane, rayFront),true);
		assertEquals(GeometryHelper.IsRayFrontOfPlane(plane, rayBack),false);
		
	}
	
	
	public void testIsPointFrontOfPlane()
	{
		Face plane = new Face( new ScenePoint(-1,0,0),  new ScenePoint(1,0,0), new ScenePoint(0,0,1) );
		
		ScenePoint pointFront = new ScenePoint(0,-1,0);
		ScenePoint pointBack = new ScenePoint(0,1,0);
		ScenePoint pointWithin = new ScenePoint(0,0,0);
		
		assertEquals(GeometryHelper.IsPointFrontOfPlane(plane, pointFront),true);
		assertEquals(GeometryHelper.IsPointFrontOfPlane(plane, pointBack),false);
		assertEquals(GeometryHelper.IsPointFrontOfPlane(plane, pointWithin),false);
	}
	
	
	public void testIsPolygonSplittedByPlane()
	{
		Face plane = new Face( new ScenePoint(-1,0,0),  new ScenePoint(1,0,0), new ScenePoint(0,0,1) );
		Face polygonParallel = new Face(new ScenePoint(-1,50,0),  new ScenePoint(1,50,0), new ScenePoint(0,50,1));
		Face polygonCustomUp = new Face(new ScenePoint(-1,50,0),  new ScenePoint(1,30,0), new ScenePoint(0,50,1));
		Face polygonCustomCross = new Face(new ScenePoint(-1,50,0),  new ScenePoint(1,-30,0), new ScenePoint(0,50,1));
		Face polygonCustomCross1 = new Face(new ScenePoint(-41,-50,0),  new ScenePoint(0,50,3), new ScenePoint(3,50,3));
		Face polygonCustomCross2 = new Face(new ScenePoint(-343,-50,66),  new ScenePoint(40,50,-300), new ScenePoint(50,60,300));
		Face polygonCustomCross3 = new Face(new ScenePoint(-1,2,0),  new ScenePoint(0,-1,0), new ScenePoint(0,2,1));
		Face polygonCustomPointTangent = new Face(new ScenePoint(-1,2,0),  new ScenePoint(0,0,0), new ScenePoint(0,2,1));
		Face polygonCustomSegmentTangent = new Face(new ScenePoint(-50,0,0),  new ScenePoint(60,0,0), new ScenePoint(0,2,0));
		
		Face face = new Face(
				
				new ScenePoint(999,64,412),
				new ScenePoint(999,121,354),
				new ScenePoint(1021,121,412)
				
				);
		
		Face splitter = new Face(
				
				new ScenePoint(-147.22,-56.034, -283.94),
				new ScenePoint(-166.12,-51.83, -338.93),
				new ScenePoint(-125.27,-44.67, -335.54)
				
				);
		//----------------------------------------
		
		Face splitter1 = new Face(
				
				new ScenePoint(893.72, 178.29, 412.84),
				new ScenePoint(893.72, 121.29, 354.45),
				new ScenePoint(871.85,121.29, 412.84)
				
				);
		
		Face faceTest = new Face(
				
				new ScenePoint(946.52,121.29,330.26),
				new ScenePoint(893.72, 121.29, 354.45),
				new ScenePoint(909.18, 161.59, 354.45)
				
				);
		
		assertEquals(GeometryHelper.InfoPolygonSplittedByPlane(splitter1, faceTest).IsSplitted,false);
		
		/*parallel*/
		assertEquals(GeometryHelper.InfoPolygonSplittedByPlane(plane, polygonParallel).IsSplitted,false);
		/*uppper polygon*/
		assertEquals(GeometryHelper.InfoPolygonSplittedByPlane(plane, polygonCustomUp).IsSplitted,false);
		
		/*crossed polygons*/
		assertEquals(GeometryHelper.InfoPolygonSplittedByPlane(plane, polygonCustomCross).IsSplitted,true);
		assertEquals(GeometryHelper.InfoPolygonSplittedByPlane(plane, polygonCustomCross1).IsSplitted,true);
		assertEquals(GeometryHelper.InfoPolygonSplittedByPlane(plane, polygonCustomCross2).IsSplitted,true);
		assertEquals(GeometryHelper.InfoPolygonSplittedByPlane(plane, polygonCustomCross3).IsSplitted,true);
		
		/*one point tangent*/
		assertEquals(GeometryHelper.InfoPolygonSplittedByPlane(plane, polygonCustomPointTangent).IsSplitted,false);
		/*one segment tangent*/
		assertEquals(GeometryHelper.InfoPolygonSplittedByPlane(plane, polygonCustomSegmentTangent).IsSplitted,false);
	
		assertEquals(GeometryHelper.InfoPolygonSplittedByPlane(splitter, face).IsSplitted,false);
		
		
		
	}
	
	public void testGetIntersectionBetweenSegmentAndPlane()
	{
		Face plane = new Face( new ScenePoint(-1,0,0),  new ScenePoint(1,0,0), new ScenePoint(0,0,1) );
		ScenePoint p1 = new ScenePoint(0,0.5,0);
		ScenePoint p2 = new ScenePoint(0,0.4,0);
		ScenePoint p3 = new ScenePoint(0,-5,0);
		ScenePoint p4 = new ScenePoint(0,0,0);
			
		
		//case when segment point is a tangent point
		assertEquals(GeometryHelper.GetIntersectionBetweenSegmentAndPlane(plane, p1, p4).EqualPoint(new ScenePoint(0,0,0)),true);
		
		assertEquals(GeometryHelper.GetIntersectionBetweenSegmentAndPlane(plane, p1, p2),null);
		assertEquals(GeometryHelper.GetIntersectionBetweenSegmentAndPlane(plane, p2, p1),null);
		assertEquals(GeometryHelper.GetIntersectionBetweenSegmentAndPlane(plane, p1, p3).EqualPoint(
				GeometryHelper.GetIntersectionBetweenSegmentAndPlane(plane, p3, p1)),true);
		
		assertEquals(GeometryHelper.GetIntersectionBetweenSegmentAndPlane(plane, p1, p3).EqualPoint(new ScenePoint(0,0,0)),true);
	}
	
	public void testIsCoplanarPolygon()
	{
		Face plane = new Face( new ScenePoint(-1,0,0),  new ScenePoint(1,0,0), new ScenePoint(0,0,1) );
		Face polygon1 = new Face( new ScenePoint(-100,0,100),  new ScenePoint(100,0,100), new ScenePoint(0,0,100));
		Face polygon2 = new Face( new ScenePoint(-100,0,100),  new ScenePoint(100,0,100), new ScenePoint(0,50,100));
		
		/*parallel*/
		Face polygon3 = new Face( new ScenePoint(-100,4,100),  new ScenePoint(100,4,100), new ScenePoint(0,4,100));
		
		assertEquals(GeometryHelper.IsCoplanarPolygon(plane, polygon1), true);
		assertEquals(GeometryHelper.IsCoplanarPolygon(plane, polygon2), false);
		assertEquals(GeometryHelper.IsCoplanarPolygon(plane, polygon3), false);
	
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
	
	
	public void testTriangulateFace()
	{
		Face face3 = new Face(
				new ScenePoint(0,0,0),
				new ScenePoint(10,0,0),
				new ScenePoint(0,10,0)
				);
		
		Face face4 = new Face(
				new ScenePoint(0,0,0),
				new ScenePoint(10,0,0),
				new ScenePoint(0,10,0)
				);
		
		face4.Points.add(new ScenePoint(10,10,0));
		
		ArrayList<ScenePoint> list = new ArrayList<ScenePoint>();
		
		list.add(new ScenePoint(0,0,0));
		list.add(new ScenePoint(10,0,0));
		list.add(new ScenePoint(0,10,0));
		list.add(new ScenePoint(10,10,0));
		list.add(new ScenePoint(5,15,0));
		
		Face face5 = new Face(0, list);
		
		//simple triangle
		assertEquals(GeometryHelper.TriangulateFace(face3).size(),1);
		
		//square
		assertEquals(GeometryHelper.TriangulateFace(face4).size(),2);
		assertEquals(GeometryHelper.TriangulateFace(face4).get(1).Points.get(2).EqualPoint(new ScenePoint(10,10,0)),true);
	
		//pentagon
		assertEquals(GeometryHelper.TriangulateFace(face5).size(),3);
		
	}
	
	
	public void testGetIntersectionPointBetweenFaceAndRay()
	{
		Face face = new Face(
				new ScenePoint(0,0,0),
				new ScenePoint(2,0,0),
				new ScenePoint(0,2,0)
				);
		
		ArrayList<ScenePoint> list = new ArrayList<ScenePoint>();
		list.add(new ScenePoint(-10,-10,0));
		list.add(new ScenePoint(-10,10,0));
		list.add(new ScenePoint(10,10,0));
		list.add(new ScenePoint(10,-10,0));
		Face square = new Face(0,list);
		
		
		Ray z = new Ray(0,new ScenePoint(0,0,-10), new ScenePoint(0,0,-9));
		Ray ray = new Ray(0,new ScenePoint(0,0,1), new ScenePoint(0,0,0));
		
		
		assertEquals(GeometryHelper.GetIntersectionPointBetweenFaceAndRay(ray, face).EqualPoint(new ScenePoint(0,0,0)),true);
	
		/*check non-triangle polygon*/
		assertEquals(GeometryHelper.GetIntersectionPointBetweenFaceAndRay(z, square).EqualPoint(new ScenePoint(0,0,0)),true);
	
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
		
		
		ScenePoint sp7 = new ScenePoint(0.0,0.0,0.0);
		ScenePoint sp8 = new ScenePoint(2.0,0.0,0.0);
		ScenePoint sp9 = new ScenePoint(0.0,0.0,-1.0);
		
		
		Face face1 = new Face(sp1,sp2,sp3);
		Face face2 = new Face(sp4,sp5,sp6);
		Face face3 = new Face(sp7,sp8,sp9);
		
		SceneObject obj = new SceneObject();
		obj.AddFace(face2);
		obj.AddFace(face1);
		obj.AddFace(face3);
	
		
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
		assertEquals(GeometryHelper.CalculateAreaOfTriangleFace(new ScenePoint(0, 0, 0),new ScenePoint(2.0, 0, 0),new ScenePoint(0, 2.0, 0) ),2.0);
	}
	
	public void testIsInFace()
	{
		Face face = new Face(
				new ScenePoint(0,0,0),
				new ScenePoint(2,0,0),
				new ScenePoint(0,2,0)
				);
		
		assertEquals(GeometryHelper.IsPointInsideTriangleFace(new ScenePoint(0.5,0.5,0),face),true);
		assertEquals(GeometryHelper.IsPointInsideTriangleFace(new ScenePoint(66.676,0.5,0),face),false);
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
				
		Ray ray3 = new Ray( 0,
				new ScenePoint(0,0,5), 
				new ScenePoint(0,1,0)	);	
		
		assertEquals(GeometryHelper.IsIntersectionRayAndFace(ray, face),true);
		assertEquals(GeometryHelper.IsIntersectionRayAndFace(ray1, face),false);
		assertEquals(GeometryHelper.IsIntersectionRayAndFace(ray2, face),false);
		
		/*tangent*/
		assertEquals(GeometryHelper.IsIntersectionRayAndFace(ray3, face),true);
	
	}
}
