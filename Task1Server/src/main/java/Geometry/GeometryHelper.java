package Geometry;

import java.util.ArrayList;
import java.util.Collections;

public class GeometryHelper {

	 private GeometryHelper() {
	        throw new AssertionError();
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
		 
		PlaneEquation eq = new PlaneEquation(plane);
		 
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
		 return (Math.abs(( eq.A*point.X + eq.B*point.Y + eq.C*point.Z + eq.D )) < 1f);
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
			      
			      if(IsPointInsideFace(resPoint,face))
			      {
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
	 
	 
	public static boolean IsPointInsideFace(ScenePoint point, Face face) {

	        double s0 = CalculateAreaOfFace(face.Points.get(0), face.Points.get(1), face.Points.get(2));
	        double s1 = CalculateAreaOfFace(face.Points.get(0), face.Points.get(1), point);
	        double s2 = CalculateAreaOfFace(face.Points.get(0), face.Points.get(2), point);
	        double s3 = CalculateAreaOfFace(face.Points.get(2), face.Points.get(1), point);
	        return Math.abs(s0 - s1 - s2 - s3) < 1f;
	    }
	 
	 
	 public static double CalculateAreaOfFace(ScenePoint a, ScenePoint b, ScenePoint c) {
		 
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
