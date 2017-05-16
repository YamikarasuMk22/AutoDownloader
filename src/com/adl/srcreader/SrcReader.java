package com.adl.srcreader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SrcReader {
	public static String ENCODING = "UTF-8";

	public static String getPageSrc(String page_url) throws Exception {
		URL url = new URL(page_url);
		URLConnection conn = url.openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), ENCODING));
		StringBuffer response = new StringBuffer();
		String line;
		while ((line = in.readLine()) != null) {
			response.append(line + "\n");
		}

		in.close();

		return response.toString();
	}
}
