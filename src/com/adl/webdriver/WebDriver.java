package com.adl.webdriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.adl.constant.ConnectionConstants;

public class WebDriver implements ConnectionConstants {
	public static ChromeDriver driver;

	public static void setUp() throws Exception {
		ChromeOptions options = new ChromeOptions();
		//chromeのパスを指定
		options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		options.addArguments("--proxy-server=" + PROXY_SERVER + ":" + PROXY_PORT);

		//chrome driverの指定
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");

		driver = new ChromeDriver(options);
	}

	public static void tearDown() throws Exception {
		driver.quit();
	}

	public static void test() {
		driver.get(ROOT_URL);

//		driver.findElement(By.id("nameInputId")).clear();
//		driver.findElement(By.id("nameInputId")).sendKeys("セレニウム男");
//		driver.findElement(By.name("sexName")).click();
//		new Select(driver.findElement(By.id("jobSelectId")))
//				.selectByVisibleText("その他");
//		driver.findElement(By.cssSelector("button")).click();
	}

	public static String getSrc() {
		driver.get(ROOT_URL);
		return driver.getPageSource();
	}
}
