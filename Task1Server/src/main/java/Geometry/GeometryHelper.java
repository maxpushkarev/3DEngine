package Geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import GeoRelationship.AccuracyComparer;
import GeoRelationship.PlaneEquation;
import GeoRelationship.PlaneFaceRelationship;
import GeoRelationship.PointsComparer;

public class GeometryHelper {

	 private GeometryHelper() {
	        throw new AssertionError();
	   }
	
	 
	 public static boolean IsPolygonFrontOfPlane(Face plane, Face polygon)
	 {
		for(ScenePoint point : polygon.Points)
		{
			if(!IsPointFrontOfPlane(plane,point) && !IsPointWithinPlane(plane,point))
			{
				return false;
			}
		}
		return true;
	 }

	 
	 public static boolean IsPolygonBackOfPlane(Face plane, Face polygon)
	 {
		for(ScenePoint point : polygon.Points)
		{
			if(IsPointWithinPlane(plane,point))
			{
				continue;
			}
			
			
			if(IsPointFrontOfPlane(plane,point))
			{
				return false;
			}
			
		}
		return true;
	 }
	 
	 
	 public static boolean IsRayFrontOfPlane(Face plane, Ray ray)
	 {
		 PlaneEquation eq = new PlaneEquation(plane);
		 double valueOrigin =  eq.A*ray.getOrigin().X + eq.B*ray.getOrigin().Y + eq.C*ray.getOrigin().Z + eq.D;
		 double valueDirection =  eq.A*ray.getDirection().X + eq.B*ray.getDirection().Y + eq.C*ray.getDirection().Z + eq.D;
		 
		 return ((valueDirection - valueOrigin) > 0);
	 }
	 
	 
	 public static boolean IsPointFrontOfPlane(Face plane, ScenePoint point)
	 {
		 PlaneEquation eq = new PlaneEquation(plane);
		 double value =  eq.A*point.X + eq.B*point.Y + eq.C*point.Z + eq.D;
		 return (Math.abs(value)/eq.MAX>=AccuracyComparer.THRESHOLD)&&(value > 0);
	 }
	 
	 
	 public static boolean IsPointWithinHouse(ScenePoint point, Scene scene)
	 {
		 
		return point.X<=scene.MaxX && 
			   point.Y<=scene.MaxY &&
			   point.Z<=scene.MaxZ &&
			   point.X>=scene.MinX && 
			   point.Y>=scene.MinY &&
			   point.Z>=scene.MinZ; 
	 }
	 
	 
	 public static boolean IsIntersectionRayAndHouse(Ray ray, Scene scene)
	 {
		 ScenePoint A = new ScenePoint(scene.MinX,scene.MinY, scene.MaxZ);
		 ScenePoint B = new ScenePoint(scene.MinX,scene.MinY, scene.MinZ);
		 ScenePoint C = new ScenePoint(scene.MaxX,scene.MinY, scene.MinZ);
		 ScenePoint D = new ScenePoint(scene.MaxX,scene.MinY, scene.MaxZ);
		 
		 ScenePoint A1 = new ScenePoint(scene.MinX,scene.MaxY, scene.MaxZ);
		 ScenePoint B1 = new ScenePoint(scene.MinX,scene.MaxY, scene.MinZ);
		 ScenePoint C1 = new ScenePoint(scene.MaxX,scene.MaxY, scene.MinZ);
		 ScenePoint D1 = new ScenePoint(scene.MaxX,scene.MaxY, scene.MaxZ);
		
		 //floor
		 boolean checkA_B_C_D = IsIntersectionRayAndFace(ray, new Face(A,B,C)) || 
				 IsIntersectionRayAndFace(ray, new Face(A,C,D));
		 
		 //roof
		 boolean checkA1_B1_C1_D1 = IsIntersectionRayAndFace(ray, new Face(A1,B1,C1)) || 
				 IsIntersectionRayAndFace(ray, new Face(A1,C1,D1));
		 
		 //left
		 boolean checkA_A1_B1_B = IsIntersectionRayAndFace(ray, new Face(A,A1,B1)) || 
				 IsIntersectionRayAndFace(ray, new Face(A,B1,B));
		 
		 //right
		 boolean checkD_D1_C1_C = IsIntersectionRayAndFace(ray, new Face(D,D1,C1)) || 
				 IsIntersectionRayAndFace(ray, new Face(D,C1,C));
		 
		 //front
		 boolean checkA_A1_C1_D = IsIntersectionRayAndFace(ray, new Face(A,A1,C1)) || 
				 IsIntersectionRayAndFace(ray, new Face(A,C1,D));
		 
		 //back
		 boolean checkB_B1_C1_C = IsIntersectionRayAndFace(ray, new Face(B,B1,C1)) || 
				 IsIntersectionRayAndFace(ray, new Face(B,C1,C));
		 
		 
		 return  checkA_B_C_D || 
				 checkA1_B1_C1_D1 ||
				 checkA_A1_B1_B || 
				 checkD_D1_C1_C ||
				 checkA_A1_C1_D ||
				 checkB_B1_C1_C;
	 }
	 
	 
	 public static boolean IsIntersectionRayAndObj(Ray ray, SceneObject obj)
	 {
		 for( Face face : obj.Faces)
		 {
			 if(IsIntersectionRayAndFace(ray,face))
			 {
				 return true;
			 }
		 }
		 
		 return false;
	 }
 
	 
	 public static ScenePoint GetNearestPointToOrigin(ScenePoint point, ArrayList<ScenePoint> points)
	 {
		 Collections.sort(points,new PointsComparer(point));
		 return points.get(0);
	 }
	 
	 
	 public static ScenePoint GetIntersectionPointBetweenObjAndRay(Ray ray, SceneObject obj)
	 {
		 ArrayList<ScenePoint> intersectList = new ArrayList<ScenePoint>();
		 
		 for( Face face : obj.Faces)
		 {
			 ScenePoint p = GetIntersectionPointBetweenFaceAndRay(ray,face);
			 if(p!=null)
			 {
				 intersectList.add(p);
			 }
		 }
		 
		 if(intersectList.size()>0)
		 {
			 return GetNearestPointToOrigin(ray.getOrigin(), intersectList);
		 }
		 
		 return null;
	 }
	 
	 
	 //triangulations of multiple-vertex faces
	 public static ArrayList<Face> TriangulateFace(Face face)
	 {
		 ArrayList<Face> triangles = new ArrayList<Face>();
		 
		 if (face.Points.size()==3)
		 {
			 triangles.add(face);
			 return triangles;
		 }
		 
		 int i=1;
		 int quantityPoints = face.Points.size();
		 ScenePoint startPoint = face.Points.get(0);
		 while (i+1<quantityPoints)
		 {
			 triangles.add(new Face(	 
					 startPoint, 
					 face.Points.get(i),
					 face.Points.get(i+1)
					 ));
			 i++;
		 }
		 
		 
		 return triangles;
	 }
	 
