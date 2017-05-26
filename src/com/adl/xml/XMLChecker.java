package com.adl.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLChecker extends XMLEditor {
	/**
	 * XMLを解析しidが存在する場合はtrueを返す
	 */
	public static boolean isExistId(String id) {
		try {
			Document document = getRootDocument();
			Element root = document.getDocumentElement();
			int match = 0;

			NodeList list = root.getElementsByTagName("comic");

			for (int i = 0; i < list.getLength(); i++) {
				Element element = (Element) list.item(i);
				String strSN = element.getAttribute("id");
				if (strSN.compareTo(id) == 0) {
					match ++;
					break;
				}
			}

			if(match >= 1) return true;
			else return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * XMLを解析し新規ダウンロード可能かをチェックする
	 * 不可:0 可能(未登録):1 可能(登録済):2
	 */
	public static int isDownloadable(String id) {
		try {
			Document document = getRootDocument();
			Element root = document.getDocumentElement();
			String result = "";
			int match = 0;

			NodeList list = root.getElementsByTagName("comic");

			for (int i = 0; i < list.getLength(); i++) {
				Element element = (Element) list.item(i);
				String strSN = element.getAttribute("id");
				if (strSN.compareTo(id) == 0) {
					result = element.getAttribute("states");
					match ++;
					break;
				}
			}
			//XMLに登録されてない場合はダウンロード可能
			if(match == 0) return 1;

			//XMLに登録されているかつstatesがsetupの場合はダウンロード可能
			if(result.equals("setup")) return 2;
			else return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
