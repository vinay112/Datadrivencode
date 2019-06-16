package com.projectname.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.projectname.base.Testbase;

public class BankManagerloginTest extends Testbase
{
	@Test
public void bankmanagerloginTest() throws InterruptedException, IOException
{
		
	verifyEquals("avsc", "csdcsd");
	Click("bml_btn_CSS");
	Assert.assertTrue(isElementPresent(By.cssSelector(or.getProperty("add_cst_bttn_CSS")))," script is not successfull");
	Thread.sleep(10000);
	logger.info("script run succesfully");
	
}
}
