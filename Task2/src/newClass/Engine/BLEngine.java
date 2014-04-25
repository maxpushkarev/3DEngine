package newClass.Engine;

import java.util.Dictionary;

import newClass.Engine.Interfaces.IBLEngine;

/**
 * @author MaxPushkarev
 *
 */
public final class BLEngine implements IBLEngine {

	
	private static final class BLConstants
	{
		static final int START_YEAR = 1990;
	    static final int END_YEAR = 2020;
	}

	
	@Override
	public void CreateInfo() {
		
		/*it's better to use reference type for many strings modifications*/
		StringBuilder stringBuilder=new StringBuilder("");
		StringBuilder outputYearInfo;
		Utils utils = new Utils();
		
		try {
			
			Dictionary<Integer,YearInfo> yearInfos = utils.LoadData(
											"input.txt",
											BLConstants.START_YEAR, 
											BLConstants.END_YEAR
										);
			
			stringBuilder.append("app v.1.13 \n"); 
			utils.CalculateAvg(yearInfos);
			outputYearInfo = utils.ExpandedPrintYearInfos(yearInfos, BLConstants.START_YEAR, BLConstants.END_YEAR);
			stringBuilder.append(utils.PrintYearInfos(yearInfos));
			stringBuilder.append("Everything's ready! \n");
			
			utils.SaveData("stats.txt",outputYearInfo);
		}
		catch(Exception e)
		{
			stringBuilder.append(String.format("\nRaising of exception: %s",e.getMessage()));
		}
		finally
		{
			System.out.println(stringBuilder);
		}
		
	}
	
}
