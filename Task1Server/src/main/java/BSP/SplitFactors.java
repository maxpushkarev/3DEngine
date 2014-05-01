package BSP;

public class SplitFactors {
	
	
	//Score = w1*abs(front – back) + splits*w2 + coplanar*w3
	
	//weights of choosing splitter factor
	//w1 - balanced bsp-tree factor;
	//w2 - number of polygon splitting factor
	//w3 - number of coplanar polygon factors;
	
	public int Coplanars;
	public int Splits;
	public int Front;
	public int Back;
	
	public double w1,w2,w3;
	
	public SplitFactors()
	{
		//weights can be modified;
		this.w1=1;
		this.w2 = 0.5;
		this.w3 = 0.5;
		
		this.Coplanars = 0;
		this.Splits = 0;
		this.Front = 0;
		this.Back = 0;
	}
	
	public SplitFactors(double _w1, double _w2, double _w3)
	{
		this.w1=_w1;
		this.w2 = _w2;
		this.w3 = _w3;
		
		this.Coplanars = 0;
		this.Splits = 0;
		this.Front = 0;
		this.Back = 0;
	}
	
	public double CalculateScore()
	{
		//Score = w1*abs(front – back) + splits*w2 + coplanar*w3
		return (this.w1* Math.abs(this.Front - this.Back) + this.Splits*this.w2 + this.Coplanars*this.w3);
	}
	
}
