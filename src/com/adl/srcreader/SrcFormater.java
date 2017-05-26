package com.adl.srcreader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adl.util.Util;

/**
 * ダウンロードサイトのSrcを解析する
 */
public class SrcFormater {

	public static String getSrcTitle(String src) {

		Pattern pattern1 = Pattern.compile("<title>([^<]+)</title>", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(src);

		if (matcher1.find()) {
			return matcher1.group(1);
		}

		return null;
	}

	/**
	 * 作品URL配列を返す
	 * Gallery
	 */
	public static List<String> getGalleryURLList(String src) {

		List<String> URLList = new ArrayList<String>();
		String[] strs = src.split("¥n");
		for (int i = 0 ; i < strs.length ; i++) {

			Pattern pattern1 = Pattern.compile("<a href=\"/(.*)\" class=\"cover\"", Pattern.CASE_INSENSITIVE);
			Matcher matcher1 = pattern1.matcher(src);

			while (matcher1.find()) {
				URLList.add(matcher1.group(1));
			}
		}

		return URLList;
	}

	/**
	 * 作品idを返す
	 * Content
	 */
	public static String getComicId(String src) {

		Pattern pattern1 = Pattern.compile("<a href=\"/g/(.*)/1/\">", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(src);

		if (matcher1.find()) {
			return matcher1.group(1);
		}

		return null;
	}

	/**
	 * 作品タイトルを返す
	 * Content
	 */
	public static String getTitle(String src) {

		Pattern pattern1 = Pattern.compile("<h1>(.*)</h1>", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(src);

		if (matcher1.find()) {
			return matcher1.group(1);
		}

		return "";
	}

	/**
	 * 作品タイトル(日本語)を返す
	 * Content
	 */
	public static String getTitleJapanese(String src) {

		Pattern pattern1 = Pattern.compile("<h2>(.*)</h2>", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(src);

		if (matcher1.find()) {
			return matcher1.group(1);
		}

		return "";
	}

	/**
	 * 作品原作タイトルを返す
	 * Content
	 */
	public static String getOriginTitle(String src) {

		Pattern pattern1 = Pattern.compile("<a href=\"/parody/(.*)/\" class", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(src);

		if (matcher1.find()) {
			return matcher1.group(1);
		}

		return "";
	}

	/**
	 * 作品登場人物を返す
	 * Content
	 */
	public static String[] getCharaList(String src) {

		Pattern pattern1 = Pattern.compile("(<span class=\"tags\"><a href=\"/character/.*</span>)", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(src);

		String[] charas = null;

		if (matcher1.find()) {
			String charastr = matcher1.group(1).replaceAll("<.+?>", "");
			charas = charastr.split(" \\(.+?\\)", 0);
		}

		return charas;
	}

	/**
	 * 作品タグ配列を返す
	 * Content
	 */
	public static String[] getTagList(String src) {

		Pattern pattern1 = Pattern.compile("(<span class=\"tags\"><a href=\"/tag/.*</span>)", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(src);

		String[] tags = null;

		if (matcher1.find()) {
			String tagstr = matcher1.group(1).replaceAll("<.+?>", "");
			tags = tagstr.split(" \\(.+?\\)", 0);
		}

		return tags;
	}

	/**
	 * 作品著者を返す
	 * Content
	 */
	public static String getArtist(String src) {

		Pattern pattern1 = Pattern.compile("<a href=\"/artist/(.*)/\" class", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(src);

		if (matcher1.find()) {
			return matcher1.group(1);
		}

		return "";
	}

	/**
	 * 作品が日本語の場合trueを返す
	 * Content
	 */
	public static Boolean isJapanese(String src) {

		Pattern pattern1 = Pattern.compile("a href=\"/language/japanese/\"", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(src);

		Boolean result = false;

		if (matcher1.find()) {
			result = true;
		}

		return result;
	}

	/**
	 * 作品が同人の場合trueを返す
	 * Content
	 */
	public static Boolean isDoujinshi(String src) {

		Pattern pattern1 = Pattern.compile("a href=\"/category/doujinshi/\"", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(src);

		Boolean result = false;

		if (matcher1.find()) {
			result = true;
		}

		return result;
	}

	/**
	 * 作品のページ数を返す
	 * Content
	 */
	public static int getPageNum(String src) {

		Pattern pattern1 = Pattern.compile("<div class=\"thumb-container\">", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(src);

		int pagenum = 0;

		while (matcher1.find()) {
			pagenum ++;
		}

		return pagenum;
	}

	/**
	 * 作品のPageRootURLを返す
	 * PageURL = RootURL + PageRootURL + Page + "/"
	 * Content
	 */
	public static String getPageRootURL(String src) {

		Pattern pattern1 = Pattern.compile("<a class=\"gallerythumb\" href=\"(.*)1/", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(src);

		if (matcher1.find()) {
			return matcher1.group(1);
		}

		return null;
	}

	/**
	 * 作品イメージの拡張子を返す
	 * Page
	 */
	public static String getImgSuffix(String src) {

		Pattern pattern1 = Pattern.compile("<img src=\"(.*)\" width=\".*\" height=\".*\" class=\"fit-horizontal\" />", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(src);

		if (matcher1.find()) {
			return Util.getSuffix(matcher1.group(1));
		}

		return null;
	}

}
