package com.adl.constant;

import java.io.File;

public interface ConnectionConstants {
	//Download Constants
	static final String DOWNLOAD_SITE_URL = "https://nhentai.net/";
	static final String CATEGORY_TAG = "tag/futanari";
	static final String DOWNLOAD_ROOT_URL = DOWNLOAD_SITE_URL + CATEGORY_TAG + "/";
	static final String DOWNLOAD_ROOT_FOLDER = "D:\\Users\\J34568\\Downloads";

	//Upload Constants
	static final String BLOG_ROOT_URL = "";
	static final String BLOG_USER = "";
	static final String BLOG_PASSWORD = "";

	//Proxy Constants
	static final String PROXY_SERVER = "tk1py02a.jbcc.co.jp";
	static final String PROXY_PORT = "8080";
	static final String PROXY_USER = "Dummy";
	static final String PROXY_PASSWORD = "Dummy";

	//XML Constants
	static final File XML_FILE_PATH = new File("comics.xml");

	//Test
	static final String TEST_URL = "https://nhentai.net/tag/futanari/";
}
