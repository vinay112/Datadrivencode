package com.projectname.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import com.projectname.utilities.ExcelReader;
import com.projectname.utilities.ExtentUtilities;
import com.projectname.utilities.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class Testbase 
{

	public static WebDriver driver;
	public static Properties or = new Properties();
	public static Properties con = new Properties();
	public static FileInputStream fis;
	public static Logger logger = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader("E:\\Sel\\Testframework\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
    public static ExtentReports repo =	ExtentUtilities.getReports();
    public static ExtentTest test;
	@BeforeSuite
	public void Setup() throws IOException {

		if (driver == null) {

			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\propert\\OR.properties");
			or.load(fis);

			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\propert\\config.properties");
			con.load(fis);

			if (con.getProperty("browser").equals("chrome")) {
//				 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executable\\chromedriver.exe");
//				driver = new ChromeDriver();
				WebDriverManager.chromedriver().setup();

				driver = new ChromeDriver();
			}
			driver.get(con.getProperty("testsite_url"));

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(con.getProperty("wait")), TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 10);

		}

	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;

		}

		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public void Click(String locator)
	{    
		if(locator.endsWith("_CSS"))
		{
			driver.findElement(By.cssSelector(or.getProperty(locator))).click();
	}
		
		
		else if(locator.endsWith("_XPATH"))
		{
			driver.findElement(By.xpath(or.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicking on :"+locator);
		
	}
	public void Type(String locator,String value)
	{
     if(locator.endsWith("_CSS"))
     {
		driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);;
	    
     }
     
     else if(locator.endsWith("_XPATH"))
     {
    	 driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);; 
     }
     test.log(LogStatus.INFO, "Typing into :"+locator+ "  entered a value :"+value);
     }
	
	public static void verifyEquals(String Actual,String Expected) throws IOException
	{
		try
		{
			Assert.assertEquals(Actual, Expected);
		}
		catch(Throwable t)
		{
			TestUtil.Screenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.Screenshotprefix + "><img src=" + TestUtil.Screenshotprefix
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.FAIL, " Verification failed with exception : " +t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.Screenshotprefix));

		}
	}
	static WebElement dropdown;

	public void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(or.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(or.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(or.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " value as " + value);

	}

	

	@AfterSuite()

	public void teardown() {
		if (driver != null) {
			driver.quit();
		}

	}
}