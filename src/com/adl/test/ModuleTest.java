package com.adl.test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import com.adl.downloader.DownloadChecker;
import com.adl.srcreader.ProxyConfig;

public class ModuleTest {

	public static void main(String[] args) throws IOException {
		ProxyConfig.proxyAccess(true, false);

		URL url = new URL("http://placehold.jp/570x90.png");
		URLConnection conn = url.openConnection();
		String contentType = DownloadChecker.getImgFileExtention(conn);

		System.out.println(contentType);

	}
}
