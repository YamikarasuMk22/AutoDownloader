package com.adl.launcher;

import com.adl.constant.ConnectionConstants;
import com.adl.srcreader.ProxyConfig;

public class ADLLauncher implements ConnectionConstants {

	public static void main(String[] args) {
		ProxyConfig.proxyAccess(true, false);

		try {
			//String src = SrcReader.getPageSrc(DOWNLOAD_ROOT_URL);
			//String title = SrcFormater.getSrcTitle(src);
			//System.out.println(title);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
