package com.adl.webdriver;

import java.util.ArrayList;
import java.util.List;

import com.adl.srcreader.SrcFormater;
import com.adl.xml.XMLChecker;

public class WDProcessor extends WebDriver {
	public static void processGallery() {
		String srcGallery = getSrc(DOWNLOAD_ROOT_URL);
		List<String> contentURLList = new ArrayList<String>();

		contentURLList = SrcFormater.getGalleryURLList(srcGallery);

		for(int i = 0; i < contentURLList.size(); i++) {
			proccessContent(DOWNLOAD_SITE_URL + contentURLList.get(i));
		}
	}

	public static void proccessContent(String url) {
		String srcContent = getSrc(url);

		//日本語でない場合、同人作品でない場合はreturn
		if(!SrcFormater.isJapanese(srcContent)) return;
		if(!SrcFormater.isDoujinshi(srcContent)) return;

		//ダウンロード不可の場合はreturn
		String comicId = SrcFormater.getComicId(srcContent);
		if (XMLChecker.isDownloadable(comicId)) return;

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


		//XML更新
		String[] param = {};
	}
}
