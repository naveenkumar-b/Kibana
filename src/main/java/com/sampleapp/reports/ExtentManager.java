package com.sampleapp.reports;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	static ExtentHtmlReporter htmlReporter;
	static ExtentReports extent;
	static Properties prop = new Properties();
	static InputStream input = null;
	public static ExtentReports setup()
	{
		if (extent == null) 
		{
			try {
				input = new FileInputStream(System.getProperty("user.dir")+"\\resources\\config.properties");
				prop.load(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Date d=new Date();
			String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
			extent = new ExtentReports();
			htmlReporter =  new ExtentHtmlReporter(System.getProperty("user.dir")+"\\ExtentReports\\"+fileName);
			extent.attachReporter(htmlReporter);
			htmlReporter.config().setReportName("Regression Testing Executed for slack:- "+prop.getProperty("URL"));
			htmlReporter.config().setTheme(Theme.STANDARD);
		}
		return extent;
	}

}
