package BSP;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;

import GeoRelationship.PlaneFaceRelationship;
import Geometry.GeometryHelper;
import Geometry.GeometryPlacing;
import Geometry.Face;
import Geometry.ScenePoint;

public class BSPEngine {
	 
	public BSPNode BSPRoot;
	public ArrayList<Face> AllFacesBeginning;
	
	public BSPEngine(ArrayList<Face> allFaces)
	{
		this.AllFacesBeginning = allFaces;
		this.BSPRoot = CreateBSPTree(this.AllFacesBeginning);
	}
	
	public BSPNode CreateBSPTree(ArrayList<Face> faces)
	{
		//when we finish creating a tree
		if(faces.size()==0)
		{
			return null;
		}
		
		//choose optimal splitter from all faces
		Face splitter = this.GetOptimalSplitter(faces);		
		
		//groups of faces to divide our world
		Hashtable<GeometryPlacing,ArrayList<Face>> faceTable = 
				new Hashtable<GeometryPlacing,ArrayList<Face>>();
		
		//initializing groups
		faceTable.put(GeometryPlacing.FRONT, new ArrayList<Face>());
		faceTable.put(GeometryPlacing.BACK, new ArrayList<Face>());
		faceTable.put(GeometryPlacing.WITHIN, new ArrayList<Face>());
		
		//complectation of the groups;
		this.SeparateFacesIntoGroups(faces, faceTable, splitter);
		
		BSPNode node = new BSPNode();		
		//splitter is the node!!!
		node.NodeFace = splitter;	
		//don't forget about coplanar faces
		node.CoplanarFaces = faceTable.get(GeometryPlacing.WITHIN);
		//and, of course, recursion!
		node.BackNode = this.CreateBSPTree(faceTable.get(GeometryPlacing.BACK));
		node.FrontNode = this.CreateBSPTree(faceTable.get(GeometryPlacing.FRONT));
		
		//ooh, ok! let's return!
		return node;	
	}
	
	public void SeparateFacesIntoGroups(ArrayList<Face> faces, Hashtable<GeometryPlacing,ArrayList<Face>> faceTable, Face splitter)
	{
		PlaneFaceRelationship rel;
		
		for (Face face : faces)
		{
			if(splitter == face)
			{
				continue;
			}
			
			if(GeometryHelper.IsCoplanarPolygon(splitter, face)){
				faceTable.get(GeometryPlacing.WITHIN).add(face);
				continue;
			}
			
			
			
			if(GeometryHelper.IsPolygonFrontOfPlane(splitter, face))
			{
				faceTable.get(GeometryPlacing.FRONT).add(face);
				continue;
			}
			
			if(GeometryHelper.IsPolygonBackOfPlane(splitter, face))
			{
				faceTable.get(GeometryPlacing.BACK).add(face);
				continue;
			}
			
			
			
			rel = GeometryHelper.InfoPolygonSplittedByPlane(splitter, face);
			if(rel.IsSplitted){
				this.splitFace(rel.CommonPoints, faceTable, splitter, face);
				continue;
			}
			
			
		}
	}
	
	
	public void sortSplittersByScore(ArrayList<Splitter> possibleSplitters)
	{
		Collections.sort(possibleSplitters, new Comparator<Splitter>(){

			public int compare(Splitter one, Splitter two) {
				
				if (one.Score > two.Score)
				 {
		            return 1;
				 }
				 else if (one.Score <  two.Score)
				 {
					 return -1;
				 }
				 else
				 {
					 return 0;
				 }
				
			}
			
		});
	}
	
	
	private boolean checkEqualsPoint(ScenePoint input, Collection<ScenePoint> collection)
	{
		for (ScenePoint commonPoint : collection)
		{
			if(input.EqualPoint(commonPoint))
			{
				return true;
			}
		
		}
		
		return false;
	}
	
	public void splitFace(HashMap<String, ScenePoint> commonPoints, 
			Hashtable<GeometryPlacing,ArrayList<Face>> faceTable,
			Face splitter, 
			Face polygon)
	{
		
		Face faceBack = new Face();
		Face faceFront = new Face();
		
		
		//common points for new faces
		for (ScenePoint point : commonPoints.values())
		{
			faceBack.Points.add(point);
			faceFront.Points.add(point);
		}
		
		
		for (ScenePoint p : polygon.Points)
		{
			//avoid repeats
			if(checkEqualsPoint(p,commonPoints.values()))
			{
				continue;
			}
			
			if(GeometryHelper.IsPointFrontOfPlane(splitter, p))
			{
				faceFront.Points.add(p);
			}
			else
			{
				faceBack.Points.add(p);
			}
		}
		
		
		//save face Id!
		faceBack.Id = polygon.Id;
		faceFront.Id = polygon.Id;
		
		//save reference object!
		faceBack.ReferenceObject = polygon.ReferenceObject;
		faceFront.ReferenceObject = polygon.ReferenceObject;
		
		
		/*change in obj list*/
		polygon.ReferenceObject.Faces.remove(polygon);
		polygon.ReferenceObject.Faces.add(faceBack);
		polygon.ReferenceObject.Faces.add(faceFront);
		
		
		//separate front and back into groups
		faceTable.get(GeometryPlacing.FRONT).add(faceFront);
		faceTable.get(GeometryPlacing.BACK).add(faceBack);
		
	}
	
	
	public Face GetOptimalSplitter(ArrayList<Face> faces)
	{		
		
		ArrayList<Splitter> possibleSplitters = new ArrayList<Splitter>();
		
		for(Face face : faces)
		{
			possibleSplitters.add(new Splitter(
					face, 
					this.GetSplitFactors(face, faces).CalculateScore()
					));
		}
		
		this.sortSplittersByScore(possibleSplitters);
		return possibleSplitters.get(0).SplitterFace;
	}
	
	public SplitFactors GetSplitFactors(Face candidate, ArrayList<Face> faces)
	{
		SplitFactors res = new SplitFactors();
		
		for (Face face : faces)
		{
			
			if(candidate == face)
			{
				continue;
			}
			
			if(GeometryHelper.IsCoplanarPolygon(candidate, face)){
				res.Coplanars++;
				continue;
			}
			
			
			
			if(GeometryHelper.IsPolygonFrontOfPlane(candidate, face))
			{
				res.Front ++;
				continue;
			}
			
			if(GeometryHelper.IsPolygonBackOfPlane(candidate, face))
			{
				res.Back ++;
				continue;
			}
			
			
			if(GeometryHelper.InfoPolygonSplittedByPlane(candidate, face).IsSplitted){
				res.Splits++;
				continue;
			}
			
			
			
		}
		
		return res;
	}
	

}
