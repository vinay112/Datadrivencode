package com.projectname.testcases;

import org.openqa.selenium.Alert;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.projectname.base.Testbase;
import com.projectname.utilities.TestUtil;

public class AddCustomerTest extends Testbase {
	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void addcustomerTest(String Firstname, String Lastname, String Pincode, String popup, String runmode)
			throws InterruptedException {

		if (!runmode.equalsIgnoreCase("Y")) {
			throw new SkipException("Skipping the test " + "AddCustomertest".toUpperCase() + "as the Run mode is NO");
		}
		Click("add_cst_bttn_CSS");
		Thread.sleep(1000);
		Type("Firstname_CSS", Firstname);
		Thread.sleep(1000);
		Type("Lastname_CSS", Lastname);
		Thread.sleep(1000);
		Type("postalcode_CSS", Pincode);
		Click("submit_CSS");
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(popup));
		alert.accept();
		Thread.sleep(2000);
	}

}
