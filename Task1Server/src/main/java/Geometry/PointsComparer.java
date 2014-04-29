package Geometry;

import java.util.Comparator;

public class PointsComparer implements Comparator<ScenePoint>
{

	 ScenePoint Origin;
	 
	 public PointsComparer(ScenePoint point)
	 {
		 this.Origin = point;
	 }

	public int compare(ScenePoint one, ScenePoint another) {
		
		
		double distanceOne = GeometryHelper.GetDistanceBetweenTwoPoints(this.Origin, one);
		double distanceAnother = GeometryHelper.GetDistanceBetweenTwoPoints(this.Origin, another);
			
		 if (distanceOne > distanceAnother)
		 {
            return 1;
		 }
		 else if (distanceOne <  distanceAnother)
		 {
			 return -1;
		 }
		 else
		 {
			 return 0;
		 }
         
	}

}