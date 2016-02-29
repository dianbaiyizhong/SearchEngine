package org.hhm.crawler.analyzer;

public class AnalyzerAssiste {
	private String seedName;
	private String text;

	public AnalyzerAssiste(String seedName, String text) {
		this.seedName = seedName;
		this.text = text;

	}

	public String getAuthor() {
		String author = "";

		try {

			if (seedName.equals("天涯论坛")) {
				int writerindex_1 = text.indexOf("楼主");
				int writerindex_2 = text.indexOf("时");
				author = text.substring(writerindex_1 + 3, writerindex_2)
						.trim();
			} else if (seedName.equals("腾讯新闻")) {
				int writerindex_1 = text.indexOf("楼主");
				int writerindex_2 = text.indexOf("时");
				author = text.substring(writerindex_1 + 3, writerindex_2)
						.trim();
			} else if (seedName.equals("网易新闻")) {
				int writerindex_1 = text.indexOf("来源");
				author = text.substring(writerindex_1 + 3).trim();
			} else if (seedName.equals("豆瓣社区")) {
				
				return text.trim();

			}

			else {
				return text;
			}

		} catch (Exception e) {

			return author;
		}

		return author;

	}

	public String getTime() {
		String time = "";
		try {

			if (seedName.equals("天涯论坛")) {
				int timeindex_1 = text.indexOf("时间：");
				int timeindex_2 = text.indexOf("点击");
				time = text.substring(timeindex_1 + 3, timeindex_2).trim();
			} else if (seedName.equals("网易新闻")) {
				int timeindex_1 = text.indexOf("来源");
				time = text.substring(0, timeindex_1).trim();
			} else {
				return text;
			}
		} catch (Exception e) {
			return time;
		}
		return time;
	}

}