	 public static ScenePoint GetIntersectionPointBetweenFaceAndRay(Ray ray, Face face)
	 {
		 ArrayList<Face> triangles = TriangulateFace(face);
		 ScenePoint resPoint = null;
		 for(Face triangle : triangles)
		 {
			 resPoint = GetIntersectionPointBetweenTriangleFaceAndRay(ray,triangle);
			 if(resPoint!=null)
			 {
				 return resPoint;
			 }
		 }
		 return null;
	 }
	 
	 
	 public static boolean IsCoplanarPolygon(Face plane, Face polygon)
	 {
		 
	        //check all polygon's points within plane
	        for(ScenePoint point : polygon.Points)
	        {
	        	if(!IsPointWithinPlane(plane, point))
	        	{
	        		return false;
	        	}
	        }
	        
	        
	        
	        return true;
	        
	 }
	 
	 
	 public static boolean IsPointWithinPlane(Face plane, ScenePoint point)
	 {
		 PlaneEquation eq = new PlaneEquation(plane);
		 double value = ( eq.A*point.X + eq.B*point.Y + eq.C*point.Z + eq.D );
		 return (Math.abs(value)/eq.MAX < AccuracyComparer.THRESHOLD);
	 }
	  
	 
	 private static boolean CalculateSplit(int start, int end, 
			 Face plane, Face polygon, HashMap<String, ScenePoint> commonPoints)
	 {
		 
		 ScenePoint startPoint ;
		 ScenePoint endPoint;
		 ScenePoint intersect;
		 
		 startPoint = polygon.Points.get(start);
		 endPoint = polygon.Points.get(end);
		 		 
		 //case when plane and polygon have the common tangent (this is not splitting)
		 if(IsPointWithinPlane(plane,startPoint) 
				 && IsPointWithinPlane(plane,endPoint))
		 {
			 return false;
		 }
		 
		 
		 intersect = GetIntersectionBetweenSegmentAndPlane(plane, startPoint, endPoint);
		 
		 if(intersect != null)
		 {
			 
			 if(!AccuracyComparer.IsPointRepeated(intersect,commonPoints.values()))
			 {
				 commonPoints.put(intersect.toString(), intersect);
			 }
		 }
		 
		 return true;
	 }
	 
	 
	 public static boolean IsPlaneFaceTangentLine(Face plane, Face face)
	 {
		 
		 int i = 0;
		 while(i<face.Points.size()-1)
		 {
			 if( IsPointWithinPlane(plane,face.Points.get(i)) &&
					 IsPointWithinPlane(plane,face.Points.get(i+1))
					 )
			 {
				 return true;
			 }
			 
			 i++;
		 }
		 
		 
		 if( IsPointWithinPlane(plane,face.Points.get(0)) &&
				 IsPointWithinPlane(plane,face.Points.get(i))
				 )
		 {
			 return true;
		 }
		 
		 
		 return false;
	 }
	 
