package Geometry;

public class PlaneEquation {
	double A;
	double B;
	double C;
	double D;
	
	public PlaneEquation(Face plane)
	{
		//A,B,C,D from Ax+By+Cz+d=0 (plane equation)
        this.A = plane.Points.get(0).Y
                * (plane.Points.get(1).Z - plane.Points.get(2).Z)
                + plane.Points.get(1).Y
                * (plane.Points.get(2).Z - plane.Points.get(0).Z)
                + plane.Points.get(2).Y
                * (plane.Points.get(0).Z - plane.Points.get(1).Z);
        
        
        this.B = plane.Points.get(0).Z
                * (plane.Points.get(1).X - plane.Points.get(2).X)
                + plane.Points.get(1).Z
                * (plane.Points.get(2).X - plane.Points.get(0).X)
                + plane.Points.get(2).Z
                * (plane.Points.get(0).X - plane.Points.get(1).X);
        
        this.C = plane.Points.get(0).X
                * (plane.Points.get(1).Y - plane.Points.get(2).Y)
                + plane.Points.get(1).X
                * (plane.Points.get(2).Y - plane.Points.get(0).Y)
                + plane.Points.get(2).X
                * (plane.Points.get(0).Y - plane.Points.get(1).Y);
        
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
	 
	 
	}
}
