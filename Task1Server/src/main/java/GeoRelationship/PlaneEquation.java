package GeoRelationship;

import Geometry.Face;

public class PlaneEquation {
	public double A;
	public double B;
	public double C;
	public double D;
	public double MAX;
	
	public PlaneEquation(Face plane)
	{
		double max;
		
		//A,B,C,D from Ax+By+Cz+d=0 (plane equation)
        this.A = plane.Points.get(0).Y
                * (plane.Points.get(1).Z - plane.Points.get(2).Z)
                + plane.Points.get(1).Y
                * (plane.Points.get(2).Z - plane.Points.get(0).Z)
                + plane.Points.get(2).Y
                * (plane.Points.get(0).Z - plane.Points.get(1).Z);
        
        max=Math.abs(this.A) > 1 ? Math.abs(this.A) : 1;
        
        this.B = plane.Points.get(0).Z
                * (plane.Points.get(1).X - plane.Points.get(2).X)
                + plane.Points.get(1).Z
                * (plane.Points.get(2).X - plane.Points.get(0).X)
                + plane.Points.get(2).Z
                * (plane.Points.get(0).X - plane.Points.get(1).X);
        
        if(Math.abs(this.B)>max)
        {
        	max = Math.abs(this.B);
        }
        
        
        this.C = plane.Points.get(0).X
                * (plane.Points.get(1).Y - plane.Points.get(2).Y)
                + plane.Points.get(1).X
                * (plane.Points.get(2).Y - plane.Points.get(0).Y)
                + plane.Points.get(2).X
                * (plane.Points.get(0).Y - plane.Points.get(1).Y);
        
        
        if(Math.abs(this.C)>max)
        {
        	max = Math.abs(this.C);
        }
        
        
        this.D = -plane.Points.get(0).X
                * (plane.Points.get(1).Y * plane.Points.get(2).Z - plane
                        .Points.get(2).Y
                        * plane.Points.get(1).Z)
                - plane.Points.get(1).X
                * (plane.Points.get(2).Y * plane.Points.get(0).Z - plane
                        .Points.get(0).Y
                        * plane.Points.get(2).Z)
                - plane.Points.get(2).X
                * (plane.Points.get(0).Y * plane.Points.get(1).Z - plane
                        .Points.get(1).Y
                        * plane.Points.get(0).Z);
        
        if(Math.abs(this.D)>max)
        {
        	max = Math.abs(this.D);
        }
        
	 this.MAX = max;
	 
	}
}
