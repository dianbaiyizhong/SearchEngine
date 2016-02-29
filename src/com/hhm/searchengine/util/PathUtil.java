package com.hhm.searchengine.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PathUtil {
	private static String index_PATH_NAME = "/indexPath.properties";

	public String getIndexPath() {
		InputStream in = getClass().getResourceAsStream(index_PATH_NAME);
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return props.getProperty("indexPath");
	}

}
