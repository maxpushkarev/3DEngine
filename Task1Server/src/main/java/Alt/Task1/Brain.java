package Alt.Task1;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import BSP.BSPEngine;
import BSP.BSPNode;
import Geometry.Face;
import Geometry.GeometryHelper;
import Geometry.Ray;
import Geometry.Scene;
import Geometry.SceneObject;
import Geometry.ScenePoint;

public class Brain {

	public Scene MainScene;
	public BSPEngine BrainBSP;
	

	public SceneObject RayTrace(Ray ray) //entry point to raytracing
	{	
		/*start root*/
		BSPNode root = this.BrainBSP.BSPRoot;
		ArrayList<ScenePoint> hits = new ArrayList<ScenePoint>();
		
		this.CollectHits(root, ray, hits);
		
		if(hits.size()==0)
		{
			return null;
		}
		else
		{
			ScenePoint hitPoint = GeometryHelper.GetNearestPointToOrigin(ray.getOrigin(), hits);
			return hitPoint.RefFace.ReferenceObject;
		}
		
	}
	
	
	private void CollectComplanarHits(BSPNode root, 
			Ray ray, 
			ArrayList<ScenePoint> hits)
	{
		
		for(Face complanar : root.CoplanarFaces)
		{
			ScenePoint hit = GeometryHelper.GetIntersectionPointBetweenFaceAndRay(ray, complanar);
			if(hit!= null)
			{
				hit.RefFace = complanar;
				hits.add(hit);
			}
		}
		
	}
	
	private void CollectHits(BSPNode root, 
			Ray ray, 
			ArrayList<ScenePoint> hits)
	{
		if(root == null)
		{
			return;
		}
	
		
		//check root
		ScenePoint hit = GeometryHelper.GetIntersectionPointBetweenFaceAndRay(ray, root.NodeFace);
		if(hit!= null)
		{
			hit.RefFace = root.NodeFace;
			hits.add(hit);
		}
		
		/*don't forget about complanar faces*/
		this.CollectComplanarHits(root, ray, hits);
		
		//TODO: fight with calculating errors
		this.CollectHits(root.FrontNode, ray, hits);
		this.CollectHits(root.BackNode, ray, hits);
		
		/*
		if(GeometryHelper.IsPointWithinPlane(root.NodeFace, ray.getOrigin())
				&&
				GeometryHelper.IsPointWithinPlane(root.NodeFace, ray.getDirection())
				)
		{
			this.CollectHits(root.FrontNode, ray, hits);
			this.CollectHits(root.BackNode, ray, hits);
		}
		else if(GeometryHelper.IsRayFrontOfPlane(root.NodeFace, ray))
		{
			//go front	
			this.CollectHits(root.FrontNode, ray, hits);
		}
		else
		{
			//go back
			this.CollectHits(root.BackNode, ray, hits);
		}
		
		*/
		
	}
	
	
	public Brain()
	{
		this.MainScene = new Scene();
	}
	
