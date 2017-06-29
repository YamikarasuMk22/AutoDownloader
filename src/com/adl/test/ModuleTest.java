package com.adl.test;

import java.io.IOException;
import java.util.List;

import com.adl.constant.ConnectionConstants;
import com.adl.srcreader.ProxyConfig;
import com.adl.xml.XMLReader;

public class ModuleTest implements ConnectionConstants {

	public static void main(String[] args) throws IOException {
		ProxyConfig.proxyAccess(true, false);

		List<String> list = XMLReader.readComicXMLParams("id");

		for(int i=0; i<list.size(); i++) {
			System.out.println(i + ":" + list.get(i));
		}


//		WDProcessor.processGallery();
//
//		try {
//			WDProcessor.createWebDriver();
//			WDProcessor.processDownloadImg("1068078" ,"1068078", "png", 2);
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
