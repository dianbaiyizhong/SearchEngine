package org.hhm.crawler.fetch.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Parser {

	public String getSourseCode(String url) {
		String sourseCode = null;
		Document doc_sourseCode = null;
		try {

			doc_sourseCode = Jsoup.connect(url).timeout(6000).get();
		} catch (IOException e) {

			System.out.println("网页源码获取失败，链接无效" + url);
			return null;
		}

		sourseCode = doc_sourseCode.html();

		return sourseCode.trim();
	}

}
