package com.mertyazilim.pages;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.mertyazilim.util.Screenshots;

public class MertPage extends PageBase {

	public MertPage(WebDriver driver) {
		super(driver);
	}

	public MertPage menuAndListView(int jobNo, String date, ExtentTest extentTest)
			throws IOException, InterruptedException {
		extentTest.log(LogStatus.INFO, "Ana Sayfaya girildi");
		extentTest.log(LogStatus.INFO, "Title is : " + driver.getTitle());

		String screenshotPath = Screenshots.getScreenshot(driver, "Ana Sayfa", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		waitForNextElement(1);

		Actions action = new Actions(driver);
		js.drawBorder(womenMenu, driver);
		action.moveToElement(womenMenu).build().perform();
		waitForNextElement(1);

		extentTest.log(LogStatus.INFO, "Women menusune uzerine gidildi");
		screenshotPath = Screenshots.getScreenshot(driver, "Women Sayfa", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));

		js.drawBorder(altMenu, driver);
		extentTest.log(LogStatus.INFO, "Alt Menuye girildi");
		screenshotPath = Screenshots.getScreenshot(driver, "Alt Menu", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		altMenu.click();
		waitForNextElement(1);

		js.scrollDown200(driver);

		waitForNextElement(1);

		js.drawBorder(listView, driver);
		extentTest.log(LogStatus.INFO, "List view secildi");
		screenshotPath = Screenshots.getScreenshot(driver, "List View", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		listView.click();
		waitForNextElement(1);

		for (int i = 0; i < 4; i++) {
			js.scrollDown200(driver);
			waitForNextElement(1);
			screenshotPath = Screenshots.getScreenshot(driver, "List View", jobNo, date);
			extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		}
		js.drawBorder(followUs, driver);
		extentTest.log(LogStatus.INFO, "Sayfa Altina inildi");
		js.scrollIntoView(followUs, driver);
		screenshotPath = Screenshots.getScreenshot(driver, "Sayfa Alti", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		waitForNextElement(1);

		return new MertPage(driver);
	}

	public MertPage altMenu(int jobNo, String date, ExtentTest extentTest) throws IOException, InterruptedException {
		extentTest.log(LogStatus.INFO, "Ana Sayfaya girildi");
		extentTest.log(LogStatus.INFO, "Title is : " + driver.getTitle());

		String screenshotPath = Screenshots.getScreenshot(driver, "Ana Sayfa", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		waitForNextElement(1);

		Actions action = new Actions(driver);
		js.drawBorder(womenMenu, driver);
		action.moveToElement(womenMenu).build().perform();
		waitForNextElement(1);

		extentTest.log(LogStatus.INFO, "Women menusune uzerine gidildi");
		screenshotPath = Screenshots.getScreenshot(driver, "Women Sayfa", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));

		js.drawBorder(altMenu, driver);
		extentTest.log(LogStatus.INFO, "Alt Menuye girildi");
		screenshotPath = Screenshots.getScreenshot(driver, "Alt Menu", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		altMenu.click();
		waitForNextElement(1);
		js.scrollDown200(driver);

		return new MertPage(driver);
	}


	public MertPage setSortOrder(int jobNo, String date, ExtentTest extentTest,String sortOrder) throws IOException, InterruptedException {

		Select filtreBox = new Select(filterBox);//Bu test aslinda FAIL cunku filtreleme gerceklesmiyor.
		filtreBox.selectByVisibleText(sortOrder);
		System.out.println("Secili Filtre : " + filtreBox.getFirstSelectedOption().getText());
		screenshotPath = Screenshots.getScreenshot(driver, "Sayfa Alti", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		waitForNextElement(1);
		
		return new MertPage(driver);
	}

	public String getSortOrder() {
		Select filtreBox = new Select(filterBox);
		return filtreBox.getFirstSelectedOption().getText();
	}
	
	public MertPage listView(int jobNo, String date, ExtentTest extentTest) throws IOException, InterruptedException {
		waitForNextElement(1);
		js.drawBorder(listView, driver);
		extentTest.log(LogStatus.INFO, "List view secildi");
		screenshotPath = Screenshots.getScreenshot(driver, "List View", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		listView.click();
		waitForNextElement(1);

		for (int i = 0; i < 4; i++) {
			js.scrollDown200(driver);
			waitForNextElement(1);
			screenshotPath = Screenshots.getScreenshot(driver, "List View", jobNo, date);
			extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		}
		js.drawBorder(followUs, driver);
		extentTest.log(LogStatus.INFO, "Sayfa Altina inildi");
		js.scrollIntoView(followUs, driver);
		screenshotPath = Screenshots.getScreenshot(driver, "Sayfa Alti", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		waitForNextElement(1);

		return new MertPage(driver);
	}

	public MertPage addToCart(int jobNo, String date, ExtentTest extentTest) throws IOException, InterruptedException {
		js.scrollDown200(driver);
		waitForNextElement(1);
		js.scrollDown200(driver);
		js.scrollDown200(driver);
		waitForNextElement(1);
		js.drawBorder(product, driver);
		screenshotPath = Screenshots.getScreenshot(driver, "product", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		product.click();

		waitForNextElement(1);
		try {
			driver.switchTo().frame(0);
			js.drawBorder(addToCart, driver);
			screenshotPath = Screenshots.getScreenshot(driver, "addToCart", jobNo, date);
			extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
			addToCart.click();
			waitForNextElement(1);
			driver.switchTo().parentFrame();
		} catch (Exception e) {
			js.drawBorder(addToCart, driver);
			screenshotPath = Screenshots.getScreenshot(driver, "addToCart", jobNo, date);
			extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
			addToCart.click();
		}

//		closedWindow.click(); // pencereyi kapatmak icin.
		js.drawBorder(proceedToCheckout, driver);
		js.scrollDown200(driver);
		js.scrollDown200(driver);
		js.scrollDown200(driver);
		screenshotPath = Screenshots.getScreenshot(driver, "proceedToCheckout", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		proceedToCheckout.click(); // proceed to checkout
//		continueShopping.click(); // continue Shopping

		js.scrollDown200(driver);
		js.scrollDown200(driver);
		waitForNextElement(1);

		js.drawBorder(lastProceedToCheckout, driver);
		screenshotPath = Screenshots.getScreenshot(driver, "lastProceedToCheckout", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		lastProceedToCheckout.click();
		js.scrollDown200(driver);
		js.scrollDown200(driver);
		waitForNextElement(1);

		System.out.println("Add to Cart completed");

		return new MertPage(driver);
	}

	public MertPage signUp(int jobNo, String date, ExtentTest extentTest) throws IOException, InterruptedException {

		waitForNextElement(2);
		email_create.sendKeys(System.currentTimeMillis() + "@testuser.com");
		waitForNextElement(1);
		emailSubmit.click();
		waitForNextElement(2);
		Mr.click();// Mr
//		Mrs.click();//Ms
		waitForNextElement(1);
		customer_firstname.sendKeys("Test Name");
		waitForNextElement(1);
		customer_lastname.sendKeys("Last Name");
		waitForNextElement(1);
		driver.findElement(By.id("passwd")).sendKeys("123456");
		waitForNextElement(1);
		address1.sendKeys("Test Adresi1");
		waitForNextElement(1);
		city.sendKeys("Bursa");
		waitForNextElement(1);

		Select stateBox = new Select(id_state);
		stateBox.selectByVisibleText("Florida");
		System.out.println("Secilen State : " + stateBox.getFirstSelectedOption().getText());
		waitForNextElement(1);

		postcode.sendKeys("33496");
		waitForNextElement(1);

		phone_mobile.sendKeys("6545619605");
		waitForNextElement(1);

		register.click();// register button
		js.scrollDown200(driver);
		js.scrollDown200(driver);
		waitForNextElement(1);

		return new MertPage(driver);
	}

	public MertPage signIN(int jobNo, String date, ExtentTest extentTest) throws IOException, InterruptedException {
		
		waitForNextElement(1);
		js.drawBorder(username, driver);
		username.sendKeys("test@testuser.com");
		js.drawBorder(password, driver);
		password.sendKeys("123456");
		js.drawBorder(login, driver);
		screenshotPath = Screenshots.getScreenshot(driver, "lastButton", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		waitForNextElement(1);
		login.click();
		waitForNextElement(1);

		return new MertPage(driver);
	}

	public MertPage checkout(int jobNo, String date, ExtentTest extentTest) throws IOException, InterruptedException {

		js.scrollDown200(driver);
		js.drawBorder(processAddress, driver);
		screenshotPath = Screenshots.getScreenshot(driver, "lastButton", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		processAddress.click();
		waitForNextElement(1);
		js.drawBorder(cgv, driver);
		cgv.click();//Agree Button
		Thread.sleep(2000);
		js.scrollDown200(driver);
		waitForNextElement(1);
		js.drawBorder(lastButton, driver);
		screenshotPath = Screenshots.getScreenshot(driver, "lastButton", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		js.clickElementByJS(lastButton, driver);

		waitForNextElement(1);

//		bankwire.click();	
		js.scrollDown200(driver);
		js.scrollDown200(driver);
		js.drawBorder(cheque, driver);
		screenshotPath = Screenshots.getScreenshot(driver, "cheque", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		cheque.click();
		waitForNextElement(1);

		js.drawBorder(confirmButton, driver);
		screenshotPath = Screenshots.getScreenshot(driver, "confirmButton", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		js.scrollDown200(driver);
		js.scrollDown200(driver);
		confirmButton.click();
		waitForNextElement(1);

		js.scrollDown200(driver);
		screenshotPath = Screenshots.getScreenshot(driver, "validatePage", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));

		return new MertPage(driver);
	}


	public boolean assertCheckout() throws IOException, InterruptedException {
		
		String pageText = js.getPageInnerText(driver).toString();
		System.out.println(pageText);

		if (pageText.contains("Your order on My Store is complete.")) {
			System.out.println("Order test is successful");
			checkAssert=true;
		} else {
			System.out.println("Order test is NOT successful");
			checkAssert=false;
		}

		System.out.println("Automation is completed...");
		return checkAssert;
	}


	
}
