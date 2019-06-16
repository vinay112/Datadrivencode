package com.projectname.listener;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.projectname.base.Testbase;
import com.projectname.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class Customlistener extends Testbase implements ITestListener {

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
	test = repo.startTest(arg0.getName().toUpperCase());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output","false");
       
			try {
				TestUtil.Screenshot();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			test.log(LogStatus.FAIL, result.getName(), result.getName().toUpperCase()+" Failed with Exception "+result.getThrowable());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.Screenshotprefix));
			repo.endTest(test);
			repo.flush();
		Reporter.log("Script executed successfully");
		Reporter.log("<a target = \"blank\" href ="+result.getName()+""+TestUtil.Screenshotprefix+" >Screenshotdatalink</a>");
        Reporter.log("<br></br>");
		Reporter.log("<a target = \"blank\" href ="+TestUtil.Screenshotprefix+" ><img src = "+TestUtil.Screenshotprefix+" height = 200 width = 200></img></a>");

	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		
		test.log(LogStatus.PASS, arg0.getName(), arg0.getName().toUpperCase()+" Pass");
		repo.endTest(test);
		repo.flush();
	}

}
