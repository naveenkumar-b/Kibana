package com.sampleapp.base.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class TopMenu 
{
	WebDriver driver;

	@FindBy(xpath="")
	protected static WebElement businessContext;

	@FindBy(xpath="")
	protected WebElement languageType;

		public TopMenu(WebDriver driver)
		{
			this.driver=driver;
		}
		
	public void selectBusinessContext(String businessLine)
	{
		Select select = new Select(businessContext);
		select.selectByVisibleText(businessLine);
	}
	
	public void selectBusinessContextByValue(String businessLine)
	{
		Select select = new Select(businessContext);
		select.selectByValue(businessLine);
	}
	
	public void selectLanguage(String language)
	{
		Select select = new Select(languageType);
		select.selectByVisibleText(language);
	}
}
