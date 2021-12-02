package com.mertyazilim.util;


public class ExcelReader extends ExcelSupport 
{
	
	public final static int testNameColumn =1;
	

	public final static int testDataStartColumn=2;


	public ExcelReader(String filePath) {
		super(filePath);
	}
	
	
	public Object[][] findTestInExcelSheet(String sheetName, String testName)
	{
		int testRowNumber = getRowNumber(sheetName, testNameColumn, testName);
		int testDataStartRow = testRowNumber +1;
		
		int testDataRows = 0;
		for(int i=testDataStartRow; getCellData(sheetName, testNameColumn, i).equals(testName); i++ )
		{
			testDataRows++;
		}
		
		int testDataCols = getCellCount(sheetName, testRowNumber) - testDataStartColumn+1;
		
		Object[][] testCaseDataSets = new Object[testDataRows][testDataCols];
		
		for(int i=0;i<testDataRows;i++)
		{
			for(int j=0;j<testDataCols;j++)
			{
				testCaseDataSets[i][j] = getCellData(sheetName, testDataStartColumn+j, testDataStartRow+i);
			}
		}
		
		return testCaseDataSets;
		
	}
	
	
}
