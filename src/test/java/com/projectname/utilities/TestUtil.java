package com.projectname.utilities;
import com.projectname.base.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

public class TestUtil extends Testbase
{
	
	public static String Screenshotprefix ;
 public static void  Screenshot() throws IOException
 {
	 Date date = new Date();
	Screenshotprefix = date.toString().replaceAll(" ", "_").replaceAll(":","_")+".jpg";
	
	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
   
	FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"//target//surefire-reports//html//"+Screenshotprefix+""));

	
 }
 @DataProvider(name="dp")
	public Object[][] getData(Method m) {
	 System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&");
	 System.out.println(m.getName());
System.out.println("***********************");
	 String SheetName = m.getName();
	 
		int rows = excel.getRowCount(SheetName);
		System.out.println(rows);
		int cols = excel.getColumnCount(SheetName);
		System.out.println(cols);

		Object data[][] = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {

				data[rowNum - 2][colNum] = excel.getCellData(SheetName,colNum,rowNum);
				 System.out.println("Fetched data from excel sheet is -- "+data[rowNum-2][colNum]);
			}
		}
		return data;

	}
 public static  boolean isTestrunnable(String testName, ExcelReader excel)
 {
	String sheetName = "Testsuite";
	int rownum = excel.getRowCount(sheetName);

	for(int rNum=2; rNum<=rownum; rNum++){
		
		String testCase = excel.getCellData(sheetName, "TCID", rNum);
		
		if(testCase.equalsIgnoreCase(testName)){
			
			String runmode = excel.getCellData(sheetName, "RunMode", rNum);
			
			if(runmode.equalsIgnoreCase("Y"))
				return true;
			else
				return false;
		}
		
		
	}
	return false;
}



 }
	
		



