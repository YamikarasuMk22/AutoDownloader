package com.adl.util;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Util {
	public static void getRandInterval() {
		try {
			int ran = (int) (Math.random() * 1000);
			Thread.sleep(ran);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String convertCSV(String[] args) {
		String csv = "";

		for (int i = 0; i < args.length; i++) {
			csv = csv + args[i] + ",";
		}

		return csv;
	}

	public static String getSuffixbyURL(String url) {
		if (url == null)
			return null;

		int point = url.lastIndexOf(".");

		if (point != -1) {
			return url.substring(point + 1);
		}
		return url;
	}

	public static String getIDbyURL(String url) {
		if (url == null)
			return null;

		String[] args = url.split("/", 0);

		return args[4];
	}

	public static void typeID(String id, Robot robo) {
		String[] idArray = id.split("");

		//カーソル移動
		for (int x = 0; x < 10; x++) {
			robo.keyPress(KeyEvent.VK_LEFT);
			robo.keyRelease(KeyEvent.VK_LEFT);
			robo.delay(50);
		}

		// 1文字ずつ処理
		for (int i = 0; i < idArray.length; i++) {
			switch (Integer.parseInt(idArray[i])) {
			case 0:
				robo.keyPress(KeyEvent.VK_0);
				robo.keyRelease(KeyEvent.VK_0);
				robo.delay(200);
				break;
			case 1:
				robo.keyPress(KeyEvent.VK_1);
				robo.keyRelease(KeyEvent.VK_1);
				robo.delay(200);
				break;
			case 2:
				robo.keyPress(KeyEvent.VK_2);
				robo.keyRelease(KeyEvent.VK_2);
				robo.delay(200);
				break;
			case 3:
				robo.keyPress(KeyEvent.VK_3);
				robo.keyRelease(KeyEvent.VK_3);
				robo.delay(200);
				break;
			case 4:
				robo.keyPress(KeyEvent.VK_4);
				robo.keyRelease(KeyEvent.VK_4);
				robo.delay(200);
				break;
			case 5:
				robo.keyPress(KeyEvent.VK_5);
				robo.keyRelease(KeyEvent.VK_5);
				robo.delay(200);
				break;
			case 6:
				robo.keyPress(KeyEvent.VK_6);
				robo.keyRelease(KeyEvent.VK_6);
				robo.delay(200);
				break;
			case 7:
				robo.keyPress(KeyEvent.VK_7);
				robo.keyRelease(KeyEvent.VK_7);
				robo.delay(200);
				break;
			case 8:
				robo.keyPress(KeyEvent.VK_8);
				robo.keyRelease(KeyEvent.VK_8);
				robo.delay(200);
				break;
			case 9:
				robo.keyPress(KeyEvent.VK_9);
				robo.keyRelease(KeyEvent.VK_9);
				robo.delay(200);
				break;
			}
		}

		robo.keyPress(KeyEvent.VK_BACK_SLASH);
		robo.keyRelease(KeyEvent.VK_BACK_SLASH);
		robo.delay(200);
	}

	public static void systemLoger(String log) {
		System.out.println(log);
	}
}
