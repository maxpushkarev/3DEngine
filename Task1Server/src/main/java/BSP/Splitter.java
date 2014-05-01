package BSP;

import Geometry.Face;

public class Splitter {

	public Face SplitterFace;
	public double Score;
	
	public Splitter(Face splitterFace, double score)
	{
		this.SplitterFace = splitterFace;
		this.Score = score;
	}
	
}
