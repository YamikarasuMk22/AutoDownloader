package com.adl.test;

import java.io.IOException;

import com.adl.constant.ConnectionConstants;
import com.adl.srcreader.ProxyConfig;
import com.adl.xml.XMLEditor;

public class ModuleTest implements ConnectionConstants {

	public static void main(String[] args) throws IOException {
		ProxyConfig.proxyAccess(true, false);

//		URL url = new URL("http://placehold.jp/570x90.png");
//		URLConnection conn = url.openConnection();
//		String contentType = DownloadChecker.getImgFileExtention(conn);
//		System.out.println(contentType);
//
//		String src = "<span class=\"tags\"><a href=\"/tag/nakadashi/\" class=\"tag tag-13720 \">nakadashi <span class=\"count\">(19,054)</span></a><a href=\"/tag/blowjob/\" class=\"tag tag-29859 \">blowjob <span class=\"count\">(17,117)</span></a><a href=\"/tag/futanari/\" class=\"tag tag-779 \">futanari <span class=\"count\">(16,068)</span></a><a href=\"/tag/big-penis/\" class=\"tag tag-30555 \">big penis <span class=\"count\">(4,141)</span></a><a href=\"/tag/inflation/\" class=\"tag tag-21989 \">inflation <span class=\"count\">(3,445)</span></a><a href=\"/tag/condom/\" class=\"tag tag-12824 \">condom <span class=\"count\">(2,238)</span></a><a href=\"/tag/cervix-penetration/\" class=\"tag tag-9661 \">cervix penetration <span class=\"count\">(1,562)</span></a><a href=\"/tag/big-balls/\" class=\"tag tag-19561 \">big balls <span class=\"count\">(386)</span></a></span>";
//
//		try {
//			WebDriver.createWebDriver();
//			//src = WebDriver.getSrc();
//			WebDriver.quitWebDriver();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		try {
			String id = "TEST";
			String[] param = { "Title", "TitleJ", "TitleO", "Chara1,Chara2", "Tag1,Tag2,Tag3", "Artist", "PageNum", "ComicURL", "ComicPath", "BlogPageURL" };;
			XMLEditor.addNode(id, param);


//			String src = SrcReader.getPageSrc(testURL);

//			List<String> url = SrcFormater.getGalleryURLList(src);
//			if(url != null) {
//				for (int i = 0; i < url.size(); i++) {
//					System.out.print(i + url.get(i) + " ");
//				}
//				System.out.print("\n");
//			}

//			System.out.println(SrcFormater.getComicId(src));
//			System.out.println(SrcFormater.getPageNum(src));
//			System.out.println(SrcFormater.getPageRootURL(src));
//			System.out.println(SrcFormater.getTitle(src));
//			System.out.println(SrcFormater.getTitleJapanese(src));
//			System.out.println(SrcFormater.getOriginTitle(src));
//			System.out.println(SrcFormater.getArtist(src));
//			System.out.println(SrcFormater.isJapanese(src));
//			System.out.println(SrcFormater.isDoujinshi(src));
//
//			String[] tags = SrcFormater.getTagList(src);
//			if(tags != null) {
//				for (int i = 0; i < tags.length; i++) {
//					System.out.print(i + tags[i] + " ");
//				}
//				System.out.print("\n");
//			}
//
//			String[] chara = SrcFormater.getCharaList(src);
//			if(chara != null) {
//				for (int i = 0; i < chara.length; i++) {
//					System.out.print(i + chara[i] + " ");
//				}
//				System.out.print("\n");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

//		driver.get("https://www.facebook.com/");
	}
}
