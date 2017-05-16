package com.adl.srcreader;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class ProxyConfig {
	public static String HOST = "tk1py02a.jbcc.co.jp";
	public static int PORT = 8080;
	public static String USER = "Dummy";
	public static String PASSWORD = "Dummy";

	@SuppressWarnings("unused")
	private String[] getProxyConfig() {
		return null;
	}

	public static void proxyAccess(boolean access, boolean login) {
		//プロキシ接続不要の場合は不要
		if(access) {
			System.setProperty("proxySet", "true");
			System.setProperty("proxyHost", HOST);
			System.setProperty("proxyPort", Integer.toString(PORT));

			//ユーザー・パス不要な場合は不要
			if(login) {
				Authenticator.setDefault(new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(USER, PASSWORD.toCharArray());
					}
				});
			}
		}
	}
}
