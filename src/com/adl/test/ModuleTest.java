package com.adl.test;

import java.net.MalformedURLException;
import java.net.URL;

import com.adl.downloader.DownloadChecker;
import com.adl.srcreader.ProxyConfig;

public class ModuleTest {

	public static void main(String[] args) throws MalformedURLException {
		ProxyConfig.proxyAccess(true, false);

		URL url = new URL("http://placehold.jp/570x90.png");
		String contentType = DownloadChecker.getImgFileExtention(url);

		System.out.println(contentType);

	}
}
