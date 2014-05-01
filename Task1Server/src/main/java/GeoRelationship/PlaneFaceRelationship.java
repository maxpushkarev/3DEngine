package GeoRelationship;

import java.util.HashMap;

import Geometry.ScenePoint;

public class PlaneFaceRelationship {
	
	public HashMap<String, ScenePoint> CommonPoints;
	public boolean IsSplitted;
	
	public PlaneFaceRelationship(boolean result)
	{
		this.IsSplitted = result;
		this.CommonPoints = null;
	}
	
	
	public PlaneFaceRelationship(HashMap<String, ScenePoint> commonPoints)
	{
		this.CommonPoints = commonPoints;
		this.IsSplitted = (commonPoints.size()==2);
	}
	
}