	 public static boolean IsPlaneFaceTangentPoint(Face plane, Face face)
	 {
		 ArrayList<ScenePoint> common = new  ArrayList<ScenePoint>();
		 
		 for(ScenePoint point : face.Points)
		 {
			 if(IsPointWithinPlane(plane,point))
			 {
				 common.add(point);
			 }
		 }
		 
		 return (common.size()==1);
		 
	 }
	 
	 public static  PlaneFaceRelationship InfoPolygonSplittedByPlane(Face plane, Face polygon)
	 {
		 int i = 0 ;
		 
		 HashMap<String, ScenePoint> commonPoints = new HashMap<String, ScenePoint>();
		 
		 //check all segments of polygon
		 while(i+1 < polygon.Points.size())
		 {
			 if(!CalculateSplit(i,i+1,plane,polygon, commonPoints))
			 {
				 return new PlaneFaceRelationship(false);
			 }
			 
			 i++;
		 }
		 
		 if(!CalculateSplit(0,i,plane,polygon, commonPoints))
		 {
			 return new PlaneFaceRelationship(false);
		 }
		 		 
		 return new PlaneFaceRelationship(commonPoints);
	 }
	 
	 
	 public static ScenePoint GetIntersectionBetweenSegmentAndPlane(Face plane, ScenePoint one, ScenePoint two)
	 {
		 	double Xa = one.X;
		 	double Ya = one.Y;
		 	double Za = one.Z;
		 	double k;
		 	
		 	PlaneEquation eq = new PlaneEquation(plane);
		 	
		 	//A,B,C,D from Ax+By+Cz+d=0
	        double A = eq.A;
	        double B = eq.B;
	        double C = eq.C;
	        double D = eq.D;
	        
	        
	      //case when plane and polygon have the common tangent
			 if(IsPointWithinPlane(plane,one) 
					 && IsPointWithinPlane(plane,two))
			 {
				 return null;
			 }
	        
	        
	        try
			 {
				//for parallel it'will be an exception 
		       k=-(A*Xa+B*Ya+C*Za+D)/(A*(two.X - one.X)+B*(two.Y - one.Y)+C*(two.Z - one.Z));
			 }
			 catch(Exception e)
			 {
				 return null;
			 }
	        
	        if(Math.abs(k)>1 || k<0)
	        {
	        	return null;
	        }
	        
	        double X0=k*(two.X - one.X)+Xa; 
		    double Y0=k*(two.Y - one.Y)+Ya; 
		    double Z0=k*(two.Z - one.Z)+Za;
		      
		    ScenePoint resPoint = new ScenePoint(X0,Y0,Z0);
		    
		    return resPoint;
	        
	 }
	 
	 
	 public static ScenePoint GetIntersectionPointBetweenTriangleFaceAndRay(Ray ray, Face face)
	 {
		 
		 	double Xa = ray.getOrigin().X;
		 	double Ya = ray.getOrigin().Y;
		 	double Za = ray.getOrigin().Z;
		 	double k;
		 	
		 	PlaneEquation eq = new PlaneEquation(face);
		 	
		 	//A,B,C,D from Ax+By+Cz+d=0
	        double A = eq.A;
	        double B = eq.B;
	        double C = eq.C;
	        double D = eq.D;
	        
	        
	        try
			 {
				//for parallel it'will be an exception 
		       k=-(A*Xa+B*Ya+C*Za+D)/(A*ray.RayVector.X+B*ray.RayVector.Y+C*ray.RayVector.Z);
			 }
			 catch(Exception e)
			 {
				 return null;
			 }
			 
		       if(k<0) //it's for line - not for ray!
		       {
		    	   return null;
		       }
		       
		       	  double X0=k*ray.RayVector.X+Xa; 
			      double Y0=k*ray.RayVector.Y+Ya; 
			      double Z0=k*ray.RayVector.Z+Za;
			      
			      ScenePoint resPoint = new ScenePoint(X0,Y0,Z0);
			      
			      if(IsPointInsideTriangleFace(resPoint,face))
			      {
			    	  if(IsPointWithinPlane(face,resPoint) && IsPointWithinPlane(face,ray.getOrigin()))
			    	  {
			    		  return null;
			    	  }
			    	  
			    	  return resPoint;
			      }
			      else
			      {
			    	  return null;
			      }
			      
	        
		 
	 }
	 
