package Geometry;

import java.util.ArrayList;


public class SceneObject {

	public String Name;
	public ArrayList<Face> Faces;
	
	public SceneObject()
	{
		this.Faces = new ArrayList<Face>(); 
	}
	
	public ArrayList<Face> AddFace(Face face)
	{
		this.Faces.add(face);
		return this.Faces;
	}
	
}
