package Geometry;

public class Ray {

	int Id;
	ScenePoint Origin;
	ScenePoint Direction;
	public Vector RayVector;
	
	public int getId()
	{
		return this.Id;
	}
	
	
	public ScenePoint getOrigin()
	{
		return this.Origin;
	}
	
	
	public ScenePoint getDirection()
	{
		return this.Direction;
	}
	
	public Ray(int id, ScenePoint origin, ScenePoint direction)
	{
		this.Id = id;
		this.Origin = origin;
		this.Direction = direction;
		this.RayVector = new Vector(origin,direction);
	}
	
	public Ray(String message)
	{
		String[] strBlocks = message.split(";");
		this.Id = Integer.parseInt(strBlocks[0]);
		this.Origin = new ScenePoint(strBlocks[1]);
		this.Direction = new ScenePoint(strBlocks[2]);
		
		this.RayVector = new Vector(this.Origin, this.Direction);
	}
	

}
