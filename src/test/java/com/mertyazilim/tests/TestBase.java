package com.mertyazilim.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
//import org.automationtesting.excelreport.Xl;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.mertyazilim.pages.HomePage;
import com.mertyazilim.util.Browser;
import com.mertyazilim.util.ExcelReader;
import com.mertyazilim.util.JSEHelper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import atu.testrecorder.ATUTestRecorder;
//import atu.testrecorder.exceptions.ATUTestRecorderException;

public abstract class TestBase {

	protected WebDriver driver;
	protected HomePage homePage;
	protected static Properties testConfig;
	protected static String postUrl;
	protected ExtentReports extent;
	protected ExtentTest extentTest;
	JSEHelper js = new JSEHelper();
	public static int jobNo;
	protected static String date;
	protected static String env; 
	protected String note;
	protected boolean testResult;
//	protected static ATUTestRecorder recorder;
//	static String videoFile="TestVideo";
	static String videoFolder;
	Logger logger;

	
	
	
	@BeforeSuite
	public void beforeSuit() throws FileNotFoundException, IOException{
		PropertyConfigurator.configure("log4j.properties");

		testConfig = new Properties();
		testConfig.load(new FileInputStream("TestConfig.properties"));
		postUrl=testConfig.getProperty("postUrl");
		env = testConfig.getProperty("environment"); 
		jobNo = 1;
    	date = new SimpleDateFormat("MM-dd-yyyy-HHmmss").format(new Date());
//		videoFolder = System.getProperty("user.dir") + "/Test-Report/" + date + "/job" + jobNo + "/";
		

		
		}

	@BeforeMethod
	public void beforeMethod(Method method) throws MalformedURLException {
		logger = Logger.getLogger(this.getClass().getSimpleName());
		logger.info("################# Starting " + method.getName() + " #################");

		driver = Browser.createDriver(testConfig.getProperty("browser"));
		String baseUrl = testConfig
				.getProperty(System.getProperty("environment", testConfig.getProperty("environment")) + ".baseUrl");
		driver.get(baseUrl);
		System.out.println(" Running URL: " + baseUrl);
		System.out.println("title : " + driver.getTitle());
		homePage = new HomePage(driver);
		System.out.println("ending beforeMethod ");
		
	}

	@BeforeTest
	public void setExtent(ITestContext testContext) {

		extent = new ExtentReports(
				System.getProperty("user.dir") + "/Test-Report/" + date + "/job" + jobNo + "/" + "_HTMLReport.html",
				true);
		extent.addSystemInfo("Host Name", "");
		extent.addSystemInfo("Team", "");
		extent.addSystemInfo("Made By", "Ramazan Sivri");
//		recorder = new ATUTestRecorder(videoFolder,videoFile,false);
//		recorder.start();
		// ""user.dir") + "\Test-Report\" + date + "\job" + jobNo + "\"" does not exist. ("C:\\Test\\","testrecord",false);
		
	}

	@AfterTest
	public void endReport() throws Exception {
		extent.flush();
		extent.close();
		jobNo++;

	}

	public static String getScreenshotForFail(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = "TestsScreenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File("Test-Report/" + date + "/job" + jobNo + "/" + destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

		
	public void login() throws IOException {
		homePage.login().loginHome(testConfig.getProperty("username"), testConfig.getProperty("password"), extentTest,jobNo, date);
	}

	@DataProvider
	public Object[][] dataProvider(Method method) {
		ExcelReader findTest = new ExcelReader(testConfig.getProperty("excelfile"));

		Object[][] testData = findTest.findTestInExcelSheet(testConfig.getProperty("exceldatasheet"), method.getName());

		return testData;
	}

	@AfterMethod
	public void tearDown(ITestResult result, Method method) throws IOException, InterruptedException {

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName());
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
			String screenshotPath = getScreenshotForFail(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {

		}
		logger.info("################# Ending " + method.getName() + " #################");

		extent.endTest(extentTest);
		Browser.quitDriver(driver);
	}

	@AfterSuite
	public void endSuit() throws Exception {
		System.out.println("Ending Suit");
//		recorder.stop();
		
	}
}
