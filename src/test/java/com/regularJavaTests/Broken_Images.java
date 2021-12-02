package com.regularJavaTests;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Broken_Images {

	static int i = 0;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "Util\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		driver.get("http://the-internet.herokuapp.com/broken_images");

//		List<WebElement> images = driver.findElements(By.tagName("img")); //sayfadaki tum resimler
		List<WebElement> images=driver.findElements(By.xpath("//div[@class='example']/img")); //Broken images alanindaki resimler

		System.out.println("Total Images are " + images.size());

		for (WebElement obj : images) {
			String url = obj.getAttribute("src");
			verifyElementActive(url);
			i++;
		}
		driver.close();
	}

	public static void verifyElementActive(String elementUrl) {
		try {
			URL url = new URL(elementUrl);

			HttpURLConnection baglanti = (HttpURLConnection) url.openConnection();

			baglanti.setConnectTimeout(3000);

			baglanti.connect();

			if (baglanti.getResponseCode() == 200) {
				System.out.println(i + " " + elementUrl + " - " + baglanti.getResponseMessage());

			}

			if (baglanti.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(i + " " + elementUrl + " - " + baglanti.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);

			}
		} catch (Exception e) {

		}
	}

}