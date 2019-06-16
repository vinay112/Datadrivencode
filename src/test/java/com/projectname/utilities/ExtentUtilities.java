package com.projectname.utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentUtilities
{
	public static ExtentReports extents;

	public static ExtentReports getReports() 
	{
		if (extents == null) 
		{
			extents = new ExtentReports(System.getProperty("user.dir")+ "\\target\\surefire-reports\\html\\extents.html", true,DisplayOrder.OLDEST_FIRST);
            extents.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\extentconfig\\ReportsConfig.xml"));
		}
		return extents;

	}

}
