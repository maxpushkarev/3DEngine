package newClass.Engine;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Hashtable;

import newClass.Engine.Interfaces.IUtils;

public final class Utils implements IUtils {

	@SuppressWarnings("serial")
	private final class UtilsException extends Exception
	{
		
		UtilsException(String message)
		{
			super(message);
		}
		
		UtilsException(FileOutputStream fos, String message) throws IOException
		{
			super(message);
			
			if(fos != null)
			{
				fos.close();
			}
			
		}
		
		UtilsException(FileInputStream fis, DataInputStream din, String message) throws IOException
		{
			super(message);
			
			if(fis != null)
			{
				fis.close();
			}
			
			if(din != null)
			{
				din.close();
			}
		}
	}
	
	private final class YearInfoComparer implements Comparator<YearInfo>
	{
		@Override
		public int compare(YearInfo one, YearInfo another) {
			
			    if(one.Year < another.Year){
			        return -1;
			    }
			    
			    if(one.Year > another.Year){
			        return 1;
			    }
			
			    return 0;
		}
		
		
	}
	
	@Override
	public Dictionary<Integer,YearInfo> LoadData(String filename, int startDate, int endDate) throws Exception {
		
		if((filename == null) || (filename.isEmpty()))
		{
			throw new UtilsException("Empty file name(((");
		}
		
		if(startDate>endDate)
		{
			throw new UtilsException("Start-Year cannot be larger than End-Year(((");
		}
		
		FileInputStream fis = null;
		DataInputStream din = null;
		Dictionary<Integer,YearInfo> yearInfos;
		String inputLine;
		String[] inputLineParts;
		int yearKey;
		int sum;
		YearInfo yearInfo;
		
		try
		{
			File inputFile = new File(System.getProperty("user.dir"),filename);
			fis = new FileInputStream(inputFile);
			yearInfos = new Hashtable<Integer,YearInfo>();
		 
			din = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(din));
			
			while ((inputLine = br.readLine()) != null) {
				
				  inputLineParts = inputLine.split(" ");
				  if(inputLineParts.length<4)
				  {
					  continue;
				  }
				  
				  yearKey = Integer.parseInt(inputLineParts[2]);
				  
				  if((yearKey>endDate)||(yearKey<startDate)) /*year bounds*/
				  {
					  continue;
				  }
				  
				  sum = Integer.parseInt(inputLineParts[3]);
				  yearInfo = yearInfos.get(yearKey);
				  
				  if(yearInfo == null)
				  {
					  yearInfos.put(yearKey, new YearInfo(yearKey,sum));
					  continue;
				  }
				  
				  yearInfo.AddSum(sum);
			}
			  
			 
		}
		catch(Exception ex)
		{
			throw new UtilsException(fis, din, ex.getMessage());
		}
	    finally
	    { 
	    	if(din!=null)
	    	{
	    		din.close();
	    	};
	    	
	    	if(fis!=null)
	    	{
	    		fis.close();
	    	}
	    }
	     
	     return yearInfos;
	}

	@Override
	public void SaveData(String filename, StringBuilder stringBuilder) throws Exception {
		
		if((filename == null) || (filename.isEmpty()))
		{
			throw new UtilsException("Empty file name(((");
		}
		
		FileOutputStream fos = null;
		
		try
		{
			File outputFile = new File(System.getProperty("user.dir"),filename);
			fos = new FileOutputStream(outputFile);
			fos.write(stringBuilder.toString().getBytes());
		}
		catch(Exception ex)
		{
			throw new UtilsException(fos, ex.getMessage());
		}
		finally
		{ 
			if(fos!=null)
			{
				fos.close();
			}
		}
		
	}

	@Override
	public StringBuilder PrintYearInfos(Dictionary<Integer,YearInfo> yearInfos) {
		
		StringBuilder stringBuilder = new StringBuilder("");
		ArrayList<YearInfo> yearInfoList = Collections.list(yearInfos.elements());
		Collections.sort(yearInfoList, new YearInfoComparer());
		
		for (YearInfo yearInfo :  yearInfoList) {

			if (yearInfo.Avg > 0) {
				stringBuilder.append(String.format("Year: %s Avg: %s \n",yearInfo.Year, yearInfo.Avg));
            }
			
		}
		
		return stringBuilder;
	}

	@Override
	public void CalculateAvg(Dictionary<Integer, YearInfo> yearInfos) {
		
		ArrayList<YearInfo> yearInfoList = Collections.list(yearInfos.elements());
		for (YearInfo yearInfo :  yearInfoList) {	
			yearInfo.CalculateAvg();
		}
		
	}

	@Override
	public StringBuilder ExpandedPrintYearInfos(
			Dictionary<Integer, YearInfo> yearInfos, 
			int startYear, int endYear) {
		
		/* Count1 - wtf? */
		
		StringBuilder stringBuilder = new StringBuilder("");
		YearInfo yearInfo;
		
		for(int i = startYear; i <= endYear; i++)
		{
			yearInfo = yearInfos.get(i);
			
			if(yearInfo==null)
			{
				stringBuilder.append(String.format("Year: %s Avg: 0 \n",i)); 
				continue;	
			}
			
			stringBuilder.append(String.format("Year: %s Avg: %s \n",yearInfo.Year, yearInfo.Avg));
			
		}
		
		return stringBuilder;
		
	}

}
