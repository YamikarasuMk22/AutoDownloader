package com.adl.webdriver;

import com.adl.srcreader.SrcFormater;
import com.adl.util.Util;
import com.adl.xml.XMLEditor;

/**
 * リソースの取得をマネジメントするclass
 * 画像への論理リンクの収集、Galleryデータの成形、HTMLの成形
 */
public class WDProcessor2 extends WDProcessor {

	private static String br = "<br>";
	private static String br2 = "<br><br>";

	/**
	 * PageURLのHTMLを解析し、ブログ用HTMLを生成するプロセス
	 */
	public static String processCreateHTML(String id, String url, int pageNum) {
		String html = "";
		boolean result = false;

		Util.systemLoger("Start Process [CreateHTML]-----");
		Util.systemLoger("[CreateHTML] URL:" + url + "X/");
		Util.systemLoger("[CreateHTML] PageSize:" + pageNum);

		String srcPage1 = getSrc(url + "1/");
		//拡張子の取得
		String suffix = SrcFormater.getImgSuffix(srcPage1);
		//イメージIDの取得
		String imgId = SrcFormater.getImgID(srcPage1);

		Util.systemLoger("[CreateHTML] Image Suffix:" + suffix);

		//XMLにイメージIDを登録
		XMLEditor.setImgId(id, imgId);

		//ダウンロードStatesを"downloading"に移行
		XMLEditor.setStats(id, "downloading");

		String imgSrc;

		for(int page = 1; page <= pageNum; page++) {

			imgSrc = DOWNLOAD_IMG_URL + "galleries/" + imgId + "/" + page + "." + suffix;

			html = html + page + ":" + br + "<img src=\"" + imgSrc + " />" + br2 + "\n";

		}

		//TODO 作成したHTMLをtxtで保存

		//ダウンロードStatesを"downloaded"に移行
		if(!html.equals("")) {
			XMLEditor.setStats(id, "downloaded");
			result = true;
		}

		Util.systemLoger("End Process [CreateHTML]----- Flag:" + result);
		return html;
	}
}
