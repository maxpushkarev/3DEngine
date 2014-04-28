package Geometry;

import java.util.ArrayList;

public class Scene {
	
	public ScenePoint CentralPoint;
	public ArrayList<SceneObject> SceneObjects;
	public double MinX, MinY, MinZ, MaxX, MaxY, MaxZ;
	
	public Scene()
	{
		
		this.SceneObjects = new ArrayList<SceneObject>();
		this.CentralPoint = new ScenePoint(0.0,0.0,0.0);
		
	}
	
	public ArrayList<SceneObject> AddSceneObject(SceneObject sceneObject)
	{
		this.SceneObjects.add(sceneObject);
		return this.SceneObjects;
	}
}