	private void LoadPoints(Element root, Dictionary<Integer, ScenePoint> allPoints)
	{
        Element points = (Element) root.getElementsByTagName("points").item(0);
        
        NodeList pointsList = points.getElementsByTagName("point");
        int pointsListLength = pointsList.getLength();
        
        String idPoint,xPoint,yPoint,zPoint;
        
        xPoint = ((Element) pointsList.item(0)).getElementsByTagName("x").item(0).getTextContent();
    	yPoint = ((Element) pointsList.item(0)).getElementsByTagName("y").item(0).getTextContent();
    	zPoint = ((Element) pointsList.item(0)).getElementsByTagName("z").item(0).getTextContent();
    	
    	this.MainScene.MaxX = this.MainScene.MinX = Double.parseDouble(xPoint);
    	this.MainScene.MaxY = this.MainScene.MinY = Double.parseDouble(yPoint);
    	this.MainScene.MaxZ = this.MainScene.MinZ = Double.parseDouble(zPoint);
    	
        
        for(int i = 0; i<pointsListLength; i++)
        {
        	
        	
        	idPoint = ((Element) pointsList.item(i)).getElementsByTagName("id").item(0).getTextContent();
        	xPoint = ((Element) pointsList.item(i)).getElementsByTagName("x").item(0).getTextContent();
        	yPoint = ((Element) pointsList.item(i)).getElementsByTagName("y").item(0).getTextContent();
        	zPoint = ((Element) pointsList.item(i)).getElementsByTagName("z").item(0).getTextContent();
        	
        	
        	if(Double.parseDouble(xPoint)<this.MainScene.MinX)
        	{
        		this.MainScene.MinX = Double.parseDouble(xPoint);
        	}
        	
        	if(Double.parseDouble(yPoint)<this.MainScene.MinY)
        	{
        		this.MainScene.MinY = Double.parseDouble(yPoint);
        	}
        	
        	if(Double.parseDouble(zPoint)<this.MainScene.MinZ)
        	{
        		this.MainScene.MinZ = Double.parseDouble(zPoint);
        	}
        	
        	
        	if(Double.parseDouble(xPoint)>this.MainScene.MaxX)
        	{
        		this.MainScene.MaxX = Double.parseDouble(xPoint);
        	}
        	
        	if(Double.parseDouble(yPoint)>this.MainScene.MaxY)
        	{
        		this.MainScene.MaxY = Double.parseDouble(yPoint);
        	}
        	
        	if(Double.parseDouble(zPoint)>this.MainScene.MaxZ)
        	{
        		this.MainScene.MaxZ = Double.parseDouble(zPoint);
        	}
        	
        	allPoints.put(Integer.parseInt(idPoint), new ScenePoint(
        			
        			Integer.parseInt(idPoint),
        			Double.parseDouble(xPoint),
        			Double.parseDouble(yPoint),
        			Double.parseDouble(zPoint)
        				
        			));
      
        }
	}
	
	
	private void loadFaces(Element root, 
			Dictionary<Integer, ScenePoint> allPoints,
			Dictionary<Integer, Face> allFaces)
	{
		
		Element faces = (Element) root.getElementsByTagName("faces").item(0);
        NodeList faceList = faces.getElementsByTagName("face");
        int faceListLength = faceList.getLength();   
        
        String idFace, point1, point2, point3;
        
        for(int i = 0; i<faceListLength; i++)
        {
        	idFace = ((Element) faceList.item(i)).getElementsByTagName("id").item(0).getTextContent();
        	point1 = ((Element) faceList.item(i)).getElementsByTagName("point-id").item(0).getTextContent();
        	point2 = ((Element) faceList.item(i)).getElementsByTagName("point-id").item(1).getTextContent();
        	point3 = ((Element) faceList.item(i)).getElementsByTagName("point-id").item(2).getTextContent();
        	
        	allFaces.put(Integer.parseInt(idFace), 
        			
        			new Face(
        					Integer.parseInt(idFace),
        					allPoints.get(Integer.parseInt(point1)),
        					allPoints.get(Integer.parseInt(point2)),
        					allPoints.get(Integer.parseInt(point3))
        					)
        			
        			);
	
        	
        }
        
		
	}
	
	
	private void loadObjects(
			Element root, 
			Dictionary<Integer, ScenePoint> allPoints,
			Dictionary<Integer, Face> allFaces
			)
	{
		
		Element objects = (Element) root.getElementsByTagName("objects").item(0);
        NodeList objList = objects.getElementsByTagName("object");
        int objListLength = objList.getLength();
		
        String name, faceId;
        NodeList faces;
        int facesCount;
        SceneObject sObj;
        Face face;
        
        for(int i = 0; i<objListLength; i++)
        {
        	name = ((Element) objList.item(i)).getElementsByTagName("name").item(0).getTextContent();

        	faces = ((Element) objList.item(i)).getElementsByTagName("face-id");
        	facesCount = faces.getLength();
        	
        	sObj = new SceneObject();
        	
        	 for(int k = 0; k < facesCount; k++)
             {
        		faceId = ((Element) faces.item(k)).getTextContent();
        		face = allFaces.get(Integer.parseInt(faceId));
        		face.ReferenceObject = sObj;
        		sObj.AddFace(face);
             }
        	 
        	 sObj.Name = name;
        	
        	this.MainScene.AddSceneObject(sObj);
        	
        }
        
	}
	
	
	public int Initialize() throws Exception
	{
		
		Dictionary<Integer, ScenePoint> allPoints = new Hashtable<Integer, ScenePoint>();
		Dictionary<Integer, Face> allFaces = new Hashtable<Integer, Face>();
		
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new URL("http://127.0.0.1:7654/scene").openStream());
		Element root = document.getDocumentElement();
        this.LoadPoints(root, allPoints);
        this.loadFaces(root, allPoints, allFaces);
        this.loadObjects(root, allPoints, allFaces);
        
        allPoints = null;
        
        // create BSP-tree
        System.out.println("Building of BSP-tree is starting.....");
        this.BrainBSP = new BSPEngine(Collections.list(allFaces.elements()));
        System.out.println("BSP-tree was build successfully!!!!");
        
        allFaces = null;
        return 0;
	}
}
