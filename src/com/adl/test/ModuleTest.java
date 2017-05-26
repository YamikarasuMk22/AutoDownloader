package com.adl.test;

import java.io.IOException;

import com.adl.constant.ConnectionConstants;
import com.adl.srcreader.ProxyConfig;
import com.adl.webdriver.WDProcessor;

public class ModuleTest implements ConnectionConstants {

	public static void main(String[] args) throws IOException {
		ProxyConfig.proxyAccess(true, false);

		WDProcessor.processGallery();

//		try {
//			WDProcessor.createWebDriver();
//			WDProcessor.processDownload("", "", 1);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		String src = WebDriver.getSrc(DOWNLOAD_ROOT_URL);
//
//		List<String> URLList = new ArrayList<String>();
//		URLList = SrcFormater.getGalleryURLList(src);
//
//		Util.systemLoger("[Gallery] GalleryURLListSize:" + URLList.size());
//
//		for(int i = 0; i < URLList.size(); i++) {
//			Util.systemLoger("[Gallery] URL:" + URLList.get(i));
//			Util.systemLoger("Start Process [Content] " + (i+1) + "/" + URLList.size() + "-----");
//		}
	}
}
