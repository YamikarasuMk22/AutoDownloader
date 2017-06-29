package com.adl.xml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader extends XMLEditor {

	/**
	 * comicのパラメータを全て取得する
	 */
	public static List<String> readComicXMLParams(String id) {
		List<String> strList = new ArrayList<String>();

		try {
			Document document = getRootDocument(XML_COMICS);
			Element root = document.getDocumentElement();

			NodeList list = root.getElementsByTagName("Comic");
			for (int i = 0; i < list.getLength(); i++) {
				Element element = (Element) list.item(i);
				String strSN = element.getAttribute("id");
				if (strSN.compareTo(id) == 0) {
					NodeList nodeList = element.getChildNodes();

					for (int j=0; j < nodeList.getLength(); j++) {
						Node comicNode = nodeList.item(j);
						if (comicNode.getNodeType() == Node.ELEMENT_NODE) {
							strList.add(comicNode.getTextContent());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strList;
	}

	/**
	 * comicの指定したID・Tagのパラメータを取得する
	 */
	public static String readComicXMLParam(String id, String Tag) {
		String str = "";

		try {
			Document document = getRootDocument(XML_COMICS);
			Element root = document.getDocumentElement();

			NodeList list = root.getElementsByTagName("Comic");
			for (int i = 0; i < list.getLength(); i++) {
				Element element = (Element) list.item(i);
				String strSN = element.getAttribute("id");
				if (strSN.compareTo(id) == 0) {
					NodeList titleList = element.getElementsByTagName(Tag);
					Element titleElement = (Element)titleList.item(0);
					str = titleElement.getFirstChild().getNodeValue();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * configのパラメータを全て取得する
	 */
	public static List<String> readCProcessConfigParam() {
		List<String> list = new ArrayList<String>();
		return list;
	}
}
