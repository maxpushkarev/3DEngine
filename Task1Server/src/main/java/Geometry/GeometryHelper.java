package Geometry;


public class GeometryHelper {

	 private GeometryHelper() {
	        throw new AssertionError();
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
	 
	 
	 
	 public static boolean IsIntersectionRayAndFace(Ray ray,
	            Face face) {
		 
		 	double Xa = ray.getOrigin().X;
		 	double Ya = ray.getOrigin().Y;
		 	double Za = ray.getOrigin().Z;
		 	double k;
		 	
		 	
		 	//a,b,c,d from Ax+By+Cz+d=0
	        double A = face.Points[0].Y
	                * (face.Points[1].Z - face.Points[2].Z)
	                + face.Points[1].Y
	                * (face.Points[2].Z - face.Points[0].Z)
	                + face.Points[2].Y
	                * (face.Points[0].Z - face.Points[1].Z);
	        
	        
	        double B = face.Points[0].Z
	                * (face.Points[1].X - face.Points[2].X)
	                + face.Points[1].Z
	                * (face.Points[2].X - face.Points[0].X)
	                + face.Points[2].Z
	                * (face.Points[0].X - face.Points[1].X);
	        
	        double C = face.Points[0].X
	                * (face.Points[1].Y - face.Points[2].Y)
	                + face.Points[1].X
	                * (face.Points[2].Y - face.Points[0].Y)
	                + face.Points[2].X
	                * (face.Points[0].Y - face.Points[1].Y);
	        
	        double D = -face.Points[0].X
	                * (face.Points[1].Y * face.Points[2].Z - face
	                        .Points[2].Y
	                        * face.Points[1].Z)
	                - face.Points[1].X
	                * (face.Points[2].Y * face.Points[0].Z - face
	                        .Points[0].Y
	                        * face.Points[2].Z)
	                - face.Points[2].X
	                * (face.Points[0].Y * face.Points[1].Z - face
	                        .Points[1].Y
	                        * face.Points[0].Z);
		 
		 try
		 {
			//for parallel it'will be an exception 
	       k=-(A*Xa+B*Ya+C*Za+D)/(A*ray.RayVector.X+B*ray.RayVector.Y+C*ray.RayVector.Z);
		 }
		 catch(Exception e)
		 {
			 return false;
		 }
		 
	       if(k<0) //it's for line - not for ray!
	       {
	    	   return false;
	       }
	       
	       
	      double X0=k*ray.RayVector.X+Xa; 
	      double Y0=k*ray.RayVector.Y+Ya; 
	      double Z0=k*ray.RayVector.Z+Za;
		 
	      //check
	      return IsPointInsideFace(new ScenePoint(X0, Y0, Z0),face);
		 
	    }
	 
	 

	 
	 
	public static boolean IsPointInsideFace(ScenePoint point, Face face) {

	        double s0 = CalculateAreaOfFace(face.Points[0], face.Points[1], face
	                .Points[2]);
	        double s1 = CalculateAreaOfFace(face.Points[0], face.Points[1], point);
	        double s2 = CalculateAreaOfFace(face.Points[0], face.Points[2], point);
	        double s3 = CalculateAreaOfFace(face.Points[2], face.Points[1], point);
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
