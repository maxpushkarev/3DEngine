package Geometry;

import java.util.ArrayList;

public class Face {
	

	public int Id;
	public ArrayList<ScenePoint> Points;
	public SceneObject ReferenceObject; //reference to the object
	
	
	public Face()
	{
		this.Points = new ArrayList<ScenePoint>();
		this.Id = 0;
		this.ReferenceObject = null;
	}
	
	public Face(ScenePoint onePoint, ScenePoint twoPoint, ScenePoint threePoint)
	{
		this.Points = new ArrayList<ScenePoint>();
		
		this.Points.add(onePoint);
		this.Points.add(twoPoint);
		this.Points.add(threePoint);
		
		this.ReferenceObject = null;
		this.Id = 0;
	}
	
	public Face(int id, ScenePoint onePoint, ScenePoint twoPoint, ScenePoint threePoint)
	{
		
		this.Points = new ArrayList<ScenePoint>();
		
		this.Points.add(onePoint);
		this.Points.add(twoPoint);
		this.Points.add(threePoint);
		
		this.ReferenceObject = null;
		this.Id = id;
	}
	
	
	
	public Face(int id, ArrayList<ScenePoint> points)
	{
		this.Id = 0;
		this.ReferenceObject = null;
		this.Points = points;
	}
	
}
