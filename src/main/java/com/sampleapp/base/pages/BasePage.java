package com.sampleapp.base.pages;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class BasePage 
{
	public static WebDriver driver;
	public Actions action;
	String parentWindow;
	String childWindow;
	public ExtentTest test;
	public TopMenu menu;
	public List<Date> alertDate= new ArrayList<Date>();
	public String timeStamp;
	public WebDriverWait wait;
	public Properties prop = new Properties();
	public InputStream input = null;
	
	public BasePage(WebDriver driver)
	{
		BasePage.driver = driver;
		PageFactory.initElements(driver, this);
		menu =new TopMenu(driver);
		PageFactory.initElements(driver, menu);
	}
	
	public BasePage(WebDriver driver,ExtentTest test)
	{
		BasePage.driver=driver;
		PageFactory.initElements(driver, this);		
		this.test=test;
		menu = new TopMenu(driver);
		PageFactory.initElements(driver, menu);
	}
	
	public void launchApplication() throws InterruptedException, AWTException, IOException
	{
		input = new FileInputStream(System.getProperty("user.dir")+"\\resources\\config.properties");
		prop.load(input);
		Thread.sleep(3000);
		driver.get(prop.getProperty("URL"));
	}
	
	public String takeScreenshot()	
	{
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try 
		{
		long fileName = Calendar.getInstance().getTimeInMillis();
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"/ExtentReports/Snapshots/"+fileName+".png"));
		return "Snapshots/"+fileName+".png";
		}
		catch(Exception e)
		{
			e.getMessage();
			return "";
		}
	}
	
	public boolean isElementPresent(String xpath)
	{
		int element_Size=driver.findElements(By.xpath(xpath)).size();
		if(element_Size==0)
			return false;
		else
			return true;
	}
	
	public void doubleClickOnElement(WebElement element)
	{
		action =new Actions(driver);
		action.doubleClick(element).perform();
	}

	public void singleClickOnElement(WebElement element)
	{
		action =new Actions(driver);
		action.click(element).perform();
	}
	
	
	
	public void waitForVisiblityOfElement(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForInVisiblityOfElement(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForElementToBeClickable(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForPageRefresh(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
	}
	
	public void waitForNotStalenessOfElement(WebElement element)
	{
		 boolean isStillOnOldPage = true;
		    while (isStillOnOldPage) {
		        try {
		        	element.getAttribute("value");
		        } catch (StaleElementReferenceException e) {
		            isStillOnOldPage = false;
		        }
		    }
		    waitForVisiblityOfElement(element);
	}
	
	public void selectOptionByVisibleText(WebElement element, String text)
	{
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	public void selectOptionByValue(WebElement element, String text)
	{
		Select select = new Select(element);
		select.selectByValue(text);
	}
	
	public static void waitForInVisiblityOfAllElements(List<WebElement> elements)
	{
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}
	
	public void scrollIntoView(WebElement element)
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static boolean isNotClickable(WebElement el, WebDriver driver) 
    {
        try{
            WebDriverWait wait = new WebDriverWait(driver, 6);
            wait.until(ExpectedConditions.elementToBeClickable(el));
            return false;
        }
        catch (Exception e){
            return true;
        }
    }
	
}
