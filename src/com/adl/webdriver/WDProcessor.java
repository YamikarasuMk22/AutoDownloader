package com.adl.webdriver;

import java.util.ArrayList;
import java.util.List;

import com.adl.srcreader.SrcFormater;
import com.adl.util.Util;
import com.adl.xml.XMLChecker;
import com.adl.xml.XMLEditor;

public class WDProcessor extends WebDriver {
	public static void processGallery() {
		Util.systemLoger("Start Process [Gallery]-----");
		String srcGallery = getSrc(DOWNLOAD_ROOT_URL);
		List<String> contentURLList = new ArrayList<String>();

		contentURLList = SrcFormater.getGalleryURLList(srcGallery);

		for(int i = 0; i < contentURLList.size(); i++) {
			processContent(DOWNLOAD_SITE_URL + contentURLList.get(i));
			Util.systemLoger("Start Process [Content] " + (i+1) + "/" + contentURLList.size() + "-----");
		}
	}

	public static void processContent(String url) {
		String srcContent = getSrc(url);

		//日本語でない場合、同人作品でない場合はreturn
		if(!SrcFormater.isJapanese(srcContent)) {
			Util.systemLoger("[Content] Not Japanese.");
			return;
		}
		if(!SrcFormater.isDoujinshi(srcContent)) {
			Util.systemLoger("[Content] Not Doujinshi.");
			return;
		}

		//ダウンロード不可の場合はreturn
		String comicId = SrcFormater.getComicId(srcContent);
		int downloadState = XMLChecker.isDownloadable(comicId);
		//不可:0 可能(未登録):1 可能(登録済):2
		if (downloadState == 0) {
			Util.systemLoger("[Content] id=" + comicId + " is Not Downloadable. DownloadStates:" + downloadState);
			return;
		}

		Util.systemLoger("[Content] DownloadStates:" + downloadState);

		//idが既に存在している場合はXMLの更新処理をスキップ
		if(downloadState == 1) {
			//パラメータの取得
			String title = SrcFormater.getTitle(srcContent);
			String titleJP = SrcFormater.getTitleJapanese(srcContent);
			String titleOR = SrcFormater.getOriginTitle(srcContent);
			String[] charas = SrcFormater.getCharaList(srcContent);
			String[] tags = SrcFormater.getTagList(srcContent);
			String artist = SrcFormater.getArtist(srcContent);
			int pageNum = SrcFormater.getPageNum(srcContent);
			String pageRootURL = SrcFormater.getPageRootURL(srcContent);

			//パラメータの成形
			String pageNumStr = Integer.toString(pageNum);
			String chara = Util.convertCSV(charas);
			String tag = Util.convertCSV(tags);

			//"Title", "TitleJ", "TitleO", "Charas", "Tags", "Artist", "PageNum", "ComicURL", "PageRootURL", "FolderPath", "BlogURL"
			String[] param = {title, titleJP, titleOR, chara, tag, artist,
					pageNumStr, url, url + pageRootURL, DOWNLOAD_ROOT_FOLDER + comicId};
			Util.systemLoger("[Content] XMLParam:\n"
					+ "\t Title=" + title + "\n"
					+ "\t TitleJ=" + titleJP + "\n"
					+ "\t TitleO=" + titleOR + "\n"
					+ "\t Charas=" + chara + "\n"
					+ "\t Tags=" + tag + "\n"
					+ "\t Artist=" + artist + "\n"
					+ "\t PageNum=" + pageNumStr + "\n"
					+ "\t ComicURL=" + url + "\n"
					+ "\t PageRootURL=" + url + pageRootURL + "\n"
					+ "\t FolderPath=" + DOWNLOAD_ROOT_FOLDER + comicId + "\n"
					+ "\t BlogURL=" + "" + "\n");

			//XML更新
			XMLEditor.addNode(comicId, param);

			//ダウンロードプロセス
			processDownload(url + pageRootURL, pageNum);
		}
	}
	public static void processDownload(String url, int pageNum) {

	}
}
