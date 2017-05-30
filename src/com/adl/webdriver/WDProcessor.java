package com.adl.webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.interactions.Actions;

import com.adl.srcreader.SrcFormater;
import com.adl.util.Util;
import com.adl.xml.XMLChecker;
import com.adl.xml.XMLEditor;

public class WDProcessor extends WebDriver {

	public static void processALL() {
		processGallery();
	}

	/**
	 * GalleryURLのHTMLを解析し、各作品のURLを取得するプロセス
	 */
	public static boolean processGallery() {
		boolean result = false;

		Util.systemLoger("Start Process [Gallery]-----");
		Util.systemLoger("[Gallery] AccessURL:" + DOWNLOAD_ROOT_URL);
		String srcGallery = getSrc(DOWNLOAD_ROOT_URL);
		List<String> contentURLList = new ArrayList<String>();

		contentURLList = SrcFormater.getGalleryURLList(srcGallery);
		Util.systemLoger("[Gallery] GalleryURLListSize:" + contentURLList.size());

		for (int i = 0; i < contentURLList.size(); i++) {
			Util.systemLoger("[Gallery] Content Process " + (i + 1) + "/" + contentURLList.size());
			result = processContent(DOWNLOAD_SITE_URL + contentURLList.get(i));
		}

		Util.systemLoger("End Process [Gallery]----- Flag:" + result);
		return result;
	}

	/**
	 * ContentURLのHTMLを解析し、パラメータを取得する
	 * パラメータチェックを行いXMLに登録を行うプロセス
	 */
	public static boolean processContent(String url) {
		boolean result = false;

		Util.systemLoger("Start Process [Content]-----");
		Util.systemLoger("[Content] ContentURL:" + url);
		String srcContent = getSrc(url);

		// 日本語でない場合、同人作品でない場合はreturn
		if (!SrcFormater.isJapanese(srcContent)) {
			Util.systemLoger("[Content] Not Japanese.");
			Util.systemLoger("End Process [Content]-----");
			return result;
		}
		if (!SrcFormater.isDoujinshi(srcContent)) {
			Util.systemLoger("[Content] Not Doujinshi.");
			Util.systemLoger("End Process [Content]-----");
			return result;
		}

		// TODO オリジナルでない場合はreturn？

		// TODO titleが英語でない場合はreturn？

		// ダウンロード不可の場合はreturn
		String comicId = SrcFormater.getComicId(srcContent);
		int downloadState = XMLChecker.isDownloadable(comicId);
		// 不可:0 可能(未登録):1 可能(登録済):2
		if (downloadState == 0) {
			Util.systemLoger("[Content] id=" + comicId + " is Not Downloadable. DownloadStates:" + downloadState);
			return result;
		}

		Util.systemLoger("[Content] DownloadStates:" + downloadState);

		// idが既に存在している場合はXMLの更新処理をスキップ
		if (downloadState == 1) {
			// パラメータの取得
			String title = SrcFormater.getTitle(srcContent);
			String titleJP = SrcFormater.getTitleJapanese(srcContent);
			String titleOR = SrcFormater.getOriginTitle(srcContent);
			String[] charas = SrcFormater.getCharaList(srcContent);
			String[] tags = SrcFormater.getTagList(srcContent);
			String artist = SrcFormater.getArtist(srcContent);
			int pageNum = SrcFormater.getPageNum(srcContent);

			// パラメータの成形
			String pageNumStr = Integer.toString(pageNum);
			String chara = "";
			String tag = "";
			if (charas != null)
				chara = Util.convertCSV(charas);
			if (tags != null)
				tag = Util.convertCSV(tags);

			// "Title", "TitleJ", "TitleO", "Charas", "Tags", "Artist",
			// "PageNum", "ComicURL", "PageRootURL", "FolderPath", "BlogURL"
			String[] param = { title, titleJP, titleOR, chara, tag, artist, pageNumStr, url,
					DOWNLOAD_ROOT_FOLDER + comicId, "" };
			Util.systemLoger("[Content] XMLParam:\n" + "\t Title=" + title + "\n" + "\t TitleJ=" + titleJP + "\n"
					+ "\t TitleO=" + titleOR + "\n" + "\t Charas=" + chara + "\n" + "\t Tags=" + tag + "\n"
					+ "\t Artist=" + artist + "\n" + "\t PageNum=" + pageNumStr + "\n" + "\t ComicURL=" + url + "\n"
					+ "\t FolderPath=" + DOWNLOAD_ROOT_FOLDER + comicId + "\n" + "\t BlogURL=" + "" + "\n");

			// XML更新
			XMLEditor.addNode(comicId, param);

			// ダウンロードプロセス
			result = processDownload(comicId, url, pageNum);
		}

		Util.systemLoger("End Process [Content]----- Flag:" + result);
		return result;
	}