	 public static boolean IsIntersectionRayAndFace(Ray ray, Face face) {

		ScenePoint point = GetIntersectionPointBetweenFaceAndRay(ray,face);
		return (point != null);
		 
	 }
	 

	 public static double GetDistanceBetweenTwoPoints(ScenePoint p1, ScenePoint p2)
	 {
		 return Math.sqrt( (p2.X-p1.X)*(p2.X-p1.X) + (p2.Y-p1.Y)*(p2.Y-p1.Y) + (p2.Z-p1.Z)*(p2.Z-p1.Z) );
	 }
	 
	 
	public static boolean IsPointInsideTriangleFace(ScenePoint point, Face face) {

	        double s0 = CalculateAreaOfTriangleFace(face.Points.get(0), face.Points.get(1), face.Points.get(2));
	        double s1 = CalculateAreaOfTriangleFace(face.Points.get(0), face.Points.get(1), point);
	        double s2 = CalculateAreaOfTriangleFace(face.Points.get(0), face.Points.get(2), point);
	        double s3 = CalculateAreaOfTriangleFace(face.Points.get(2), face.Points.get(1), point);
	        return Math.abs(s0 - s1 - s2 - s3) < 1f;
	    }
	 
	 
	 public static double CalculateAreaOfTriangleFace(ScenePoint a, ScenePoint b, ScenePoint c) {
		 
		 	double px = b.X - a.X;
	        double py = b.Y - a.Y;
	        double pz = b.Z - a.Z;

	        double qx = c.X - a.X;
	        double qy = c.Y - a.Y;
	        double qz = c.Z - a.Z;

	        double x = py * qz - pz * qy;
	        double y = pz * qx - px * qz;
	        double z = px * qy - py * qx;

	        double s = (double) (.5 * Math.sqrt(x * x + y * y + z * z));
	        return Math.abs(s);

	    }
	 
	 
}
