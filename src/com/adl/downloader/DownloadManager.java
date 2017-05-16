package com.adl.downloader;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadManager {
	public static String ROOT_DOUNLOAD_FOLDER_PATH = "";

	//マックスのページ数分ダウンロードする
	public static void downloadImgs(String lootUrl, String id, int maxPage) {

		for(int page = 1; page < maxPage; page++) {

			try {
				//拡張子等はSrcFormaterでurlを解析し取得する
				URL url = new URL(lootUrl + "/ImageName00" + page + ".jpg");
				URLConnection conn = url.openConnection();

				String extention = DownloadChecker.getImgFileExtention(conn);

				String downloadFolder = ROOT_DOUNLOAD_FOLDER_PATH
						+ id + "ImageName00" + page + extention;

				Downloader.downloadFromURL(conn, downloadFolder);

				getRandInterval();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getRandInterval() {
		try {
			int ran = (int)(Math.random() * 10000);
			Thread.sleep(ran);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//作品を保存するフォルダーの作成
	public static String createDownloadFolder() {
		return null;

	}
}
