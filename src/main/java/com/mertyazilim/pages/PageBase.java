package com.mertyazilim.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.mertyazilim.util.JSEHelper;


public class PageBase extends PageFactory {
	protected WebDriver driver;
	protected JSEHelper js = new JSEHelper();
	protected Properties testConfig = new Properties();
	protected String screenshotPath;
	protected Logger logger;
	protected boolean checkAssert;
	

	public PageBase(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		logger = Logger.getLogger(this.getClass().getSimpleName());

	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void clickOn(WebElement element, boolean draw) throws Exception {
		WebDriverWait wdWait = new WebDriverWait(driver, 20);
		try {
			wdWait.until(ExpectedConditions.elementToBeClickable(element));
			if (element.isDisplayed()) {
				if (draw == true) {
					js.drawBorder(element, driver);
				}
				Thread.sleep(500);
				js.clickElementByJS(element, driver);
				System.out.println("Clicked on " + element.getText());
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	public void sendKey(WebElement element, String stringData, boolean draw) throws Exception {
		WebDriverWait wdWait = new WebDriverWait(driver, 60);
		try {
			wdWait.until(ExpectedConditions.visibilityOf(element));
			if (element.isDisplayed()) {
				if (!element.getText().equals("")) {
					element.clear();
					if (draw == true) {
						js.drawBorder(element, driver);
					}
				}
				element.sendKeys(stringData);
				Thread.sleep(500);
				System.out.println("Sending data is : " + stringData);
			}
		} catch (RuntimeException rte) {

			throw (rte);
		}

	}

	public void waitForCondition(String Path) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 120);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Path)));
	}

	public void waitForNextElement(int s) throws InterruptedException {
		long k = 3000 * s;
		Thread.sleep(k);
	}

	public void waitForCondition(WebDriver driver, WebElement element, int time) {
		new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForCondition(List<WebElement> elements) {
		new WebDriverWait(driver, 120).until(ExpectedConditions.visibilityOfAllElements(elements));

	}

	public String getPropEnv(String obj) throws FileNotFoundException, IOException {

		Properties testConfig = new Properties();
		testConfig.load(new FileInputStream("TestConfig.properties"));
		String objProp = testConfig
				.getProperty(System.getProperty("environment", testConfig.getProperty("environment")) + "." + obj);

		return objProp;
	}

	public String getProp(String obj) throws FileNotFoundException, IOException {

		Properties testConfig = new Properties();
		testConfig.load(new FileInputStream("TestConfig.properties"));
		String objProp = testConfig.getProperty(obj);

		return objProp;
	}


// PAGE FACTORY	
	// user name
			@FindBy(id = "username")
			WebElement user;

	// password
	@FindBy(id = "password")
	WebElement pass;

			// login button
			@FindBy(name = "login")
			WebElement loginBtn;

	// isLogin
	@FindBy(xpath = "//strong[2]")
	WebElement isLogin;
	
	
	// MyAccountButton 
	@FindBy(xpath ="//a[contains(text(),'My account')]")
	WebElement myAccountButton;
			
	// Shop Button 
	@FindBy(xpath ="//a[contains(text(),'Shop')]")
	WebElement shopButton;
			
	// Select Box Path
	@FindBy(name = "orderby")
	WebElement selSort;	
	
	
	// womenMenu
	@FindBy(linkText = "WOMEN")
	WebElement womenMenu;
	
	//Alt Menu 
	@FindBy(xpath ="//a[@title='Summer Dresses']")
	WebElement altMenu;
	
	// List View
	@FindBy(id = "list")
	WebElement listView;
	
	//follow us
	@FindBy(xpath ="//*[@id='social_block']/h4")
	WebElement followUs;
	
	//Summer Dresses
	@FindBy(xpath ="//a[@title='Summer Dresses']")
	WebElement summerDresses;
	
	//Product
	@FindBy(xpath ="//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img")
	WebElement product;
	
	//Add To Cart
	@FindBy(xpath ="//*[@id=\"add_to_cart\"]/button")
	WebElement addToCart;
	
	//Proceed To Checkout
	@FindBy(xpath ="//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")
	WebElement proceedToCheckout;
	
	//Pencere Kapat
	@FindBy(xpath ="//*[@id=\"layer_cart\"]/div[1]/div[1]/span")
	WebElement closedWindow;
	
	//Continue Shopping
	@FindBy(xpath ="//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span")
	WebElement continueShopping;
	
	//Last Proceed To Checkout
	@FindBy(xpath ="//*[@id=\"center_column\"]/p[2]/a[1]")
	WebElement lastProceedToCheckout;
	
	//Email_create
	@FindBy(id ="email_create")
	WebElement email_create;
	
	//E-Mail Submit
	@FindBy(xpath ="//*[@id='SubmitCreate']")
	WebElement emailSubmit;
	
	//Mr
	@FindBy(id ="id_gender1")
	WebElement Mr;
	
	//Mr
	@FindBy(id ="id_gender2")
	WebElement Mrs;
	
	//Customer_firstname
	@FindBy(id ="customer_firstname")
	WebElement customer_firstname;
	
	//Customer_lastname
	@FindBy(id ="customer_lastname")
	WebElement customer_lastname;
	
	//Passwd
	@FindBy(xpath ="passwd")
	WebElement passwd;
	
	//address1
	@FindBy(id ="address1")
	WebElement address1;
	
	//city
	@FindBy(id ="city")
	WebElement city;
	
	//id_state
	@FindBy(id ="id_state")
	WebElement id_state;
	
	//postcode
	@FindBy(id ="postcode")
	WebElement postcode;

	//phone_mobile
	@FindBy(id ="phone_mobile")
	WebElement phone_mobile;
	
	//submitAccount-Register
	@FindBy(id ="submitAccount")
    WebElement register;
	
	//processAddress
	@FindBy(name ="processAddress")
	WebElement processAddress;

	//cgv
	@FindBy(id ="cgv")
	WebElement cgv;
	
	//lastButton
	@FindBy(name ="processCarrier")
	WebElement lastButton;

	//bankwire
	@FindBy(className ="bankwire")
	WebElement bankwire;
	
	//cheque
	@FindBy(className ="cheque")
	WebElement cheque;
	
	//confirmButton
	@FindBy(xpath ="//*[@id=\"cart_navigation\"]/button")
	WebElement confirmButton;
	
	//username
	@FindBy(id ="email")
	WebElement username;
	
	//password
	@FindBy(id ="passwd")
	WebElement password;
	
	//login
	@FindBy(id ="SubmitLogin")
	WebElement login;
	
	//
	@FindBy(id ="")
	WebElement f;
	
//	// ===Status Fields======
//
//	// Refresh
//	@FindBy(id = "r2:1:commandButton1203")
//	WebElement refresh;
//
//	// Sim number
//	@FindBy(xpath = "//*[@id='r2:1:panelLabelAndMessage15']/td[2]")
//	WebElement simNumber;
//
//	// phoneStatus
//	@FindBy(xpath = "//*[@id='r2:1:panelLabelAndMessage14']/td[2]")
//	WebElement phoneStatus;// *[@id="r2:1:panelLabelAndMessage15"]/td[2]
//
//	// Min Status
//	@FindBy(xpath = "//*[@id='r2:1:panelLabelAndMessage23']/td[2]")
//	WebElement minStatus;
//
//	// Sim Status
//	@FindBy(xpath = "//*[@id='r2:1:panelLabelAndMessage16']/td[2]")
//	WebElement simStatus;
//
//	// Latest Transaction status
//	@FindBy(xpath = "//*[@id='r2:1:panelLabelAndMessage72']/td[2]")
//	WebElement latestTransaction;
//
//	// Service End Day
//	@FindBy(xpath = "//*[@id='r2:1:panelLabelAndMessage26']/td[2]")
//	WebElement serviceEndDay;
//
//	// ============Ticket History Menu============
//	// History menu
//	@FindBy(css = "a[id*=disAcr]")
//	List<WebElement> menuHistory;
////	List<WebElement> rs = driver.findElements(By.cssSelector("a[id*=disAcr]"));
////	List<WebElement> th = driver.findElements(By.xpath("//a[@class='xfh']"));
//	// ticket History
//	@FindBy(xpath = "//a[@class='xfh']")
//	List<WebElement> th;
//
//
//	// Open Ticket History 2. satir
//	@FindBy(xpath = "//table[@class='x11e x123']/tbody/tr[2]/td")
//	WebElement openTicHis;
//
////		List<WebElement> list = driver.findElements(By.xpath("//div[@class='xb7']"));
//
//	@FindBy(xpath = "//div[@class='xb7']")
//	List<WebElement> cp1;
//
//	// ticket History
//	@FindBy(css = "*[class*=p_AFTextOnly]")
//	List<WebElement> cp2;
////		List<WebElement> port = driver.findElements(By.cssSelector("button[class*=p_AFTextOnly]"));
//
//	/*
//	 * driver.findElement(By.cssSelector("input[id=username]")).sendKeys("testuser1");  equal value
//	 * 
//	 * driver.findElement(By.cssSelector("input[id^=user]")).sendKeys("testuser1"); starts with value
//	 * 
//	 * driver.findElement(By.cssSelector("input[id$=name]")).sendKeys("testuser1"); ending with value
//	 * 
//	 * driver.findElement(By.cssSelector("input[class*=input-text]")).sendKeys("testuser1"); contains with value
//	 * 
//	 */
//	
//	// ticket History
//		@FindBy(css = "*[class*=p_AFTextOnly]")
//		List<WebElement> cp4;
//	
//	
//	@FindBy(css = "*[class*=x7j]")// p_AFTextOnly
//	List<WebElement> cp3;
//	
//	// Account Email
//
//	@FindBy(id = "r2:0:s12:it17::content")
//	WebElement accountEmailField;
//
//	// Radyo Purchase Plan
//	@FindBy(id = "r2:1:r1:0:sor1:_0")
//	WebElement radyoPurchasePlan;
//
//	// newPayment
//	@FindBy(id = "r2:1:r1:0:sor1:_0")
//	WebElement newPayment;
//
//	// *[@id="r2:0:r1:0:gb3"]
//
////	// Radyo Service Pin
////	@FindBy(xpath = "//*[@id='r2:0:r1:0:sor1:_1']")//r2:0:r1:0:sor1:_1
////	WebElement radyoServicePin;
////
////	// Enter Service Pin
////	@FindBy(id = "r2:1:r1:0:it1::content")
////	WebElement enterServicePin;
////
////	// Validate Button
////	@FindBy(id = "r2:1:r1:0:cb6")
////	WebElement validateServicePin;
//
//	// Radyo Service Pin
//	@FindBy(name = "r2:0:r1:1:sor1")//r2:0:r1:1:sor1:_1(id)
//	WebElement radyoServicePin;
//
//	// Enter Service Pin
//	@FindBy(id = "r2:0:r1:0:it1::content")//r2:0:r1:0:it1::content
//	WebElement enterServicePin;
//
//	// Validate Button
//	@FindBy(id = "r2:1:r1:0:cb6")
//	WebElement validateServicePin;
//
//	// Radyo Min Activate
//	@FindBy(id = "r2:1:r1:0:sor2:_0")
//	WebElement radyoMinActivate;
//
//	// Enter Zip Code to Validate
//	@FindBy(id = "r2:1:r1:0:it3::content")
//	WebElement enterZipCode;
//
//	// TW_Continue Button
//	@FindBy(id = "r2:1:r1:0:cb7")
//	WebElement tW_Continue;
//
//	// Process Transaction
//	@FindBy(id = "r2:1:r1:1:cb1")
//	WebElement processTransaction;
//
////	// email
////	@FindBy(id = "r2:1:r1:2:it1::content")
////	WebElement email;
//
//	// Send email button
//	@FindBy(id = "r2:1:r1:2:cb1")
//	WebElement emailSend;
//
//	// ==============================================
//
//	// ============ WFM==================================
//
//	// New Line/ Reactivate
//	@FindBy(id = "r2:1:r1:1:cb2")
//	WebElement lineReactivate;
//
//	// Sim Zip at WFM
//	@FindBy(id = "r2:1:r1:2:it2::content")
//	WebElement simZipWfm;
//
//	// WFM Airtime Pin
//	@FindBy(id = "r2:1:r1:2:inputText1::content")
//	WebElement wfmAirtimePin;
//
//	// Validate Button
//	@FindBy(id = "r2:1:r1:2:commandButton1")
//	WebElement validateCardWfm;
//
//	// Activate Button Wfm
//	@FindBy(id = "r2:1:r1:2:cb3")
//	WebElement activateButtonWfm;
//
//	// Send Email WFMe
//	@FindBy(id = "r2:1:r1:3:cb1")
//	WebElement sendEmailWfm;
//
//	// WFM Activation Menu
//	@FindBy(xpath = "//*[@id='r2:1:c482']") // r2:0:cl33
//	WebElement activationMenuWfm;
//
//	// Process Transaction
//	@FindBy(id = "r2:1:r1:1:cb1")
//	WebElement h;
//
//	// email
//	@FindBy(id = "r2:1:r1:2:it1::content")
//	WebElement i;
//
//	// Add To Reserve
//	@FindBy(xpath = "//*[@id='r2:0:panelLabelAndMessage40']")
//	WebElement addToReserve; // *[@id="r2:0:panelLabelAndMessage40"]/td[2]
//
//	// ==============================================

//
//	// Serial Number
//	@FindBy(id = "r2:0:s12:it1::content")
//	WebElement esn; // it1::content
//
//	// search service
//	@FindBy(id = "r2:0:s12:cb11")
//	WebElement sservice;
//
//	// incomingCall Button
//	@FindBy(xpath = "//*[@id='commandImageLink3']")
//	WebElement incomingCall;
//
////	driver.findElement(By.xpath("//*[@id='commandImageLink3']"))
//
//	// logout
//	@FindBy(xpath = "//*[@id=\"cl1\"]/div/table/tbody/tr/td[2]/a")
//	WebElement logout;
//
//	// search ESN Button
//	@FindBy(id = "cil1")
//	WebElement searchESNBt;
//
//	// Marriage Link
//	@FindBy(id = "r2:1:cl44")
//	WebElement marryLink;
//
//	// New Sim Number
//	@FindBy(id = "r2:1:r1:1:it1::content")
//	WebElement simNum;
//
//	// Marry Button
//	@FindBy(id = "r2:1:r1:1:cb1")
//	WebElement marry;
//
//	// Marry Message
//	@FindBy(xpath = "//*[@id='r2:1:r1:1:plam8']/tbody/tr/td[2]")
//	WebElement marryMessage;
//
//	// New Customer
//	@FindBy(id = "r2:1:commandButton1200")
//	WebElement newCustomer;
//
//	// Same Customer
//	@FindBy(id = "r2:1:commandButton1201")
//	WebElement sameCustomer;
//
//	// Contact Profile
//	@FindBy(id = "r2:0:commandButton1202")
//	WebElement contactProfile;
//	
////	List<WebElement> rs = driver.findElements(By.cssSelector("a[class=x7j p_AFTextOnly]"));
//
//	// Contact Profile challenge
//	@FindBy(id = "r2:1:r1:0:t1:0:sbc1::content")
//	WebElement challenge;
//
//	// Contact Profile challenge continue
//	@FindBy(id = "r2:1:r1:0:cb1")
//	WebElement challengeCont;
//
//	// Contact Profile group
//		@FindBy(xpath = "r2:1:r1:0:cb1")
//		WebElement group;
//	
//	
//	// New Contact
//	@FindBy(id = "r2:0:s12:cb4")
//	WebElement newContact;
//
//	// Contact Select
//	@FindBy(id = "r2:0:s12:soc3::content")
//	WebElement selContact;
//
//	// ZipCode
//	@FindBy(id = "r2:0:s12:it14::content")
//	WebElement zipContact;
//
//	// No e-mail
//	@FindBy(id = "r2:0:s12:sbc3::content")
//	WebElement noEmail;
//
//	// Security Pin
//	@FindBy(id = "r2:0:s12:it22::content")
//	WebElement secPin;
//
//	// Create Contact
//	@FindBy(id = "r2:0:s12:cb5")
//	WebElement createContact;
//
//	// Create Contact
//	@FindBy(xpath = "//*[@id='r2:0:commandLink1002']")
//	WebElement custID;
//	// *[@id="r2:0:commandLink1002"]
//
//	// accEmail
//	// Create Contact
//	@FindBy(id = "r2:0:commandLink1002")
//	WebElement dAccEmail;
//
//	// *[@id="r2:0:panelLabelAndMessage35"]/td[2]
//
//	// Transaction Menu  //*[@id="r2:0:sdi3::head"]/table/tbody/tr/td[2]/table/tbody/tr/td[2]/table/tbody/tr/td
//	@FindBy(xpath = "//*[@id=\"r2:0:sdi3::head\"]/table/tbody/tr/td[2]/table/tbody/tr/td[2]/table/tbody/tr/td")
//	WebElement transactionMenu;
//////*[@id='r2:0:sdi3::head']/table/tbody/tr/td[2]/table/tbody/tr/td[2]/nobr
//	
//	@FindBy(xpath = "//*[@class='x1et']")
//	List<WebElement> leftMenu;
//	
//	@FindBy(xpath = "//*[@class='xgn']")
//	List<WebElement> leftSubMenu;
//	
//	@FindBy(xpath = "//*[@class='x25']")
//	List<WebElement> actCon;
//	
//	@FindBy(xpath = "//input[@class='x25']")
//	List<WebElement> actConinput;
//	
//	@FindBy(css = "//*[@class='x7j p_AFTextOnly']")
//	List<WebElement> actBut;//x7j p_AFTextOnly
//	
//	@FindBy(xpath = "//*[@class='xts xvl']")
//	List<WebElement> status;
//	
//	@FindBy(xpath = "//*[@class='xw6']")
//	List<WebElement> radioClass;
//	
//	
////	@FindBy(xpath = "//a[@class='xfh']") --r2:1:r1:2:it1::content--x25--x1u--xw6
////	List<WebElement> th;
//	
//	
//	// History Menu
//	final String path = "//a[contains(@id,'r2:0:sdi1::disAcr')]";
//	@FindBy(xpath = path)
//	// *[@id='r2:0:sdi1::disAcr']
//	// By.XPath("//a[contains(@id,'r2:0:sdi1::disAcr')]")
//	// *[@id="r2:0:sdi3::head"]/table/tbody/tr/td[2]/table/tbody/tr/td[2]/nobr
//	WebElement historyMenu;// *[@id="r2:0:sdi1::head"]/table/tbody/tr/td[2]/table/tbody/tr/td[2]/nobr
//
//	// Activation
//	@FindBy(id = "r2:1:cl33")
//	WebElement activationMenu; // r2:1:c482 //*[@id="r2:0:cl33"]
//
//	// Redemption
//	@FindBy(id = "r2:1:cl36")
//	WebElement redemptionMenu;
//
//	// Add Airtime
//	@FindBy(id = "r2:1:r1:1:sdi1::disAcr")
//	WebElement addAirtime; // r2:1:r1:0:sdi1::disAcr
//
//	// Purchase Airtime in Activation
//	@FindBy(id = "r2:1:r1:1:sdi2::disAcr")
//	WebElement purchaseAAirtime;
//
//	// Purchase Airtime in Redemption(div)
//	@FindBy(id = "r2:1:r1:3:sdi2::disAcr")
//	WebElement purchaseRAirtime;// r2:1:r1:1:sdi2::disAcr
//
//	// Sim Zip Code
//	@FindBy(id = "r2:0:r1:1:it2::content")
//	WebElement simZipCode; // r2:0:r1:1:it2::content
//
//	// Activate Airtime Pin
//	@FindBy(id = "r2:0:r1:1:inputText1::content")
//	WebElement aAirtimePin; // r2:0:r1:1:inputText1::content
//
//	// Validate Card
//	@FindBy(id = "r2:0:r1:1:commandButton1")
//	WebElement validateCard;//r2:0:r1:1:commandButton1
//
//	// Validate Card Message
//	@FindBy(id = "//*[@id='r2:1:r1:1:panelLabelAndMessage2']/tbody/tr/td[2]")
//	WebElement valCardMessg;
//
//	// Reserved Pins
//	@FindBy(id = "r2:1:r1:1:cb1")
//	WebElement reservedPins;
//
//	// Activate Button
//	@FindBy(id = "r2:1:r1:1:cb3")
//	WebElement activate;
//
//	// Result Min after Activate
//	@FindBy(xpath = "//*[@id='r2:1:r1:2:plam3']/td[2]")
//	WebElement resMin;
//
//	// Result Rate Plan after Activate
//	@FindBy(xpath = "//*[@id='r2:1:r1:2:plam6']/td[1]/label")
//	WebElement resRatePlan;
//
//	// Result Min Status after Activate
//	@FindBy(xpath = "//*[@id='r2:1:r1:2:plam2']/td[1]/label")
//	WebElement resMinStatus;
//
//	// Result Auto Refill after Activate
//	@FindBy(xpath = "//*[@id='r2:1:r1:2:plam9']/td[1]/label")
//	WebElement resAutoRefill;
//
//	// Result Activation Date
//	@FindBy(xpath = "//*[@id='r2:1:r1:2:plam7']/td[2]")
//	WebElement resActDate;
//
//	// Result Service End Date
//	@FindBy(xpath = "//*[@id='r2:1:r1:2:plam8']/td[2]")
//	WebElement resSerEndDate;
//
//	// Redemption Airtime Pin
//	@FindBy(id = "r2:1:r1:3:rit1::content")
//	WebElement rAirtimePin;
//
//	// Redeem Button
//	@FindBy(id = "r2:1:r1:3:cb10")
//	WebElement redeemButton;
//
//	// Result Activation Date
//	@FindBy(xpath = "//*[@id='r2:1:r1:2:plam7']/td[2]")
//	WebElement resActDate1;
//
//	// Result Service End Date
//	@FindBy(xpath = "//*[@id='r2:1:r1:2:plam8']/td[2]")
//	WebElement resSerEndDate1;
//
//	// Select Airtime Plan in Activation
//	@FindBy(xpath = "//*[@id='r2:1:r1:1:r1:1:t1::db']/table/tbody/tr[3]/td[2]")
//	WebElement selAirPlanAc;
//
//	// Add Button
//	@FindBy(id = "r2:1:r1:1:r1:1:cb4")
//	WebElement addButton;
//
//	// Add New Payment
//	@FindBy(id = "r2:1:r1:1:r1:1:gb1")
//	WebElement addNewPayment;// r2:1:r1:0:r2:1:gb1
//
//	// Refresh CC
//	@FindBy(id = "r2:1:r1:1:r1:1:cb8")
//	WebElement ccRefresh;// r2:1:r1:1:r1:1:cb8
//
//	// Select New Payment
//	@FindBy(id = "r2:1:r1:1:r1:1:soc1::content")
//	WebElement selNewPayment;// r2:1:r1:0:r2:1:soc1::content
//
//	// CVV Value
//	@FindBy(id = "r2:1:r1:1:r1:1:it1::content")
//	WebElement cvv; // r2:1:r1:0:r2:1:it1::content
//
//	// Purchase Activate Button
//	@FindBy(id = "r2:1:r1:1:r1:1:cb1")
//	WebElement purchaseActivate;// r2:1:r1:0:r2:1:cb2
//
//	// ----New Credit Card-----
//
//	// Card Number
//	@FindBy(id = "account_number::content")
//	WebElement ccNo;
//
//	// Exp month
//	@FindBy(id = "soc2::content")
//	WebElement selExpMonth;
//
//	// Exp Year
//	@FindBy(id = "soc3::content")
//	WebElement selExpYear;
//
//	// First Name
//	@FindBy(id = "it7::content")
//	WebElement firstName;
//
//	// LastName
//	@FindBy(id = "it8::content")
//	WebElement lastName;
//
//	// Address1
//	@FindBy(id = "it5::content")
//	WebElement address1;
//
//	// CC Zip Code
//	@FindBy(id = "it4::content")
//	WebElement ccZip;
//
//	// City
//	@FindBy(id = "it2::content")
//	WebElement ccCity;
//
//	// State Select
//	@FindBy(id = "soc5::content")
//	WebElement ccState;
//
//	// Save CC Info Check
//	@FindBy(id = "sbc1::content")
//	WebElement saveAsSource;
//
//	// Register CC
//	@FindBy(id = "cb1")
//	WebElement register;
//
//	// ================end new CC==============
//
//	// Select Airtime Plan in Redemption
//	@FindBy(xpath = "//*[@id='r2:1:r1:3:r2:1:t1::db']/table/tbody/tr[3]/td[2]")
//	WebElement selAirPlanRe;// *[@id="r2:1:r1:3:r2:1:t1::db"]/table/tbody/tr[3]/td[2]/span
//
//	// Plus + Button in redemption
//	@FindBy(id = "r2:1:r1:3:r2:1:cb21")
//	WebElement plusButton;
//
//	// Add New Payment
//	@FindBy(id = "r2:1:r1:3:r2:1:gb1")
//	WebElement addNewPaymentr;
//
//	// Select New Payment
//	@FindBy(id = "r2:1:r1:3:r2:1:soc1::content")
//	WebElement selNewPaymentr;
//
//	// CVV Value
//	@FindBy(id = "r2:1:r1:3:r2:1:it1::content")
//	WebElement cvvr;
//
//	// Refresh CC
//	@FindBy(id = "r2:1:r1:3:r2:1:cb8")
//	WebElement ccRefreshr;// r2:1:r1:3:r2:1:cb8
//
//	// Add Now in Redemption Button
//	@FindBy(id = "r2:1:r1:3:r2:1:cb2")
//	WebElement addNowRedemption;
//
//	// ================Starting Min - Sim Change ==============
//	// Min Change
//	@FindBy(id = "r2:1:cl34")
//	WebElement minChange;
//
//	// Sim Change
//	@FindBy(id = "r2:1:cl35")
//	WebElement simChange;
//
//	// VerifyPin
//	@FindBy(id = "r2:1:r1:1:t1:0:sbc1")
//	WebElement VerifyPin;
//
//	// Continue r2:1:r1:1:cb1
//	@FindBy(id = "r2:1:r1:1:cb1")
//	WebElement continueButton;
//
//	// Min Activation Zip Code
//	@FindBy(id = "r2:1:r1:3:it2::content")
//	WebElement minActivationZipCode;// r2:1:r1:3:it2::content
//
//	// Process min Change
//	@FindBy(id = "r2:1:r1:3:cb1")
//	WebElement processMinChange;
//
//	// Current Min
//	@FindBy(xpath = "//*[@id='r2:1:r1:3:it1::content']")
//	WebElement currentMin;// r2:1:r1:3:it1::content
//
//	// New number Radio
//	@FindBy(id = "r2:1:r1:3:sor1:_0")
//	WebElement newNumberRadio;
//
//	// Same Number Radio
//	@FindBy(id = "r2:1:r1:3:sor1:_1")
//	WebElement sameNumberRadio;
//
//	// Sim ActivationZipCode
//	@FindBy(id = "r2:1:r1:3:it2::content")
//	WebElement simActivationZipCode;
//
//	// Sim Process
//	@FindBy(id = "r2:1:r1:3:cb1")
//	WebElement simProcess;
//
//	// New Sim Number
//	@FindBy(id = "r2:1:r1:3:it1::content")
//	WebElement newSimNumber;
//
//	// ================Starting DeActivation ==============
//
//	// deActivation
//	@FindBy(id = "r2:1:cl39")
//	WebElement deActivation;
//
//	// Reason Selection- Value 10 past due
//	@FindBy(id = "r2:1:r1:3:soc1::content")
//	WebElement selectReason;
//
//	// Deactivate
//	@FindBy(id = "r2:1:r1:3:cb1")
//	WebElement deActivate;
//
//	// Deactivation Message
//	@FindBy(xpath = "//*[@id='r2:1:r1:2:pgl3T']/div")
//	WebElement deActivationMessage;
//
//	String Post_ValidateAddToReservePayloadSM = "{\n" + "   \"externalID\": \"tf-add-to-res-extID\",\n"
//			+ "   \"relatedParties\": [\n" + "      {\n" + "         \"party\": {\n"
//			+ "            \"partyID\": \"T-CETRA\",\n" + "            \"languageAbility\": \"ENG\",\n"
//			+ "            \"partyExtension\": [\n" + "               {\n"
//			+ "                  \"name\": \"vendorName\",\n" + "                  \"value\": \"TCETRA\"\n"
//			+ "               },\n" + "               {\n" + "                  \"name\": \"vendorStore\",\n"
//			+ "                  \"value\": \"1234\"\n" + "               },\n" + "               {\n"
//			+ "                  \"name\": \"vendorTerminal\",\n" + "                  \"value\": \"1234\"\n"
//			+ "               },\n" + "               {\n" + "                  \"name\": \"sourceSystem\",\n"
//			+ "                  \"value\": \"RTR\"\n" + "               }\n" + "            ]\n" + "         },\n"
//			+ "         \"roleType\": \"partner\"\n" + "      }\n" + "   ],\n"
//			+ "   \"orderDate\": \"2019-10-23T11:06:37-0400\",\n" + "   \"orderItems\": [\n" + "      {\n"
//			+ "         \"id\": \"1\",\n" + "         \"action\": \"ADD_TO_RESERVE\",\n" + "         \"quantity\": 1,\n"
//			+ "         \"product\": {\n" + "            \"productSerialNumber\": \"updateESN\",\n"
//			+ "            \"productCategory\": \"HANDSET\",\n" + "            \"productSpecification\": {\n"
//			+ "               \"brand\": \"SIMPLE_MOBILE\"\n" + "            },\n"
//			+ "            \"relatedServices\": [\n" + "               {\n" + "                  \"id\": \"101\",\n"
//			+ "                  \"category\": \"SERVICE_PLAN\",\n" + "                  \"isRedeemNow\": false,\n"
//			+ "                  \"isRecurring\": false,\n" + "                  \"characteristicSpecification\": [\n"
//			+ "                     {\n" + "                        \"name\": \"relatedProgramId\",\n"
//			+ "                        \"value\": \"\"\n" + "                     }\n" + "                  ]\n"
//			+ "               }\n" + "            ],\n" + "            \"supportingResources\": [\n"
//			+ "               {\n" + "                  \"resourceType\": \"SIM_CARD\",\n"
//			+ "                  \"serialNumber\": \"updateSIM\"\n" + "               }\n" + "            ]\n"
//			+ "     \n" + "         },\n" + "         \"productOffering\": {\n"
//			+ "            \"id\": \"SMNRTR20025\",\n" + "            \"productSpecification\": {\n"
//			+ "               \"id\": \"SM025TT\"\n" + "            }\n" + "         },\n"
//			+ "         \"location\": {\n" + "            \"postalAddress\": {\n"
//			+ "               \"zipcode\": \"33178\"\n" + "            }\n" + "         }\n" + "      }\n" + "   ],\n"
//			+ "   \"location\": [\n" + "      {\n" + "         \"addressType\": \"BILLING\",\n"
//			+ "         \"address\": {\n" + "            \"zipcode\": \"33178\"\n" + "         }\n" + "      }\n"
//			+ "   ],\n" + "   \"type\": \"ACTIVATE_BAN\"\n" + "}";
//
//	// =========================================extID==updateESN==updateSIM====actionValue--updateAccountEmail
//	String Post_ValidateAddToReservePayloadTW = "{\n" + "    \"externalID\": \"1tfaddtores-extID\",\n"
//			+ "    \"relatedParties\": [\n" + "        {\n" + "            \"party\": {\n"
//			+ "                \"partyID\": \"T-CETRA\",\n" + "                \"languageAbility\": \"ENG\",\n"
//			+ "                \"partyExtension\": [\n" + "                    {\n"
//			+ "                        \"name\": \"vendorName\",\n" + "                        \"value\": \"T-CETRA\"\n"
//			+ "                    },\n" + "                    {\n"
//			+ "                        \"name\": \"vendorStore\",\n" + "                        \"value\": \"1234\"\n"
//			+ "                    },\n" + "                    {\n"
//			+ "                        \"name\": \"vendorTerminal\",\n"
//			+ "                        \"value\": \"1234\"\n" + "                    },\n" + "                    {\n"
//			+ "                        \"name\": \"sourceSystem\",\n" + "                        \"value\": \"RTR\"\n"
//			+ "                    }\n" + "                ]\n" + "            },\n"
//			+ "            \"roleType\": \"partner\"\n" + "        },\n" + "        {\n" + "      \"party\": {\n"
//			+ "        \"individual\": {\n" + "          \"id\": \"637755314\",\n"
//			+ "          \"firstName\": \"Indirect\",\n" + "          \"lastName\": \"Channel\"\n" + "        },\n"
//			+ "        \"partyExtension\": [\n" + "          {\n" + "            \"name\": \"accountEmail\",\n"
//			+ "            \"value\": \"updateE_Mail\"\n" + "          }\n" + "        ]\n" + "      }\n"
//			+ "        }\n" + "    ],\n" + "    \"orderDate\": \"updateDate\",\n" + "    \"orderItems\": [\n"
//			+ "        {\n" + "            \"id\": \"1\",\n" + "            \"action\": \"ADD_TO_RESERVE\",\n"
//			+ "            \"quantity\": 1,\n" + "            \"product\": {\n"
//			+ "                \"productSerialNumber\": \"updateESN\",\n"
//			+ "                \"productCategory\": \"HANDSET\",\n" + "                \"productSpecification\": {\n"
//			+ "                    \"brand\": \"TOTAL_WIRELESS\"\n" + "                },\n"
//			+ "                \"relatedServices\": [\n" + "                    {\n"
//			+ "                        \"id\": \"1\",\n" + "                        \"category\": \"SERVICE_PLAN\",\n"
//			+ "                        \"isRedeemNow\": false,\n" + "                        \"isRecurring\": false,\n"
//			+ "                        \"characteristicSpecification\": [\n" + "                            {\n"
//			+ "                                \"name\": \"relatedProgramId\",\n"
//			+ "                                \"value\": \"\"\n" + "                            }\n"
//			+ "                        ]\n" + "                    }\n" + "                ],\n"
//			+ "                \"supportingResources\": [\n" + "                    {\n"
//			+ "                        \"resourceType\": \"SIM_CARD\",\n"
//			+ "                        \"serialNumber\": \"updateSIM\"\n" + "                    }\n"
//			+ "                ]\n" + "            },\n" + "            \"productOffering\": {\n"
//			+ "                \"id\": \"updatePINPART\",\n" + "                \"productSpecification\": {\n"
//			+ "                    \"id\": \"updatePINPART\"\n" + "                }\n" + "            },\n"
//			+ "            \"location\": {\n" + "                \"postalAddress\": {\n"
//			+ "                    \"zipcode\": \"33178\"\n" + "                }\n" + "            }\n" + "        }\n"
//			+ "    ],\n" + "    \"location\": [\n" + "        {\n" + "            \"addressType\": \"BILLING\",\n"
//			+ "            \"address\": {\n" + "                \"zipcode\": \"33172\"\n" + "            }\n"
//			+ "        }\n" + "    ]\n" + "}";
//
//	// =========================================extID==updateESN==updateSIM====actionValue
//
//	String Post_ValidateorderPayload = "{\n" + "   \"externalID\": \"tf-add-to-res-extID\",\n"
//			+ "   \"relatedParties\": [\n" + "      {\n" + "         \"party\": {\n"
//			+ "            \"partyID\": \"T-CETRA\",\n" + "            \"languageAbility\": \"ENG\",\n"
//			+ "            \"partyExtension\": [\n" + "               {\n"
//			+ "                  \"name\": \"vendorName\",\n" + "                  \"value\": \"T-CETRA\"\n"
//			+ "               },\n" + "               {\n" + "                  \"name\": \"vendorStore\",\n"
//			+ "                  \"value\": \"1234\"\n" + "               },\n" + "               {\n"
//			+ "                  \"name\": \"vendorTerminal\",\n" + "                  \"value\": \"1234\"\n"
//			+ "               },\n" + "               {\n" + "                  \"name\": \"sourceSystem\",\n"
//			+ "                  \"value\": \"RTR\"\n" + "               }\n" + "            ]\n" + "         },\n"
//			+ "         \"roleType\": \"partner\"\n" + "      }\n" + "   ],\n"
//			+ "   \"orderDate\": \"updateDate\",\n" + "   \"orderItems\": [\n" + "      {\n"
//			+ "         \"id\": \"1\",\n" + "         \"action\": \"ACTIVATION\",\n" + "         \"quantity\": 1,\n"
//			+ "         \"product\": {\n" + "            \"productSerialNumber\": \"updateESN\",\n"
//			+ "            \"productCategory\": \"HANDSET\",\n" + "            \"productSpecification\": {\n"
//			+ "               \"brand\": \"SIMPLE_MOBILE\"\n" + "            },\n"
//			+ "            \"relatedServices\": [\n" + "               {\n" + "                  \"id\": \"101\",\n"
//			+ "                  \"category\": \"SERVICE_PLAN\",\n" + "                  \"isRedeemNow\": true,\n"
//			+ "                  \"isRecurring\": false,\n" + "                  \"characteristicSpecification\": [\n"
//			+ "                     {\n" + "                        \"name\": \"relatedProgramId\",\n"
//			+ "                        \"value\": \"\"\n" + "                     }\n" + "                  ]\n"
//			+ "               }\n" + "            ],\n" + "            \"supportingResources\": [\n"
//			+ "               {\n" + "                  \"resourceType\": \"SIM_CARD\",\n"
//			+ "                  \"serialNumber\": \"updateSIM\"\n" + "               }\n" + "            ]\n"
//			+ "         },\n" + "         \"productOffering\": {\n" + "            \"id\": \"SMNRTR20040\",\n"
//			+ "            \"productSpecification\": {\n" + "               \"id\": \"SM040UNL\"\n" + "            }\n"
//			+ "         },\n" + "         \"location\": {\n" + "            \"postalAddress\": {\n"
//			+ "               \"zipcode\": \"33178\"\n" + "            }\n" + "         }\n" + "      }\n" + "   ],\n"
//			+ "   \"location\": [\n" + "      {\n" + "         \"addressType\": \"BILLING\",\n"
//			+ "         \"address\": {\n" + "            \"zipcode\": \"33178\"\n" + "         }\n" + "      }\n"
//			+ "   ],\n" + "   \"type\": \"ACTIVATE_BAN\"\n" + "}";
//
//	// ================================
//	String valSubAddToReserveSM="{\n" + 
//			"   \"externalID\": \"itqADD_TO_RESERVE15-extID\",\n" + 
//			"   \"relatedParties\": [\n" + 
//			"      {\n" + 
//			"         \"party\": {\n" + 
//			"            \"partyID\": \"T-CETRA\",\n" + 
//			"            \"languageAbility\": \"ENG\",\n" + 
//			"            \"partyExtension\": [\n" + 
//			"               {\n" + 
//			"                  \"name\": \"vendorName\",\n" + 
//			"                  \"value\": \"TCETRA\"\n" + 
//			"               },\n" + 
//			"               {\n" + 
//			"                  \"name\": \"vendorStore\",\n" + 
//			"                  \"value\": \"1234\"\n" + 
//			"               },\n" + 
//			"               {\n" + 
//			"                  \"name\": \"vendorTerminal\",\n" + 
//			"                  \"value\": \"1234\"\n" + 
//			"               },\n" + 
//			"               {\n" + 
//			"                  \"name\": \"sourceSystem\",\n" + 
//			"                  \"value\": \"RTR\"\n" + 
//			"               }\n" + 
//			"            ]\n" + 
//			"         },\n" + 
//			"         \"roleType\": \"partner\"\n" + 
//			"      }\n" + 
//			"   ],\n" + 
//			"   \"orderDate\": \"updateDate\",\n" + 
//			"   \"orderItems\": [\n" + 
//			"      {\n" + 
//			"         \"id\": \"1\",\n" + 
//			"         \"action\": \"ADD_TO_RESERVE\",\n" + 
//			"         \"quantity\": 1,\n" + 
//			"         \"product\": {\n" + 
//			"            \"productSerialNumber\": \"updateESN\",\n" + 
//			"            \"productCategory\": \"HANDSET\",\n" + 
//			"            \"productSpecification\": {\n" + 
//			"               \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"            },\n" + 
//			"            \"relatedServices\": [\n" + 
//			"               {\n" + 
//			"                  \"id\": \"101\",\n" + 
//			"                  \"category\": \"SERVICE_PLAN\",\n" + 
//			"                  \"isRedeemNow\": false,\n" + 
//			"                  \"isRecurring\": false,\n" + 
//			"                  \"characteristicSpecification\": [\n" + 
//			"                     {\n" + 
//			"                        \"name\": \"relatedProgramId\",\n" + 
//			"                        \"value\": \"\"\n" + 
//			"                     }\n" + 
//			"                  ]\n" + 
//			"               }\n" + 
//			"            ],\n" + 
//			"            \"supportingResources\": [\n" + 
//			"               {\n" + 
//			"                  \"resourceType\": \"SIM_CARD\",\n" + 
//			"                  \"serialNumber\": \"updateSIM\"\n" + 
//			"               }\n" + 
//			"            ]\n" + 
//			"     \n" + 
//			"         },\n" + 
//			"         \"productOffering\": {\n" + 
//			"            \"id\": \"SMNRTR20025\",\n" + 
//			"            \"productSpecification\": {\n" + 
//			"               \"id\": \"SM025TT\"\n" + 
//			"            }\n" + 
//			"         },\n" + 
//			"         \"location\": {\n" + 
//			"            \"postalAddress\": {\n" + 
//			"               \"zipcode\": \"33178\"\n" + 
//			"            }\n" + 
//			"         }\n" + 
//			"      }\n" + 
//			"   ],\n" + 
//			"   \"location\": [\n" + 
//			"      {\n" + 
//			"         \"addressType\": \"BILLING\",\n" + 
//			"         \"address\": {\n" + 
//			"            \"zipcode\": \"33178\"\n" + 
//			"         }\n" + 
//			"      }\n" + 
//			"   ],\n" + 
//			"   \"type\": \"ACTIVATE_BAN\"\n" + 
//			"}";
//	
//	
//	String post_ValidateActivateSM2L="{\n" + 
//			"  \"externalID\": \"ICO-extID\",\n" + 
//			"  \"relatedParties\": [\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyID\": \"IAS\",\n" + 
//			"        \"languageAbility\": \"ENG\",\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorName\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorStore\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorTerminal\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"sourceSystem\",\n" + 
//			"            \"value\": \"RTR\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"partner\"\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"accountEmail\",\n" + 
//			"            \"value\": \"innovation@directors.com\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"partySecret\",\n" + 
//			"            \"value\": \"1234\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"customer\"\n" + 
//			"    }\n" + 
//			"  ],\n" + 
//			"  \"type\": \"ACTIVATE_BAN\",\n" + 
//			"  \"orderDate\": \"updateDate\",\n" + 
//			"  \"orderItems\": [\n" + 
//			"    {\n" + 
//			"      \"id\": \"1\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ACTIVATION\",\n" + 
//			"     \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART1\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\",\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN1\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM1\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"id\": \"2\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ACTIVATION\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART2\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\",\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN2\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM2\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    }\n" + 
//			"  ]\n" + 
//			"}";
//	// ================================
//	
//	String post_EstimateActivateSM1L="{\n" + 
//			"  \"externalID\": \"ICO-extID\",\n" + 
//			"  \"relatedParties\": [\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyID\": \"IAS\",\n" + 
//			"        \"languageAbility\": \"ENG\",\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorName\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorStore\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorTerminal\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"sourceSystem\",\n" + 
//			"            \"value\": \"RTR\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"partner\"\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"partySecret\",\n" + 
//			"            \"value\": \"1234\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"customer\"\n" + 
//			"    }\n" + 
//			"  ],\n" + 
//			"  \"type\": \"ACTIVATE_BAN\",\n" + 
//			"  \"orderDate\": \"updateDate\",\n" + 
//			"  \"orderItems\": [\n" + 
//			"    {\n" + 
//			"      \"id\": \"1\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ESTIMATE\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART1\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\"\n" + 
//			"          \n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN1\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM1\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    }\n" + 
//			"    \n" + 
//			"  ]\n" + 
//			"}";
//	// ================================
//	String post_EstimateActivateSM3L="{\n" + 
//			"  \"externalID\": \"ICO-extID\",\n" + 
//			"  \"relatedParties\": [\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyID\": \"IAS\",\n" + 
//			"        \"languageAbility\": \"ENG\",\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorName\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorStore\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorTerminal\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"sourceSystem\",\n" + 
//			"            \"value\": \"RTR\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"partner\"\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"partySecret\",\n" + 
//			"            \"value\": \"1234\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"customer\"\n" + 
//			"    }\n" + 
//			"  ],\n" + 
//			"  \"type\": \"ACTIVATE_BAN\",\n" + 
//			"  \"orderDate\": \"updateDate\",\n" + 
//			"  \"orderItems\": [\n" + 
//			"    {\n" + 
//			"      \"id\": \"1\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ESTIMATE\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART1\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\"\n" + 
//			"          \n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN1\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM1\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"      {\n" + 
//			"      \"id\": \"2\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ESTIMATE\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART2\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN2\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM2\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"      {\n" + 
//			"      \"id\": \"3\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ESTIMATE\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART3\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\"\n" + 
//			"          \n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN3\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateESN3\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    }\n" + 
//			"  ]\n" + 
//			"}";
//	// ================================
//	String post_EstimateActivateSM4L ="{\n" + 
//			"  \"externalID\": \"ICO-extID\",\n" + 
//			"  \"relatedParties\": [\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyID\": \"IAS\",\n" + 
//			"        \"languageAbility\": \"ENG\",\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorName\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorStore\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorTerminal\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"sourceSystem\",\n" + 
//			"            \"value\": \"RTR\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"partner\"\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"partySecret\",\n" + 
//			"            \"value\": \"1234\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"customer\"\n" + 
//			"    }\n" + 
//			"  ],\n" + 
//			"  \"type\": \"ACTIVATE_BAN\",\n" + 
//			"  \"orderDate\": \"updateDate\",\n" + 
//			"  \"orderItems\": [\n" + 
//			"    {\n" + 
//			"      \"id\": \"1\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ESTIMATE\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART1\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\"\n" + 
//			"          \n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN1\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM1\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"      {\n" + 
//			"      \"id\": \"2\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ESTIMATE\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART2\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN2\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM2\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"id\": \"3\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ESTIMATE\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART3\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN3\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM3\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"      {\n" + 
//			"      \"id\": \"4\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ESTIMATE\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART4\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\"\n" + 
//			"          \n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN4\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM4\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    }\n" + 
//			"  ]\n" + 
//			"}";
//	
//	// ================================
//	String post_EstimateActivateSM5L ="{\n" + 
//			"  \"externalID\": \"ICO-extID\",\n" + 
//			"  \"relatedParties\": [\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyID\": \"IAS\",\n" + 
//			"        \"languageAbility\": \"ENG\",\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorName\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorStore\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorTerminal\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"sourceSystem\",\n" + 
//			"            \"value\": \"RTR\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"partner\"\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"partySecret\",\n" + 
//			"            \"value\": \"1234\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"customer\"\n" + 
//			"    }\n" + 
//			"  ],\n" + 
//			"  \"type\": \"ACTIVATE_BAN\",\n" + 
//			"  \"orderDate\": \"updateDate\",\n" + 
//			"  \"orderItems\": [\n" + 
//			"    {\n" + 
//			"      \"id\": \"1\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ESTIMATE\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART1\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\"\n" + 
//			"          \n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN1\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM1\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"      {\n" + 
//			"      \"id\": \"2\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ESTIMATE\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART2\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN2\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM2\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"id\": \"3\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ESTIMATE\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART3\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN3\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM3\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"\n" + 
//			"    {\n" + 
//			"      \"id\": \"4\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ESTIMATE\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART4\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN4\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM4\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"      {\n" + 
//			"      \"id\": \"5\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ESTIMATE\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART5\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\"\n" + 
//			"          \n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN5\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateESN5\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    }\n" + 
//			"  ]\n" + 
//			"}";
//	
//	// ================================
//	
//	String post_EstimateActivateSM2L = "{\n" + 
//			"  \"externalID\": \"ICO-extID\",\n" + 
//			"  \"relatedParties\": [\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyID\": \"IAS\",\n" + 
//			"        \"languageAbility\": \"ENG\",\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorName\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorStore\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorTerminal\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"sourceSystem\",\n" + 
//			"            \"value\": \"RTR\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"partner\"\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"partySecret\",\n" + 
//			"            \"value\": \"1234\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"customer\"\n" + 
//			"    }\n" + 
//			"  ],\n" + 
//			"  \"type\": \"ACTIVATE_BAN\",\n" + 
//			"   \"orderDate\": \"updateDate\",\n" + 
//			"  \"orderItems\": [\n" + 
//			"    {\n" + 
//			"      \"id\": \"1\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ESTIMATE\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART1\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\"\n" + 
//			"          \n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN1\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM1\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"      {\n" + 
//			"      \"id\": \"2\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ESTIMATE\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART2\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN2\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM2\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    }\n" + 
//			"  ]\n" + 
//			"}";
//	
//	// ================================
//	String post_ValidateActivateSM5L="{\n" + 
//			"  \"externalID\": \"ICO-extID\",\n" + 
//			"  \"relatedParties\": [\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyID\": \"IAS\",\n" + 
//			"        \"languageAbility\": \"ENG\",\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorName\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorStore\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorTerminal\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"sourceSystem\",\n" + 
//			"            \"value\": \"RTR\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"partner\"\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"partySecret\",\n" + 
//			"            \"value\": \"1234\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"customer\"\n" + 
//			"    }\n" + 
//			"  ],\n" + 
//			"  \"type\": \"ACTIVATE_BAN\",\n" + 
//			"   \"orderDate\": \"updateDate\",\n" + 
//			"  \"orderItems\": [\n" + 
//			"    {\n" + 
//			"      \"id\": \"1\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ACTIVATION\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART1\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\",\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN1\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM1\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"         {\n" + 
//			"      \"id\": \"2\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ACTIVATION\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART2\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\",\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN2\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM2\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"id\": \"3\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ACTIVATION\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART3\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\",\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN3\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM3\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"     {\n" + 
//			"      \"id\": \"4\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ACTIVATION\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART4\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\",\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN4\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM4\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"      {\n" + 
//			"      \"id\": \"5\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ACTIVATION\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART5\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\",\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN5\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM5\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    }\n" + 
//			"  ]\n" + 
//			"}";
//	// ================================
//	
//	String post_ValidateActivateSM4L="{\n" + 
//			"  \"externalID\": \"ICO-extID\",\n" + 
//			"  \"relatedParties\": [\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyID\": \"IAS\",\n" + 
//			"        \"languageAbility\": \"ENG\",\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorName\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorStore\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorTerminal\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"sourceSystem\",\n" + 
//			"            \"value\": \"RTR\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"partner\"\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"partySecret\",\n" + 
//			"            \"value\": \"1234\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"customer\"\n" + 
//			"    }\n" + 
//			"  ],\n" + 
//			"  \"type\": \"ACTIVATE_BAN\",\n" + 
//			"  \"orderDate\": \"updateDate\",\n" + 
//			"  \"orderItems\": [\n" + 
//			"    {\n" + 
//			"      \"id\": \"1\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ACTIVATION\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART1\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\",\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN1\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM1\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"         {\n" + 
//			"      \"id\": \"2\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ACTIVATION\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART2\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\",\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN2\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM2\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"id\": \"3\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ACTIVATION\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART3\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\",\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN3\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM3\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"      {\n" + 
//			"      \"id\": \"4\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ACTIVATION\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART4\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\",\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN4\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM4\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    }\n" + 
//			"  ]\n" + 
//			"}";
//	
//	// ================================
//	
//	String post_ValidateActivateSM3L="{\n" + 
//			"  \"externalID\": \"ICO-extID\",\n" + 
//			"  \"relatedParties\": [\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyID\": \"IAS\",\n" + 
//			"        \"languageAbility\": \"ENG\",\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorName\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorStore\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorTerminal\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"sourceSystem\",\n" + 
//			"            \"value\": \"RTR\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"partner\"\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"partySecret\",\n" + 
//			"            \"value\": \"1234\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"customer\"\n" + 
//			"    }\n" + 
//			"  ],\n" + 
//			"  \"type\": \"ACTIVATE_BAN\",\n" + 
//			"  \"orderDate\": \"updateDate\",\n" + 
//			"  \"orderItems\": [\n" + 
//			"    {\n" + 
//			"      \"id\": \"1\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ACTIVATION\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART1\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\",\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN1\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM1\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"         {\n" + 
//			"      \"id\": \"2\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ACTIVATION\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART2\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\",\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN2\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM2\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    },\n" + 
//			"      {\n" + 
//			"      \"id\": \"3\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ACTIVATION\",\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART3\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\",\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN3\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM3\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    }\n" + 
//			"  ]\n" + 
//			"}";
//	// ================================
//	
//	String post_ValidateActivateSM1L = "{\n" + 
//			"  \"externalID\": \"ICO-extID\",\n" + 
//			"  \"relatedParties\": [\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyID\": \"IAS\",\n" + 
//			"        \"languageAbility\": \"ENG\",\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorName\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorStore\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"vendorTerminal\",\n" + 
//			"            \"value\": \"IN_COMM\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"sourceSystem\",\n" + 
//			"            \"value\": \"RTR\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"partner\"\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"accountEmail\",\n" + 
//			"            \"value\": \"innovation@directors.com\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"partySecret\",\n" + 
//			"            \"value\": \"1234\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"customer\"\n" + 
//			"    }\n" + 
//			"  ],\n" + 
//			"  \"type\": \"ACTIVATE_BAN\",\n" + 
//			"  \"orderDate\": \"updateDate\",\n" + 
//			"  \"orderItems\": [\n" + 
//			"    {\n" + 
//			"      \"id\": \"1\",\n" + 
//			"      \"quantity\": \"1\",\n" + 
//			"      \"action\": \"ACTIVATION\",\n" + 
//			"     \"productOffering\": {\n" + 
//			"        \"id\": \"updatePINPART1\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"SM050BBUNL\",\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"product\": {\n" + 
//			"        \"relatedServices\": [\n" + 
//			"          {\n" + 
//			"            \"category\": \"SERVICE_PLAN\",\n" + 
//			"            \"isEnrollNow\": false,\n" + 
//			"            \"isRedeemNow\": true\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"productSerialNumber\": \"updateESN1\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"SIMPLE_MOBILE\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM1\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33172\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    }\n" + 
//			"   \n" + 
//			"  ]\n" + 
//			"}";
//
//	// ================================
//
//	String Post_SubmitOrderPayloadSM = "{\n" + "   \"externalID\": \"tf-add-to-res-extID\",\n"
//			+ "   \"relatedParties\": [\n" + "      {\n" + "         \"party\": {\n"
//			+ "            \"partyID\": \"T-CETRA\",\n" + "            \"languageAbility\": \"ENG\",\n"
//			+ "            \"partyExtension\": [\n" + "               {\n"
//			+ "                  \"name\": \"vendorName\",\n" + "                  \"value\": \"T-CETRA\"\n"
//			+ "               },\n" + "               {\n" + "                  \"name\": \"vendorStore\",\n"
//			+ "                  \"value\": \"1234\"\n" + "               },\n" + "               {\n"
//			+ "                  \"name\": \"vendorTerminal\",\n" + "                  \"value\": \"1234\"\n"
//			+ "               },\n" + "               {\n" + "                  \"name\": \"sourceSystem\",\n"
//			+ "                  \"value\": \"RTR\"\n" + "               }\n" + "            ]\n" + "         },\n"
//			+ "         \"roleType\": \"partner\"\n" + "      }\n" + "   ],\n"
//			+ "   \"orderDate\": \"2019-10-23T11:23:38-0400\",\n" + "   \"orderItems\": [\n" + "      {\n"
//			+ "         \"id\": \"1\",\n" + "         \"action\": \"ACTIVATION\",\n" + "         \"quantity\": 1,\n"
//			+ "         \"product\": {\n" + "            \"productSerialNumber\": \"updateESN\",\n"
//			+ "            \"productCategory\": \"HANDSET\",\n" + "            \"productSpecification\": {\n"
//			+ "               \"brand\": \"SIMPLE_MOBILE\"\n" + "            },\n"
//			+ "            \"relatedServices\": [\n" + "               {\n" + "                  \"id\": \"101\",\n"
//			+ "                  \"category\": \"SERVICE_PLAN\",\n" + "                  \"isRedeemNow\": true,\n"
//			+ "                  \"isRecurring\": false,\n" + "                  \"characteristicSpecification\": [\n"
//			+ "                     {\n" + "                        \"name\": \"relatedProgramId\",\n"
//			+ "                        \"value\": \"\"\n" + "                     }\n" + "                  ]\n"
//			+ "               }\n" + "            ],\n" + "            \"supportingResources\": [\n"
//			+ "               {\n" + "                  \"resourceType\": \"SIM_CARD\",\n"
//			+ "                  \"serialNumber\": \"updateSIM\"\n" + "               }\n" + "            ]\n"
//			+ "         },\n" + "         \"productOffering\": {\n" + "            \"id\": \"SMNRTR20040\",\n"
//			+ "            \"productSpecification\": {\n" + "               \"id\": \"SM040UNL\"\n" + "            }\n"
//			+ "         },\n" + "         \"location\": {\n" + "            \"postalAddress\": {\n"
//			+ "               \"zipcode\": \"33178\"\n" + "            }\n" + "         }\n" + "      }\n" + "   ],\n"
//			+ "   \"location\": [\n" + "      {\n" + "         \"addressType\": \"BILLING\",\n"
//			+ "         \"address\": {\n" + "            \"zipcode\": \"33178\"\n" + "         }\n" + "      }\n"
//			+ "   ],\n" + "   \"type\": \"ACTIVATE_BAN\"\n" + "}";
//
//	String inactiveESN = "{\n" + "    \"relatedParties\": [\n" + "        {\n"
//			+ "            \"roleType\": \"partner\",\n" + "            \"party\": {\n"
//			+ "                \"languageAbility\": \"ENG\",\n" + "                \"partyExtension\": [\n"
//			+ "                    {\n" + "                        \"name\": \"partyTransactionID\",\n"
//			+ "                        \"value\": \"5f7aa212-73b2-4f82-8b37-b9100c01c86f\"\n"
//			+ "                    },\n" + "                    {\n"
//			+ "                        \"name\": \"sourceSystem\",\n" + "                        \"value\": \"API\"\n"
//			+ "                    },\n" + "                    {\n"
//			+ "                        \"name\": \"vendorStore\",\n" + "                        \"value\": \"302\"\n"
//			+ "                    },\n" + "                    {\n"
//			+ "                        \"name\": \"vendorTerminal\",\n" + "                        \"value\": \"302\"\n"
//			+ "                    }\n" + "                ],\n" + "                \"partyID\": \"TCETRA\"\n"
//			+ "            }\n" + "        },\n" + "        {\n" + "            \"roleType\": \"customer\",\n"
//			+ "            \"party\": {\n" + "                \"partyExtension\": [\n" + "                    {\n"
//			+ "                        \"name\": \"accountEmail\",\n"
//			+ "                        \"value\": \"updateEmail\"\n" + "                    },\n"
//			+ "                    {\n" + "                        \"name\": \"accountPassword\",\n"
//			+ "                        \"value\": \"xyz1234\"\n" + "                    }\n" + "                ]\n"
//			+ "            }\n" + "        }\n" + "    ],\n" + "    \"externalID\": \"123\",\n"
//			+ "    \"orderDate\": \"updateDateName\",\n" + "    \"orderItems\": [\n" + "        {\n"
//			+ "            \"orderItemExtension\": [\n" + "                {\n"
//			+ "                    \"name\": \"groupIdentifier\",\n" + "                    \"value\": \"\"\n"
//			+ "                }\n" + "            ],\n" + "            \"product\": {\n"
//			+ "                \"subCategory\": \"BRANDED\",\n" + "                \"productSpecification\": {\n"
//			+ "                    \"brand\": \"TOTAL_WIRELESS\"\n" + "                },\n"
//			+ "                \"productSerialNumber\": \"updateESN\",\n"
//			+ "                \"productCharacteristics\": [\n" + "                    {\n"
//			+ "                        \"name\": \"manufacturer\",\n" + "                        \"value\": \"APPLE\"\n"
//			+ "                    },\n" + "                    {\n" + "                        \"name\": \"model\",\n"
//			+ "                        \"value\": \"MKRD2LL/A\"\n" + "                    },\n"
//			+ "                    {\n" + "                        \"name\": \"isCarrierPreloaded\",\n"
//			+ "                        \"value\": \"false\"\n" + "                    }\n" + "                ],\n"
//			+ "                \"supportingResources\": [\n" + "                    {\n"
//			+ "                        \"productIdentifier\": \"updatePIN\",\n"
//			+ "                        \"resourceType\": \"AIRTIME_CARD\"\n" + "                    },\n"
//			+ "                    {\n" + "                        \"serialNumber\": \"updateSIM\",\n"
//			+ "                        \"resourceType\": \"SIM_CARD\"\n" + "                    }\n"
//			+ "                ],\n" + "                \"productCategory\": \"HANDSET\"\n" + "            },\n"
//			+ "            \"action\": \"ACTIVATION\",\n" + "            \"location\": {\n"
//			+ "                \"postalAddress\": {\n" + "                    \"zipcode\": \"33178\"\n"
//			+ "                }\n" + "            },\n" + "            \"id\": \"1\"\n" + "        }\n" + "    ]\n"
//			+ "}";
//
//	String PortInactiveESN = "{\n" + "    \"orderDate\": \"updateDate\",\n" + "    \"relatedParties\": [\n"
//			+ "        {\n" + "            \"roleType\": \"partner\",\n" + "            \"party\": {\n"
//			+ "                \"partyExtension\": [\n" + "                    {\n"
//			+ "                        \"name\": \"partyTransactionID\",\n"
//			+ "                        \"value\": \"adrrd37d5f-ee0f-4423-aae1-extID\"\n" + "                    },\n"
//			+ "                    {\n" + "                        \"name\": \"sourceSystem\",\n"
//			+ "                        \"value\": \"API\"\n" + "                    },\n" + "                    {\n"
//			+ "                        \"name\": \"vendorStrore\",\n" + "                        \"value\": \"302\"\n"
//			+ "                    },\n" + "                    {\n"
//			+ "                        \"name\": \"vendorTerminal\",\n" + "                        \"value\": \"302\"\n"
//			+ "                    }\n" + "                ],\n" + "                \"partyID\": \"TCETRA\",\n"
//			+ "                \"languageAbility\": \"ENG\"\n" + "            }\n" + "        },\n" + "        {\n"
//			+ "            \"roleType\": \"customer\",\n" + "            \"party\": {\n"
//			+ "                \"partyExtension\": [\n" + "                    {\n"
//			+ "                        \"name\": \"accountEmail\",\n"
//			+ "                        \"value\": \"updateEmail\"\n" + "                    },\n"
//			+ "                    {\n" + "                        \"name\": \"accountPassword\",\n"
//			+ "                        \"value\": \"xyz1234\"\n" + "                    }\n" + "                ]\n"
//			+ "            }\n" + "        }\n" + "    ],\n" + "    \"externalID\": \"1234\",\n"
//			+ "    \"orderItems\": [\n" + "        {\n" + "            \"product\": {\n"
//			+ "                \"subCategory\": \"BRANDED\",\n" + "                \"productCategory\": \"HANDSET\",\n"
//			+ "                \"productSpecification\": {\n" + "                    \"brand\": \"TOTAL_WIRELESS\"\n"
//			+ "                },\n" + "                \"productCharacteristics\": [\n" + "                    {\n"
//			+ "                        \"name\": \"manufacturer\",\n" + "                        \"value\": \"APPLE\"\n"
//			+ "                    },\n" + "                    {\n" + "                        \"name\": \"model\",\n"
//			+ "                        \"value\": \"MKRD2LL/A\"\n" + "                    },\n"
//			+ "                    {\n" + "                        \"name\": \"isCarrierPreloaded\",\n"
//			+ "                        \"value\": \"N\"\n" + "                    }\n" + "                ],\n"
//			+ "                \"productSerialNumber\": \"updateESN\",\n"
//			+ "                \"supportingResources\": [\n" + "                    {\n"
//			+ "                        \"productIdentifier\": \"updatePIN\",\n"
//			+ "                        \"resourceType\": \"AIRTIME_CARD\"\n" + "                    },\n"
//			+ "                    {\n" + "                        \"serialNumber\": \"updateSIM\",\n"
//			+ "                        \"resourceType\": \"SIM_CARD\"\n" + "                    }\n"
//			+ "                ]\n" + "            },\n" + "            \"id\": \"1\",\n"
//			+ "            \"location\": {\n" + "                \"postalAddress\": {\n"
//			+ "                    \"zipcode\": \"33178\"\n" + "                }\n" + "            },\n"
//			+ "            \"action\": \"PORT\",\n" + "            \"orderItemExtension\": [\n" + "                {\n"
//			+ "                    \"name\": \"currentMIN\",\n" + "                    \"value\": \"updateMIN\"\n"
//			+ "                },\n" + "                {\n"
//			+ "                    \"name\": \"currentServiceProvider\",\n"
//			+ "                    \"value\": \"NET10\"\n" + "                },\n" + "                {\n"
//			+ "                    \"name\": \"groupIdentifier\",\n" + "                    \"value\": \"\"\n"
//			+ "                }\n" + "            ]\n" + "        }\n" + "    ]\n" + "}";
////====================	extID==updateESN==updateSIM=======updateORDERDATE===updateSOURCESystem==API==updateAccountEmail
//	String Post_ValSubmitOrderPayloadTW = "{\n" + "    \"orderDate\": \"2019-11-26T09:34:23-04:00\",\n"
//			+ "    \"relatedParties\": [\n" + "        {\n" + "            \"roleType\": \"partner\",\n"
//			+ "            \"party\": {\n" + "                \"partyExtension\": [\n" + "                    {\n"
//			+ "                        \"name\": \"partyTransactionID\",\n"
//			+ "                        \"value\": \"adrrd37d5f-ee0f-4423-aae1-extID\"\n" + "                    },\n"
//			+ "                    {\n" + "                        \"name\": \"sourceSystem\",\n"
//			+ "                        \"value\": \"API\"\n" + "                    },\n" + "                    {\n"
//			+ "                        \"name\": \"vendorStrore\",\n" + "                        \"value\": \"302\"\n"
//			+ "                    },\n" + "                    {\n"
//			+ "                        \"name\": \"vendorTerminal\",\n" + "                        \"value\": \"302\"\n"
//			+ "                    }\n" + "                ],\n" + "                \"partyID\": \"TCETRA\",\n"
//			+ "                \"languageAbility\": \"ENG\"\n" + "            }\n" + "        },\n" + "        {\n"
//			+ "            \"roleType\": \"customer\",\n" + "            \"party\": {\n"
//			+ "                \"partyExtension\": [\n" + "                    {\n"
//			+ "                        \"name\": \"accountEmail\",\n"
//			+ "                        \"value\": \"updateAccountEmail\"\n" + "                    },\n"
//			+ "                    {\n" + "                        \"name\": \"accountPassword\",\n"
//			+ "                        \"value\": \"1234\"\n" + "                    }\n" + "                ]\n"
//			+ "            }\n" + "        }\n" + "    ],\n" + "    \"externalID\": \"1234\",\n"
//			+ "    \"orderItems\": [\n" + "        {\n" + "            \"product\": {\n"
//			+ "                \"subCategory\": \"BRANDED\",\n" + "                \"productCategory\": \"HANDSET\",\n"
//			+ "                \"productSpecification\": {\n" + "                    \"brand\": \"TOTAL_WIRELESS\"\n"
//			+ "                },\n" + "                \"productCharacteristics\": [\n" + "                    {\n"
//			+ "                        \"name\": \"manufacturer\",\n" + "                        \"value\": \"APPLE\"\n"
//			+ "                    },\n" + "                    {\n" + "                        \"name\": \"model\",\n"
//			+ "                        \"value\": \"MKRD2LL/A\"\n" + "                    },\n"
//			+ "                    {\n" + "                        \"name\": \"isCarrierPreloaded\",\n"
//			+ "                        \"value\": \"N\"\n" + "                    }\n" + "                ],\n"
//			+ "                \"productSerialNumber\": \"updateESN\",\n"
//			+ "                \"supportingResources\": [\n" + "                    {\n"
//			+ "                        \"productIdentifier\": \"updatePIN\",\n"
//			+ "                        \"resourceType\": \"AIRTIME_CARD\"\n" + "                    },\n"
//			+ "                    {\n" + "                        \"serialNumber\": \"updateSIM\",\n"
//			+ "                        \"resourceType\": \"SIM_CARD\"\n" + "                    }\n"
//			+ "                ]\n" + "            },\n" + "            \"id\": \"1\",\n"
//			+ "            \"location\": {\n" + "                \"postalAddress\": {\n"
//			+ "                    \"zipcode\": \"33178\"\n" + "                }\n" + "            },\n"
//			+ "            \"action\": \"ACTIVATION\"\n" + "            \n" + "        }\n" + "    ]\n" + "}";
//
//	String activationEligibility = "{\n" + "    \"relatedParties\": [\n" + "      {\n" + "        \"partY\": {\n"
//			+ "          \"partyID\": \"DAP\",\n" + "          \"languageAbility\": \"ENG\",\n"
//			+ "          \"partyExtension\": [\n" + "            {\n"
//			+ "              \"name\": \"partyTransactionID\",\n" + "              \"value\": \"web_12312342-extID\"\n"
//			+ "            },\n" + "            {\n" + "              \"name\": \"sourceSystem\",\n"
//			+ "              \"value\": \"INDIRECT\"\n" + "            }\n" + "          ]\n" + "        },\n"
//			+ "        \"roleType\": \"internal\"\n" + "      }\n" + "    ],\n" + "    \"location\": {\n"
//			+ "      \"postalAddress\": {\n" + "        \"zipcode\": \"33178\"\n" + "      }\n" + "    },\n"
//			+ "    \"serviceCategory\": [\n" + "      {\n" + "        \"type\": \"context\",\n"
//			+ "        \"value\": \"updateContext\"\n" + "      }\n" + "    ],\n" + "    \"serviceSpecification\": {\n"
//			+ "      \"brand\": \"TOTAL_WIRELESS\"\n" + "    },\n" + "    \"resources\": [\n" + "      {\n"
//			+ "        \"serialNumber\": \"updateESN\",\n" + "        \"name\": \"productSerialNumber\",\n"
//			+ "        \"type\": \"HANDSET\"\n" + "      },\n" + "      {\n"
//			+ "        \"serialNumber\": \"updateSIM\",\n" + "        \"name\": \"serialNumber\",\n"
//			+ "        \"type\": \"SIM_CARD\"\n" + "      }\n" + "    ]\n" + "}";
//
////#extID-updateDate-updateESN-updateSIM-updateE_Mail-updatePINPART
//	String stageActivation = "{\n" + "  \"externalID\": \"UUID-extID\",\n" + "  \"orderDate\": \"updateDate\",\n"
//			+ "  \"relatedParties\": [\n" + "    {\n" + "      \"party\": {\n" + "        \"partyID\": \"IAS\",\n"
//			+ "        \"languageAbility\": \"ENG\",\n" + "        \"partyExtension\": [\n" + "          {\n"
//			+ "            \"name\": \"partyTransactionID\",\n"
//			+ "            \"value\": \"5f7aa212-73b2-4f82-8b37-extID\"\n" + "          },\n" + "          {\n"
//			+ "            \"name\": \"sourceSystem\",\n" + "            \"value\": \"RTR\"\n" + "          }\n"
//			+ "        ]\n" + "      },\n" + "      \"roleType\": \"partner\"\n" + "    },\n" + "    {\n"
//			+ "      \"party\": {\n" + "        \"individual\": {\n" + "         \n"
//			+ "          \"firstName\": \"Mathew\",\n" + "          \"lastName\": \"Lee\"\n" + "        },\n"
//			+ "        \"partyExtension\": [\n" + "          {\n" + "            \"name\": \"accountEmail\",\n"
//			+ "            \"value\": \"updateE_Mail\" \n" + "          }\n" + "        ]\n" + "      },\n"
//			+ "      \"roleType\": \"customer\"\n" + "    }\n" + "  ],\n" + "  \"orderItems\": [\n" + "    {\n"
//			+ "      \"action\": \"updateAction\",\n" + "      \"id\": \"1\",\n" + "      \"quantity\": 0,\n"
//			+ "      \"product\": {\n" + "        \"productSerialNumber\": \"updateESN\",\n"
//			+ "        \"productCategory\": \"HANDSET\",\n" + "        \"productSpecification\": {\n"
//			+ "          \"brand\": \"TOTAL_WIRELESS\"\n" + "        },\n" + "        \"supportingResources\": [\n"
//			+ "          {\n" + "            \"resourceType\": \"SIM_CARD\",\n"
//			+ "            \"serialNumber\": \"updateSIM\"\n" + "          }\n" + "        ]\n" + "      },\n"
//			+ "      \"location\": {\n" + "        \"postalAddress\": {\n" + "          \"zipcode\": \"95815\"\n"
//			+ "        }\n" + "      },\n" + "      \"productOffering\": {\n"
//			+ "                \"id\": \"updatePINPART\",\n" + "                \"productSpecification\": {\n"
//			+ "                    \"id\": \"updatePINPART\"\n" + "                }\n" + "            }\n" + "    }\n"
//			+ "  ]\n" + "}";
//
//	String stagePortParent="{\n" + 
//			"  \"externalID\": \"UUID-extID\",\n" + 
//			"  \"orderDate\": \"updateDate\",\n" + 
//			"  \"relatedParties\": [\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyID\": \"T-CETRA\",\n" + 
//			"        \"languageAbility\": \"ENG\",\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"partyTransactionID\",\n" + 
//			"            \"value\": \"partyid-UUID-extID\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"sourceSystem\",\n" + 
//			"            \"value\": \"RTR\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"partner\"\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"individual\": {\n" + 
//			"          \"firstName\": \"Mathew\",\n" + 
//			"          \"lastName\": \"Lee\"\n" + 
//			"        },\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"accountEmail\",\n" + 
//			"            \"value\": \"updateE_Mail\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"customer\"\n" + 
//			"    }\n" + 
//			"  ],\n" + 
//			"  \"orderItems\": [\n" + 
//			"    {\n" + 
//			"      \"action\": \"PORT\",\n" + 
//			"      \"id\": \"1\",\n" + 
//			"      \"quantity\": 0,\n" + 
//			"      \"product\": {\n" + 
//			"        \"productSerialNumber\": \"updateESN\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"TOTAL_WIRELESS\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"orderItemExtension\": [\n" + 
//			"        {\n" + 
//			"          \"name\": \"currentMin\",\n" + 
//			"          \"value\": \"updateMIN\"\n" + 
//			"        },\n" + 
//			"        {\n" + 
//			"          \"name\": \"currentAccountNumber\",\n" + 
//			"          \"value\": \"5678798\"\n" + 
//			"        },\n" + 
//			"        {\n" + 
//			"          \"name\": \"currentAccountSecret\",\n" + 
//			"          \"value\": \"236541\"\n" + 
//			"        },\n" + 
//			"        {\n" + 
//			"          \"name\": \"currentServiceProvider\",\n" + 
//			"          \"value\": \"VZW\"\n" + 
//			"        }\n" + 
//			"      ],\n" + 
//			"      \"customerAccount\": {\n" + 
//			"        \"customerAccountContact\": [\n" + 
//			"          {\n" + 
//			"            \"contactMedium\": {\n" + 
//			"              \"contactDetails\": [\n" + 
//			"                {\n" + 
//			"                  \"city\": \"Miami\",\n" + 
//			"                  \"postcode\": \"33178\",\n" + 
//			"                  \"stateOrProvince\": \"FL\",\n" + 
//			"                  \"street1\": \"3409 SW 113th TER\"\n" + 
//			"                }\n" + 
//			"              ],\n" + 
//			"              \"type\": \"postalAddress\"\n" + 
//			"            }\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"contactDetails\": [\n" + 
//			"              {\n" + 
//			"                \"number\": \"3602589653\"\n" + 
//			"              }\n" + 
//			"            ],\n" + 
//			"            \"type\": \"telephoneNumber\"\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"customerAccountExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"last4DigitsOfSSN\",\n" + 
//			"            \"value\": \"6541\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33178\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"TWRTR00085\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"TWRTR00085\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    }\n" + 
//			"  ]\n" + 
//			"}";
//	
//	
//	String stagePortOrder1="{\n" + 
//			"  \"externalID\": \"UUID-extID-4500\",\n" + 
//			"  \"orderDate\": \"updateDate\",\n" + 
//			"  \"relatedParties\": [\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"partyID\": \"T-CETRA\",\n" + 
//			"        \"languageAbility\": \"ENG\",\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"partyTransactionID\",\n" + 
//			"            \"value\": \"partyid-UUID-extID\"\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"name\": \"sourceSystem\",\n" + 
//			"            \"value\": \"RTR\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"partner\"\n" + 
//			"    },\n" + 
//			"    {\n" + 
//			"      \"party\": {\n" + 
//			"        \"individual\": {\n" + 
//	
//			"          \"firstName\": \"Mathew\",\n" + 
//			"          \"lastName\": \"Lee\"\n" + 
//			"        },\n" + 
//			"        \"partyExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"accountEmail\",\n" + 
//			"            \"value\": \"updateE_Mail\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"roleType\": \"customer\"\n" + 
//			"    }\n" + 
//			"  ],\n" + 
//			"  \"orderItems\": [\n" + 
//			"    {\n" + 
//			"      \"action\": \"PORT\",\n" + 
//			"      \"id\": \"1\",\n" + 
//			"      \"quantity\": 0,\n" + 
//			"      \"product\": {\n" + 
//			"        \"productSerialNumber\": \"updateESN\",\n" + 
//			"        \"productCategory\": \"HANDSET\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"brand\": \"TOTAL_WIRELESS\"\n" + 
//			"        },\n" + 
//			"        \"supportingResources\": [\n" + 
//			"          {\n" + 
//			"            \"resourceType\": \"SIM_CARD\",\n" + 
//			"            \"serialNumber\": \"updateSIM\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"       \"orderItemExtension\": [\n" + 
//			"        {\n" + 
//			"          \"name\": \"groupIdentifier\",\n" + 
//			"          \"value\": \"updateGroupIdentifier\"\n" + 
//			"        },\n" + 
//			"      \n" + 
//			"        {\n" + 
//			"          \"name\": \"currentMin\",\n" + 
//			"          \"value\": \"updateMIN\"\n" + 
//			"        },\n" + 
//			"        {\n" + 
//			"          \"name\": \"currentAccountNumber\",\n" + 
//			"          \"value\": \"5678798\"\n" + 
//			"        },\n" + 
//			"        {\n" + 
//			"          \"name\": \"currentAccountSecret\",\n" + 
//			"          \"value\": \"236541\"\n" + 
//			"        },\n" + 
//			"        {\n" + 
//			"          \"name\": \"currentServiceProvider\",\n" + 
//			"          \"value\": \"VZW\"\n" + 
//			"        }\n" + 
//			"      ],\n" + 
//			"      \"customerAccount\": {\n" + 
//			"        \"customerAccountContact\": [\n" + 
//			"          {\n" + 
//			"            \"contactMedium\": {\n" + 
//			"              \"contactDetails\": [\n" + 
//			"                {\n" + 
//			"                  \"city\": \"Miami\",\n" + 
//			"                  \"postcode\": \"33178\",\n" + 
//			"                  \"stateOrProvince\": \"FL\",\n" + 
//			"                  \"street1\": \"3409 SW 113th TER\"\n" + 
//			"                }\n" + 
//			"              ],\n" + 
//			"              \"type\": \"postalAddress\"\n" + 
//			"            }\n" + 
//			"          },\n" + 
//			"          {\n" + 
//			"            \"contactDetails\": [\n" + 
//			"              {\n" + 
//			"                \"number\": \"3602589653\"\n" + 
//			"              }\n" + 
//			"            ],\n" + 
//			"            \"type\": \"telephoneNumber\"\n" + 
//			"          }\n" + 
//			"        ],\n" + 
//			"        \"customerAccountExtension\": [\n" + 
//			"          {\n" + 
//			"            \"name\": \"last4DigitsOfSSN\",\n" + 
//			"            \"value\": \"6541\"\n" + 
//			"          }\n" + 
//			"        ]\n" + 
//			"      },\n" + 
//			"      \"location\": {\n" + 
//			"        \"postalAddress\": {\n" + 
//			"          \"zipcode\": \"33178\"\n" + 
//			"        }\n" + 
//			"      },\n" + 
//			"      \"productOffering\": {\n" + 
//			"        \"id\": \"TWRTR00085\",\n" + 
//			"        \"productSpecification\": {\n" + 
//			"          \"id\": \"TWRTR00085\"\n" + 
//			"        }\n" + 
//			"      }\n" + 
//			"    }\n" + 
//			"  ]\n" + 
//			"}";
////	#extID-updateDate-updateESN-updateSIM-updateE_Mail
//	String stageActivation2 = "{\n" + "  \"externalID\": \"UUID-extID\",\n" + "  \"orderDate\": \"updateDate\",\n"
//			+ "  \"relatedParties\": [\n" + "    {\n" + "      \"party\": {\n" + "        \"partyID\": \"T-CETRA\",\n"
//			+ "        \"languageAbility\": \"ENG\",\n" + "        \"partyExtension\": [\n" + "          {\n"
//			+ "            \"name\": \"partyTransactionID\",\n"
//			+ "            \"value\": \"5f7aa212-73b2-4f82-8b37-extID\"\n" + "          },\n" + "          {\n"
//			+ "            \"name\": \"sourceSystem\",\n" + "            \"value\": \"RTR\"\n" + "          }\n"
//			+ "        ]\n" + "      },\n" + "      \"roleType\": \"partner\"\n" + "    },\n" + "    {\n"
//			+ "      \"party\": {\n" + "        \"individual\": {\n" + "         \n"
//			+ "          \"firstName\": \"Mathew\",\n" + "          \"lastName\": \"Lee\"\n" + "        },\n"
//			+ "        \"partyExtension\": [\n" + "          {\n" + "            \"name\": \"accountEmail\",\n"
//			+ "            \"value\": \"updateE_Mail\"\n" + "          }\n" + "        ]\n" + "      },\n"
//			+ "      \"roleType\": \"customer\"\n" + "    }\n" + "  ],\n" + "  \"orderItems\": [\n" + "    {\n"
//			+ "      \"action\": \"updateAction\",\n" + "      \"id\": \"1\",\n" + "      \"quantity\": 0,\n"
//			+ "      \"product\": {\n" + "        \"productSerialNumber\": \"updateESN\",\n"
//			+ "        \"productCategory\": \"HANDSET\",\n" + "        \"productSpecification\": {\n"
//			+ "          \"brand\": \"TOTAL_WIRELESS\"\n" + "        },\n" + "        \"supportingResources\": [\n"
//			+ "          {\n" + "            \"resourceType\": \"SIM_CARD\",\n"
//			+ "            \"serialNumber\": \"updateSIM\"\n" + "          }\n" + "        ]\n" + "      },\n"
//			+ "      \"orderItemExtension\": [\n" + "        {\n" + "          \"name\": \"groupIdentifier\",\n"
//			+ "          \"value\": \"updateGroupIdentifier\"\n" + "        }\n" + "      ],\n"
//			+ "      \"location\": {\n" + "        \"postalAddress\": {\n" + "          \"zipcode\": \"33178\"\n"
//			+ "        }\n" + "      }\n" + "    }\n" + "  ]\n" + "}";
//
//	String stagePortOrder = "{\n" + "  \"externalID\": \"UUID-extID\",\n" + "  \"orderDate\": \"updateDate\",\n"
//			+ "  \"relatedParties\": [\n" + "    {\n" + "      \"party\": {\n" + "        \"partyID\": \"VENDOR_ID\",\n"
//			+ "        \"languageAbility\": \"ENG\",\n" + "        \"partyExtension\": [\n" + "          {\n"
//			+ "            \"name\": \"partyTransactionID\",\n" + "            \"value\": \"partyid-UUID\"\n"
//			+ "          },\n" + "          {\n" + "            \"name\": \"sourceSystem\",\n"
//			+ "            \"value\": \"RTR\"\n" + "          }\n" + "        ]\n" + "      },\n"
//			+ "      \"roleType\": \"partner\"\n" + "    },\n" + "    {\n" + "      \"party\": {\n"
//			+ "        \"individual\": {\n" + "          \n" + "          \"firstName\": \"Mathew\",\n"
//			+ "          \"lastName\": \"Lee\"\n" + "        }\n" + "        \n" + "      },\n"
//			+ "      \"roleType\": \"customer\"\n" + "    }\n" + "  ],\n" + "  \"orderItems\": [\n" + "    {\n"
//			+ "      \"action\": \"PORT\",\n" + "      \"id\": \"1\",\n" + "      \"quantity\": 0,\n"
//			+ "      \"product\": {\n" + "        \"productSerialNumber\": \"updateESN\",\n"
//			+ "        \"productCategory\": \"HANDSET\",\n" + "        \"productSpecification\": {\n"
//			+ "          \"brand\": \"TOTAL_WIRELESS\"\n" + "        },\n" + "        \"supportingResources\": [\n"
//			+ "          {\n" + "            \"resourceType\": \"SIM_CARD\",\n"
//			+ "            \"serialNumber\": \"updateSIM\"\n" + "          }\n" + "        ]\n" + "      },\n"
//			+ "      \"orderItemExtension\": [\n" + "        {\n" + "          \"name\": \"currentMin\",\n"
//			+ "          \"value\": \"updateMIN\"\n" + "        },\n" + "        {\n"
//			+ "          \"name\": \"currentAccountNumber\",\n" + "          \"value\": \"5678798\"\n" + "        },\n"
//			+ "        {\n" + "          \"name\": \"currentAccountSecret\",\n" + "          \"value\": \"236541\"\n"
//			+ "        },\n" + "        {\n" + "          \"name\": \"currentServiceProvider\",\n"
//			+ "          \"value\": \"VZW\"\n" + "        }\n" + "      ],\n" + "      \"customerAccount\": {\n"
//			+ "        \"customerAccountContact\": [\n" + "          {\n" + "            \"contactMedium\": {\n"
//			+ "              \"contactDetails\": [\n" + "                {\n"
//			+ "                  \"city\": \"Miami\",\n" + "                  \"postcode\": \"33178\",\n"
//			+ "                  \"stateOrProvince\": \"FL\",\n"
//			+ "                  \"street1\": \"3409 SW 113th TER\"\n" + "                }\n" + "              ],\n"
//			+ "              \"type\": \"postalAddress\"\n" + "            }\n" + "          },\n" + "          {\n"
//			+ "            \"contactDetails\": [\n" + "              {\n"
//			+ "                \"number\": \"3602589653\"\n" + "              }\n" + "            ],\n"
//			+ "            \"type\": \"telephoneNumber\"\n" + "          }\n" + "        ],\n"
//			+ "        \"customerAccountExtension\": [\n" + "          {\n"
//			+ "            \"name\": \"last4DigitsOfSSN\",\n" + "            \"value\": \"6541\"\n" + "          }\n"
//			+ "        ]\n" + "      },\n" + "      \"location\": {\n" + "        \"postalAddress\": {\n"
//			+ "          \"zipcode\": \"33178\"\n" + "        }\n" + "      }\n" + "    }\n" + "  ]\n" + "}";
//
//	String newSubmitAddtoReserve = "{\n" + 
//			"    \"externalID\": \"UMESH-400-extID-449\",\n" + 
//			"    \"orderDate\": \"updateDate\",\n" + 
//			"    \"relatedParties\": [\n" + 
//			"        {\n" + 
//			"            \"party\": { \n" + 
//			"                \"partyID\": \"IAS\",\n" + 
//			"                \"languageAbility\": \"ENG\",\n" + 
//			"                \"partyExtension\": [\n" + 
//			"                    {\n" + 
//			"                        \"name\": \"vendorName\",\n" + 
//			"                        \"value\": \"IAS\"\n" + 
//			"                    },\n" + 
//			"                    {\n" + 
//			"                        \"name\": \"vendorStore\",\n" + 
//			"                        \"value\": \"1234\"\n" + 
//			"                    },\n" + 
//			"                    {\n" + 
//			"                        \"name\": \"vendorTerminal\",\n" + 
//			"                        \"value\": \"4321\"\n" + 
//			"                    },\n" + 
//			"                    {\n" + 
//			"                        \"name\": \"sourceSystem\",\n" + 
//			"                        \"value\": \"RTR\"\n" + 
//			"                    }\n" + 
//			"                ]\n" + 
//			"            },\n" + 
//			"            \"roleType\": \"partner\"\n" + 
//			"        }\n" + 
//			"    ],\n" + 
//			"    \"orderItems\": [\n" + 
//			"        {\n" + 
//			"            \"id\": \"1\",\n" + 
//			"            \"quantity\": 1,\n" + 
//			"            \"action\": \"ACTIVATION\",\n" + 
//			"            \"product\": {\n" + 
//			"                \"productSerialNumber\": \"updateESN\",\n" + 
//			"                \"productCategory\": \"HANDSET\",\n" + 
//			"                \"resourceSpecification\": {\n" + 
//			"                    \"brand\": \"TOTAL_WIRELESS\"\n" + 
//			"                },\n" + 
//			"                \"supportingResources\": [\n" + 
//			"                    {\n" + 
//			"                        \"resourceCategory\": \"SIM_CARD\",\n" + 
//			"                        \"serialNumber\": \"updateSIM\"\n" + 
//			"                    }\n" + 
//			"                ],\n" + 
//			"                \"relatedServices\": [\n" + 
//			"                    {\n" + 
//			"                        \"id\": \"1\",\n" + 
//			"                        \"category\": \"SERVICE_PLAN\",\n" + 
//			"                        \"isRedeemNow\": false,\n" + 
//			"                        \"isRecurring\": false\n" + 
//			"                    }\n" + 
//			"                ]\n" + 
//			"            },\n" + 
//			"            \"location\": {\n" + 
//			"                \"postalAddress\": {\n" + 
//			"                    \"zipcode\": \"95815\"\n" + 
//			"                }\n" + 
//			"            },\n" + 
//			"            \"productOffering\": {\n" + 
//			"                \"id\": \"updatePINPART\",\n" + 
//			"                \"productSpecification\": {\n" + 
//			"                    \"id\": \"updatePINPART\"\n" + 
//			"                }\n" + 
//			"            }\n" + 
//			"        }\n" + 
//			"    ]\n" + 
//			"}";
//	
//	
//	String submitOrder1 = "{\n" + "  \"externalID\": \"UUID-extID\",\n" + "  \"orderDate\": \"updateDate\",\n"
//			+ "  \"relatedParties\": [\n" + "    {\n" + "      \"party\": {\n" + "        \"partyID\": \"T-CETRA\",\n"
//			+ "        \"languageAbility\": \"ENG\",\n" + "        \"partyExtension\": [\n" + "          {\n"
//			+ "            \"name\": \"vendorName\",\n" + "            \"value\": \"T-CETRA\"\n" + "          },\n"
//			+ "          {\n" + "            \"name\": \"vendorStore\",\n" + "            \"value\": \"1234\"\n"
//			+ "          },\n" + "          {\n" + "            \"name\": \"vendorTerminal\",\n"
//			+ "            \"value\": \"1234\"\n" + "          },\n" + "          {\n"
//			+ "            \"name\": \"sourceSystem\",\n" + "            \"value\": \"RTR\"\n" + "          }\n"
//			+ "        ]\n" + "      },\n" + "      \"roleType\": \"partner\"\n" + "    },\n" + "    {\n"
//			+ "      \"party\": {\n" + "        \"individual\": {\n" + "          \n"
//			+ "          \"firstName\": \"Umesh\",\n" + "          \"lastName\": \"Havinal\"\n" + "        },\n"
//			+ "        \"partyExtension\": [\n" + "          \n" + "        ]\n" + "      },\n"
//			+ "      \"roleType\": \"customer\"\n" + "    }\n" + "  ],\n" + "  \"orderItems\": [\n" + "    {\n"
//			+ "      \"action\": \"PROCESS\",\n" + "      \"id\": \"1\",\n" + "      \"quantity\": 1,\n"
//			+ "      \"product\": {\n" + "        \"productSpecification\": {\n"
//			+ "          \"brand\": \"TOTAL_WIRELESS\"\n" + "        }\n" + "      },\n"
//			+ "      \"orderItemExtension\": [\n" + "        {\n" + "          \"name\": \"planName\",\n"
//			+ "          \"value\": \"$100, 100GB Shared Plan, 4 Lines\"\n" + "        },\n" + "        {\n"
//			+ "          \"name\": \"groupName\",\n" + "          \"value\": \"GROUP 1\"\n" + "        },\n"
//			+ "        {\n" + "          \"name\": \"groupIdentifier\",\n"
//			+ "          \"value\": \"updateGroupIdentifier\"\n" + "        }\n" + "      ]\n" + "    }\n" + "  ]\n"
//			+ "}";
//
//	String submitOrder = "{\n" + "  \"externalID\": \"UUID-extID\",\n" + "  \"orderDate\": \"updateDate\",\n"
//			+ "  \"relatedParties\": [\n" + "    {\n" + "      \"party\": {\n" + "        \"partyID\": \"IAS\",\n"
//			+ "        \"languageAbility\": \"ENG\",\n" + "        \"partyExtension\": [\n" + "          {\n"
//			+ "            \"name\": \"vendorName\",\n" + "            \"value\": \"T-CETRA\"\n" + "          },\n"
//			+ "          {\n" + "            \"name\": \"vendorStore\",\n" + "            \"value\": \"1234\"\n"
//			+ "          },\n" + "          {\n" + "            \"name\": \"vendorTerminal\",\n"
//			+ "            \"value\": \"1234\"\n" + "          },\n" + "          {\n"
//			+ "            \"name\": \"sourceSystem\",\n" + "            \"value\": \"RTR\"\n" + "          }\n"
//			+ "        ]\n" + "      },\n" + "      \"roleType\": \"partner\"\n" + "    },\n" + "    {\n"
//			+ "      \"party\": {\n" + "        \"individual\": {\n" + "          \n"
//			+ "          \"firstName\": \"tracfone\",\n" + "          \"lastName\": \"qa team\"\n" + "        },\n"
//			+ "        \"partyExtension\": [\n" + "          {\n" + "            \"name\": \"accountEmail\",\n"
//			+ "            \"value\": \"updateE_Mail\"\n" + "          }\n" + "        ]\n" + "      },\n"
//			+ "      \"roleType\": \"customer\"\n" + "    }\n" + "  ],\n" + "  \"orderItems\": [\n" + "    {\n"
//			+ "      \"action\": \"PROCESS\",\n" + "      \"id\": \"1\",\n" + "      \"quantity\": 1,\n"
//			+ "      \"product\": {\n" + "        \"productSpecification\": {\n"
//			+ "          \"brand\": \"TOTAL_WIRELESS\"\n" + "        }\n" + "      },\n"
//			+ "      \"orderItemExtension\": [\n" + "        {\n" + "          \"name\": \"planName\",\n"
//			+ "          \"value\": \"$100, 100GB Shared Plan, 4 Lines\"\n" + "        },\n" + "        {\n"
//			+ "          \"name\": \"groupName\",\n" + "          \"value\": \"GROUP 1\"\n" + "        },\n"
//			+ "        {\n" + "          \"name\": \"groupIdentifier\",\n"
//			+ "          \"value\": \"updateGroupIdentifier\"\n" + "        }\n" + "      ]\n" + "    }\n" + "  ]\n"
//			+ "}";
//
////	String submitStagingActivation = "{\n" + "  \"externalID\": \"extID\",\n" + "  \"orderDate\": \"updateDate\",\n"
////			+ "  \"relatedParties\": [\n" + "    {\n" + "      \"party\": {\n" + "        \"partyID\": \"T-CETRA\",\n"
////			+ "        \"languageAbility\": \"ENG\",\n" + "        \"partyExtension\": [\n" + "          {\n"
////			+ "            \"name\": \"vendorName\",\n" + "            \"value\": \"T-CETRA\"\n" + "          },\n"
////			+ "          {\n" + "            \"name\": \"vendorStore\",\n" + "            \"value\": \"1234\"\n"
////			+ "          },\n" + "          {\n" + "            \"name\": \"vendorTerminal\",\n"
////			+ "            \"value\": \"1234\"\n" + "          },\n" + "          {\n"
////			+ "            \"name\": \"sourceSystem\",\n" + "            \"value\": \"RTR\"\n" + "          }\n"
////			+ "        ]\n" + "      },\n" + "      \"roleType\": \"partner\"\n" + "    },\n" + "    {\n"
////			+ "      \"party\": {\n" + "        \"individual\": {\n" + "         \n"
////			+ "          \"firstName\": \"Ramazan\",\n" + "          \"lastName\": \"Automation\"\n" + "        }\n"
////			+ "       \n" + "      },\n" + "      \"roleType\": \"customer\"\n" + "    }\n" + "  ],\n"
////			+ "  \"orderItems\": [\n" + "    {\n" + "      \"action\": \"PROCESS\",\n" + "      \"id\": \"1\",\n"
////			+ "      \"quantity\": 1,\n" + "      \"product\": {\n" + "        \"productSpecification\": {\n"
////			+ "          \"brand\": \"TOTAL_WIRELESS\"\n" + "        }\n" + "      },\n"
////			+ "      \"orderItemExtension\": [\n" + "        {\n" + "          \"name\": \"planName\",\n"
////			+ "          \"value\": \"$100, 100GB Shared Plan, 4 Lines\"\n" + "        },\n" + "        {\n"
////			+ "          \"name\": \"groupName\",\n" + "          \"value\": \"GROUP 1\"\n" + "        },\n"
////			+ "        {\n" + "          \"name\": \"groupIdentifier\",\n"
////			+ "          \"value\": \"updateGroupIdentifier\"\n" + "        }\n" + "      ]\n" + "    }\n" + "  ]\n"
////			+ "}";

}
