package Geometry;

import GeoRelationship.AccuracyComparer;



public final class ScenePoint  {
	
	public int Id;

	public double X;

	public double Y;

	public double Z;
	
	public Face RefFace;
	
	@Override
	public String toString()
	{
		return String.format("X: %s; Y: %s; Z: %s", this.X,this.Y, this.Z);
	}
	
	public ScenePoint(int id, double x, double y, double z)
	{	
		 this.X = (double)Math.round(x * 100) / 100;
		 this.Y = (double)Math.round(y * 100) / 100;
		 this.Z = (double)Math.round(z * 100) / 100;
		
		 this.Id=id;
		 this.RefFace = null;
	}
	
	public ScenePoint(double x, double y, double z)
	{
		
		 this.X = (double)Math.round(x * 100) / 100;
		 this.Y = (double)Math.round(y * 100) / 100;
		 this.Z = (double)Math.round(z * 100) / 100;
		
		this.Id=0;
		this.RefFace = null;
	}
	
	public ScenePoint(String strCoords)
	{
		
		
		String[] strBlocks = strCoords.split(",");
		double x = Double.parseDouble(strBlocks[0]);
		double y = Double.parseDouble(strBlocks[1]);
		double z = Double.parseDouble(strBlocks[2]);
		
		
		 this.X = (double)Math.round(x * 100) / 100;
		 this.Y = (double)Math.round(y * 100) / 100;
		 this.Z = (double)Math.round(z * 100) / 100;
		
		
		this.Id = 0;
		this.RefFace = null;
	}
	
	public boolean EqualPoint(ScenePoint anotherPoint)
	{
		return (Math.abs(this.X - anotherPoint.X) < AccuracyComparer.THRESHOLD) && 
				(Math.abs(this.Y - anotherPoint.Y) < AccuracyComparer.THRESHOLD) && 
				(Math.abs(this.Z - anotherPoint.Z) < AccuracyComparer.THRESHOLD);
	}
	
}
