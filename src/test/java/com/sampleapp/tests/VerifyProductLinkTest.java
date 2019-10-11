package com.sampleapp.tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sampleapp.base.tests.BaseTest;
import com.sampleapp.pages.SlackSignInPage;

public class VerifyProductLinkTest extends BaseTest{
	
	SlackSignInPage slackSignInPage;
	@BeforeClass
	public void launchApplication() throws InterruptedException, AWTException, IOException
	{
		try {
			init("verifyProductLinkTest","Sudheendran","SlackSignInPage");
			openBrowser("Chrome");
			this.slackSignInPage = new SlackSignInPage(driver);
		} catch (Exception e) {
			test.get(0).skip("@BeforeClass configuration failed");
		}
	}
	
	@Test
	public void verifyProductLinkTest() throws InterruptedException, AWTException, IOException
	{
		try 
		{
			slackSignInPage.navigateToProductPage();
			String productPageTitle = driver.getTitle();
			System.out.println(productPageTitle);
			if(productPageTitle.equals("Collaboration software | Slack"))
			{
				String resultPath = slackSignInPage.takeScreenshot();
				test.get(0).pass("Product page title is matching as expected").addScreenCaptureFromPath(resultPath);
				Assert.assertEquals(productPageTitle, "Collaboration software | Slack");
			}
			else
			{
				String resultPath = slackSignInPage.takeScreenshot();
				test.get(0).fail("Product page title is not matching as expected").addScreenCaptureFromPath(resultPath);
				Assert.fail("Product page title is not matching as expected");
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			String resultPath =slackSignInPage.takeScreenshot();
			test.get(0).skip("Skipping the test due to an exception: "+e.getMessage()).addScreenCaptureFromPath(resultPath);
			
		}
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown() throws IOException
	{
		driver.quit();
	}

}
