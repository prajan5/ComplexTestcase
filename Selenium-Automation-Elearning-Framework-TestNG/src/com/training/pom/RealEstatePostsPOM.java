package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
	
public class RealEstatePostsPOM {
		private WebDriver driver; 
	
	public RealEstatePostsPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		
		}
			
	//Locators for Posts functionality
	@FindBy(xpath="//div[contains(text(),'Posts')]")
	public WebElement posts;
	
	@FindBy(linkText="Add New")
	public WebElement Addnew;
	
	@FindBy(id ="title")
	public WebElement posttitle;
	
	@FindBy(id ="content")
	public WebElement content;
	
	@FindBy(xpath ="//ul[@id ='categorychecklist']")
	private WebElement categorychecklist;
	
	@FindBy(xpath = "//input[@class='button button-primary button-large' and @value='Publish']")
	public WebElement publishBtn;
	
	@FindBy(xpath="//div//p[contains(text(),'Post published')]")
	public WebElement publishMessage;
	
	
	
	//Methods for Posts functionality
	public void AddPosts(String sPostTitle,String sBodyText) throws InterruptedException {
		//Click Post menu
		this.posts.click();
		Thread.sleep(1000);
		//Click Add New from submenu
		this.Addnew.click();
		//Enter Title 
		this.posttitle.sendKeys(sPostTitle);
		Thread.sleep(1000);
		//Enter content in the body
		this.content.click();
		this.content.sendKeys(sBodyText);
		
	    }
		
	public void selectCategoryCheckbox(String sCategory) throws InterruptedException
    {
		WebElement checkboxCategory=driver.findElement(By.xpath("//label[contains(text(),'"+sCategory+"')]/input[@type='checkbox' and @name = 'post_category[]']"));
		checkboxCategory.click();
		Thread.sleep(10000);
		System.out.println(sCategory+"checkbox state is"+checkboxCategory.isSelected());
		Thread.sleep(3000);
	
    }	
	//Click publish button
	public void clickPublishButton()
	{
			this.publishBtn.click();
	}
     //To get the published message after New Posts
	public String getpublishedMessage() throws InterruptedException {
		Thread.sleep(1000);
		String sMsg = this.publishMessage.getText();
		return sMsg;
	}
	
	
	

}






