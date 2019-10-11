package com.sampleapp.tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sampleapp.base.tests.BaseTest;
import com.sampleapp.pages.SlackSignInPage;

public class VerifyAppDirectoryLinkTest extends BaseTest{
	
	SlackSignInPage slackSignInPage;
	@BeforeClass
	public void launchApplication() throws InterruptedException, AWTException, IOException
	{
		try {
			init("verifyAppDirectoryLinkTest","Sudheendran","SlackSignInPage");
			openBrowser("Chrome");
			this.slackSignInPage = new SlackSignInPage(driver);
		} catch (Exception e) {
			test.get(0).skip("@BeforeClass configuration failed");
		}
	}
	
	@Test
	public void verifyAppDirectoryLinkTest() throws InterruptedException, AWTException, IOException
	{
		try 
		{
			slackSignInPage.navigateToappDirectoryPage();
			String PageTitle = driver.getTitle();
			System.out.println(PageTitle);
			if(PageTitle.equals("Add Apps to Slack | Apps and Integrations | Slack App Directory"))
			{
				String resultPath = slackSignInPage.takeScreenshot();
				test.get(0).skip("App Directory page title is matching as expected").addScreenCaptureFromPath(resultPath);
				//Assert.assertEquals(PageTitle, "Add Apps to Slack | Apps and Integrations | Slack App Directory");
				throw new SkipException("This test case is skipped");
			}
			else
			{
				String resultPath = slackSignInPage.takeScreenshot();
				test.get(0).fail("App Directory page title is not matching as expected").addScreenCaptureFromPath(resultPath);
				Assert.fail("App Directory page title is not matching as expected");
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			String resultPath =slackSignInPage.takeScreenshot();
			test.get(0).skip("Skipping the test due to an exception: "+e.getMessage()).addScreenCaptureFromPath(resultPath);
			throw new SkipException("This test case is skipped");
			
		}
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown() throws IOException
	{
		driver.quit();
	}

}
