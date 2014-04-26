package Geometry;

public final class ScenePoint {
	
	public double X;
	public double Y;
	public double Z;
	
	public ScenePoint(double x, double y, double z)
	{
		this.X = x;
		this.Y = y;
		this.Z = z;
	}
	
	public ScenePoint(String strCoords)
	{
		String[] strBlocks = strCoords.split(",");
		this.X = Double.parseDouble(strBlocks[0]);
		this.Y = Double.parseDouble(strBlocks[1]);
		this.Z = Double.parseDouble(strBlocks[2]);
	}
}
