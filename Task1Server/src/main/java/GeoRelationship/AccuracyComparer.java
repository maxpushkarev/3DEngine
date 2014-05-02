package GeoRelationship;

import java.util.Collection;

import Geometry.ScenePoint;

public class AccuracyComparer {

	 private AccuracyComparer() {
	        throw new AssertionError();
	 }
	
	//problem with splitting polygons because of accuracy
	public static double THRESHOLD = 0.05;
	
	public static boolean IsPointRepeated(ScenePoint point, Collection<ScenePoint> points)
	{
		
		for(ScenePoint p : points)
		{
			if(p.EqualPoint(point))
			{
				return true;
			}
		}
		
		return false;
	}
	
}
