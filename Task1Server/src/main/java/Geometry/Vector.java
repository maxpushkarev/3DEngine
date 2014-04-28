package Geometry;

public class Vector {

	public double X;
	public double Y;
	public double Z;
	
	public Vector(ScenePoint startPoint, ScenePoint endPoint)
	{
		this.X = endPoint.X - startPoint.X;
		this.Y = endPoint.Y - startPoint.Y;
		this.Z = endPoint.Z - startPoint.Z;
	}
	
}
