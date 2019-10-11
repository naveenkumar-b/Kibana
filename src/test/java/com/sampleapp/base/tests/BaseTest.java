package com.sampleapp.base.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.sampleapp.reports.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver =null;
	public ExtentReports rep  = ExtentManager.setup();
	public ArrayList<ExtentTest> test =new ArrayList<ExtentTest>();
	public Hashtable<String, String> moduleName=new Hashtable<String, String>();
	public Hashtable<String, String> authorName=new Hashtable<String, String>();
	public Properties prop = new Properties();
	public InputStream input = null;
	private static final String CHROMIUM_PATH = System.getProperty("user.dir") + "\\chromium\\chrome.exe";
	
	public void init(String testName, String author, String testCategory) throws IOException
	{
		test.add(rep.createTest(testName));
		test.get(0).assignAuthor(author);
		test.get(0).assignCategory(testCategory);
		input = new FileInputStream(System.getProperty("user.dir")+"\\resources\\config.properties");
		prop.load(input);
	}
	
	
	public void openBrowser(String bName) throws MalformedURLException, UnknownHostException
	{
		if(bName.equals("Mozilla"))
		{
			driver = new FirefoxDriver();
			
		}
		else if(bName.equals("Chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("disable-infobars");
			options.addArguments("chrome.switches","--disable-extensions");
			options.addArguments("exited_cleanly","true");
			options.addArguments("exit_type","None");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\ExeFiles\\chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			driver= setupChromeDriver(chromeOptions());
			//driver = new RemoteWebDriver(new URL("http://nllh4000521380:4444/wd/hub"), capabilities);
			
			
		}
		else if(bName.equals("ie"))	
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\ExeFiles\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			
		}	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	private static WebDriver setupChromeDriver(ChromeOptions options) {
        WebDriverManager
                .chromedriver()
                .version("2.44")
                .targetPath(new File(System.getProperty("user.dir") + "\\ExeFiles").getAbsolutePath())
                .forceCache()
                .setup();
        return new ChromeDriver(options);
    }

    private static ChromeOptions chromeOptions() {
        File chromeBinary = new File(CHROMIUM_PATH);
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"\\ExtentReports\\Snapshots\\DownloadedFiles");
        options.setExperimentalOption("prefs", chromePrefs);
        options.setBinary(chromeBinary.getAbsolutePath());
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--always-authorize-plugins");
        options.addArguments("--incognito");
        options.addArguments("--window-size=800,600");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--ignore-ssl-errors");
        options.addArguments("--ssl-protocol=any");
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--no-sandbox");
        options.addArguments("--v");
        options.addArguments("--disable-setuid-sandbox");
        options.addArguments("--disable-extensions");
//        options.addArguments("--proxy-server=" + PROXY_URL);
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-debugging-port=9222");
        options.setExperimentalOption("useAutomationExtension", false);
        return options;
    }
	
	@AfterSuite
	public void flushReport() throws IOException
	{
		rep.flush();
		//driver.close();
		//String killChromeDriver = System.getProperty("user.dir")+"\\BatchFiles\\kill_chromedriver.bat";
		//Runtime.getRuntime().exec(killChromeDriver);
		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
	}

}
