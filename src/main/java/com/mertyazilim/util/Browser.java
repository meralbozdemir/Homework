package com.mertyazilim.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;




public class Browser 
{
	public static WebDriver createDriver(String browser) throws MalformedURLException
	{
		WebDriver driver=null; 
		
		
		if(browser.equalsIgnoreCase("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.gecko.driver","Util\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		}
		
		else if(browser.equalsIgnoreCase("RemoteChrome"))
		{
			driver = new RemoteWebDriver(new URL("http://10.10.19.49:4444/wd/hub"), DesiredCapabilities.chrome());
		}

		
		else if(browser.equalsIgnoreCase("ChromeLocal"))
		{
			System.setProperty("webdriver.chrome.driver","Util\\chromedriver.exe"); 
			driver = new ChromeDriver();
					
		}
		
		
		else if(browser.equalsIgnoreCase("ChromeHeadLess"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("window-size=1680x1050");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-plugins");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-extensions");
			options.addArguments("--headless");
			options.addArguments("--disable-dev-shm-usage");

			//WebDriverManager.chromedriver().version("2.40").setup();
			WebDriverManager.chromedriver().forceDownload().setup();
			driver = new ChromeDriver(options);
			EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
			edriver.manage().window().maximize();
			edriver.manage().deleteAllCookies();
			edriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			edriver.manage().timeouts().pageLoadTimeout(900, TimeUnit.SECONDS);
			java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
			java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

			driver = edriver;

		}
	
		else if(browser.equalsIgnoreCase("Chrome"))
		{

			WebDriverManager.chromedriver().setup();
			//WebDriverManager.chromedriver().version("74.0.3729.6").forceDownload().setup();
			driver = new ChromeDriver();
            EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
            edriver.manage().window().maximize();
            edriver.manage().deleteAllCookies();
			edriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			edriver.manage().timeouts().pageLoadTimeout(900, TimeUnit.SECONDS);
			java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
			java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

             driver = edriver;
             System.out.println("======Chrome Launched=========");
		}
		
		
		else if(browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver","C:\\Users\\rsivri\\Selenium\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
				
		else
		{
			throw new InvalidParameterException(browser + " - is not a valid web browser for web driver.");
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		return driver;
	}
	
	public static void quitDriver(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(5000);
		driver.quit();
	}

} 
		
