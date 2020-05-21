package com.training.sanity.tests;
	
	import org.testng.annotations.Test;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Properties;
	import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By.ByXPath;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeSuite;
	import org.testng.annotations.Test;

import com.training.dataproviders.RealEstateDataProviders;
import com.training.generics.ScreenShot;
	import com.training.pom.RealEstateCategoryPOM;
	import com.training.pom.RealEstateLoginPOM;
import com.training.pom.RealEstatePostsPOM;
import com.training.utility.DriverFactory;
	import com.training.utility.DriverNames;

	public class RealEstateMutiplePostsCategoryTC78 {
			private WebDriver driver;
			private String baseUrl;
			private RealEstateLoginPOM RlloginPOM;
			private RealEstateCategoryPOM RlCategory;
			private RealEstatePostsPOM RlPosts;
			private static Properties properties;
			private ScreenShot screenShot;
		;
		

			@BeforeSuite
			public static void setUpBeforeClass() throws IOException {
				properties = new Properties();
				FileInputStream inStream = new FileInputStream("./resources/others.properties");
				properties.load(inStream);
			}

		
			@BeforeClass
			public void setUp() throws Exception {
				driver = DriverFactory.getDriver(DriverNames.CHROME);
				RlloginPOM = new RealEstateLoginPOM(driver); 
				RlCategory= new RealEstateCategoryPOM(driver);
				RlPosts=new RealEstatePostsPOM(driver);
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
				screenShot.captureScreenShot("TC78_Dashboard");
			}
			
			@Test(priority=2,dataProvider = "excel-inputs-TC78", dataProviderClass = RealEstateDataProviders.class)
			public void ValidateNewCategory(String sCategoryTitle,String sCategorySlug,
					String sCategoryDescrip,String sPostTitle,String sPostcontent) throws InterruptedException 
			{
				JavascriptExecutor js;
				js = (JavascriptExecutor) driver;
				System.out.println("Add category");
				RlCategory.AddCategory(sCategoryTitle,sCategorySlug,sCategoryDescrip);
				RlCategory.clickAddNewCategoryBtn();
				System.out.println("Add Post with added category");
				RlPosts.AddPosts(sPostTitle, sPostcontent);
				Thread.sleep(1000);
				js.executeScript("window.scrollBy(0,200)");
				RlPosts.selectCategoryCheckbox(sCategoryTitle);
				screenShot.captureScreenShot("TC78_"+sCategoryTitle);
				js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
				Thread.sleep(1000);
				RlPosts.clickPublishButton();
				String sExpectedMsg = "Post published. View post";
				String sActualMsg = RlPosts.getpublishedMessage();
				//Verify the published message after new posts
				Assert.assertEquals(sActualMsg,sExpectedMsg);
				System.out.println(sActualMsg);
				screenShot.captureScreenShot("TC78_Publish"+sCategoryTitle+"");
			
			}

			
			@Test(priority=5)
			public void ValidateLogOut() throws InterruptedException
			{
				System.out.println("Validate logout functionality");
				WebElement Webadmin = driver.findElement(By.id("wp-admin-bar-my-account"));
				Actions act = new Actions(driver);
				act.moveToElement(Webadmin).build().perform();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//a[@Class='ab-item'][contains(text(),'Log Out')]")).click();
				screenShot.captureScreenShot("LogoutCategory");
			}
	}		
			
			
		
			
			
		


	

