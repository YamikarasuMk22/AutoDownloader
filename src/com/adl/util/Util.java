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

	public static String convertCSV(String[] args) {
		String csv = "";

		for(int i = 0; i < args.length; i++) {
			csv = csv + args[i] + ",";
		}

		return csv;
	}

	public static void systemLoger(String log) {
		System.out.println(log);
	}
}