	/**
	 * PageURLのHTMLを解析し、イメージの拡張子を取得、保存フォルダを作成するプロセス
	 */
	public static boolean processDownload(String id, String url, int pageNum) {
		boolean result = false;

		Util.systemLoger("Start Process [Download]-----");
		Util.systemLoger("[Download] URL:" + url + "X/");
		Util.systemLoger("[Download] PageSize:" + pageNum);

		String srcPage1 = getSrc(url + "1/");
		//拡張子の取得
		String suffix = SrcFormater.getImgSuffix(srcPage1);
		//イメージIDの取得
		String imgId = SrcFormater.getImgID(srcPage1);

		Util.systemLoger("[Download] Image Suffix:" + suffix);

		//XMLにイメージIDを登録
		XMLEditor.setImgId(id, imgId);


		//ダウンロードフォルダーにIDでフォルダーを作成
		//※Chromeの設定でダウンロードフォルダーの設定を行うこと
		File downloadDir = new File(DOWNLOAD_ROOT_FOLDER + "\\" + id);
		downloadDir.mkdir();

		Util.systemLoger("[Download] DownloadDir:" + DOWNLOAD_ROOT_FOLDER + "\\" + id);

		//ダウンロードStatesを"downloading"に移行
		XMLEditor.setStats(id, "downloading");

		for(int page = 1; page <= pageNum; page++) {
			Util.systemLoger("Start Process [DownloadImg]-----");
			result = processDownloadImg(id, imgId, suffix, page);
		}

		//ダウンロードStatesを"downloaded"に移行
		if(result) 	XMLEditor.setStats(id, "downloaded");

		Util.systemLoger("End Process [Download]----- Flag:" + result);
		return result;
	}

	/**
	 * イメージの保存、保存チェックをするプロセス
	 * @return
	 */
	public static boolean processDownloadImg(String id, String imgId, String suffix, int page) {
		boolean result = false;

		//ローカルにファイルが存在していないかチェック
		File file = new File(DOWNLOAD_ROOT_FOLDER + "\\" + id + "\\" + page + "." + suffix);
		if (file.exists()) {
			Util.systemLoger("[DownloadImg] File Already Exist:" + DOWNLOAD_ROOT_FOLDER + "\\" + id + "\\" + page + "." + suffix);
			result = true;
		}

		Util.systemLoger("[DownloadImg] ID:" + id + ", ImgID:" + imgId + ", Suffix:" + suffix + ", Page:" + page);
		String downloadURL = DOWNLOAD_IMG_URL + "galleries/" + imgId + "/" + page + "." + suffix;
		Util.systemLoger("[DownloadImg] ImgURL:" + downloadURL);

		driver.get(downloadURL);

		//イメージを右クリック
		new Actions(driver).contextClick().build().perform();

		//ブラウザメニュー操作
		try {
			Robot robot = new Robot();

			robot.delay(DEFAULT_KEY_INTERVAL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);
			robot.delay(DEFAULT_KEY_INTERVAL);

			Util.typeID(id, robot);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(DEFAULT_KEY_INTERVAL);
		} catch (AWTException e) {
			e.printStackTrace();
		}

		//ダウンロードフォルダーにファイルが存在するかチェック
		if (file.exists()) {
			Util.systemLoger("[DownloadImg] DownloadSuccess:" + page + "." + suffix);
			result = true;
		} else {
			Util.systemLoger("[DownloadImg] DownloadError:" + page + "." + suffix);
			result = false;
		}

		//TODO png→jpgに変換する？

		Util.systemLoger("End Process [DownloadImg]----- Flag:" + result);
		return result;
	}
}
