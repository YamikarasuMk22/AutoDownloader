package com.adl.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLEditor {

	private static final File XML_FILE_PATH = new File("comic.xml");

	public static Document getRootDocument() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document document = builder.parse(XML_FILE_PATH);

			return document;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean createXML(File file, Document document) {
		Transformer transformer = null;
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			return false;
		}
		transformer.setOutputProperty("indent", "yes");
		transformer.setOutputProperty("encoding", "UTF-8");

		try {
			transformer.transform(new DOMSource(document), new StreamResult(file));
		} catch (TransformerException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static void createXMLFile() {
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		Document document = documentBuilder.newDocument();
		Element root = document.createElement("root");
		document.appendChild(root);

		File file = new File("comics.xml");
		createXML(file, document);
	}

	// タグは半角空白区切り
	public static void addNode(String id, String[] nodeParams) {
		String[] nodeElems = { "Title", "Artist", "Circle", "Tags", "PageNum", "ComicURL", "ComicPath", "BlogPageURL" };

		try {
			Document document = getRootDocument();
			Element root = document.getDocumentElement();

			Element comic = document.createElement("comic");
			comic.setAttribute("id", id);
			comic.setAttribute("uploaded", "0");
			root.appendChild(comic);

			for (int i = 0; i < nodeElems.length; i++) {
				Element elem = document.createElement(nodeElems[i]);
				elem.appendChild(document.createTextNode(nodeParams[i]));
				comic.appendChild(elem);
				elem = null;
			}

			createXML(XML_FILE_PATH, document);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//サンプル 指定したidのuploadedを1に変更
	public static void updateNodeUploaded(String id) {

		try {
			Document document = getRootDocument();
			Element root = document.getDocumentElement();

			NodeList list = root.getElementsByTagName("comic");
			for (int i = 0; i < list.getLength(); i++) {
				Element element = (Element) list.item(i);
				String strSN = element.getAttribute("id");
				if (strSN.compareTo(id) == 0) {
					element.setAttribute("uploaded", "1");
					break;
				}
			}

			createXML(XML_FILE_PATH, document);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}