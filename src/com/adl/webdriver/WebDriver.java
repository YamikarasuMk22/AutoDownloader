package com.adl.webdriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriver {
	public static ChromeDriver driver;
	public static String baseUrl;

	public static void setUp() throws Exception {
		ChromeOptions options = new ChromeOptions();
		//chromeのパスを指定
		options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		options.addArguments("--proxy-server=tk1py02a.jbcc.co.jp:8080");

		//chrome driverの指定
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");

		driver = new ChromeDriver(options);
		baseUrl = "https://ja.wikipedia.org/wiki/%E3%83%A1%E3%82%A4%E3%83%B3%E3%83%9A%E3%83%BC%E3%82%B8";
	}

	public static void tearDown() throws Exception {
		driver.quit();
	}

	public static void test() {
		driver.get(baseUrl);
//		driver.findElement(By.id("nameInputId")).clear();
//		driver.findElement(By.id("nameInputId")).sendKeys("セレニウム男");
//		driver.findElement(By.name("sexName")).click();
//		new Select(driver.findElement(By.id("jobSelectId")))
//				.selectByVisibleText("その他");
//		driver.findElement(By.cssSelector("button")).click();
	}
}
