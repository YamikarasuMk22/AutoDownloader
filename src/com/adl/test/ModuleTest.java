package com.adl.test;

import java.io.IOException;

import com.adl.srcreader.ProxyConfig;
import com.adl.webdriver.WebDriver;

public class ModuleTest {

	public static void main(String[] args) throws IOException {
		ProxyConfig.proxyAccess(true, false);

//		URL url = new URL("http://placehold.jp/570x90.png");
//		URLConnection conn = url.openConnection();
//		String contentType = DownloadChecker.getImgFileExtention(conn);

//		System.out.println(contentType);

		try {
			WebDriver.setUp();
			WebDriver.test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
