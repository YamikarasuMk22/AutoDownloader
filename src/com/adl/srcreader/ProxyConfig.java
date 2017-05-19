package com.adl.srcreader;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import com.adl.constant.ConnectionConstants;

public class ProxyConfig implements ConnectionConstants {

	public static void proxyAccess(boolean access, boolean login) {
		//プロキシ接続不要の場合は不要
		if(access) {
			System.setProperty("proxySet", "true");
			System.setProperty("proxyHost", PROXY_SERVER);
			System.setProperty("proxyPort", PROXY_PORT);

			//ユーザー・パス不要な場合は不要
			if(login) {
				Authenticator.setDefault(new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(PROXY_USER, PROXY_PASSWORD.toCharArray());
					}
				});
			}
		}
	}
}
