package com.adl.webdriver;

import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.adl.constant.ConnectionConstants;

public class WebDriver implements ConnectionConstants {
	public static ChromeDriver driver;

	public static void createWebDriver() throws Exception {
		//chrome driverの指定
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		//chromeのパスを指定
		options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

		//proxy serverの指定
		options.addArguments("--proxy-server=" + PROXY_SERVER + ":" + PROXY_PORT);
		//Proxy proxy = new Proxy();
		//proxy.setHttpProxy(PROXY_SERVER + ":" + PROXY_PORT);
		//capabilities.setCapability("proxy", proxy);

		Map<String, Object> prefs = new Hashtable<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.prompt_for_download", "false");
		prefs.put("download.default_directory", DOWNLOAD_ROOT_FOLDER);

		capabilities.setCapability("chrome.prefs", prefs);

		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		ChromeDriver localDriver = new ChromeDriver(capabilities);
		driver = localDriver;

		driver.manage().window().setSize(new Dimension(100, 50));
	}

	public static void deleteWebDriver() throws Exception {
		driver.quit();
	}

	public static String openWebSite(String url) {
		String srcStr = "";

		try {
			createWebDriver();

			driver.get(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return srcStr;
	}

	public static void closeWebSite() throws Exception {
		driver.quit();
	}

	public static String getSrc(String url) {
		String srcStr = "";

		try {
			createWebDriver();

			driver.get(url);
			srcStr = driver.getPageSource();

			deleteWebDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return srcStr;
	}
}
