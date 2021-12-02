package com.regularJavaTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SignUpCheckout {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "Util\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		driver.get("http://automationpractice.com/index.php");

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.linkText("WOMEN"))).build().perform();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@title='Summer Dresses']")).click();

		JavascriptExecutor js = ((JavascriptExecutor) driver);

		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img")).click();
		Thread.sleep(3000);
		try {
			driver.switchTo().frame(0);
			driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button")).click();  //Add To Cart		
			Thread.sleep(7000);
			driver.switchTo().parentFrame();
		}catch(Exception e) {
			driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button")).click();  //Add To Cart
		}
		
//		driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/span")).click(); // pencereyi kapatmak icin.
		driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click(); // proceed to checkout
//		driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span")).click(); // continue Shopping
		Thread.sleep(7000);
		
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]")).click();
		Thread.sleep(7000);
		driver.findElement(By.id("email_create")).sendKeys(System.currentTimeMillis()+"@testuser.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]")).click();
		Thread.sleep(7000);
		driver.findElement(By.id("id_gender1")).click();//Mr
//		driver.findElement(By.id("id_gender1")).click();//Ms
		Thread.sleep(7000);
		driver.findElement(By.id("customer_firstname")).sendKeys("Test Name");
		Thread.sleep(3000);
		driver.findElement(By.id("customer_lastname")).sendKeys("Last Name");
		Thread.sleep(3000);
		driver.findElement(By.id("passwd")).sendKeys("123456");
		Thread.sleep(3000);
		driver.findElement(By.id("address1")).sendKeys("Test Adresi1");
		Thread.sleep(3000);
		driver.findElement(By.id("city")).sendKeys("Bursa");
		Thread.sleep(3000);
		
		Select stateBox = new Select(driver.findElement(By.id("id_state")));
		stateBox.selectByVisibleText("Florida");
		System.out.println("Secilen State : " + stateBox.getFirstSelectedOption().getText());		
		Thread.sleep(3000);
		
		driver.findElement(By.id("postcode")).sendKeys("33496");
		Thread.sleep(3000);
		
		driver.findElement(By.id("phone_mobile")).sendKeys("6545619605");
		Thread.sleep(3000);
		
		driver.findElement(By.id("submitAccount")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.name("processAddress")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.id("cgv")).click();
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
			
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id=\"form\"]/p/button")));  	
		Thread.sleep(5000);
		
//		driver.findElement(By.className("bankwire")).click();
//		Thread.sleep(3000);
		
		driver.findElement(By.className("cheque")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();
		Thread.sleep(7000);
		
		String pageText = js.executeScript("return document.documentElement.innerText;").toString();
		System.out.println(pageText);
		
		if(pageText.contains("Your order on My Store is complete.")) {
			System.out.println("Order test is successful");
		} else {
			System.out.println("Order test is NOT successful");
		}
		
		System.out.println("Automation is completed...");

		driver.close();

	}

}
