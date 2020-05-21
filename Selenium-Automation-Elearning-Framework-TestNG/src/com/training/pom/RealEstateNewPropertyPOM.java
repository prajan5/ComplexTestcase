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
	
public class RealEstateNewPropertyPOM {
		private WebDriver driver; 
	
	public RealEstateNewPropertyPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		
		}
	
	//Locators for Properties 
	@FindBy(xpath="//div[contains(text(),'Properties')]")
	public WebElement properties;
	
	@FindBy(linkText="Add New")
	public WebElement Addnew;
	
	@FindBy(id ="title")
	public WebElement propertytitle;
	
	@FindBy(id ="content")
	public WebElement content;
	
	@FindBy(xpath="//div//textarea[@class='cmb2_textarea' and @id='_price']")
	public WebElement pricetxt;
	
	@FindBy(xpath="//div//input[@type='text' and @id='_price_per']")
	public WebElement PricePertxt;
	
	//Main details tab
	
	@FindBy(xpath="//a[@href='#main_details_tab'  and @id='ui-id-2'][contains(text(),'Main Details')]")
	public WebElement Maindetailslink;
	
	@FindBy(xpath="//div/input[@type='text' and @id = '_status']")
	public WebElement statustxt;
	
	@FindBy(xpath="//div/input[@type='text' and @id = '_location']")
	public WebElement locationtxt;
	
	@FindBy(xpath="//div/input[@type='text' and @id = '_possession']")
	public WebElement _possessiontxt;
	
	//Location tab
			
	@FindBy(xpath="//a[@href='#locations_tab'  and @id='ui-id-3'][contains(text(),'Location')]")
	public WebElement locationlink;
	
	@FindBy(xpath="	//div/input[@type='text' and @id = '_friendly_address']")
	public WebElement addresstxt;
	
	
	@FindBy(xpath="	//div/input[@type='text' and @id = '_address']")
	public WebElement googleMapAaddresstxt;
	
	
	@FindBy(xpath="	//div/input[@type='text' and @id = '_geolocation_lat']")
	public WebElement latitudetxt;
	
	
	@FindBy(xpath="	//div/input[@type='text' and @id = '_geolocation_long']")
	public WebElement longtitudetxt;
	
	//Details tab	
	
	@FindBy(xpath="//a[@href='#details_tab'  and @id='ui-id-4'][contains(text(),'Details')]")
	public WebElement detailslink;
	
	@FindBy(xpath="	//div/input[@type='text' and @id = '_storage_room']")
	public WebElement storageroomtxt;
		
	//Locators to scroll the window
	@FindBy(xpath="//div[@class ='categorychecklist-holder']")
	public WebElement Keywordchecklist;
	
	@FindBy(xpath="//ul[@id='property_featurechecklist']")
	public WebElement Featurechecklist;
	
	@FindBy(xpath="//ul[@id='regionchecklist']")
	public WebElement Regionchecklist;
	
	//Locators To publish the message
	
	@FindBy(xpath="//div[@id='major-publishing-action']")
	public WebElement PublishinAction;
				
	@FindBy(xpath = "//input[@class='button button-primary button-large' and @id='publish']")
	public WebElement publishBtn;
	
	@FindBy(xpath="//div//p[contains(text(),'Post published')]")
	public WebElement publishMessage;
	
	//Homepage 
	
	@FindBy(xpath="//div//a[@title='Real Estate']")
	public WebElement RealEstateicon;
	
	@FindBy(xpath="//input[@title='Search input' and @type = 'search']")
	public WebElement Searchinput;
	
	@FindBy(xpath="//span[@class='overlap']")
	public WebElement searchinputclick;
	
	
	
	
	




				
	//Methods 
	public void clickPropertieslink() throws InterruptedException {
		this.properties.click();
	
	    }
	
	//Click Add New to add property 
	public void clickAddNew() throws InterruptedException {
		this.Addnew.click();
	
	    }
	
	//To enter Property Title
	public void sendTitle(String sPropTitle) {
		this.propertytitle.clear();
		this.propertytitle.sendKeys(sPropTitle);
	}
	//To enter content in body of text
	public void sendContent(String sContent) {
		this.content.clear(); 
		this.content.sendKeys(sContent); 
		
	}
	
	//To enter PricePer
	public void sendPrice(String sPrice) {
		this.pricetxt.sendKeys(sPrice);
	}
		
	//To enter PricePer
	public void sendPricePer(String sPricePer) {
		this.PricePertxt.sendKeys(sPricePer); 
		}
		
	//To click Main details link
	public void clickMaindDetailslink() {
		this.Maindetailslink.click();
			
		}
		
   public void sendMainDetails(String sStatus,String sLocation,String sPossession)
   {
		this.statustxt.clear(); 
		this.statustxt.sendKeys(sStatus); 
		this.locationtxt.clear(); 
		this.locationtxt.sendKeys(sLocation); 
		this._possessiontxt.clear(); 
		this._possessiontxt.sendKeys(sPossession); 
		}	
			
	//To click Location link
	public void clickLocationlink() {
		this.locationlink.click();
				
		}	
			
	//To enter Location details
	public void sendLocationDetails(String sAddress,String sGoogleMapAddress,String sLatitude,String sLongtitude) {
		this.addresstxt.clear(); 
		this.addresstxt.sendKeys(sAddress); 
		this.googleMapAaddresstxt.clear(); 
		this.googleMapAaddresstxt.sendKeys(sGoogleMapAddress); 
		this.latitudetxt.clear(); 
		this.latitudetxt.sendKeys(sLatitude); 
		this.longtitudetxt.clear(); 
		this.longtitudetxt.sendKeys(sLongtitude); 
		}
	
	//To click Location link
	public void clickDetailslink() {
		this.detailslink.click();
				
		}	
	
	//To enter Storage room
	public void sendStorageRoom(String sStorageRoom) {
		this.storageroomtxt.clear(); 
		this.storageroomtxt.sendKeys(sStorageRoom); 
				}	
	
	//To select keyword checkbox
	public void selectKeywordCheckbox(String sKeyword) throws InterruptedException
    {
	
	 WebElement keywordcheckbox = driver.findElement(By.xpath("//span[contains(text(),'"+sKeyword+"')]/../input[@type='checkbox']"));
	 keywordcheckbox.click();
	 Thread.sleep(1000);
	 System.out.println(keywordcheckbox.isSelected());
	 System.out.println(sKeyword+"checkbox state is"+keywordcheckbox.isSelected());
	
    }	 
	//To select Feature checkbox
	public void selectFeatureCheckbox(String sFeature) throws InterruptedException
    {
	
		WebElement checkbox1feature=driver.findElement(By.xpath("//label[contains(text(),'"+sFeature+"')]/input[@type='checkbox' and @name = 'tax_input[property_feature][]']"));
		//js.executeScript("arguments[0].scrollIntoView(true);",checkbox1feature);
		checkbox1feature.click();
		Thread.sleep(10000);
		System.out.println(sFeature+"checkbox state is"+checkbox1feature.isSelected());
		Thread.sleep(3000);
	
    }	
	//To select Region checkbox
	public void selectRegionCheckbox(String sRegion) throws InterruptedException
    {
	
		WebElement checkboxRegion=driver.findElement(By.xpath("//label[contains(text(), '"+sRegion+"')]/input[@type='checkbox' and @name = 'tax_input[region][]']"));
		checkboxRegion.click();
		Thread.sleep(10000);
		System.out.println(sRegion+"state is"+checkboxRegion.isSelected());
		Thread.sleep(10000);
	
    }
	
	public void clickpublishBtn() throws InterruptedException {
		this.publishBtn.click();
	    }
	
	//To get the published message 
	public String getpublishedMessage() throws InterruptedException {
		Thread.sleep(1000);
		String sMsg = this.publishMessage.getText();
		return sMsg;
	
	}
	
	//To click Real Estate icon
	public void clickRealEstateicon() throws InterruptedException {
	  this.RealEstateicon.click();
	}
				    
		
	public void searchProperty(String sPropertyTitle) {
		// TODO Auto-generated method stub
		 this.Searchinput.sendKeys(sPropertyTitle);
		this.searchinputclick.click();
		}
	
	
	
	
	
	
}





