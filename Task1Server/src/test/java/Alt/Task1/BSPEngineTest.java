package Alt.Task1;

import java.util.ArrayList;

import BSP.BSPEngine;
import BSP.Splitter;
import Geometry.Face;
import Geometry.ScenePoint;
import junit.framework.TestCase;

public class BSPEngineTest extends TestCase {

	public BSPEngineTest( String testName )
    {
        super( testName );
    }
	
	public void testGetSplitFactors()
	{
		Face candidate = new Face(			
				new ScenePoint(-1,0,0),
				new ScenePoint(1,0,0),
				new ScenePoint(0,0,1)			
				);
		
		Face another = new Face(			
				new ScenePoint(2,0,0),
				new ScenePoint(4,-5,0),
				new ScenePoint(4,4,0)			
				);
		
		ArrayList<Face> faces = new ArrayList<Face>();
		faces.add(candidate);
		faces.add(another);
		
		BSPEngine bsp = new BSPEngine(new ArrayList<Face>());
		
		assertEquals(bsp.GetSplitFactors(candidate, faces).Splits,1);
		assertEquals(bsp.GetSplitFactors(candidate, faces).CalculateScore(),0.5);
		another.Points.get(1).Y = 8.0;
		assertEquals(bsp.GetSplitFactors(candidate, faces).Back,1);
		another.Points.get(1).Y = -8.0;
		another.Points.get(2).Y = -6.0;
		assertEquals(bsp.GetSplitFactors(candidate, faces).Front,1);
		assertEquals(bsp.GetSplitFactors(candidate, faces).Back,0);
		
		another.Points.get(0).X = -100.0;
		another.Points.get(0).Y = 0.0;
		another.Points.get(0).X = 0.0;
		
		another.Points.get(1).X = 100.0;
		another.Points.get(1).Y = 0.0;
		another.Points.get(1).X = 0.0;
		
		another.Points.get(2).X = 0.0;
		another.Points.get(2).Y = 0.0;
		another.Points.get(2).X = 100.0;
		
		assertEquals(bsp.GetSplitFactors(candidate, faces).Coplanars,1);
		assertEquals(bsp.GetSplitFactors(candidate, faces).Splits,0);
		
	}
	
	
	public void testChooseOptimalSplitter()
	{
		
		ArrayList<Splitter> possibleSplitters = new ArrayList<Splitter>();

			possibleSplitters.add(new Splitter(
					new Face(), 
					3.0
					));
			
			possibleSplitters.add(new Splitter(
					new Face(), 
					1.0
					));
			
			possibleSplitters.add(new Splitter(
					new Face(), 
					5.0
					));
			
			BSPEngine bsp = new BSPEngine(new ArrayList<Face>());
			
			bsp.sortSplittersByScore(possibleSplitters);
			assertEquals(possibleSplitters.get(0).Score,1.0);
		
	}
	
}
