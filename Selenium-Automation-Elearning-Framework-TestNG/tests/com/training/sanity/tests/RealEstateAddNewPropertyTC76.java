package com.training.sanity.tests;

import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RealEstateAddFeaturePropertyPOM;
import com.training.pom.RealEstateLoginPOM;
import com.training.pom.RealEstateNewPropertyPOM;
import com.training.pom.RealEstatePostsPOM;
import com.training.pom.RealEstatePropertiesPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

	public class RealEstateAddNewPropertyTC76 {
		private WebDriver driver;
		private String baseUrl;
		private RealEstateLoginPOM RlloginPOM;
		private RealEstateNewPropertyPOM RlAddNewProperty;
		private static Properties properties;
		private ScreenShot screenShot;
	
	    String sKeyWord = "CentralPriyaKeyWord";
		String sPropertyTitle = "WProtitle";
		String sPropertyContent = "WProcontent";
		
		//Price
		
		String sPrice = "200,000";
		String sPricePer = "100";
		//Main details
		String sStatus ="Completed";
		String sLocation = "Vellore";	
		String sPossession = "Notimmediate";
		//Location
		String sAddress = "Chennai";
		String sGoogleMap ="Chennai";
		String sLatitude = "100";
		String sLongtitude = "86";
		//
		String sStorageRoom = "2";
		//Feature & Region
		String sFeature = "FinancialF1";
		String sRegion= "PriyaRegion1";
	
		
	

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
			RlAddNewProperty= new RealEstateNewPropertyPOM(driver);
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
				
		
		
		@Test(priority=1)
		public void ValidateLoginTest() {
			RlloginPOM.clickLoginLink();
			RlloginPOM.sendUserName("admin");
			RlloginPOM.sendPassword("admin@123");
			RlloginPOM.clickSubmitBtn(); 
			screenShot.captureScreenShot("TC76_Dashboard");
		}
		
		@Test(priority=2)
		public void ValidateNewProperty() throws InterruptedException, AWTException 
		{
			JavascriptExecutor js;
			js = (JavascriptExecutor) driver;
		
            System.out.println("Add New properties");
            //Click properties link
			RlAddNewProperty.clickPropertieslink();
			//Click Add new sub menu
			RlAddNewProperty.clickAddNew();
			//Enter Title   
			RlAddNewProperty.sendTitle(sPropertyTitle);
			//Enter Content
			RlAddNewProperty.sendContent(sPropertyContent);
			js.executeScript("window.scrollBy(0,500)");
			//Enter Price
			RlAddNewProperty.sendPrice(sPrice);
			RlAddNewProperty.sendPricePer(sPricePer);
		
			//Enter Main details 
			RlAddNewProperty.clickMaindDetailslink();
			RlAddNewProperty.sendMainDetails(sStatus,sLocation,sPossession);
			//Enter Location details
			RlAddNewProperty.clickLocationlink();
			RlAddNewProperty.sendLocationDetails(sAddress,sGoogleMap,sLatitude,sLongtitude);
			//Enter Details 
			RlAddNewProperty.clickDetailslink();
			RlAddNewProperty.sendStorageRoom(sStorageRoom);
			Thread.sleep(5000);
			js.executeScript("arguments[0].scrollIntoView(true);",RlAddNewProperty.Keywordchecklist);
			Thread.sleep(5000);
			RlAddNewProperty.selectKeywordCheckbox(sKeyWord);
			js.executeScript("arguments[0].scrollIntoView(true);",RlAddNewProperty.Featurechecklist);
			RlAddNewProperty.selectFeatureCheckbox("FinancialF1");
			js.executeScript("arguments[0].scrollIntoView(true);",RlAddNewProperty.Regionchecklist);
			RlAddNewProperty.selectRegionCheckbox("PriyaRegion1");
			screenShot.captureScreenShot("TC_76_+");
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
			//Click publish button	
			RlAddNewProperty.clickpublishBtn();
		    //Verify the published message after new posts
			String sExpectedMsg = "Post published. View post";
			String sActualMsg = RlAddNewProperty.getpublishedMessage();
			Assert.assertEquals(sActualMsg,sExpectedMsg);
			System.out.println(sActualMsg);
			screenShot.captureScreenShot("TC_76_PublishNewpropertyMessage");
							
			
			
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
			screenShot.captureScreenShot("TC76_Logout");
		}
		
		
		
		@Test(priority=4)
		public void VerifyAddedProperty() throws InterruptedException
		{
			RlAddNewProperty.clickRealEstateicon();
			System.out.println("Search for added property");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1300)");
			Thread.sleep(1000);
			String OldWindowHandle=driver.getWindowHandle();
			RlAddNewProperty.searchProperty(sPropertyTitle);
			
			for(String strHandle:driver.getWindowHandles()) {
				if(!strHandle.equals(OldWindowHandle)) {
					driver.switchTo().window(strHandle);
				}
			
			}
			
			String spriceformat = "Rs."+sPrice+"";
			String sPriceperformat = "Rs."+sPricePer+" / sq ft";
			Assert.assertEquals(sStorageRoom,driver.findElement(By.xpath( "//li[@class='single-property-detail-_storage_room']//span")).getText());
			Assert.assertEquals(sKeyWord,driver.findElement(By.xpath("//a[contains(text(),'CentralPriyaKeyWord')]")).getText());
			Assert.assertEquals(sPropertyContent,driver.findElement(By.xpath("//p[contains(text(),'"+sPropertyContent+"')]")).getText());
			Assert.assertEquals(sFeature,driver.findElement(By.xpath("//a[contains(text(),'"+sFeature+"')]")).getText());
			Assert.assertEquals(spriceformat,driver.findElement(By.xpath("//div[@class='property-pricing']/div[1]")).getText());
			Assert.assertEquals(sPriceperformat,driver.findElement(By.xpath("//div[@class='property-pricing']/div[2]")).getText());
			screenShot.captureScreenShot("TC76_PropertyHomepage");
		}
	}
		


	

