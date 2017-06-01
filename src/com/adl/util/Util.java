package com.adl.util;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Util {
	/**
	 * 0.1～1.0秒のインターバルを生成する
	 */
	public static void getRandInterval() {
		try {
			int ran = (int) (Math.random() * 1000);
			Thread.sleep(ran);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文字列配列をカンマ区切りで連結する
	 */
	public static String convertCSV(String[] args) {
		String csv = "";

		for (int i = 0; i < args.length; i++) {
			if(i < args.length - 1)
				csv = csv + args[i] + ",";
			else
				csv = csv + args[i];
		}

		return csv;
	}

	/**
	 * 画像URLから拡張子を抽出する
	 */
	public static String getSuffixbyURL(String url) {
		if (url == null)
			return null;

		int point = url.lastIndexOf(".");

		if (point != -1) {
			return url.substring(point + 1);
		}
		return url;
	}

	/**
	 * nhentai.netのURL構造に基づき
	 * URLに含まれる作品ID、イメージIDを抽出する
	 */
	public static String getIDbyURL(String url) {
		if (url == null)
			return null;

		String[] args = url.split("/", 0);

		return args[4];
	}

	/**
	 * id名のディレクトリをキーボードから入力する
	 * イメージ保存時のディレクトリ指定に使用
	 */
	public static void typeIDDir(String id, Robot robo) {

		// カーソル移動
		for (int x = 0; x < 10; x++) {
			robo.keyPress(KeyEvent.VK_LEFT);
			robo.keyRelease(KeyEvent.VK_LEFT);
			robo.delay(50);
		}

		// 1文字ずつ処理
		sendKey(id, robo);

		robo.keyPress(KeyEvent.VK_BACK_SLASH);
		robo.keyRelease(KeyEvent.VK_BACK_SLASH);
		robo.delay(200);
	}

	/**
	 * 文字列(半角英小文字+数字)をキーボードから入力する
	 * TODO 大文字に対応する
	 */
	private static void sendKey(String text, Robot robo) {
		for (int i = 0; i < text.length(); i++) {

			char c = text.charAt(i);
			int keycode;

			if (0 <= c && c <= 9) {
				keycode = KeyEvent.VK_0 + (c - '0');
			} else if ('a' <= c && c <= 'z') {
				keycode = KeyEvent.VK_A + (c - 'a');
			} else {
				throw new IllegalArgumentException("unsupported : " + c);
			}

			robo.keyPress(keycode);
			robo.delay(100);
		}
	}

	public static void systemLoger(String log) {
		System.out.println(log);
	}
}
