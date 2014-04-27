package Geometry;

import java.util.ArrayList;


public class Scene {
	
	public ArrayList<SceneObject> SceneObjects;
	
	public Scene()
	{
		this.SceneObjects = new ArrayList<SceneObject>();
	}
	
	public ArrayList<SceneObject> AddSceneObject(SceneObject sceneObject)
	{
		this.SceneObjects.add(sceneObject);
		return this.SceneObjects;
	}
}
