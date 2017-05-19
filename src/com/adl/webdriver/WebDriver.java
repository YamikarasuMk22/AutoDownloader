package com.adl.webdriver;

import java.util.Hashtable;
import java.util.Map;

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
		//chromeのパスを指定
		options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		options.addArguments("--proxy-server=" + PROXY_SERVER + ":" + PROXY_PORT);

		Map<String, Object> prefs = new Hashtable<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.prompt_for_download", "false");
		prefs.put("download.default_directory", DOWNLOAD_ROOT_FOLDER);

		options.setExperimentalOption("chrome.prefs", prefs);

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		driver = new ChromeDriver(options);
	}

	public static void quitWebDriver() throws Exception {
		driver.quit();
	}

	public static void init() {
		WDProcessor.processGallery();
	}

	public static String getSrc(String url) {
		driver.get(url);
		return driver.getPageSource();
	}
}
