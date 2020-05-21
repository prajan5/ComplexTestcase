package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
	
public class RealEstateAddMultipleRegionPOM {
		private WebDriver driver; 
	
	public RealEstateAddMultipleRegionPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		
		}
	
	//Locators for Posts functionality
	@FindBy(xpath="//div[contains(text(),'Properties')]")
	public WebElement properties;
	
	@FindBy(linkText="Regions")
	public WebElement Regions;
	
	@FindBy(xpath = "//input[@id='tag-name']")
	private WebElement name;
	
	@FindBy(xpath = "//input[@id='tag-slug']")
	private WebElement slug;
	
	@FindBy(xpath = "//textarea[@id='tag-description']")
	private WebElement description;
	
	
	@FindBy(xpath="//input[@class = 'button button-primary' and @value = 'Add New Region']")
	public WebElement AddNewRegionbtn;		
	
	@FindBy(xpath = "//input[@id='tag-search-input']")
	private WebElement searchByName;
	
	
	@FindBy(xpath="//input[@type='submit' and  @value='Search Regions']")
	private WebElement searchRegionsbtn;
		
	
	//Methods 
	public void clickPropertieslink() throws InterruptedException {
		this.properties.click();
	
	    }
	
	//Click Add Region link
	public void clickRegionslink() throws InterruptedException {
		this.Regions.click();
	
	    }
	//Methods for Region functionality
	public void AddRegion(String sName,String sSlug,String sDesc) throws InterruptedException {
		//Enter Name
		this.name.sendKeys(sName);
		Thread.sleep(1000);
		//Enter slug
		this.slug.sendKeys(sSlug);
		Thread.sleep(1000);
		//Enter description
		this.description.click();
		this.description.sendKeys(sDesc);
		Thread.sleep(1000);
	}
	
	//Select parent Region
	public void selectParentRegion(String sParentRegion) throws InterruptedException {
		
			Select objselect = new Select(driver.findElement(By.xpath("//select[@id= 'parent']")));
			List<WebElement> wegetallRegions = objselect.getOptions();
			for (WebElement weEachRegion :wegetallRegions)
		    {
				String sValue = weEachRegion.getText();
				if (sValue.equalsIgnoreCase(sParentRegion))
		        {
				  objselect.selectByVisibleText(sValue);
					break;
		      	}
		
		    }
	  }
	
	//Click Add New Region button
	public void clickAddNewRegionbtn() throws InterruptedException {
		this.AddNewRegionbtn.click();
	}
	

	 public void SearchRegion(String sName) throws InterruptedException
	   {
		   this.searchByName.sendKeys(sName);
		   Thread.sleep(1000);
		   this.searchRegionsbtn.click();
	   }

	 public void SearchInputClear()throws InterruptedException
	   {
		   this.searchByName.clear();
		   Thread.sleep(1000);
		 	   }
	
}






