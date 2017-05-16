package com.adl.downloader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.activation.FileTypeMap;

public class DownloadChecker {
	public static String getContentType(URLConnection conn) {

		String contentType = null;
		contentType = conn.getContentType();

		return contentType;
	}

	public static String getContentTypeByInputStream(String file) {

		String contentType = null;

		try {
			InputStream is = new BufferedInputStream(new FileInputStream(file));
			contentType = URLConnection.guessContentTypeFromStream(is);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentType;
	}

	public static String getContentTypeByMap(String file) {
		FileTypeMap filetypeMap = FileTypeMap.getDefaultFileTypeMap();
		String mimetype = filetypeMap.getContentType(file);

		return mimetype;
	}

	public static String getImgFileExtention(URLConnection conn) {
		String contentType = getContentType(conn);

		if(contentType.equals("image/jpeg"))
			return ".jpg";
		else if(contentType.equals("image/png"))
			return ".png";
		else if(contentType.equals("image/gif"))
			return ".gif";
		else
			return null;
	}
}