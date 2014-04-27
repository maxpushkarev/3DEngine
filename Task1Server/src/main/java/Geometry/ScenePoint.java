package Geometry;



public final class ScenePoint {
	
	public int Id;

	public double X;

	public double Y;

	public double Z;
	
	public ScenePoint(int id, double x, double y, double z)
	{
		this.X = x;
		this.Y = y;
		this.Z = z;
		
		this.Id=id;
	}
	
	public ScenePoint(double x, double y, double z)
	{
		this.X = x;
		this.Y = y;
		this.Z = z;
		
		this.Id=0;
	}
	
	public ScenePoint(String strCoords)
	{
		String[] strBlocks = strCoords.split(",");
		this.X = Double.parseDouble(strBlocks[0]);
		this.Y = Double.parseDouble(strBlocks[1]);
		this.Z = Double.parseDouble(strBlocks[2]);
		
		this.Id = 0;
	}
	
	public boolean EqualPoint(ScenePoint anotherPoint)
	{
		return (this.X == anotherPoint.X) && 
				(this.Y == anotherPoint.Y) && 
				(this.Z == anotherPoint.Z);
	}
}
