package com.adl.launcher;

import com.adl.srcreader.ProxyConfig;
import com.adl.srcreader.SrcFormater;
import com.adl.srcreader.SrcReader;

public class ADLLauncher {
	public static String URL = "https://ja.wikipedia.org/wiki/テスト";

	public static void main(String[] args) {
		ProxyConfig.ProxyAccess(true, false);

		try {
			String src = SrcReader.getPageSrc(URL);
			String title = SrcFormater.getTitle(src);
			System.out.println(title);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
