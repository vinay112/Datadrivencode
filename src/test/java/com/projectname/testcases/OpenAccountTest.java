package com.projectname.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.projectname.base.Testbase;
import com.projectname.utilities.TestUtil;

public class OpenAccountTest extends Testbase
{
	

		@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
		public void openAccountTest(String customer ,String currency) throws InterruptedException {
			if(!(TestUtil.isTestrunnable("openAccountTest", excel))){
				
				throw new SkipException("Skipping the test "+"openAccountTest".toUpperCase()+ "as the Run mode is NO");
			}
			
		     Click("openaccount_CSS");
			select("customer_CSS", customer);
			select("currency_CSS", currency);
			Click("process_CSS");
			Thread.sleep(2000);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.accept();

		}

	}

