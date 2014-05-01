package BSP;

import java.util.ArrayList;

import Geometry.Face;

public class BSPNode {

	public Face NodeFace; //what face in the node
	public ArrayList<Face> CoplanarFaces; //faces in the node plain
	
	public BSPNode FrontNode; //front node at tree
	public BSPNode BackNode; // back node at tree
	
	public BSPNode()
	{
		this.FrontNode = null;
		this.BackNode = null;
		this.CoplanarFaces = new ArrayList<Face>();
		this.NodeFace = null;
	}
	
	
}
