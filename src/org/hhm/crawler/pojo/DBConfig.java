package org.hhm.crawler.pojo;

public class DBConfig {

	public static String getDataBaseName() {
		return dataBaseName;
	}

	public static void setDataBaseName(String dataBaseName) {
		DBConfig.dataBaseName = dataBaseName;
	}

	public static String getiP() {
		return iP;
	}

	public static void setiP(String iP) {
		DBConfig.iP = iP;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		DBConfig.userName = userName;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		DBConfig.password = password;
	}

	private static String dataBaseName;
	private static String iP;
	private static String userName;
	private static String password;

}
