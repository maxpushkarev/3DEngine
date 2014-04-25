package newClass.Engine.Interfaces;

import java.util.Dictionary;

import newClass.Engine.YearInfo;

public interface IUtils {
	
	Dictionary<Integer,YearInfo> LoadData(String filename, int startDate, int endDate) throws Exception;
	void SaveData(String filename, StringBuilder stringBuilder) throws Exception;
	StringBuilder PrintYearInfos(Dictionary<Integer,YearInfo> yearInfos);
	StringBuilder ExpandedPrintYearInfos(Dictionary<Integer,YearInfo> yearInfos, int startYear, int endYear);
	void CalculateAvg(Dictionary<Integer,YearInfo> yearInfos);
	
}
