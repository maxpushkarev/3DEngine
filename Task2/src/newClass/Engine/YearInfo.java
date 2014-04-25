package newClass.Engine;

public final class YearInfo
{
	int Year;
	int Sum;
	double Avg;
	int Count;
	
	
	YearInfo(int year, int sum)
	{			
		this.Year = year;
		this.Sum = sum;
		this.Count = 1;
	}
	
	protected double CalculateAvg()
	{
		this.Avg = (double) this.Sum / (double) this.Count;
		return this.Avg;
	}
	
	protected int AddSum(int sum)
	{
		this.Sum+=sum;
		this.Count++;
		return this.Sum;
	}
}