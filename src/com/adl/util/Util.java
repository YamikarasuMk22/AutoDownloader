package com.adl.util;

public class Util {
	public static void getRandInterval() {
		try {
			int ran = (int)(Math.random() * 1000);
			Thread.sleep(ran);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
