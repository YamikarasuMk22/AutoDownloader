package com.adl.srcreader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SrcFormater {
	public static String getTitle(String src) {

		Pattern title_pattern1 = Pattern.compile("<title>([^<]+)</title>", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = title_pattern1.matcher(src);
		if (matcher1.find()) {
			return matcher1.group(1);
		}
		return null;
	}

	public static String getURLList(String src) {

		Pattern title_pattern1 = Pattern.compile("<title>([^<]+)</title>", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = title_pattern1.matcher(src);
		if (matcher1.find()) {
			return matcher1.group(1);
		}
		return null;
	}

	public static String removeTags(String src) {
		return null;
	}
}
