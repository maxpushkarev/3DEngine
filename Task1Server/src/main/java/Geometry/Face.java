package Geometry;

public class Face {
	

	public int Id;
	public ScenePoint[] Points;
	
	public Face()
	{
		
		this.Points = new ScenePoint[3];
		this.Id = 0;
	}
	
	public Face(ScenePoint onePoint, ScenePoint twoPoint, ScenePoint threePoint)
	{
		
		this.Points = new ScenePoint[]{onePoint, twoPoint, threePoint};
		this.Id = 0;
	}
	
	public Face(int id, ScenePoint onePoint, ScenePoint twoPoint, ScenePoint threePoint)
	{
		
		this.Points = new ScenePoint[]{onePoint, twoPoint, threePoint};
		this.Id = id;
	}
	
	
}
