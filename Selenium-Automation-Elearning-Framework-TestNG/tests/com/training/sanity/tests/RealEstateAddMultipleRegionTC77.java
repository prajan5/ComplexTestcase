package com.training.sanity.tests;

import org.testng.annotations.Test;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.training.dataproviders.RealEstateDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.RealEstateAddMultipleRegionPOM;
import com.training.pom.RealEstateLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

	public class RealEstateAddMultipleRegionTC77 {
		private WebDriver driver;
		private String baseUrl;
		private RealEstateLoginPOM RlloginPOM;
		private RealEstateAddMultipleRegionPOM RlAddMultipleRegion;
		private static Properties properties;
		private ScreenShot screenShot;
	

		@BeforeTest
		public static void setUpBeforeClass() throws IOException {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/others.properties");
			properties.load(inStream);
		}

	
		@BeforeClass
		public void setUp() throws Exception {
			driver = DriverFactory.getDriver(DriverNames.CHROME);
			Thread.sleep(10000);
			RlloginPOM = new RealEstateLoginPOM(driver); 
			RlAddMultipleRegion= new RealEstateAddMultipleRegionPOM(driver);
			baseUrl = properties.getProperty("baseURL");
			screenShot = new ScreenShot(driver); 
			// open the browser 
			driver.get(baseUrl);
		}
			
		@AfterClass()
		public void tearDown() throws Exception {
			Thread.sleep(1000);
			driver.quit();
		}
				
		
		
		@Test(priority=1,dataProvider = "excel-inputs-Login", dataProviderClass = RealEstateDataProviders.class)
		public void ValidateLoginTest(String username, String password) {
			RlloginPOM.clickLoginLink();
			RlloginPOM.sendUserName(username);
			RlloginPOM.sendPassword(password);
			RlloginPOM.clickSubmitBtn(); 
			System.out.println("Logged in successfully");
			screenShot.captureScreenShot("TC77_Dashboard");
		}
		
		@Test(priority=2,dataProvider = "excel-inputs-TC77", dataProviderClass = RealEstateDataProviders.class)
		public void ValidateNewRegion(String sName,String sSlug, String sDescription, String sParentRegion) throws InterruptedException, AWTException 
		{
			JavascriptExecutor js;
			js = (JavascriptExecutor) driver;
			System.out.println("Adding Regions");
            RlAddMultipleRegion.clickPropertieslink();
            RlAddMultipleRegion.clickRegionslink();
            RlAddMultipleRegion.AddRegion(sName,sSlug,sDescription);
            RlAddMultipleRegion.selectParentRegion(sParentRegion);
			Thread.sleep(1000);
			RlAddMultipleRegion.clickAddNewRegionbtn();
			Thread.sleep(1000);
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
			RlAddMultipleRegion.SearchRegion(sName);
			Thread.sleep(5000);
			System.out.println("Verify Added catergory is in the existing module");
			String sGetName = driver.findElement(By.xpath("//tr[1]//td[1]//strong[1]//a[1]")).getText();
			String sGetDesc = driver.findElement(By.xpath("//tr[1]//td[2]")).getText();
		    String sGetslug = driver.findElement(By.xpath("//tr[1]//td[3]")).getText();
		   	Assert.assertEquals(sGetName,sName);
			Assert.assertEquals(sGetDesc,sDescription);
			Assert.assertEquals(sGetslug,sSlug.toLowerCase());
			screenShot.captureScreenShot("TC77_AddedRegion"+sName+"");
			RlAddMultipleRegion.SearchInputClear();
			
				
		 }
			
		@Test(priority=3)
		public void LogOutTest() throws InterruptedException
		{
			System.out.println("Validate logout functionality");
			WebElement Webadmin = driver.findElement(By.id("wp-admin-bar-my-account"));
			Actions act = new Actions(driver);
			act.moveToElement(Webadmin).build().perform();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@Class='ab-item'][contains(text(),'Log Out')]")).click();
			screenShot.captureScreenShot("TC77_Logout");
		}
		
		
	}
		


	

