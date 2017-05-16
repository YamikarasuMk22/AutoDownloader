package com.adl.downloader;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLConnection;

public class Downloader {
	public static String DOWNLOAD_FOLDER = "C:\\Temp\\image1.jpg";

	public static void downloadFromURL(URLConnection urlconn, String path) {
	try {
		HttpURLConnection conn =(HttpURLConnection) urlconn;
		conn.setAllowUserInteraction(false);
		conn.setInstanceFollowRedirects(true);
		conn.setRequestMethod("GET");conn.connect();

		int httpStatusCode = conn.getResponseCode();

		if(httpStatusCode != HttpURLConnection.HTTP_OK) {
			throw new Exception();
		}

		// Input Stream
		DataInputStream dataInStream = new DataInputStream(conn.getInputStream());

		// Output Stream
		DataOutputStream dataOutStream = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(path)));

		// Read Data
		byte[] b = new byte[4096];
		int readByte = 0;

		while(-1 != (readByte = dataInStream.read(b))) {
			dataOutStream.write(b, 0, readByte);
		}

		// Close Stream
		dataInStream.close();
		dataOutStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
