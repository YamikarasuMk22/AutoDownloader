package com.adl.downloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.activation.FileTypeMap;

import com.adl.constant.ConnectionConstants;
import com.adl.util.Util;

public class DownloadChecker implements ConnectionConstants {
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

		if (contentType.equals("image/jpeg"))
			return ".jpg";
		else if (contentType.equals("image/png"))
			return ".png";
		else if (contentType.equals("image/gif"))
			return ".gif";
		else
			return null;
	}

	public static boolean createDownloadFolder(String id) {
		File downloadFolder = new File(DOWNLOAD_ROOT_FOLDER + id);
		if (downloadFolder.exists()) {
			Util.systemLoger("\t" + DOWNLOAD_ROOT_FOLDER + id + " already exist.");
			return false;
		} else {
			downloadFolder.mkdir();
			Util.systemLoger("\t" + DOWNLOAD_ROOT_FOLDER + id + " mkdir.");
			return true;
		}
	}
}