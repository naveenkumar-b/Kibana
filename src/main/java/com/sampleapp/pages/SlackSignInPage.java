package com.sampleapp.pages;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sampleapp.base.pages.BasePage;


public class SlackSignInPage extends BasePage{
	
	@FindBy(xpath="(//a[contains(text(),'Product')])[3]")
	protected WebElement productLink;
	
	@FindBy(xpath="//a[contains(text(),'Enterprise')]")
	protected WebElement enterpriseLink;
	
	@FindBy(xpath="(//a[contains(text(),'Pricing')])[3]")
	protected WebElement pricingLink;

	@FindBy(xpath="(//a[contains(text(),'Support')])[3]")
	protected WebElement supportLink;
	
	@FindBy(xpath="//a[contains(text(),'Slack Guides')]")
	protected WebElement slackGuideLink;
	
	@FindBy(xpath="//a[contains(text(),'App Directory')]")
	protected WebElement appDirectoryLink;
	
	@FindBy(xpath="//a[contains(text(),'API')]")
	protected WebElement apiLink;
	
	
	
	public SlackSignInPage(WebDriver driver) throws InterruptedException, AWTException, IOException 
	{
		super(driver);
		PageFactory.initElements(driver, this);
		launchApplication();
		Thread.sleep(2000);
	} 
	
	public void navigateToProductPage() throws InterruptedException
	{
		productLink.click();
		Thread.sleep(2000);
	}
	public void navigateToEnterpricePage() throws InterruptedException
	{
		enterpriseLink.click();
		Thread.sleep(2000);
	}
	
	public void navigateToPricingPage() throws InterruptedException
	{
		pricingLink.click();
		Thread.sleep(2000);
	}
	public void navigateToSupportPage() throws InterruptedException
	{
		supportLink.click();
		Thread.sleep(2000);
	}
	public void navigateToslackGuidePage() throws InterruptedException
	{
		slackGuideLink.click();
		Thread.sleep(2000);
	}
	public void navigateToappDirectoryPage() throws InterruptedException
	{
		appDirectoryLink.click();
		Thread.sleep(2000);
	}
	public void navigateToAPIPage() throws InterruptedException
	{
		apiLink.click();
		Thread.sleep(2000);
	}

}
